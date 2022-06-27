package id.kodesumsi.telkompengmas.data

import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.data.source.local.LocalDataSource
import id.kodesumsi.telkompengmas.data.source.network.ApiResponse
import id.kodesumsi.telkompengmas.data.source.network.RemoteDataSource
import id.kodesumsi.telkompengmas.data.source.network.request.CreateNewChildRequest
import id.kodesumsi.telkompengmas.data.source.network.request.RegisterRequest
import id.kodesumsi.telkompengmas.domain.model.Child
import id.kodesumsi.telkompengmas.domain.model.User
import id.kodesumsi.telkompengmas.domain.repository.PosyanduRepository
import id.kodesumsi.telkompengmas.utils.RepositoryUtility.saveAuthResponseToLocal
import id.kodesumsi.telkompengmas.utils.toUser
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class PosyanduRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): PosyanduRepository {
    override fun posyanduLogin(username: String, password: String): Flowable<Resource<User>> {
        val result = PublishSubject.create<Resource<User>>()

        result.onNext(Resource.Loading(null))
        remoteDataSource.posyanduLogin(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        saveAuthResponseToLocal(localDataSource, response.data, result)
                    }
                    is ApiResponse.Error -> {
                        result.onNext(Resource.Error(response.errorMessage))
                    }
                }
            }

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    override fun posyanduRegister(registerRequest: RegisterRequest): Flowable<Resource<User>> {
        val result = PublishSubject.create<Resource<User>>()

        result.onNext(Resource.Loading(null))
        remoteDataSource.posyanduRegister(registerRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        result.onNext(
                            Resource.Success( data = response.data.toUser() )
                        )
                    }
                    is ApiResponse.Error -> {
                        result.onNext(Resource.Error(response.errorMessage))
                    }
                }
            }

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    override fun getPosyanduChildList(token: String): Flowable<Resource<List<Child>>> {
        val result = PublishSubject.create<Resource<List<Child>>>()

        result.onNext(Resource.Loading(null))
        remoteDataSource.getPosyanduChildList(token)
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

    override fun postPosyanduNewChildData(
        token: String,
        createNewChildRequest: CreateNewChildRequest
    ): Flowable<Resource<Child>> {
        val result = PublishSubject.create<Resource<Child>>()

        result.onNext(Resource.Loading(null))
        remoteDataSource.postPosyanduNewChildData(token, createNewChildRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        result.onNext(
                            Resource.Success( data = response.data )
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