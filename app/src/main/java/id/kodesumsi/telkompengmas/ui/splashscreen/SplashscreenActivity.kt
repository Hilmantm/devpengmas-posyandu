package id.kodesumsi.telkompengmas.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseActivity
import id.kodesumsi.telkompengmas.databinding.ActivitySplashscreenBinding
import id.kodesumsi.telkompengmas.ui.auth.AuthActivity
import id.kodesumsi.telkompengmas.ui.auth.ChooseRoleFragment
import id.kodesumsi.telkompengmas.ui.main.MainActivity
import id.kodesumsi.telkompengmas.ui.onboarding.OnboardingActivity
import id.kodesumsi.telkompengmas.utils.Constant.Companion.SPLASHSCREEN_DELAY

@AndroidEntryPoint
class SplashscreenActivity : BaseActivity<ActivitySplashscreenBinding>() {

    private val viewModel: SplashscreenActivityViewModel by viewModels()

    override fun setupViewBinding(): (LayoutInflater) -> ActivitySplashscreenBinding {
        return ActivitySplashscreenBinding::inflate
    }

    override fun setupViewInstance(savedInstanceState: Bundle?) {
        Handler(mainLooper).postDelayed({

            // check users
            viewModel.getUser()
            viewModel.currentUser.observe(this) { loginUser ->
                if (loginUser != null) {
                    val toBeranda = Intent(this, MainActivity::class.java)
                    toBeranda.putExtra(ChooseRoleFragment.ROLE, loginUser.role)
                    startActivity(toBeranda)
                    finish()
                } else {
                    val preferences = getSharedPreferences(getString(R.string.PREFERENCE_FILE_KEY), Context.MODE_PRIVATE)
                    val isFirstTime =  preferences.getBoolean(getString(R.string.FIRST_TIME), true)
                    Log.d("SplashscreenActivity", "onCreate: $isFirstTime")

                    if (isFirstTime) {
                        val intentToOnboarding = Intent(this, OnboardingActivity::class.java)
                        startActivity(intentToOnboarding)
                        finish()
                    } else {
                        val toAuth = Intent(this, AuthActivity::class.java)
                        startActivity(toAuth)
                        finish()
                    }

                }
            }
        }, SPLASHSCREEN_DELAY)
    }
}