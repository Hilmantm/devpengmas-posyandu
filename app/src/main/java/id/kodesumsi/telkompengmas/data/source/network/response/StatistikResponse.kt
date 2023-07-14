package id.kodesumsi.telkompengmas.data.source.network.response

import com.google.gson.annotations.SerializedName

data class StatistikResponse(

    @field:SerializedName("berat")
    val berat: String? = null,

    @field:SerializedName("tinggi")
    val tinggi: String? = null,

    @field:SerializedName("lingkar_kepala")
    val lingkar_kepala: String? = null,

    @field:SerializedName("imt")
    val imt: String? = null,

    @field:SerializedName("normal_weight")
    val normal_weight: String? = null,

    @field:SerializedName("normal_height")
    val normal_height: String? = null,

    @field:SerializedName("normal_headcircumference")
    val normal_headcircumference: String? = null,

)
