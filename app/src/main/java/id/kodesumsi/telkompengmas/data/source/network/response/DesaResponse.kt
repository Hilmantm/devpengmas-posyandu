package id.kodesumsi.telkompengmas.data.source.network.response

import com.google.gson.annotations.SerializedName

data class DesaResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String

)
