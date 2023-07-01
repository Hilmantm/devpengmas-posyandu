package id.kodesumsi.telkompengmas.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.data.source.network.request.UpdateChildDataRequest
import id.kodesumsi.telkompengmas.databinding.BottomSheetUpdateChildDataBinding
import id.kodesumsi.telkompengmas.utils.Constant
import id.kodesumsi.telkompengmas.utils.Constant.Companion.ROLE_PARENT
import id.kodesumsi.telkompengmas.utils.Constant.Companion.ROLE_POSYANDU
import id.kodesumsi.telkompengmas.utils.Utility

@AndroidEntryPoint
class BottomSheetUpdateChildData: BottomSheetDialogFragment() {

    private var _binding: BottomSheetUpdateChildDataBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BottomSheetUpdateChildDataViewModel by viewModels()

    private var childId: Int? = null
    private var token: String? = null
    private var userRole: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetUpdateChildDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        if (this.userRole == ROLE_PARENT) {
//            binding.zScoreHeightTitle.visibility = View.GONE
//            binding.zScoreHeightField.visibility = View.GONE
//            binding.zScoreWeightTitle.visibility = View.GONE
//            binding.zScoreWeightField.visibility = View.GONE
//            binding.zScoreHeadCircumferenceTitle.visibility = View.GONE
//            binding.zScoreHeadCircumferenceField.visibility = View.GONE
//        } else {
//            // set adapter for zscore spinner
//            val zScoreValueHeight = Utility.getSpinnerValue(Constant.HEIGHT)
//            val zScoreValueWeight = Utility.getSpinnerValue(Constant.WEIGHT)
//            val zScoreValueHeadCircumference = Utility.getSpinnerValue(Constant.HEAD_CIRCUMFERENCE)
//
//            ArrayAdapter.createFromResource(requireContext(), R.array.z_score_height_select, android.R.layout.simple_spinner_item).also { arrayAdapter ->
//                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                binding.zScoreHeightField.adapter = arrayAdapter
//                binding.zScoreHeightField.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                        viewModel.zScoreHeight.postValue(zScoreValueHeight[p2])
//                    }
//
//                    override fun onNothingSelected(p0: AdapterView<*>?) {
//                        viewModel.zScoreHeight.postValue(zScoreValueHeight[0])
//                    }
//
//                }
//            }
//
//            ArrayAdapter.createFromResource(requireContext(), R.array.z_score_weight_select, android.R.layout.simple_spinner_item).also { arrayAdapter ->
//                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                binding.zScoreWeightField.adapter = arrayAdapter
//                binding.zScoreWeightField.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                        viewModel.zScoreWeight.postValue(zScoreValueWeight[p2])
//                    }
//
//                    override fun onNothingSelected(p0: AdapterView<*>?) {
//                        viewModel.zScoreWeight.postValue(zScoreValueWeight[0])
//                    }
//
//                }
//            }
//
//            ArrayAdapter.createFromResource(requireContext(), R.array.z_score_head_circumference_select, android.R.layout.simple_spinner_item).also { arrayAdapter ->
//                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                binding.zScoreHeadCircumferenceField.adapter = arrayAdapter
//                binding.zScoreHeadCircumferenceField.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                        viewModel.zScoreHeadCircumference.postValue(zScoreValueHeadCircumference[p2])
//                    }
//
//                    override fun onNothingSelected(p0: AdapterView<*>?) {
//                        viewModel.zScoreHeadCircumference.postValue(zScoreValueHeadCircumference[0])
//                    }
//
//                }
//            }
//        }

        binding.btnUpdateChild.setOnClickListener {
            val weight = binding.weightBodyField.text.toString()
            val height = binding.heightBodyField.text.toString()
            val headCircumference = binding.headCircumferenceField.text.toString()

            Log.d("BottomSheetUpdateChildData", "userRole = ${this.userRole}")

            when(this.userRole) {
                ROLE_PARENT -> {
                    if (this.userRole == ROLE_PARENT && weight.isNotEmpty() && height.isNotEmpty() && headCircumference.isNotEmpty() && token != null && childId != null) {
                        val updateChildDataRequest = UpdateChildDataRequest(
                            childId = this.childId!!,
                            weight = weight.toInt(),
                            height = height.toInt(),
                            headCircumference = headCircumference.toInt()
                        )
                        updateDataChild(updateChildDataRequest)
                    }
                }
                ROLE_POSYANDU -> {
//                    val zScoreHeight = viewModel.zScoreHeight.value
//                    val zScoreWeight = viewModel.zScoreWeight.value
//                    val zScoreHeadCircumference = viewModel.zScoreHeadCircumference.value
                    if (this.userRole == ROLE_POSYANDU && weight.isNotEmpty() && height.isNotEmpty() && headCircumference.isNotEmpty() && token != null && childId != null) {
                        val updateChildDataRequest = UpdateChildDataRequest(
                            childId = this.childId!!,
                            weight = weight.toInt(),
                            height = height.toInt(),
                            headCircumference = headCircumference.toInt(),
//                            heightZScore = zScoreHeight,
//                            weightZScore = zScoreWeight,
//                            headCircumferenceZScore = zScoreHeadCircumference
                        )
                        updateDataChild(updateChildDataRequest)
                    }
                }
            }

        }
    }

    private fun updateDataChild(updateChildDataRequest: UpdateChildDataRequest) {
        viewModel.updateChildData(token!!, this.userRole!!, updateChildDataRequest).observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    Toast.makeText(
                        requireContext(),
                        "Success update data",
                        Toast.LENGTH_SHORT
                    ).show()
                    dismiss()
                    activity?.finish()
                    activity?.startActivity(activity?.intent)
                }
                is Resource.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "Error ${it.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Loading -> {

                }
            }
        }
    }

    fun setChildId(id: Int) {
        this.childId = id
    }

    fun setToken(token: String) {
        this.token = token
    }

    fun setUserRole(role: Int) {
        this.userRole = role
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}