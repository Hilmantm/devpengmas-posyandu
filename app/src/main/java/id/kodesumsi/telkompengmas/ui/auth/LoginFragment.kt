package id.kodesumsi.telkompengmas.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseFragment
import id.kodesumsi.telkompengmas.databinding.FragmentLoginBinding


class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override fun setupViewBinding(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding {
        return FragmentLoginBinding::inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.loginToRegister.setOnClickListener {
            view?.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

}