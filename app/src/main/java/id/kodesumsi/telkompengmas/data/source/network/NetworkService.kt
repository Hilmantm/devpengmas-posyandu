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

    // =========== ORANG TUA ===========
    @POST("auth/orang-tua/login")
    fun postOrangtuaLogin(
        @Field("username") username: String,
        @Field("password") password: String
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
    fun getOrangtuaChildList(
        @Header("Authorization") token: String
    ): Flowable<BaseResponse<ListOfResponse<Child>>>

    @POST("/api/orang-tua/data-anak")
    fun postOrangtuaNewChildData(
        @Header("Authorization") token: String,
        @Field("nama") name: String,
        @Field("panggilan") surname: String,
        @Field("tanggal_lahir") birthDate: String,
        @Field("tinggi") height: String,
        @Field("berat") weight: String,
        @Field("lingkar_kepala") headCircumference: String
    ): Flowable<BaseResponse<Child>>


    // =========== POSYANDU ===========
    @POST("auth/posyandu/login")
    fun postPosyanduLogin(
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

    @GET("/api/posyandu/data-anak")
    fun getPosyanduChildList(
        @Header("Authorization") token: String
    ): Flowable<BaseResponse<ListOfResponse<Child>>>

    @POST("/api/posyandu/data-anak")
    fun postOrangtuaNewChildData(
        @Header("Authorization") token: String,
        @Field("nama") name: String,
        @Field("panggilan") surname: String,
        @Field("tanggal_lahir") birthDate: String,
        @Field("tinggi") height: String,
        @Field("berat") weight: String,
        @Field("lingkar_kepala") headCircumference: String,
        @Field("nama_orang_tua") parentName: String,
        @Field("alamat") address: String
    ): Flowable<BaseResponse<Child>>

}