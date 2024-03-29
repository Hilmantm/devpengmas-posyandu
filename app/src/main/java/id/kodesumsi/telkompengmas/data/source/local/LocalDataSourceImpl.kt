package id.kodesumsi.telkompengmas.data.source.local

import id.kodesumsi.telkompengmas.data.source.local.dao.DoctorDao
import id.kodesumsi.telkompengmas.data.source.local.dao.UserDao
import id.kodesumsi.telkompengmas.data.source.local.entity.DoctorEntity
import id.kodesumsi.telkompengmas.data.source.local.entity.UserEntity
import id.kodesumsi.telkompengmas.data.source.network.response.AuthResponse
import id.kodesumsi.telkompengmas.domain.model.User
import id.kodesumsi.telkompengmas.utils.toUserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

class LocalDataSourceImpl(
    private val userDao: UserDao,
    private val doctorDao: DoctorDao
): LocalDataSource {
    override fun saveUser(userRole: Int, authResponse: AuthResponse): Completable {
        return userDao.saveUser(authResponse.toUserEntity(userRole))
    }

    override fun getUser(): Single<UserEntity> {
        return userDao.getUser()
    }

    override fun removeUser(user: UserEntity): Completable {
        return userDao.removeUser(user)
    }

    override fun saveDoctor(doctor: DoctorEntity): Completable {
        return doctorDao.saveDoctor(doctor)
    }

    override fun getDoctors(): Flowable<List<DoctorEntity>> {
        return doctorDao.getDoctors()
    }
}