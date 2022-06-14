package id.kodesumsi.telkompengmas.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseActivity
import id.kodesumsi.telkompengmas.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun setupViewBinding(): (LayoutInflater) -> ActivityMainBinding {
        return ActivityMainBinding::inflate
    }

    override fun setupViewInstance(savedInstanceState: Bundle?) {

    }

}