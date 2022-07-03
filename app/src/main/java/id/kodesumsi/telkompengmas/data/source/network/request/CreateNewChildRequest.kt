package id.kodesumsi.telkompengmas.data.source.network.request

data class CreateNewChildRequest(

    val name: String,

    val panggilan: String,

    val tanggal_lahir: String,

    val tinggi: String,

    val berat: String,

    val lingkar_kepala: String,

    val nama_orang_tua: String? = null,

    val alamat: String? = null,

    val gender: String,

    val z_score_tinggi: Float? = null,

    val z_score_berat: Float? = null,

    val z_score_lingkar_kepala: Float? = null

)
