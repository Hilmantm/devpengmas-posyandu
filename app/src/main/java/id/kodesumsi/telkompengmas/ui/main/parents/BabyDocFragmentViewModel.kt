package id.kodesumsi.telkompengmas.ui.main.parents

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.kodesumsi.telkompengmas.data.source.dummy.DummyData
import id.kodesumsi.telkompengmas.data.source.local.LocalDataSource
import id.kodesumsi.telkompengmas.domain.model.Doctor
import id.kodesumsi.telkompengmas.domain.usecase.UserUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class BabyDocFragmentViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): ViewModel() {

    val doctors: MutableLiveData<List<Doctor>> = MutableLiveData()

    fun getDoctors() {
        userUseCase.getDoctors()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                doctors.postValue(it.data)
            }, {
                Log.d("ChatDocFragmentViewModel", "error : ${it.message.toString()}")
                doctors.postValue(null)
            })
    }

}