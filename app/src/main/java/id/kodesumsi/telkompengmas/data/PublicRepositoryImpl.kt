package id.kodesumsi.telkompengmas.data

import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.data.source.local.LocalDataSource
import id.kodesumsi.telkompengmas.data.source.network.ApiResponse
import id.kodesumsi.telkompengmas.data.source.network.RemoteDataSource
import id.kodesumsi.telkompengmas.domain.model.Desa
import id.kodesumsi.telkompengmas.domain.model.Doctor
import id.kodesumsi.telkompengmas.domain.model.Posyandu
import id.kodesumsi.telkompengmas.domain.model.User
import id.kodesumsi.telkompengmas.domain.repository.PublicRepository
import id.kodesumsi.telkompengmas.utils.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class PublicRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): PublicRepository {
    override fun getDesaList(): Flowable<Resource<List<Desa>>> {
        val result = PublishSubject.create<Resource<List<Desa>>>()

        result.onNext(Resource.Loading(null))
        remoteDataSource.getDesaList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        result.onNext(
                            Resource.Success( data = response.data.data.map { it.toDesa() } )
                        )
                    }
                    is ApiResponse.Error -> {
                        result.onNext(Resource.Error(response.errorMessage))
                    }
                    else -> {}
                }
            }

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    override fun getPosyanduList(desaId: Int): Flowable<Resource<List<Posyandu>>> {
        val result = PublishSubject.create<Resource<List<Posyandu>>>()

        result.onNext(Resource.Loading(null))
        remoteDataSource.getPosyanduList(desaId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        result.onNext(
                            Resource.Success( data = response.data.data.map { it.toPosyandu() } )
                        )
                    }
                    is ApiResponse.Error -> {
                        result.onNext(Resource.Error(response.errorMessage))
                    }
                    else -> {}
                }
            }

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    override fun getUser(): Single<Resource<User>> {
        return localDataSource.getUser().map { Resource.Success(data = it.toUser()) }
    }

    override fun logout(user: User): Completable {
        return localDataSource.removeUser(user.toUserEntity())
    }

    override fun saveDoctor(doctor: Doctor): Completable {
        return localDataSource.saveDoctor(doctor = doctor.toDocktorEntity())
    }

    override fun getDoctors(): Flowable<Resource<List<Doctor>>> {
        return localDataSource.getDoctors().map { doctors ->
            Resource.Success(data = doctors.map { it.toDoctor() })
        }
    }
}