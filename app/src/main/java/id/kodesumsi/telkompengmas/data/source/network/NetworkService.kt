package id.kodesumsi.telkompengmas.data.source.network

import id.kodesumsi.telkompengmas.data.source.network.response.BaseResponse
import id.kodesumsi.telkompengmas.domain.model.dummy.User
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NetworkService {

    @POST("auth/posyandu/login")
    fun postPosyanduLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Flowable<BaseResponse<User>>

    @POST("auth/orang-tua/login")
    fun postOrangtuaLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Flowable<BaseResponse<User>>

    @POST("auth/posyandu/register")
    fun postPosyanduRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("id_desa") idDesa: Int,
        @Field("id_posyandu") idPosyandu: Int
    ): Flowable<BaseResponse<User>>

    @POST("auth/orang-tua/register")
    fun postOrangtuaRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("id_desa") idDesa: Int,
        @Field("id_posyandu") idPosyandu: Int
    ): Flowable<BaseResponse<User>>

}