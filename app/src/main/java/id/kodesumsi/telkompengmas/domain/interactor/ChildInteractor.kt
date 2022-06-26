package id.kodesumsi.telkompengmas.domain.interactor

import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.domain.model.Child
import id.kodesumsi.telkompengmas.domain.repository.ChildRepository
import id.kodesumsi.telkompengmas.domain.usecase.ChildUseCase
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class ChildInteractor  @Inject constructor(
    private val childRepository: ChildRepository
): ChildUseCase {
    override fun getChildList(token: String): Flowable<Resource<List<Child>>> {
        return childRepository.getChildList(token)
    }

}