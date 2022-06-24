package id.kodesumsi.telkompengmas.data.source.network

import android.util.Log
import id.kodesumsi.telkompengmas.domain.model.dummy.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val networkService: NetworkService
): RemoteDataSource {
    override fun posyanduLogin(username: String, password: String): Flowable<ApiResponse<User>> {
        val result = PublishSubject.create<ApiResponse<User>>()
        val client = networkService.postPosyanduLogin(username, password)
        client.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val user = response.results
                result.onNext(if (user != null) ApiResponse.Success(user) else ApiResponse.Empty)
            }, { error ->
                result.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    override fun orangtuaLogin(username: String, password: String): Flowable<ApiResponse<User>> {
        val result = PublishSubject.create<ApiResponse<User>>()
        val client = networkService.postOrangtuaLogin(username, password)
        client.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val user = response.results
                result.onNext(if (user != null) ApiResponse.Success(user) else ApiResponse.Empty)
            }, { error ->
                result.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }
}