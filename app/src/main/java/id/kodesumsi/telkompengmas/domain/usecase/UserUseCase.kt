package id.kodesumsi.telkompengmas.domain.usecase

import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.domain.model.dummy.User
import io.reactivex.rxjava3.core.Flowable

interface UserUseCase {

    fun login(username: String, password: String, userRole: Int): Flowable<Resource<User>>

}