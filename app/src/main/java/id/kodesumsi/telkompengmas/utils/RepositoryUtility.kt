package id.kodesumsi.telkompengmas.utils

import android.util.Log
import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.data.source.local.LocalDataSource
import id.kodesumsi.telkompengmas.data.source.network.response.AuthResponse
import id.kodesumsi.telkompengmas.domain.model.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject

object RepositoryUtility {

    fun saveAuthResponseToLocal(localDataSource: LocalDataSource, userRole: Int, response: AuthResponse, result: PublishSubject<Resource<User>>) {
        // save to local database
        localDataSource.saveUser(userRole, response)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                result.onNext(
                    Resource.Success( data = response.toUser() )
                )
            }, {
                Log.d("UserRepository", "error : ${it.message.toString()}")
                result.onNext(Resource.Error(it.message.toString()))
            })
    }

}