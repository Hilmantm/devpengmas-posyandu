package id.kodesumsi.telkompengmas.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseFragment
import id.kodesumsi.telkompengmas.databinding.FragmentChooseRoleBinding

class ChooseRoleFragment : BaseFragment<FragmentChooseRoleBinding>() {
    override fun setupViewBinding(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentChooseRoleBinding {
        return FragmentChooseRoleBinding::inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.authPosyandu.setOnClickListener {
            val data = bundleOf(ROLE to POSYANDU_ROLE)
            binding.authPosyandu.findNavController().navigate(R.id.action_chooseRoleFragment_to_loginFragment, data)
        }
        binding.authParent.setOnClickListener {
            val data = bundleOf(ROLE to PARENT_ROLE)
            binding.authParent.findNavController().navigate(R.id.action_chooseRoleFragment_to_loginFragment, data)
        }
    }

    companion object {
        const val ROLE = "role"
        const val POSYANDU_ROLE = 1
        const val PARENT_ROLE = 2
    }

}