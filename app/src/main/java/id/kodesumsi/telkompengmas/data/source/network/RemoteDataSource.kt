package id.kodesumsi.telkompengmas.data.source.network

import id.kodesumsi.telkompengmas.data.source.network.request.CreateNewChildRequest
import id.kodesumsi.telkompengmas.data.source.network.request.RegisterRequest
import id.kodesumsi.telkompengmas.data.source.network.request.UpdateChildDataRequest
import id.kodesumsi.telkompengmas.data.source.network.response.*
import id.kodesumsi.telkompengmas.domain.model.Child
import io.reactivex.rxjava3.core.Flowable

interface RemoteDataSource {

    // =========== ORANG TUA ===========
    fun getDesaList(): Flowable<ApiResponse<ListOfResponse<DesaResponse>>>

    fun getPosyanduList(desaId: Int): Flowable<ApiResponse<ListOfResponse<PosyanduResponse>>>

    // =========== ORANG TUA ===========
    fun orangtuaLogin(email: String, password: String): Flowable<ApiResponse<AuthResponse>>

    fun orangtuaRegister(registerRequest: RegisterRequest): Flowable<ApiResponse<AuthResponse>>

    fun getOrangtuaChildList(token: String): Flowable<ApiResponse<ListOfResponse<ChildResponse>>>

    fun postOrangtuaNewChildData(token: String, createNewChildRequest: CreateNewChildRequest): Flowable<ApiResponse<Child>>

    fun getOrangtuaStatistics(token: String, childId: Int): Flowable<ApiResponse<List<ChildStatisticsResponse>>>

    fun postOrangtuaStatistics(token: String, updateChildDataRequest: UpdateChildDataRequest): Flowable<ApiResponse<Child>>

    // =========== POSYANDU ===========
    fun posyanduLogin(email: String, password: String): Flowable<ApiResponse<AuthResponse>>

    fun posyanduRegister(registerRequest: RegisterRequest): Flowable<ApiResponse<AuthResponse>>

    fun getPosyanduChildList(token: String): Flowable<ApiResponse<ListOfResponse<ChildResponse>>>

    fun postPosyanduNewChildData(token: String, createNewChildRequest: CreateNewChildRequest): Flowable<ApiResponse<Child>>

    fun getPosyanduStatistics(token: String, childId: Int): Flowable<ApiResponse<List<ChildStatisticsResponse>>>

    fun postPosyanduStatistics(token: String, updateChildDataRequest: UpdateChildDataRequest): Flowable<ApiResponse<Child>>

}