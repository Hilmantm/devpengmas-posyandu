package id.kodesumsi.telkompengmas.domain.interactor

import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.data.source.network.request.CreateNewChildRequest
import id.kodesumsi.telkompengmas.data.source.network.request.RegisterRequest
import id.kodesumsi.telkompengmas.domain.model.Child
import id.kodesumsi.telkompengmas.domain.model.User
import id.kodesumsi.telkompengmas.domain.repository.ParentRepository
import id.kodesumsi.telkompengmas.domain.repository.PosyanduRepository
import id.kodesumsi.telkompengmas.domain.usecase.UserUseCase
import id.kodesumsi.telkompengmas.utils.Constant.Companion.ROLE_PARENT
import id.kodesumsi.telkompengmas.utils.Constant.Companion.ROLE_POSYANDU
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val parentRepository: ParentRepository,
    private val posyanduRepository: PosyanduRepository
): UserUseCase {

    override fun login(
        username: String,
        password: String,
        userRole: Int
    ): Flowable<Resource<User>> {
        return when(userRole) {
            ROLE_POSYANDU -> posyanduRepository.posyanduLogin(username, password)
            ROLE_PARENT -> parentRepository.orangtuaLogin(username, password)
            else -> parentRepository.orangtuaLogin(username, password)
        }
    }

    override fun register(
        userRole: Int,
        registerRequest: RegisterRequest
    ): Flowable<Resource<User>> {
        return when(userRole) {
            ROLE_POSYANDU -> posyanduRepository.posyanduRegister(registerRequest)
            ROLE_PARENT -> parentRepository.orangtuaRegister(registerRequest)
            else -> parentRepository.orangtuaRegister(registerRequest)
        }
    }

    override fun getChildList(token: String, userRole: Int): Flowable<Resource<List<Child>>> {
        return when(userRole) {
            ROLE_POSYANDU -> posyanduRepository.getPosyanduChildList(token)
            ROLE_PARENT -> parentRepository.getOrangtuaChildList(token)
            else -> parentRepository.getOrangtuaChildList(token)
        }
    }

    override fun createNewChildData(
        token: String,
        userRole: Int,
        createNewChildRequest: CreateNewChildRequest
    ): Flowable<Resource<Child>> {
        return when(userRole) {
            ROLE_POSYANDU -> posyanduRepository.postPosyanduNewChildData(token, createNewChildRequest)
            ROLE_PARENT -> parentRepository.postOrangtuaNewChildData(token, createNewChildRequest)
            else -> parentRepository.postOrangtuaNewChildData(token, createNewChildRequest)
        }
    }

}