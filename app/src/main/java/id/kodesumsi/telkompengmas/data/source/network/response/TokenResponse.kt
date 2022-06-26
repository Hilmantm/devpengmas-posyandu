package id.kodesumsi.telkompengmas.data.source.network.response

import com.google.gson.annotations.SerializedName

data class TokenResponse(

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("value")
    val value: String

)
