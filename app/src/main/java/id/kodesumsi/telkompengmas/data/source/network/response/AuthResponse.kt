package id.kodesumsi.telkompengmas.data.source.network.response

import com.google.gson.annotations.SerializedName
import id.kodesumsi.telkompengmas.domain.model.User

data class AuthResponse(

    @field:SerializedName("user")
    val user: UserResponse,

    @field:SerializedName("token")
    val token: TokenResponse

)
