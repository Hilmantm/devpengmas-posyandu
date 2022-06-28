package id.kodesumsi.telkompengmas.ui.splashscreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.kodesumsi.telkompengmas.data.source.local.LocalDataSource
import id.kodesumsi.telkompengmas.domain.model.User
import id.kodesumsi.telkompengmas.utils.toUser
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SplashscreenActivityViewModel @Inject constructor(
    private val localDataSource: LocalDataSource
): ViewModel() {

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

}