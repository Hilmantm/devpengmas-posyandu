package id.kodesumsi.telkompengmas.domain.repository

import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.domain.model.Desa
import id.kodesumsi.telkompengmas.domain.model.Posyandu
import id.kodesumsi.telkompengmas.domain.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface PublicRepository {

    fun getDesaList(): Flowable<Resource<List<Desa>>>

    fun getPosyanduList(desaId: Int): Flowable<Resource<List<Posyandu>>>

    fun getUser(): Single<Resource<User>>

    fun logout(user: User): Completable

}