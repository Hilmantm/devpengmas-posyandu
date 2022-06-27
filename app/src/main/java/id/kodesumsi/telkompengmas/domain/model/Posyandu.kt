package id.kodesumsi.telkompengmas.domain.model

data class Posyandu(

    val id: Int? = null,

    val thumbUrl: String? = null,

    val name: String? = null,

    val lat: String? = null,

    val lng: String? = null,

    val status: Boolean? = null, // 0 => tutup; 1 => buka

    val address: String? = null,

    val rating: Float? = null,

    val phone: String? = null,

    val desaId: Int? = null

)
