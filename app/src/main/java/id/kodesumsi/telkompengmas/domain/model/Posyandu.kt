package id.kodesumsi.telkompengmas.domain.model

data class Posyandu(

    val id: Int,

    val thumbUrl: String,

    val name: String,

    val lat: String,

    val lng: String,

    val status: Boolean, // 0 => tutup; 1 => buka

    val address: String,

    val rating: Float,

    val phone: String

)
