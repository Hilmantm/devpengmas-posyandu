package id.kodesumsi.telkompengmas.data.source.network.response

import com.google.gson.annotations.SerializedName

data class StatistikResponse(

    @field:SerializedName("berat")
    val berat: String? = null,

    @field:SerializedName("tinggi")
    val tinggi: String? = null,

    @field:SerializedName("lingkar_kepala")
    val lingkar_kepala: String? = null

)
