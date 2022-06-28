package id.kodesumsi.telkompengmas.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.kodesumsi.telkompengmas.data.source.local.LocalDataSource
import id.kodesumsi.telkompengmas.data.source.local.entity.UserEntity
import id.kodesumsi.telkompengmas.domain.model.User
import id.kodesumsi.telkompengmas.domain.usecase.UserUseCase
import id.kodesumsi.telkompengmas.utils.toUser
import id.kodesumsi.telkompengmas.utils.toUserEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class BerandaFragmentViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val localDataSource: LocalDataSource
): ViewModel() {

    val logoutResult: MutableLiveData<Boolean> = MutableLiveData()

    val currentUser: MutableLiveData<User> = MutableLiveData()

    fun getUser() {
        localDataSource.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                currentUser.postValue(it.toUser())
            }, {
                Log.d("UserRepository", "error : ${it.message.toString()}")
                currentUser.postValue(null)
            })
    }

    fun logout(user: User) {
        Log.d("BerandaViewModel", "user: ${user.toUserEntity()}")
        localDataSource.removeUser(user.toUserEntity())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                logoutResult.postValue(true)
            }, {
                Log.d("UserRepository", "error : ${it.message.toString()}")
                logoutResult.postValue(false)
            })
    }

}