package id.kodesumsi.telkompengmas.ui.auth

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.kodesumsi.telkompengmas.domain.usecase.UserUseCase
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): ViewModel() {

    fun login(username: String, password: String, userRole: Int) = LiveDataReactiveStreams.fromPublisher(userUseCase.login(username, password, userRole))

}