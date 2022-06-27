package id.kodesumsi.telkompengmas.domain.repository

import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.data.source.network.request.CreateNewChildRequest
import id.kodesumsi.telkompengmas.data.source.network.request.RegisterRequest
import id.kodesumsi.telkompengmas.data.source.network.request.UpdateChildDataRequest
import id.kodesumsi.telkompengmas.domain.model.Child
import id.kodesumsi.telkompengmas.domain.model.ChildStatistics
import id.kodesumsi.telkompengmas.domain.model.User
import io.reactivex.rxjava3.core.Flowable

interface PosyanduRepository {

    fun posyanduLogin(username: String, password: String): Flowable<Resource<User>>

    fun posyanduRegister(registerRequest: RegisterRequest): Flowable<Resource<User>>

    fun getPosyanduChildList(token: String): Flowable<Resource<List<Child>>>

    fun postPosyanduNewChildData(token: String, createNewChildRequest: CreateNewChildRequest): Flowable<Resource<Child>>

    fun getPosyanduStatistics(token: String, childId: Int): Flowable<Resource<List<ChildStatistics>>>

    fun postPosyanduStatistics(token: String, updateChildDataRequest: UpdateChildDataRequest): Flowable<Resource<Child>>

}