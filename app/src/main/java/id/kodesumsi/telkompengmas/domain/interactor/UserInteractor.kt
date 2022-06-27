package id.kodesumsi.telkompengmas.domain.interactor

import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.data.source.network.request.CreateNewChildRequest
import id.kodesumsi.telkompengmas.data.source.network.request.RegisterRequest
import id.kodesumsi.telkompengmas.data.source.network.request.UpdateChildDataRequest
import id.kodesumsi.telkompengmas.domain.model.*
import id.kodesumsi.telkompengmas.domain.repository.ParentRepository
import id.kodesumsi.telkompengmas.domain.repository.PosyanduRepository
import id.kodesumsi.telkompengmas.domain.repository.PublicRepository
import id.kodesumsi.telkompengmas.domain.usecase.UserUseCase
import id.kodesumsi.telkompengmas.utils.Constant.Companion.ROLE_PARENT
import id.kodesumsi.telkompengmas.utils.Constant.Companion.ROLE_POSYANDU
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val parentRepository: ParentRepository,
    private val posyanduRepository: PosyanduRepository,
    private val publicRepository: PublicRepository
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

    override fun getChildStatistics(
        token: String,
        userRole: Int,
        childId: Int
    ): Flowable<Resource<List<ChildStatistics>>> {
        return when(userRole) {
            ROLE_POSYANDU -> posyanduRepository.getPosyanduStatistics(token, childId)
            ROLE_PARENT -> parentRepository.getOrangtuaStatistics(token, childId)
            else -> parentRepository.getOrangtuaStatistics(token, childId)
        }
    }

    override fun updateChildData(
        token: String,
        userRole: Int,
        updateChildDataRequest: UpdateChildDataRequest
    ): Flowable<Resource<Child>> {
        return when(userRole) {
            ROLE_POSYANDU -> posyanduRepository.postPosyanduStatistics(token, updateChildDataRequest)
            ROLE_PARENT -> parentRepository.postOrangtuaStatistics(token, updateChildDataRequest)
            else -> parentRepository.postOrangtuaStatistics(token, updateChildDataRequest)
        }
    }

    override fun getListDesa(): Flowable<Resource<List<Desa>>> {
        return publicRepository.getDesaList()
    }

    override fun getListPosyandu(desaId: Int): Flowable<Resource<List<Posyandu>>> {
        return publicRepository.getPosyanduList(desaId)
    }

}