package id.kodesumsi.telkompengmas.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.kodesumsi.telkompengmas.base.BaseActivity
import id.kodesumsi.telkompengmas.databinding.ActivityOnboardingBinding
import id.kodesumsi.telkompengmas.ui.auth.AuthActivity
import id.kodesumsi.telkompengmas.ui.main.MainActivity

@AndroidEntryPoint
class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>() {

    private val viewModel: OnboardingViewModel by viewModels()

    override fun setupViewBinding(): (LayoutInflater) -> ActivityOnboardingBinding {
        return ActivityOnboardingBinding::inflate
    }

    override fun setupViewInstance(savedInstanceState: Bundle?) {
        binding.onboardingViewpager.adapter = OnboardingViewpagerAdapter(this)
        binding.onboardingIndicator.attachTo(binding.onboardingViewpager)

        binding.onboardingViewpager.isUserInputEnabled = false
        viewModel.onboardingCurrentItem.postValue(binding.onboardingViewpager.currentItem)

        viewModel.onboardingCurrentItem.observe(this) { position ->
            if (position == 2) {
                setNextToAuth()
            }
        }

        binding.skipBtn.setOnClickListener {
            binding.onboardingViewpager.currentItem = 2
            viewModel.onboardingCurrentItem.postValue(binding.onboardingViewpager.currentItem)
        }

        binding.nextBtn.setOnClickListener {
            binding.onboardingViewpager.currentItem += 1
            viewModel.onboardingCurrentItem.postValue(binding.onboardingViewpager.currentItem)
        }

    }

    private fun setNextToAuth() {
        binding.nextBtn.text = "Mulai"
        binding.nextBtn.setOnClickListener {
            // check session or check current user is login?
            val isLogin = false
            if (isLogin) {
                val toMain = Intent(this, MainActivity::class.java)
                startActivity(toMain)
                finish()
            } else {
                val toLogin = Intent(this, AuthActivity::class.java)
                startActivity(toLogin)
                finish()
            }
        }
    }

}