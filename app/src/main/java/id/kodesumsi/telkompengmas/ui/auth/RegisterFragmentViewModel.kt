package id.kodesumsi.telkompengmas.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
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
    val seePassword: MutableLiveData<Boolean> = MutableLiveData()

    fun register(userRole: Int, registerRequest: RegisterRequest) = userUseCase.register(userRole, registerRequest).toLiveData()

    fun getListDesa() = userUseCase.getListDesa().toLiveData()

    fun getListPosyandu(desaId: Int) = userUseCase.getListPosyandu(desaId).toLiveData()
}