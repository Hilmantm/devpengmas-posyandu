package id.kodesumsi.telkompengmas.data.source.network

import id.kodesumsi.telkompengmas.data.source.network.request.CreateNewChildRequest
import id.kodesumsi.telkompengmas.data.source.network.request.RegisterRequest
import id.kodesumsi.telkompengmas.data.source.network.response.AuthResponse
import id.kodesumsi.telkompengmas.data.source.network.response.ListOfResponse
import id.kodesumsi.telkompengmas.domain.model.Child
import io.reactivex.rxjava3.core.Flowable

interface RemoteDataSource {

    // =========== ORANG TUA ===========
    fun orangtuaLogin(username: String, password: String): Flowable<ApiResponse<AuthResponse>>

    fun orangtuaRegister(registerRequest: RegisterRequest): Flowable<ApiResponse<AuthResponse>>

    fun getOrangtuaChildList(token: String): Flowable<ApiResponse<ListOfResponse<Child>>>

    fun postOrangtuaNewChildData(token: String, createNewChildRequest: CreateNewChildRequest): Flowable<ApiResponse<Child>>

    // =========== POSYANDU ===========
    fun posyanduLogin(username: String, password: String): Flowable<ApiResponse<AuthResponse>>

    fun posyanduRegister(registerRequest: RegisterRequest): Flowable<ApiResponse<AuthResponse>>

    fun getPosyanduChildList(token: String): Flowable<ApiResponse<ListOfResponse<Child>>>

    fun postPosyanduNewChildData(token: String, createNewChildRequest: CreateNewChildRequest): Flowable<ApiResponse<Child>>

}