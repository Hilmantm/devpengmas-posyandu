package id.kodesumsi.telkompengmas.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.data.source.network.request.UpdateChildDataRequest
import id.kodesumsi.telkompengmas.databinding.BottomSheetUpdateChildDataBinding

@AndroidEntryPoint
class BottomSheetUpdateChildData: BottomSheetDialogFragment() {

    private var _binding: BottomSheetUpdateChildDataBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BottomSheetUpdateChildDataViewModel by viewModels()

    private var childId: Int? = null
    private var token: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetUpdateChildDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnUpdateChild.setOnClickListener {
            val weight = binding.weightBodyField.text.toString()
            val height = binding.heightBodyField.text.toString()
            val headCircumference = binding.headCircumferenceField.text.toString()
            val zScoreHeight = binding.zScoreHeightField.text.toString()
            val zScoreWeight = binding.zScoreWeightField.text.toString()
            val zScoreHeadCircumference = binding.zScoreHeadCircumferenceField.text.toString()

            if (zScoreHeight.isNotEmpty() && zScoreWeight.isNotEmpty() && zScoreHeadCircumference.isNotEmpty() && weight.isNotEmpty() && height.isNotEmpty() && headCircumference.isNotEmpty() && token != null && childId != null) {
                val updateChildDataRequest = UpdateChildDataRequest(
                    childId = this.childId!!,
                    weight = weight.toInt(),
                    height = height.toInt(),
                    headCircumference = headCircumference.toInt(),
                    heightZScore = zScoreHeight.toFloat(),
                    weightZScore = zScoreWeight.toFloat(),
                    headCircumferenceZScore = zScoreHeadCircumference.toFloat()
                )
                viewModel.updateChildData(token!!, childId!!, updateChildDataRequest).observe(viewLifecycleOwner) {
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
        }
    }

    fun setChildId(id: Int) {
        this.childId = id
    }

    fun setToken(token: String) {
        this.token = token
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}