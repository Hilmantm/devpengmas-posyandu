package id.kodesumsi.telkompengmas.data

import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.data.source.local.LocalDataSource
import id.kodesumsi.telkompengmas.data.source.network.ApiResponse
import id.kodesumsi.telkompengmas.data.source.network.RemoteDataSource
import id.kodesumsi.telkompengmas.domain.model.Child
import id.kodesumsi.telkompengmas.domain.model.User
import id.kodesumsi.telkompengmas.domain.repository.ChildRepository
import id.kodesumsi.telkompengmas.domain.repository.UserRepository
import id.kodesumsi.telkompengmas.utils.toUser
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class ChildRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): ChildRepository {
    override fun getChildList(token: String): Flowable<Resource<List<Child>>> {
        val result = PublishSubject.create<Resource<List<Child>>>()

        result.onNext(Resource.Loading(null))
        remoteDataSource.getChildList(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        result.onNext(
                            Resource.Success( data = response.data.data )
                        )
                    }
                    is ApiResponse.Error -> {
                        result.onNext(Resource.Error(response.errorMessage))
                    }
                }
            }

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }
}