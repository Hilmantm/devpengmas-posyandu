package id.kodesumsi.telkompengmas.ui.auth

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.kodesumsi.telkompengmas.data.source.network.request.RegisterRequest
import id.kodesumsi.telkompengmas.domain.usecase.UserUseCase

@HiltViewModel
class RegisterFragmentViewModel(
    private val userUseCase: UserUseCase
): ViewModel() {

    fun register(registerRequest: RegisterRequest) = LiveDataReactiveStreams.fromPublisher(userUseCase.register(registerRequest))

}