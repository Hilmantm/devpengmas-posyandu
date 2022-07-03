package id.kodesumsi.telkompengmas.data.source.network

import id.kodesumsi.telkompengmas.data.source.network.response.*
import id.kodesumsi.telkompengmas.domain.model.Child
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.*

interface NetworkService {

    // =========== PUBLIC ===========
    @GET("/api/desa")
    fun getDesaList(): Flowable<BaseResponse<ListOfResponse<DesaResponse>>>

    @GET("/api/posyandu")
    fun getPosyanduList(
        @Query("id_desa") desaId: Int
    ): Flowable<BaseResponse<ListOfResponse<PosyanduResponse>>>


    // =========== ORANG TUA ===========
    @FormUrlEncoded
    @POST("/api/auth/orang-tua/login")
    fun postOrangtuaLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Flowable<BaseResponse<AuthResponse>>

    @FormUrlEncoded
    @POST("/api/auth/orang-tua/register")
    fun postOrangtuaRegister(
        @Field("nama") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("id_desa") idDesa: Int,
        @Field("id_posyandu") idPosyandu: Int
    ): Flowable<BaseResponse<AuthResponse>>

    @GET("/api/orang-tua/data-anak")
    fun getOrangtuaChildList(
        @Header("Authorization") token: String
    ): Flowable<BaseResponse<ListOfResponse<ChildResponse>>>

    @FormUrlEncoded
    @POST("/api/orang-tua/data-anak")
    fun postOrangtuaNewChildData(
        @Header("Authorization") token: String,
        @Field("nama") name: String,
        @Field("panggilan") surname: String,
        @Field("tanggal_lahir") birthDate: String,
        @Field("tinggi") height: String,
        @Field("berat") weight: String,
        @Field("lingkar_kepala") headCircumference: String,
        @Field("gender") gender: String,
        @Field("z_score_berat") zScoreWeight: Float,
        @Field("z_score_tinggi") zScoreHeight: Float,
        @Field("z_score_lingkar_kepala") zScoreHeadCircumference: Float
    ): Flowable<BaseResponse<Child>>

    @GET("/api/orang-tua/statistik-anak/{childId}")
    fun getOrangtuaStatistics(
        @Header("Authorization") token: String,
        @Path("childId") childId: Int
    ): Flowable<BaseResponse<List<ChildStatisticsResponse>>>

    @FormUrlEncoded
    @POST("/api/orang-tua/statistik-anak")
    fun postOrangtuaStatistics(
        @Header("Authorization") token: String,
        @Field("id_anak") childId: Int,
        @Field("berat") weight: Int,
        @Field("tinggi") height: Int,
        @Field("lingkar_kepala") headCircumference: Int,
        @Field("z_score_berat") zScoreWeight: Float,
        @Field("z_score_tinggi") zScoreHeight: Float,
        @Field("z_score_lingkar_kepala") zScoreHeadCircumference: Float
    ): Flowable<BaseResponse<Child>>


    // =========== POSYANDU ===========
    @FormUrlEncoded
    @POST("/api/auth/posyandu/login")
    fun postPosyanduLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Flowable<BaseResponse<AuthResponse>>

    @FormUrlEncoded
    @POST("/api/auth/posyandu/register")
    fun postPosyanduRegister(
        @Field("nama") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("id_desa") idDesa: Int,
        @Field("id_posyandu") idPosyandu: Int
    ): Flowable<BaseResponse<AuthResponse>>

    @GET("/api/posyandu/data-anak")
    fun getPosyanduChildList(
        @Header("Authorization") token: String
    ): Flowable<BaseResponse<ListOfResponse<ChildResponse>>>

    @FormUrlEncoded
    @POST("/api/posyandu/data-anak")
    fun postPosyanduNewChildData(
        @Header("Authorization") token: String,
        @Field("nama") name: String,
        @Field("panggilan") surname: String,
        @Field("tanggal_lahir") birthDate: String,
        @Field("tinggi") height: String,
        @Field("berat") weight: String,
        @Field("lingkar_kepala") headCircumference: String,
        @Field("nama_orang_tua") parentName: String,
        @Field("alamat") address: String,
        @Field("gender") gender: String,
        @Field("z_score_berat") zScoreWeight: Float,
        @Field("z_score_tinggi") zScoreHeight: Float,
        @Field("z_score_lingkar_kepala") zScoreHeadCircumference: Float
    ): Flowable<BaseResponse<Child>>

    @GET("/api/posyandu/statistik-anak/{childId}")
    fun getPosyanduStatistics(
        @Header("Authorization") token: String,
        @Path("childId") childId: Int
    ): Flowable<BaseResponse<List<ChildStatisticsResponse>>>

    @FormUrlEncoded
    @POST("/api/posyandu/statistik-anak")
    fun postPosyanduStatistics(
        @Header("Authorization") token: String,
        @Field("id_anak") childId: Int,
        @Field("berat") weight: Int,
        @Field("tinggi") height: Int,
        @Field("lingkar_kepala") headCircumference: Int,
        @Field("z_score_berat") zScoreWeight: Float,
        @Field("z_score_tinggi") zScoreHeight: Float,
        @Field("z_score_lingkar_kepala") zScoreHeadCircumference: Float
    ): Flowable<BaseResponse<Child>>

}