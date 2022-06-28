package id.kodesumsi.telkompengmas.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.databinding.BottomSheetUpdateChildDataBinding

class BottomSheetUpdateChildData: BottomSheetDialogFragment() {

    private var _binding: BottomSheetUpdateChildDataBinding? = null
    private val binding get() = _binding!!

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
            Toast.makeText(requireContext(), "Submit", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}