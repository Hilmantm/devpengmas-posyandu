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

}