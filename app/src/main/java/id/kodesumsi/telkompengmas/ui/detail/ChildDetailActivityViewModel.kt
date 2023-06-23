package id.kodesumsi.telkompengmas.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.github.mikephil.charting.data.Entry
import dagger.hilt.android.lifecycle.HiltViewModel
import id.kodesumsi.telkompengmas.domain.model.User
import id.kodesumsi.telkompengmas.domain.usecase.UserUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ChildDetailActivityViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): ViewModel() {

    val currentUser: MutableLiveData<User?> = MutableLiveData()
    val weightEntries: MutableLiveData<List<Entry>> = MutableLiveData()
    val heightEntries: MutableLiveData<List<Entry>> = MutableLiveData()
    val headCircumferenceEntries: MutableLiveData<List<Entry>> = MutableLiveData()

    fun getChildStatistics(token: String, userRole: Int, childId: Int) = userUseCase.getChildStatistics(token = token, userRole = userRole, childId = childId).toLiveData()

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