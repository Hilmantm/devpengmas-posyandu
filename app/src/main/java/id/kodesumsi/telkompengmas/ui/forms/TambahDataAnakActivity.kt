package id.kodesumsi.telkompengmas.ui.forms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseActivity
import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.data.source.network.request.CreateNewChildRequest
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
        viewModel.getUser()
        viewModel.currentUser.observe(this) { currentUser ->
            if (currentUser != null) {
                binding.btnAddChild.setOnClickListener {
                    val token = currentUser.token
                    val name = binding.childNameField.text.toString()
                    val nickname = binding.childNicknameField.text.toString()
                    val birthDate = binding.birthDateField.text.toString()
                    val weight = binding.weightBodyField.text.toString()
                    val height = binding.heightBodyField.text.toString()
                    val headCircumference = binding.headCircumferenceField.text.toString()
                    val gender = viewModel.genderChoose.value

                    if (name.isNotEmpty() && nickname.isNotEmpty() && birthDate.isNotEmpty() && weight.isNotEmpty() && height.isNotEmpty() && headCircumference.isNotEmpty()) {
                        val createNewChildRequest = CreateNewChildRequest(
                            name = name,
                            panggilan = nickname,
                            tanggal_lahir = birthDate,
                            berat = weight,
                            tinggi = height,
                            lingkar_kepala = headCircumference,
                            gender = gender!!
                        )
                        viewModel.postNewChild(token = token!!, userRole = currentUser.role!!, createNewChildRequest = createNewChildRequest).observe(this@TambahDataAnakActivity) {
                            when (it) {
                                is Resource.Success -> {
                                    Toast.makeText(this, "Success to add child", Toast.LENGTH_SHORT).show()
                                    onBackPressed()
                                }
                                is Resource.Error -> {
                                    Toast.makeText(this, "Failed to add child ${it.message.toString()}", Toast.LENGTH_SHORT).show()
                                }
                                is Resource.Loading -> {

                                }
                            }
                        }
                    }
                }

            }
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