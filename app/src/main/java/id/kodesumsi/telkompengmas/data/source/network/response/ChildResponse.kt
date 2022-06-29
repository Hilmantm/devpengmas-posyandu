package id.kodesumsi.telkompengmas.data.source.network.response

import com.google.gson.annotations.SerializedName

data class ChildResponse(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("nama")
    val nama: String? = null,

    @field:SerializedName("panggilan")
    val panggilan: String? = null,

    @field:SerializedName("tanggal_lahir")
    val tanggal_lahir: String? = null,

    @field:SerializedName("alamat")
    val alamat: String? = null,

    @field:SerializedName("id_desa")
    val id_desa: String? = null,

    @field:SerializedName("id_posyandu")
    val id_posyandu: String? = null,

    @field:SerializedName("id_orang_tua")
    val id_orang_tua: String? = null,

    @field:SerializedName("nama_orang_tua")
    val nama_orang_tua: String? = null,

    @field:SerializedName("gender")
    val gender: String? = null,

    @field:SerializedName("image")
    val image: String? = null

)
