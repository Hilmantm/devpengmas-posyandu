package id.kodesumsi.telkompengmas.domain.usecase

import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.domain.model.Child
import id.kodesumsi.telkompengmas.domain.repository.ChildRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

interface ChildUseCase {

    fun getChildList(token: String): Flowable<Resource<List<Child>>>

}