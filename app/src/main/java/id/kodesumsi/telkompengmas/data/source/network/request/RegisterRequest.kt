package id.kodesumsi.telkompengmas.data.source.network.request

data class RegisterRequest(

    val name: String,

    val email: String,

    val password: String,

    val idDesa: Int,

    val idPosyandu: Int,

    val userRole: Int

)
