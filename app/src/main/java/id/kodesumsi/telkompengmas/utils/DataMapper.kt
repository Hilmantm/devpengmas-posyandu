package id.kodesumsi.telkompengmas.utils

import android.util.Log
import id.kodesumsi.telkompengmas.data.source.local.entity.DoctorEntity
import id.kodesumsi.telkompengmas.data.source.local.entity.UserEntity
import id.kodesumsi.telkompengmas.data.source.network.request.CreateNewChildRequest
import id.kodesumsi.telkompengmas.data.source.network.request.RegisterRequest
import id.kodesumsi.telkompengmas.data.source.network.request.UpdateChildDataRequest
import id.kodesumsi.telkompengmas.data.source.network.response.*
import id.kodesumsi.telkompengmas.domain.model.*

fun Doctor.toDocktorEntity(): DoctorEntity {
    return DoctorEntity(
        name = this.name,
        phone = this.phone
    )
}

fun DoctorEntity.toDoctor(): Doctor {
    return Doctor(
        id = this.id,
        name = this.name,
        phone = this.phone
    )
}

fun RegisterRequest.toAuthResponse(): AuthResponse {
    return AuthResponse(
        user = UserResponse(name = this.name, email = this.email, idDesa = this.idDesa, idPosyandu = this.idPosyandu),
        token = TokenResponse(type = "", value = "")
    )
}

fun AuthResponse.toUser(): User {
    Log.d("Data Mapper", "toUser: $this")
    return User(name = this.user.name, email = this.user.email, idDesa = this.user.idDesa, iDPosyandu = this.user.idPosyandu)
}

fun AuthResponse.toUserEntity(userRole: Int): UserEntity {
    return UserEntity(
        name = this.user.name,
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
        date = this.date,
        zScoreHeight = this.z_score_tinggi,
        zScoreWeight = this.z_score_berat,
        zScoreHeadCircumference = this.z_score_lingkar_kepala,
        statistics = this.statistik.toStatisticsModel()
    )
}

fun StatistikResponse.toStatisticsModel(): Statistics {
    return Statistics(
        weight = this.berat,
        height = this.tinggi,
        headCircumference = this.lingkar_kepala,
        normalWeight = this.normal_weight,
        normalHeight = this.normal_height,
        normalHeadCircumference = this.normal_headcircumference,
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
        name = this.nama,
        id = this.id,
        desaId = this.id_desa,
        lat = this.latitude,
        lng = this.longitude,
        address = this.alamat
    )
}

fun ChildResponse.toChild(): Child {
    return Child(
        id = this.id,
        name = this.nama,
        surname = this.panggilan,
        birthDate = this.tanggal_lahir,
        address = this.alamat,
        idDesa = this.id_desa?.toInt(),
        idPosyandu = this.id_posyandu?.toInt(),
        idParent = this.id_orang_tua?.toInt(),
        parentName = this.nama_orang_tua,
        gender = this.gender,
        image = this.image
    )
}