package id.kodesumsi.telkompengmas.data.source.network

import id.kodesumsi.telkompengmas.data.source.network.response.BaseResponse
import id.kodesumsi.telkompengmas.domain.model.dummy.User
import io.reactivex.rxjava3.core.Flowable

interface RemoteDataSource {

    fun posyanduLogin(username: String, password: String): Flowable<ApiResponse<User>>

    fun orangtuaLogin(username: String, password: String): Flowable<ApiResponse<User>>

}