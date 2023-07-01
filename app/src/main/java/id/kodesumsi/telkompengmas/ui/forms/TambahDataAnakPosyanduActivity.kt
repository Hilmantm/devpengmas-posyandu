package id.kodesumsi.telkompengmas.ui.forms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseActivity
import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.data.source.network.request.CreateNewChildRequest
import id.kodesumsi.telkompengmas.databinding.ActivityTambahDataAnakPosyanduBinding
import id.kodesumsi.telkompengmas.utils.Constant
import id.kodesumsi.telkompengmas.utils.Constant.Companion.HEAD_CIRCUMFERENCE
import id.kodesumsi.telkompengmas.utils.Constant.Companion.HEIGHT
import id.kodesumsi.telkompengmas.utils.Constant.Companion.WEIGHT
import id.kodesumsi.telkompengmas.utils.Utility
import id.kodesumsi.telkompengmas.utils.Utility.getSpinnerValue

@AndroidEntryPoint
class TambahDataAnakPosyanduActivity : BaseActivity<ActivityTambahDataAnakPosyanduBinding>() {

    private val viewModel: TambahDataAnakPosyanduActivityViewModel by viewModels()

    override fun setupViewBinding(): (LayoutInflater) -> ActivityTambahDataAnakPosyanduBinding {
        return ActivityTambahDataAnakPosyanduBinding::inflate
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

        // set adapter for zscore spinner
//        val zScoreValueHeight = getSpinnerValue(HEIGHT)
//        val zScoreValueWeight = getSpinnerValue(WEIGHT)
//        val zScoreValueHeadCircumference = getSpinnerValue(HEAD_CIRCUMFERENCE)

//        ArrayAdapter.createFromResource(this, R.array.z_score_height_select, android.R.layout.simple_spinner_item).also { arrayAdapter ->
//            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            binding.zScoreHeightField.adapter = arrayAdapter
//            binding.zScoreHeightField.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                    viewModel.zScoreHeight.postValue(zScoreValueHeight[p2])
//                }
//
//                override fun onNothingSelected(p0: AdapterView<*>?) {
//                    viewModel.zScoreHeight.postValue(zScoreValueHeight[0])
//                }
//
//            }
//        }
//
//        ArrayAdapter.createFromResource(this, R.array.z_score_weight_select, android.R.layout.simple_spinner_item).also { arrayAdapter ->
//            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            binding.zScoreWeightField.adapter = arrayAdapter
//            binding.zScoreWeightField.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                    viewModel.zScoreWeight.postValue(zScoreValueWeight[p2])
//                }
//
//                override fun onNothingSelected(p0: AdapterView<*>?) {
//                    viewModel.zScoreWeight.postValue(zScoreValueWeight[0])
//                }
//
//            }
//        }
//
//        ArrayAdapter.createFromResource(this, R.array.z_score_head_circumference_select, android.R.layout.simple_spinner_item).also { arrayAdapter ->
//            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            binding.zScoreHeadCircumferenceField.adapter = arrayAdapter
//            binding.zScoreHeadCircumferenceField.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                    viewModel.zScoreHeadCircumference.postValue(zScoreValueHeadCircumference[p2])
//                }
//
//                override fun onNothingSelected(p0: AdapterView<*>?) {
//                    viewModel.zScoreHeadCircumference.postValue(zScoreValueHeadCircumference[0])
//                }
//
//            }
//        }

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
                    val parentName = binding.parentField.text.toString()
                    val address = binding.addressField.text.toString()
//                    val zScoreHeight = viewModel.zScoreHeight.value
//                    val zScoreWeight = viewModel.zScoreWeight.value
//                    val zScoreHeadCircumference = viewModel.zScoreHeadCircumference.value

                    if (address.isNotEmpty() && parentName.isNotEmpty() && name.isNotEmpty() && nickname.isNotEmpty() && birthDate.isNotEmpty() && weight.isNotEmpty() && height.isNotEmpty() && headCircumference.isNotEmpty()) {
                        val createNewChildRequest = CreateNewChildRequest(
                            name = name,
                            panggilan = nickname,
                            tanggal_lahir = birthDate,
                            berat = weight,
                            tinggi = height,
                            lingkar_kepala = headCircumference,
                            gender = gender!!,
                            nama_orang_tua = parentName,
                            alamat = address,
//                            z_score_tinggi = zScoreHeight,
//                            z_score_berat = zScoreWeight,
//                            z_score_lingkar_kepala = zScoreHeadCircumference
                        )
                        viewModel.postNewChild(token = token!!, userRole = currentUser.role!!, createNewChildRequest = createNewChildRequest).observe(this@TambahDataAnakPosyanduActivity) {
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