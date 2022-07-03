package id.kodesumsi.telkompengmas.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import id.kodesumsi.telkompengmas.databinding.BottomSheetSaveDoctorBinding
import id.kodesumsi.telkompengmas.domain.model.Doctor

@AndroidEntryPoint
class BottomSheetAddDoctor: BottomSheetDialogFragment() {

    private var _binding: BottomSheetSaveDoctorBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BottomSheetAddDoctorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetSaveDoctorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.saveResult.postValue(null)
        binding.saveDoctor.setOnClickListener {
            val doctorName = binding.doctorNameField.text.toString()
            val doctorPhone = binding.doctorPhoneField.text.toString()
            if (doctorName.isNotEmpty() && doctorPhone.isNotEmpty()) {
                val newDoctor = Doctor(
                    name = doctorName,
                    phone = doctorPhone
                )
                viewModel.saveDoctor(newDoctor)
                viewModel.saveResult.observe(viewLifecycleOwner) {
                    if (it != null) {
                        dismiss()
                    }
                }
            }
        }
    }

}