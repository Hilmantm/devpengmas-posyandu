package id.kodesumsi.telkompengmas.data.source.network

import android.util.Log
import id.kodesumsi.telkompengmas.data.source.network.request.RegisterRequest
import id.kodesumsi.telkompengmas.data.source.network.response.AuthResponse
import id.kodesumsi.telkompengmas.data.source.network.response.ListOfResponse
import id.kodesumsi.telkompengmas.domain.model.Child
import id.kodesumsi.telkompengmas.utils.toAuthResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val networkService: NetworkService
): RemoteDataSource {
    override fun posyanduLogin(username: String, password: String): Flowable<ApiResponse<AuthResponse>> {
        val result = PublishSubject.create<ApiResponse<AuthResponse>>()
        val client = networkService.postPosyanduLogin(username, password)
        client.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val user = response.data
                result.onNext(if (user != null) ApiResponse.Success(user) else ApiResponse.Empty)
            }, { error ->
                result.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    override fun orangtuaLogin(username: String, password: String): Flowable<ApiResponse<AuthResponse>> {
        val result = PublishSubject.create<ApiResponse<AuthResponse>>()
        val client = networkService.postOrangtuaLogin(username, password)
        client.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val user = response.data
                result.onNext(if (user != null) ApiResponse.Success(user) else ApiResponse.Empty)
            }, { error ->
                result.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    override fun posyanduRegister(registerRequest: RegisterRequest): Flowable<ApiResponse<AuthResponse>> {
        val result = PublishSubject.create<ApiResponse<AuthResponse>>()
        val client = networkService.postPosyanduRegister(
            name = registerRequest.name,
            email = registerRequest.email,
            password = registerRequest.password,
            idDesa = registerRequest.idDesa,
            idPosyandu = registerRequest.idPosyandu
        )
        client.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val code = response.code
                val registeredUser = registerRequest.toAuthResponse()
                result.onNext(if (code == 200) ApiResponse.Success(registeredUser) else ApiResponse.Empty)
            }, { error ->
                result.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    override fun orangtuaRegister(registerRequest: RegisterRequest): Flowable<ApiResponse<AuthResponse>> {
        val result = PublishSubject.create<ApiResponse<AuthResponse>>()
        val client = networkService.postOrangtuaRegister(
            name = registerRequest.name,
            email = registerRequest.email,
            password = registerRequest.password,
            idDesa = registerRequest.idDesa,
            idPosyandu = registerRequest.idPosyandu
        )
        client.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val code = response.code
                val registeredUser = registerRequest.toAuthResponse()
                result.onNext(if (code == 200) ApiResponse.Success(registeredUser) else ApiResponse.Empty)
            }, { error ->
                result.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    override fun getChildList(token: String): Flowable<ApiResponse<ListOfResponse<Child>>> {
        val result = PublishSubject.create<ApiResponse<ListOfResponse<Child>>>()
        val client = networkService.getChildList(token)

        client.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val code = response.code
                result.onNext(if (code == 200 && response.data!!.data.isNotEmpty()) ApiResponse.Success(response.data) else ApiResponse.Empty)
            }, { error ->
                result.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }
}