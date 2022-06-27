package id.kodesumsi.telkompengmas.data.source.network.response

import com.google.gson.annotations.SerializedName

data class PosyanduResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("nama")
    val nama: String,

    @field:SerializedName("alamat")
    val alamat: String,

    @field:SerializedName("longitude")
    val longitude: String,

    @field:SerializedName("latitude")
    val latitude: String,

    @field:SerializedName("id_desa")
    val id_desa: Int

)
