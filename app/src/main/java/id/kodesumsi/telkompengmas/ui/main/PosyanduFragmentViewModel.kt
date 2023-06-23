package id.kodesumsi.telkompengmas.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.kodesumsi.telkompengmas.data.source.dummy.DummyData
import id.kodesumsi.telkompengmas.data.source.local.LocalDataSource
import id.kodesumsi.telkompengmas.domain.model.Posyandu
import id.kodesumsi.telkompengmas.domain.model.User
import id.kodesumsi.telkompengmas.domain.usecase.UserUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PosyanduFragmentViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): ViewModel() {

    val posyandu: MutableLiveData<Posyandu> = MutableLiveData()
    val posyanduId: MutableLiveData<Int> = MutableLiveData()

    fun getPosyandus(desaId: Int) = userUseCase.getListPosyandu(desaId).toLiveData()

    val currentUser: MutableLiveData<User> = MutableLiveData()

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