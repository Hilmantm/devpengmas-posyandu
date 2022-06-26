package id.kodesumsi.telkompengmas.domain.interactor

import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.data.source.network.request.RegisterRequest
import id.kodesumsi.telkompengmas.domain.model.User
import id.kodesumsi.telkompengmas.domain.repository.UserRepository
import id.kodesumsi.telkompengmas.domain.usecase.UserUseCase
import id.kodesumsi.telkompengmas.utils.Constant.Companion.ROLE_PARENT
import id.kodesumsi.telkompengmas.utils.Constant.Companion.ROLE_POSYANDU
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val userRepository: UserRepository
): UserUseCase {

    override fun login(
        username: String,
        password: String,
        userRole: Int
    ): Flowable<Resource<User>> {
        return when(userRole) {
            ROLE_POSYANDU -> userRepository.posyanduLogin(username, password)
            ROLE_PARENT -> userRepository.orangtuaLogin(username, password)
            else -> userRepository.orangtuaLogin(username, password)
        }
    }

    override fun register(registerRequest: RegisterRequest): Flowable<Resource<User>> {
        return when(registerRequest.userRole) {
            ROLE_POSYANDU -> userRepository.posyanduRegister(registerRequest)
            ROLE_PARENT -> userRepository.orangtuaRegister(registerRequest)
            else -> userRepository.orangtuaRegister(registerRequest)
        }
    }

}