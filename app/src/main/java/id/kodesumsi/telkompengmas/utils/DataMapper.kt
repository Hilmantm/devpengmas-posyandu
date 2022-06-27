package id.kodesumsi.telkompengmas.utils

import id.kodesumsi.telkompengmas.data.source.local.entity.UserEntity
import id.kodesumsi.telkompengmas.data.source.network.request.CreateNewChildRequest
import id.kodesumsi.telkompengmas.data.source.network.request.RegisterRequest
import id.kodesumsi.telkompengmas.data.source.network.request.UpdateChildDataRequest
import id.kodesumsi.telkompengmas.data.source.network.response.AuthResponse
import id.kodesumsi.telkompengmas.data.source.network.response.ChildStatisticsResponse
import id.kodesumsi.telkompengmas.data.source.network.response.TokenResponse
import id.kodesumsi.telkompengmas.data.source.network.response.UserResponse
import id.kodesumsi.telkompengmas.domain.model.Child
import id.kodesumsi.telkompengmas.domain.model.ChildStatistics
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

fun CreateNewChildRequest.toChild(): Child {
    return Child(
        name = this.name,
        surname = this.panggilan,
        birthDate = this.tanggal_lahir,
        height = this.tinggi.toFloat(),
        weight = this.berat.toFloat(),
        headCircumference = this.lingkar_kepala.toFloat()
    )
}

fun UpdateChildDataRequest.toChild(): Child {
    return Child(
        id = this.childId,
        height = this.height.toFloat(),
        weight = this.weight.toFloat(),
        headCircumference = this.headCircumference.toFloat()
    )
}

fun ChildStatisticsResponse.toChildStatistics(): ChildStatistics {
    return ChildStatistics(
        id = this.id,
        childId = this.id_anak,
        height = this.tinggi,
        weight = this.berat,
        headCircumference = this.lingkar_kepala,
        date = this.date
    )
}