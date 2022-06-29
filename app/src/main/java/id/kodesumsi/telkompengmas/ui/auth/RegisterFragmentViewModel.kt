package id.kodesumsi.telkompengmas.ui.auth

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.kodesumsi.telkompengmas.data.source.network.request.RegisterRequest
import id.kodesumsi.telkompengmas.domain.usecase.UserUseCase
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): ViewModel() {

    val idDesa: MutableLiveData<Int> = MutableLiveData()
    val idPosyandu: MutableLiveData<Int> = MutableLiveData()

    fun register(userRole: Int, registerRequest: RegisterRequest) = LiveDataReactiveStreams.fromPublisher(userUseCase.register(userRole, registerRequest))

    fun getListDesa() = LiveDataReactiveStreams.fromPublisher(userUseCase.getListDesa())

    fun getListPosyandu(desaId: Int) = LiveDataReactiveStreams.fromPublisher(userUseCase.getListPosyandu(desaId))
}