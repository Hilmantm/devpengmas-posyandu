package id.kodesumsi.telkompengmas.data.source.network

import id.kodesumsi.telkompengmas.data.source.network.response.AuthResponse
import id.kodesumsi.telkompengmas.data.source.network.response.BaseResponse
import id.kodesumsi.telkompengmas.data.source.network.response.ListOfResponse
import id.kodesumsi.telkompengmas.domain.model.Child
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface NetworkService {

    @POST("auth/posyandu/login")
    fun postPosyanduLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Flowable<BaseResponse<AuthResponse>>

    @POST("auth/orang-tua/login")
    fun postOrangtuaLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Flowable<BaseResponse<AuthResponse>>

    @POST("auth/posyandu/register")
    fun postPosyanduRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("id_desa") idDesa: Int,
        @Field("id_posyandu") idPosyandu: Int
    ): Flowable<BaseResponse<AuthResponse>>

    @POST("auth/orang-tua/register")
    fun postOrangtuaRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("id_desa") idDesa: Int,
        @Field("id_posyandu") idPosyandu: Int
    ): Flowable<BaseResponse<AuthResponse>>

    @GET("/api/orang-tua/data-anak")
    fun getChildList(
        @Header("Authorization") token: String
    ): Flowable<BaseResponse<ListOfResponse<Child>>>

}