package id.kodesumsi.telkompengmas.ui.auth

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.domain.model.dummy.User
import id.kodesumsi.telkompengmas.domain.usecase.UserUseCase
import id.kodesumsi.telkompengmas.utils.Constant.Companion.ROLE_PARENT
import id.kodesumsi.telkompengmas.utils.Constant.Companion.ROLE_POSYANDU
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): ViewModel() {

    fun login(username: String, password: String, userRole: Int) = LiveDataReactiveStreams.fromPublisher(userUseCase.login(username, password, userRole))

}