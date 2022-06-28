package id.kodesumsi.telkompengmas.ui.main.parents

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.kodesumsi.telkompengmas.data.source.dummy.DummyData
import id.kodesumsi.telkompengmas.data.source.local.LocalDataSource
import id.kodesumsi.telkompengmas.domain.model.Article
import id.kodesumsi.telkompengmas.domain.usecase.UserUseCase
import javax.inject.Inject

@HiltViewModel
class ArtikelFragmentViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val localDataSource: LocalDataSource
): ViewModel() {

    val article: MutableLiveData<List<Article>> = MutableLiveData()

    fun getArticles() {
        article.postValue(DummyData.getArticles())
    }

}