package id.kodesumsi.telkompengmas.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseFragment
import id.kodesumsi.telkompengmas.databinding.FragmentOnboardingBinding


class OnboardingFragment(private val position: Int) : BaseFragment<FragmentOnboardingBinding>() {

    override fun setupViewBinding(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentOnboardingBinding {
        return FragmentOnboardingBinding::inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when(position) {
            0 -> {
                Glide.with(requireContext()).load(R.drawable.onboarding_1).into(binding.onboardingIllustration)
                binding.onboardingDesc.text = getString(R.string.onboarding_1_desc)
            }
            1 -> {
                Glide.with(requireContext()).load(R.drawable.onboarding_2).into(binding.onboardingIllustration)
                binding.onboardingDesc.text = getString(R.string.onboarding_2_desc)
            }
            2 -> {
                Glide.with(requireContext()).load(R.drawable.onboarding_3).into(binding.onboardingIllustration)
                binding.onboardingDesc.text = getString(R.string.onboarding_3_desc)
            }
        }
    }

}