package id.kodesumsi.telkompengmas.data.source.network.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("data")
    val data: T? = null,

    @field:SerializedName("total_pages")
    val total_pages: Int? = null,

    @field:SerializedName("total_results")
    val total_results: Int? = null
)