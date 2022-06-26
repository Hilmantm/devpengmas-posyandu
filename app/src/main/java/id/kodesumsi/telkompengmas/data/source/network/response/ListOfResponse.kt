package id.kodesumsi.telkompengmas.data.source.network.response

import com.google.gson.annotations.SerializedName

data class ListOfResponse<T>(

    @field:SerializedName("limit")
    val limit: String? = null,

    @field:SerializedName("total")
    val total: Int? = null,

    @field:SerializedName("data")
    val data: List<T> = listOf()

)