package id.kodesumsi.telkompengmas.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.ui.main.MainActivity
import id.kodesumsi.telkompengmas.ui.onboarding.OnboardingActivity
import id.kodesumsi.telkompengmas.utils.Constant.Companion.SPLASHSCREEN_DELAY

@SuppressLint("CustomSplashScreen")
class SplashscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Handler(mainLooper).postDelayed({
            val intentToOnboarding = Intent(this, OnboardingActivity::class.java)
            startActivity(intentToOnboarding)
            finish()
        }, SPLASHSCREEN_DELAY)

    }
}