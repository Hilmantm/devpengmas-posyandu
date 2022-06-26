package id.kodesumsi.telkompengmas.data.source.network.response

import id.kodesumsi.telkompengmas.domain.model.User

data class AuthResponse(

    val user: UserResponse,
    val token: TokenResponse

)
