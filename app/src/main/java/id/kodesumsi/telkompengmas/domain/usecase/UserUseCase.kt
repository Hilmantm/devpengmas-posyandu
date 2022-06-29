package id.kodesumsi.telkompengmas.domain.usecase

import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.data.source.network.request.CreateNewChildRequest
import id.kodesumsi.telkompengmas.data.source.network.request.RegisterRequest
import id.kodesumsi.telkompengmas.data.source.network.request.UpdateChildDataRequest
import id.kodesumsi.telkompengmas.domain.model.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface UserUseCase {

    fun login(email: String, password: String, userRole: Int): Flowable<Resource<User>>

    fun register(userRole: Int, registerRequest: RegisterRequest): Flowable<Resource<User>>

    fun getChildList(token: String, userRole: Int): Flowable<Resource<List<Child>>>

    fun createNewChildData(token: String, userRole: Int, createNewChildRequest: CreateNewChildRequest): Flowable<Resource<Child>>

    fun getChildStatistics(token: String, userRole: Int, childId: Int): Flowable<Resource<List<ChildStatistics>>>

    fun updateChildData(token: String, userRole: Int, updateChildDataRequest: UpdateChildDataRequest): Flowable<Resource<Child>>

    // =============== public repo ===============
    fun getListDesa(): Flowable<Resource<List<Desa>>>

    fun getListPosyandu(desaId: Int): Flowable<Resource<List<Posyandu>>>

    fun getUser(): Single<Resource<User>>

    fun logout(user: User): Completable

}