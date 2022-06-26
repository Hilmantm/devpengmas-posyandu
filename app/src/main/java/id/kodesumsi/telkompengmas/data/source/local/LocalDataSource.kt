package id.kodesumsi.telkompengmas.data.source.local

import id.kodesumsi.telkompengmas.data.source.local.entity.UserEntity
import id.kodesumsi.telkompengmas.data.source.network.response.AuthResponse
import id.kodesumsi.telkompengmas.domain.model.User
import io.reactivex.rxjava3.core.Maybe

interface LocalDataSource {

    fun saveUser(authResponse: AuthResponse): Maybe<UserEntity>

}