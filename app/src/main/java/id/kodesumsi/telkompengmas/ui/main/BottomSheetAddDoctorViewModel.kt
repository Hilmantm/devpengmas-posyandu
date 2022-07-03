package id.kodesumsi.telkompengmas.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.kodesumsi.telkompengmas.domain.model.Doctor
import id.kodesumsi.telkompengmas.domain.usecase.UserUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class BottomSheetAddDoctorViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): ViewModel() {

    val saveResult: MutableLiveData<Doctor> = MutableLiveData()

    fun saveDoctor(doctor: Doctor) {
        userUseCase.saveDoctor(doctor)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                saveResult.postValue(doctor)
            }, {
                Log.d("AddDoctorViewModel", "error : ${it.message.toString()}")
            })
    }

}