package id.kodesumsi.telkompengmas.utils

import id.kodesumsi.telkompengmas.data.source.local.entity.UserEntity
import id.kodesumsi.telkompengmas.data.source.network.request.CreateNewChildRequest
import id.kodesumsi.telkompengmas.data.source.network.request.RegisterRequest
import id.kodesumsi.telkompengmas.data.source.network.request.UpdateChildDataRequest
import id.kodesumsi.telkompengmas.data.source.network.response.*
import id.kodesumsi.telkompengmas.domain.model.*

fun RegisterRequest.toAuthResponse(): AuthResponse {
    return AuthResponse(
        user = UserResponse(nama = this.name, email = this.email, idDesa = this.idDesa, idPosyandu = this.idPosyandu),
        token = TokenResponse(type = "", value = "")
    )
}

fun AuthResponse.toUser(): User {
    return User(name = this.user.nama, email = this.user.email, idDesa = this.user.idDesa, iDPosyandu = this.user.idPosyandu)
}

fun AuthResponse.toUserEntity(userRole: Int): UserEntity {
    return UserEntity(
        name = this.user.nama,
        email = this.user.email,
        idDesa = this.user.idDesa,
        idPosyandu = this.user.idPosyandu,
        token = this.token.value,
        userRole = userRole
    )
}

fun UserEntity.toUser(): User {
    return User(
        id = this.id,
        name = this.name,
        email = this.email,
        idDesa = this.idDesa,
        iDPosyandu = this.idPosyandu,
        token = this.token,
        role = this.userRole
    )
}

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        name = this.name,
        email = this.email,
        idDesa = this.idDesa ?: 0,
        idPosyandu = this.iDPosyandu ?: 0,
        userRole = this.role ?: 2,
        token = this.token ?: ""
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

fun DesaResponse.toDesa(): Desa {
    return Desa(
        id = this.id,
        name = this.name
    )
}

fun PosyanduResponse.toPosyandu(): Posyandu {
    return Posyandu(
        id = this.id,
        desaId = this.id_desa,
        lat = this.latitude,
        lng = this.longitude,
        address = this.alamat
    )
}