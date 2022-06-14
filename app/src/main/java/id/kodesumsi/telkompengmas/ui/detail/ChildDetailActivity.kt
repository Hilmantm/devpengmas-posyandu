package id.kodesumsi.telkompengmas.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import id.kodesumsi.telkompengmas.base.BaseActivity
import id.kodesumsi.telkompengmas.databinding.ActivityChildDetailBinding

class ChildDetailActivity : BaseActivity<ActivityChildDetailBinding>() {
    override fun setupViewBinding(): (LayoutInflater) -> ActivityChildDetailBinding {
        return ActivityChildDetailBinding::inflate
    }

    override fun setupViewInstance(savedInstanceState: Bundle?) {

    }

}