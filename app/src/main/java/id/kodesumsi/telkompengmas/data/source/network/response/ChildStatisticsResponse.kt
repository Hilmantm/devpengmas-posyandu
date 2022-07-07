package id.kodesumsi.telkompengmas.data.source.network.response

import com.google.gson.annotations.SerializedName

data class ChildStatisticsResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("tinggi")
    val tinggi: Float,

    @field:SerializedName("berat")
    val berat: Float,

    @field:SerializedName("lingkar_kepala")
    val lingkar_kepala: Float,

    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("id_anak")
    val id_anak: Int,

    @field:SerializedName("z_score")
    val z_score: Float,

    @field:SerializedName("statistik")
    val statistik: StatistikResponse

)
