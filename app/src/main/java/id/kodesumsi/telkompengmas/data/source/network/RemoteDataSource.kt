package id.kodesumsi.telkompengmas.data.source.network

import id.kodesumsi.telkompengmas.data.source.network.request.RegisterRequest
import id.kodesumsi.telkompengmas.data.source.network.response.AuthResponse
import id.kodesumsi.telkompengmas.data.source.network.response.ListOfResponse
import id.kodesumsi.telkompengmas.domain.model.Child
import io.reactivex.rxjava3.core.Flowable

interface RemoteDataSource {

    fun posyanduLogin(username: String, password: String): Flowable<ApiResponse<AuthResponse>>

    fun orangtuaLogin(username: String, password: String): Flowable<ApiResponse<AuthResponse>>

    fun posyanduRegister(registerRequest: RegisterRequest): Flowable<ApiResponse<AuthResponse>>

    fun orangtuaRegister(registerRequest: RegisterRequest): Flowable<ApiResponse<AuthResponse>>

    fun getChildList(token: String): Flowable<ApiResponse<ListOfResponse<Child>>>

}