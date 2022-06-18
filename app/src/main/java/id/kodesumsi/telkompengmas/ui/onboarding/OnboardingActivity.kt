package id.kodesumsi.telkompengmas.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import id.kodesumsi.telkompengmas.base.BaseActivity
import id.kodesumsi.telkompengmas.databinding.ActivityOnboardingBinding
import id.kodesumsi.telkompengmas.ui.auth.AuthActivity
import id.kodesumsi.telkompengmas.ui.main.MainActivity

class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>() {
    override fun setupViewBinding(): (LayoutInflater) -> ActivityOnboardingBinding {
        return ActivityOnboardingBinding::inflate
    }

    override fun setupViewInstance(savedInstanceState: Bundle?) {
        binding.onboardingViewpager.adapter = OnboardingViewpagerAdapter(this)
        binding.onboardingIndicator.attachTo(binding.onboardingViewpager)


        binding.nextBtn.setOnClickListener {
            if (binding.onboardingViewpager.currentItem > 1) {
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
            } else {
                binding.onboardingViewpager.currentItem += 1
            }
        }

    }

}