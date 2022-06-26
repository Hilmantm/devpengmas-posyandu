package id.kodesumsi.telkompengmas.utils

import id.kodesumsi.telkompengmas.data.source.local.entity.UserEntity
import id.kodesumsi.telkompengmas.data.source.network.request.RegisterRequest
import id.kodesumsi.telkompengmas.data.source.network.response.AuthResponse
import id.kodesumsi.telkompengmas.data.source.network.response.TokenResponse
import id.kodesumsi.telkompengmas.data.source.network.response.UserResponse
import id.kodesumsi.telkompengmas.domain.model.User

fun RegisterRequest.toAuthResponse(): AuthResponse {
    return AuthResponse(
        user = UserResponse(nama = this.name, email = this.email, idDesa = this.idDesa, idPosyandu = this.idPosyandu),
        token = TokenResponse(type = "", value = "")
    )
}

fun AuthResponse.toUser(): User {
    return User(name = this.user.nama, email = this.user.email, idDesa = this.user.idDesa, iDPosyandu = this.user.idPosyandu)
}

fun AuthResponse.toUserEntity(): UserEntity {
    return UserEntity(
        name = this.user.nama,
        email = this.user.email,
        idDesa = this.user.idDesa,
        idPosyandu = this.user.idPosyandu,
        token = this.token.value
    )
}

fun UserEntity.toUser(): User {
    return User(
        name = this.name,
        email = this.email,
        idDesa = this.idDesa,
        iDPosyandu = this.idPosyandu,
        token = this.token
    )
}