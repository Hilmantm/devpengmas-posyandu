package id.kodesumsi.telkompengmas.data.source.network

import android.annotation.SuppressLint
import android.util.Log
import id.kodesumsi.telkompengmas.data.source.network.request.CreateNewChildRequest
import id.kodesumsi.telkompengmas.data.source.network.request.RegisterRequest
import id.kodesumsi.telkompengmas.data.source.network.request.UpdateChildDataRequest
import id.kodesumsi.telkompengmas.data.source.network.response.*
import id.kodesumsi.telkompengmas.domain.model.Child
import id.kodesumsi.telkompengmas.utils.Utility
import id.kodesumsi.telkompengmas.utils.toAuthResponse
import id.kodesumsi.telkompengmas.utils.toChild
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject


class RemoteDataSourceImpl @Inject constructor(
    private val networkService: NetworkService
): RemoteDataSource {
    @SuppressLint("CheckResult")
    override fun posyanduLogin(email: String, password: String): Flowable<ApiResponse<AuthResponse>> {
        val result = PublishSubject.create<ApiResponse<AuthResponse>>()
        val client = networkService.postPosyanduLogin(email, password)
        client.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val user = response.data
                result.onNext(if (user != null) ApiResponse.Success(user) else ApiResponse.Empty)
            }, { error ->
                val errorMessage = Utility.getErrorMessage(error)
                result.onNext(ApiResponse.Error(errorMessage))
                Log.e("RemoteDataSource", errorMessage)
                Log.e("RemoteDataSource", error.toString())
            })
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    override fun getDesaList(): Flowable<ApiResponse<ListOfResponse<DesaResponse>>> {
        val result = PublishSubject.create<ApiResponse<ListOfResponse<DesaResponse>>>()
        val client = networkService.getDesaList()
        client.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val desa = response.data
                result.onNext(if (desa?.data!!.isNotEmpty()) ApiResponse.Success(desa) else ApiResponse.Empty)
            }, { error ->
                val errorMessage = Utility.getErrorMessage(error)
                result.onNext(ApiResponse.Error(errorMessage))
                Log.e("RemoteDataSource", errorMessage)
                Log.e("RemoteDataSource", error.toString())
            })
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    override fun getPosyanduList(desaId: Int): Flowable<ApiResponse<ListOfResponse<PosyanduResponse>>> {
        val result = PublishSubject.create<ApiResponse<ListOfResponse<PosyanduResponse>>>()
        val client = networkService.getPosyanduList(desaId)
        client.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val posyandu = response.data
                result.onNext(if (posyandu?.data!!.isNotEmpty()) ApiResponse.Success(posyandu) else ApiResponse.Empty)
            }, { error ->
                val errorMessage = Utility.getErrorMessage(error)
                result.onNext(ApiResponse.Error(errorMessage))
                Log.e("RemoteDataSource", errorMessage)
                Log.e("RemoteDataSource", error.toString())
            })
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    override fun orangtuaLogin(email: String, password: String): Flowable<ApiResponse<AuthResponse>> {
        val result = PublishSubject.create<ApiResponse<AuthResponse>>()
        val client = networkService.postOrangtuaLogin(email, password)
        client.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val user = response.data
                result.onNext(if (user != null) ApiResponse.Success(user) else ApiResponse.Empty)
            }, { error ->
                val errorMessage = Utility.getErrorMessage(error)
                result.onNext(ApiResponse.Error(errorMessage))
                Log.e("RemoteDataSource", errorMessage)
                Log.e("RemoteDataSource", error.toString())
            })
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
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
                val errorMessage = Utility.getErrorMessage(error)
                result.onNext(ApiResponse.Error(errorMessage))
                Log.e("RemoteDataSource", errorMessage)
                Log.e("RemoteDataSource", error.toString())
            })
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    override fun getPosyanduChildList(token: String): Flowable<ApiResponse<ListOfResponse<ChildResponse>>> {
        val result = PublishSubject.create<ApiResponse<ListOfResponse<ChildResponse>>>()
        val client = networkService.getPosyanduChildList("Bearer $token")

        client.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val code = response.code
                result.onNext(if (code == 200 && response.data!!.data.isNotEmpty()) ApiResponse.Success(response.data) else ApiResponse.Empty)
            }, { error ->
                val errorMessage = Utility.getErrorMessage(error)
                result.onNext(ApiResponse.Error(errorMessage))
                Log.e("RemoteDataSource", errorMessage)
                Log.e("RemoteDataSource", error.toString())
            })

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    override fun postPosyanduNewChildData(
        token: String,
        createNewChildRequest: CreateNewChildRequest
    ): Flowable<ApiResponse<Child>> {
        val result = PublishSubject.create<ApiResponse<Child>>()
        val client = networkService.postPosyanduNewChildData(
            token = "Bearer $token",
            name = createNewChildRequest.name,
            surname = createNewChildRequest.panggilan,
            birthDate = createNewChildRequest.tanggal_lahir,
            height = createNewChildRequest.tinggi,
            weight = createNewChildRequest.berat,
            headCircumference = createNewChildRequest.lingkar_kepala,
            parentName = createNewChildRequest.nama_orang_tua.toString(),
            address = createNewChildRequest.alamat.toString(),
            gender = createNewChildRequest.gender,
            zScoreHeight = createNewChildRequest.z_score_tinggi ?: 0f,
            zScoreWeight = createNewChildRequest.z_score_berat ?: 0f,
            zScoreHeadCircumference = createNewChildRequest.z_score_lingkar_kepala ?: 0f
        )

        client.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val code = response.code
                val newChild = createNewChildRequest.toChild()
                result.onNext(if (code == 200) ApiResponse.Success(newChild) else ApiResponse.Empty)
            }, { error ->
                val errorMessage = Utility.getErrorMessage(error)
                result.onNext(ApiResponse.Error(errorMessage))
                Log.e("RemoteDataSource", errorMessage)
                Log.e("RemoteDataSource", error.toString())
            })

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    override fun getPosyanduStatistics(
        token: String,
        childId: Int
    ): Flowable<ApiResponse<List<ChildStatisticsResponse>>> {
        val result = PublishSubject.create<ApiResponse<List<ChildStatisticsResponse>>>()
        val client = networkService.getPosyanduStatistics("Bearer $token", childId)

        client.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val code = response.code
                result.onNext(if (code == 200 && response.data != null) ApiResponse.Success(response.data) else ApiResponse.Empty)
            }, { error ->
                val errorMessage = Utility.getErrorMessage(error)
                result.onNext(ApiResponse.Error(errorMessage))
                Log.e("RemoteDataSource", errorMessage)
                Log.e("RemoteDataSource", error.toString())
            })

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    override fun postPosyanduStatistics(
        token: String,
        updateChildDataRequest: UpdateChildDataRequest
    ): Flowable<ApiResponse<Child>> {
        val result = PublishSubject.create<ApiResponse<Child>>()
        val client = networkService.postPosyanduStatistics(
            token = "Bearer $token",
            childId = updateChildDataRequest.childId,
            height = updateChildDataRequest.height,
            weight = updateChildDataRequest.weight,
            headCircumference = updateChildDataRequest.headCircumference,
            zScoreHeight = updateChildDataRequest.heightZScore ?: 0f,
            zScoreWeight = updateChildDataRequest.weightZScore ?: 0f,
            zScoreHeadCircumference = updateChildDataRequest.headCircumferenceZScore ?: 0f
        )

        client.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val code = response.code
                val dataUpdate = updateChildDataRequest.toChild()
                result.onNext(if (code == 200) ApiResponse.Success(dataUpdate) else ApiResponse.Empty)
            }, { error ->
                val errorMessage = Utility.getErrorMessage(error)
                result.onNext(ApiResponse.Error(errorMessage))
                Log.e("RemoteDataSource", errorMessage)
                Log.e("RemoteDataSource", error.toString())
            })

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
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
                val errorMessage = Utility.getErrorMessage(error)
                result.onNext(ApiResponse.Error(errorMessage))
                Log.e("RemoteDataSource", errorMessage)
                Log.e("RemoteDataSource", error.toString())
            })
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    override fun getOrangtuaChildList(token: String): Flowable<ApiResponse<ListOfResponse<ChildResponse>>> {
        val result = PublishSubject.create<ApiResponse<ListOfResponse<ChildResponse>>>()
        val client = networkService.getOrangtuaChildList("Bearer $token")

        client.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val code = response.code
                result.onNext(if (code == 200 && response.data!!.data.isNotEmpty()) ApiResponse.Success(response.data) else ApiResponse.Empty)
            }, { error ->
                val errorMessage = Utility.getErrorMessage(error)
                result.onNext(ApiResponse.Error(errorMessage))
                Log.e("RemoteDataSource", errorMessage)
                Log.e("RemoteDataSource", error.toString())
            })

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    override fun postOrangtuaNewChildData(
        token: String,
        createNewChildRequest: CreateNewChildRequest
    ): Flowable<ApiResponse<Child>> {
        val result = PublishSubject.create<ApiResponse<Child>>()
        val client = networkService.postOrangtuaNewChildData(
            token = "Bearer $token",
            name = createNewChildRequest.name,
            surname = createNewChildRequest.panggilan,
            birthDate = createNewChildRequest.tanggal_lahir,
            height = createNewChildRequest.tinggi,
            weight = createNewChildRequest.berat,
            headCircumference = createNewChildRequest.lingkar_kepala,
            gender = createNewChildRequest.gender
        )

        client.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val code = response.code
                val newChild = createNewChildRequest.toChild()
                result.onNext(if (code == 200) ApiResponse.Success(newChild) else ApiResponse.Empty)
            }, { error ->
                val errorMessage = Utility.getErrorMessage(error)
                result.onNext(ApiResponse.Error(errorMessage))
                Log.e("RemoteDataSource", errorMessage)
                Log.e("RemoteDataSource", error.toString())
            })

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    override fun getOrangtuaStatistics(
        token: String,
        childId: Int
    ): Flowable<ApiResponse<List<ChildStatisticsResponse>>> {
        val result = PublishSubject.create<ApiResponse<List<ChildStatisticsResponse>>>()
        val client = networkService.getOrangtuaStatistics("Bearer $token", childId)

        client.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val code = response.code
                result.onNext(if (code == 200 && response.data != null) ApiResponse.Success(response.data) else ApiResponse.Empty)
            }, { error ->
                val errorMessage = Utility.getErrorMessage(error)
                result.onNext(ApiResponse.Error(errorMessage))
                Log.e("RemoteDataSource", errorMessage)
                Log.e("RemoteDataSource", error.toString())
            })

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    override fun postOrangtuaStatistics(
        token: String,
        updateChildDataRequest: UpdateChildDataRequest
    ): Flowable<ApiResponse<Child>> {
        val result = PublishSubject.create<ApiResponse<Child>>()
        val client = networkService.postOrangtuaStatistics(
            token = "Bearer $token",
            childId = updateChildDataRequest.childId,
            height = updateChildDataRequest.height,
            weight = updateChildDataRequest.weight,
            headCircumference = updateChildDataRequest.headCircumference
        )

        client.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val code = response.code
                val dataUpdate = updateChildDataRequest.toChild()
                result.onNext(if (code == 200) ApiResponse.Success(dataUpdate) else ApiResponse.Empty)
            }, { error ->
                val errorMessage = Utility.getErrorMessage(error)
                result.onNext(ApiResponse.Error(errorMessage))
                Log.e("RemoteDataSource", errorMessage)
                Log.e("RemoteDataSource", error.toString())
            })

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }
}