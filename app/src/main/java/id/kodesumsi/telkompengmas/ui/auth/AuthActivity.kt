package id.kodesumsi.telkompengmas.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import id.kodesumsi.telkompengmas.base.BaseActivity
import id.kodesumsi.telkompengmas.databinding.ActivityAuthBinding

class AuthActivity : BaseActivity<ActivityAuthBinding>() {
    override fun setupViewBinding(): (LayoutInflater) -> ActivityAuthBinding {
        return ActivityAuthBinding::inflate
    }

    override fun setupViewInstance(savedInstanceState: Bundle?) {

    }

}