package id.kodesumsi.telkompengmas.ui.forms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseActivity
import id.kodesumsi.telkompengmas.databinding.ActivityTambahDataAnakBinding
import id.kodesumsi.telkompengmas.utils.Constant

@AndroidEntryPoint
class TambahDataAnakActivity : BaseActivity<ActivityTambahDataAnakBinding>() {

    private val viewModel: TambahDataAnakActivityViewModel by viewModels()

    override fun setupViewBinding(): (LayoutInflater) -> ActivityTambahDataAnakBinding {
        return ActivityTambahDataAnakBinding::inflate
    }

    override fun setupViewInstance(savedInstanceState: Bundle?) {
        viewModel.genderChoose.postValue(Constant.MAN)
        viewModel.genderChoose.observe(this) { gender ->
            if (gender == Constant.MAN) {
                boyChoosen()
            } else if (gender == Constant.WOMAN) {
                girlChoosen()
            }
        }
        binding.imageBoy.setOnClickListener {
            viewModel.genderChoose.postValue(Constant.MAN)
        }
        binding.imageGirl.setOnClickListener {
            viewModel.genderChoose.postValue(Constant.WOMAN)
        }
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun boyChoosen() {
        Glide.with(this).load(R.drawable.boy_choose).into(binding.imageBoy)
        Glide.with(this).load(R.drawable.girl_default).into(binding.imageGirl)
    }

    private fun girlChoosen() {
        Glide.with(this).load(R.drawable.boy_default).into(binding.imageBoy)
        Glide.with(this).load(R.drawable.girl_choose).into(binding.imageGirl)
    }

}