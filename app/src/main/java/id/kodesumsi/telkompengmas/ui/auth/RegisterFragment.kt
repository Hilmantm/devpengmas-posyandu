package id.kodesumsi.telkompengmas.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseFragment
import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.databinding.FragmentRegisterBinding

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    private val viewModel: RegisterFragmentViewModel by viewModels()

    override fun setupViewBinding(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding {
        return FragmentRegisterBinding::inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // set desa spinner data
        /* viewModel.getListDesa().observe(viewLifecycleOwner) { resourceListDesa ->
            when (resourceListDesa) {
                is Resource.Success -> {
                    val adapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayListOf(resourceListDesa.data))
                    binding.desaField.adapter = adapter
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "error: ${resourceListDesa.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }*/
        val listDesa = arrayListOf("Pasawahan", "Andir", "Bojongsoang")
        val adapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listDesa)
        binding.desaField.adapter = adapter
        binding.desaField.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(
                    requireContext(),
                    "Pilihan desa adalah ${p0?.getItemAtPosition(p2)}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        val listPosyandu = arrayListOf("Posyandu 1", "Posyandu 2", "Posyandu 3", "Posyandu 4")
        val posyanduAdapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listPosyandu)
        binding.posyanduField.adapter = posyanduAdapter
        binding.posyanduField.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(
                    requireContext(),
                    "Pilihan posyandu adalah ${p0?.getItemAtPosition(p2)}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }

}