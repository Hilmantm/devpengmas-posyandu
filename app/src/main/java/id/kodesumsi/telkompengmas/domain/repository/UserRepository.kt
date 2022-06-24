package id.kodesumsi.telkompengmas.domain.repository

import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.data.source.network.request.RegisterRequest
import id.kodesumsi.telkompengmas.domain.model.dummy.User
import io.reactivex.rxjava3.core.Flowable

interface UserRepository {

    fun posyanduLogin(username: String, password: String): Flowable<Resource<User>>

    fun orangtuaLogin(username: String, password: String): Flowable<Resource<User>>

    fun posyanduRegister(registerRequest: RegisterRequest): Flowable<Resource<User>>

    fun orangtuaRegister(registerRequest: RegisterRequest): Flowable<Resource<User>>

}