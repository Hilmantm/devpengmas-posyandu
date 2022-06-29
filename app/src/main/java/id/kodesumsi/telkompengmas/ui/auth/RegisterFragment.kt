package id.kodesumsi.telkompengmas.ui.auth

import android.content.Intent
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
import id.kodesumsi.telkompengmas.data.source.network.request.RegisterRequest
import id.kodesumsi.telkompengmas.databinding.FragmentRegisterBinding
import id.kodesumsi.telkompengmas.domain.model.Desa
import id.kodesumsi.telkompengmas.domain.model.Posyandu
import id.kodesumsi.telkompengmas.domain.model.PosyanduSpinner
import id.kodesumsi.telkompengmas.ui.main.MainActivity
import id.kodesumsi.telkompengmas.utils.Constant.Companion.ROLE_PARENT

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    private val viewModel: RegisterFragmentViewModel by viewModels()

    override fun setupViewBinding(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding {
        return FragmentRegisterBinding::inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getListDesa().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    val desaList: ArrayList<Desa> = getListOfDesa(it.data ?: listOf())
                    val adapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, desaList)
                    binding.desaField.adapter = adapter
                    binding.desaField.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                            val desa: Desa = p0?.getItemAtPosition(p2) as Desa
                            viewModel.idDesa.postValue(desa.id)
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {

                        }
                    }
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


        viewModel.idDesa.observe(viewLifecycleOwner) { desaId ->
            viewModel.getListPosyandu(desaId).observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Success -> {
                        val posyanduList: ArrayList<PosyanduSpinner> = getListOfPosyandu(it.data ?: listOf())
                        val adapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, posyanduList)
                        binding.posyanduField.adapter = adapter
                        binding.posyanduField.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                                val posyandu: PosyanduSpinner = p0?.getItemAtPosition(p2) as PosyanduSpinner
                                viewModel.idPosyandu.postValue(posyandu.id)
                            }

                            override fun onNothingSelected(p0: AdapterView<*>?) {

                            }
                        }
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

        binding.btnRegister.setOnClickListener {
            val idPosyandu = viewModel.idPosyandu.value
            val idDesa = viewModel.idDesa.value
            val namaLengkap = binding.fullnameField.text.toString()
            val email = binding.emailField.text.toString()
            val password = binding.passwordField.text.toString()
            val userRole = arguments?.getInt(ChooseRoleFragment.ROLE) ?: ROLE_PARENT

            if (idPosyandu != null && idDesa != null && namaLengkap.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                val registerRequest = RegisterRequest(
                    name = namaLengkap,
                    email = email,
                    password = password,
                    idDesa = idDesa,
                    idPosyandu = idPosyandu
                )
                viewModel.register(userRole = userRole, registerRequest).observe(viewLifecycleOwner) {
                    when (it) {
                        is Resource.Success -> {
                            Toast.makeText(requireContext(), "Register Success", Toast.LENGTH_SHORT).show()
                            activity?.onBackPressed()
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

    private fun getListOfDesa(listDesa: List<Desa>): ArrayList<Desa> {
        val result: ArrayList<Desa> = arrayListOf()

        for (desa in listDesa) {
            result.add(desa)
        }

        return result
    }

    private fun getListOfPosyandu(listPosyandu: List<Posyandu>): ArrayList<PosyanduSpinner> {
        val result: ArrayList<PosyanduSpinner> = arrayListOf()

        for (posyandu in listPosyandu) {
            result.add(PosyanduSpinner(id = posyandu.id, name = posyandu.name ?: ""))
        }

        return result
    }

}