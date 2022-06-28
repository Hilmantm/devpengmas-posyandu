package id.kodesumsi.telkompengmas.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseFragment
import id.kodesumsi.telkompengmas.databinding.FragmentLoginBinding
import id.kodesumsi.telkompengmas.ui.main.MainActivity

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override fun setupViewBinding(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding {
        return FragmentLoginBinding::inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val endpoint = arguments?.getInt(ChooseRoleFragment.ROLE)
        Log.d("LoginFragment", "onViewCreated: $endpoint")
        binding.loginToRegister.setOnClickListener {
            view?.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnLogin.setOnClickListener {
            // get current role
            val role = arguments?.getInt(ChooseRoleFragment.ROLE) ?: 1

            // save user data with token

            // intent to main activity
            val toMain = Intent(requireContext(), MainActivity::class.java)
            toMain.putExtra(ChooseRoleFragment.ROLE, role)
            startActivity(toMain)
            activity?.finish()
        }
    }

}