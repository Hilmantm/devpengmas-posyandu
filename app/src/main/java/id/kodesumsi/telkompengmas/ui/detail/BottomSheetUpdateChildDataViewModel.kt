package id.kodesumsi.telkompengmas.ui.detail

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.kodesumsi.telkompengmas.data.source.network.request.UpdateChildDataRequest
import id.kodesumsi.telkompengmas.domain.usecase.UserUseCase
import javax.inject.Inject

@HiltViewModel
class BottomSheetUpdateChildDataViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): ViewModel() {

    fun updateChildData(token: String, userRole: Int, updateChildDataRequest: UpdateChildDataRequest) = LiveDataReactiveStreams.fromPublisher(userUseCase.updateChildData(token, userRole, updateChildDataRequest))

}