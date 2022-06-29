package id.kodesumsi.telkompengmas.data.source.network.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @field:SerializedName("name")
    val name: String = "Nama Default",

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("id_desa")
    val idDesa: Int,

    @field:SerializedName("id_posyandu")
    val idPosyandu: Int

)
