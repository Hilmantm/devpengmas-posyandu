package id.kodesumsi.telkompengmas.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.kodesumsi.telkompengmas.data.source.network.request.UpdateChildDataRequest
import id.kodesumsi.telkompengmas.domain.usecase.UserUseCase
import javax.inject.Inject

@HiltViewModel
class BottomSheetUpdateChildDataViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): ViewModel() {

    val zScoreHeight: MutableLiveData<Float> = MutableLiveData()
    val zScoreWeight: MutableLiveData<Float> = MutableLiveData()
    val zScoreHeadCircumference: MutableLiveData<Float> = MutableLiveData()

    fun updateChildData(token: String, userRole: Int, updateChildDataRequest: UpdateChildDataRequest) = userUseCase.updateChildData(token, userRole, updateChildDataRequest).toLiveData()

}