package id.kodesumsi.telkompengmas.ui.auth

import android.util.Log
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.data.source.local.LocalDataSource
import id.kodesumsi.telkompengmas.data.source.network.response.AuthResponse
import id.kodesumsi.telkompengmas.domain.model.User
import id.kodesumsi.telkompengmas.domain.usecase.UserUseCase
import id.kodesumsi.telkompengmas.utils.toUser
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val localDataSource: LocalDataSource
): ViewModel() {

    // fun login(username: String, password: String, userRole: Int) = LiveDataReactiveStreams.fromPublisher(userUseCase.login(username, password, userRole))

    val loginResult: MutableLiveData<Boolean> = MutableLiveData()

    fun login(userRole: Int, authResponse: AuthResponse) {
        localDataSource.saveUser(userRole, authResponse)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                loginResult.postValue(true)
            }, {
                Log.d("UserRepository", "error : ${it.message.toString()}")
                loginResult.postValue(false)
            })
    }

}