package id.kodesumsi.telkompengmas.domain.usecase

import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.data.source.network.request.CreateNewChildRequest
import id.kodesumsi.telkompengmas.data.source.network.request.RegisterRequest
import id.kodesumsi.telkompengmas.domain.model.Child
import id.kodesumsi.telkompengmas.domain.model.User
import io.reactivex.rxjava3.core.Flowable

interface UserUseCase {

    fun login(username: String, password: String, userRole: Int): Flowable<Resource<User>>

    fun register(userRole: Int, registerRequest: RegisterRequest): Flowable<Resource<User>>

    fun getChildList(token: String, userRole: Int): Flowable<Resource<List<Child>>>

    fun createNewChildData(token: String, userRole: Int, createNewChildRequest: CreateNewChildRequest): Flowable<Resource<Child>>

}