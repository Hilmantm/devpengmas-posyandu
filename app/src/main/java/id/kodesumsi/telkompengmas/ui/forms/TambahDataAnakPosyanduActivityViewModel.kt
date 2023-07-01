package id.kodesumsi.telkompengmas.ui.forms

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.kodesumsi.telkompengmas.data.source.local.LocalDataSource
import id.kodesumsi.telkompengmas.data.source.network.request.CreateNewChildRequest
import id.kodesumsi.telkompengmas.domain.model.User
import id.kodesumsi.telkompengmas.domain.usecase.UserUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class TambahDataAnakPosyanduActivityViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): ViewModel() {

    val genderChoose: MutableLiveData<String> = MutableLiveData()

    val currentUser: MutableLiveData<User?> = MutableLiveData()

//    val zScoreHeight: MutableLiveData<Float> = MutableLiveData()
//    val zScoreWeight: MutableLiveData<Float> = MutableLiveData()
//    val zScoreHeadCircumference: MutableLiveData<Float> = MutableLiveData()

    fun postNewChild(token: String, userRole: Int, createNewChildRequest: CreateNewChildRequest) = userUseCase.createNewChildData(token, userRole, createNewChildRequest).toLiveData()

    fun getUser() {
        userUseCase.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                currentUser.postValue(it.data)
            }, {
                Log.d("UserRepository", "error : ${it.message.toString()}")
                currentUser.postValue(null)
            })
    }

}