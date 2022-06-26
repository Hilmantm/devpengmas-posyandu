package id.kodesumsi.telkompengmas.domain.repository

import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.domain.model.Child
import io.reactivex.rxjava3.core.Flowable

interface ChildRepository {

    fun getChildList(token: String): Flowable<Resource<List<Child>>>

}