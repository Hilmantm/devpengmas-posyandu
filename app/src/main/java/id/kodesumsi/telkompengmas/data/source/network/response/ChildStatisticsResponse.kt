package id.kodesumsi.telkompengmas.data.source.network.response

import com.google.gson.annotations.SerializedName

data class ChildStatisticsResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("tinggi")
    val tinggi: Int,

    @field:SerializedName("berat")
    val berat: Int,

    @field:SerializedName("ligkar_kepala")
    val lingkar_kepala: Int,

    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("id_anak")
    val id_anak: Int

)
