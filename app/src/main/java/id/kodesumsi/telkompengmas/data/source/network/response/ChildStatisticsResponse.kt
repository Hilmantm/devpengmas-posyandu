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

    @field:SerializedName("z_score_tinggi")
    val z_score_tinggi: Float,

    @field:SerializedName("z_score_berat")
    val z_score_berat: Float,

    @field:SerializedName("z_score_lingkar_kepala")
    val z_score_lingkar_kepala: Float,

    @field:SerializedName("z_score_imt")
    val z_score_imt: Float,

    @field:SerializedName("statistik")
    val statistik: StatistikResponse

)
