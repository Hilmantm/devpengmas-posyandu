package id.kodesumsi.telkompengmas.data.source.local

import id.kodesumsi.telkompengmas.data.source.local.entity.UserEntity
import id.kodesumsi.telkompengmas.data.source.network.response.AuthResponse
import id.kodesumsi.telkompengmas.domain.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface LocalDataSource {

    fun saveUser(userRole: Int, authResponse: AuthResponse): Completable

    fun getUser(): Single<UserEntity>

    fun removeUser(user: UserEntity): Completable

}