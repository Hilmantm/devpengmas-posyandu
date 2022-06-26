package id.kodesumsi.telkompengmas.data.source.network

import id.kodesumsi.telkompengmas.data.source.network.request.RegisterRequest
import id.kodesumsi.telkompengmas.data.source.network.response.AuthResponse
import io.reactivex.rxjava3.core.Flowable

interface RemoteDataSource {

    fun posyanduLogin(username: String, password: String): Flowable<ApiResponse<AuthResponse>>

    fun orangtuaLogin(username: String, password: String): Flowable<ApiResponse<AuthResponse>>

    fun posyanduRegister(registerRequest: RegisterRequest): Flowable<ApiResponse<AuthResponse>>

    fun orangtuaRegister(registerRequest: RegisterRequest): Flowable<ApiResponse<AuthResponse>>

}