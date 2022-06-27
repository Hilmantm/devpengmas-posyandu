package id.kodesumsi.telkompengmas.domain.repository

import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.domain.model.Desa
import id.kodesumsi.telkompengmas.domain.model.Posyandu
import io.reactivex.rxjava3.core.Flowable

interface PublicRepository {

    fun getDesaList(): Flowable<Resource<List<Desa>>>

    fun getPosyanduList(desaId: Int): Flowable<Resource<List<Posyandu>>>

}