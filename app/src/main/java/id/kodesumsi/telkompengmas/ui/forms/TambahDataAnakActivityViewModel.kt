package id.kodesumsi.telkompengmas.ui.forms

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.kodesumsi.telkompengmas.data.source.local.LocalDataSource
import id.kodesumsi.telkompengmas.domain.usecase.UserUseCase
import javax.inject.Inject

@HiltViewModel
class TambahDataAnakActivityViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val localDataSource: LocalDataSource
): ViewModel() {

    val genderChoose: MutableLiveData<String> = MutableLiveData()

}