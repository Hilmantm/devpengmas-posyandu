package id.kodesumsi.telkompengmas.domain.model

data class User(

    val id: Int? = null,

    val email: String,

    val pass: String? = null,

    val name: String,

    val role: Int? = null,

    val phone: String? = null,

    val idDesa: Int? = null,

    val iDPosyandu: Int? = null,

    val token: String? = null

)
