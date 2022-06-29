package id.kodesumsi.telkompengmas.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseFragment
import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.data.source.network.response.AuthResponse
import id.kodesumsi.telkompengmas.data.source.network.response.TokenResponse
import id.kodesumsi.telkompengmas.data.source.network.response.UserResponse
import id.kodesumsi.telkompengmas.databinding.FragmentLoginBinding
import id.kodesumsi.telkompengmas.ui.main.MainActivity
import id.kodesumsi.telkompengmas.utils.Constant.Companion.ROLE_PARENT

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel: LoginFragmentViewModel by viewModels()

    override fun setupViewBinding(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding {
        return FragmentLoginBinding::inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val endpoint = arguments?.getInt(ChooseRoleFragment.ROLE)
        Log.d("LoginFragment", "onViewCreated: $endpoint")
        binding.loginToRegister.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnLogin.setOnClickListener {
            if (binding.loginEmail.text.isNotEmpty() && binding.loginPassword.text.isNotEmpty()) {
                viewModel.login(
                    email = binding.loginEmail.text.toString(),
                    password = binding.loginPassword.text.toString(),
                    userRole = endpoint ?: ROLE_PARENT
                ).observe(viewLifecycleOwner) {
                    when (it) {
                        is Resource.Success -> {
                            // intent to main activity
                            val toMain = Intent(requireContext(), MainActivity::class.java)
                            toMain.putExtra(ChooseRoleFragment.ROLE, endpoint)
                            startActivity(toMain)
                            activity?.finish()
                        }
                        is Resource.Error -> {
                            Toast.makeText(
                                requireContext(),
                                "Error login ${it.message}",
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

}