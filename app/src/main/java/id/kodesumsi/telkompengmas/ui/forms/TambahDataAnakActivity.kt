package id.kodesumsi.telkompengmas.ui.forms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import id.kodesumsi.telkompengmas.base.BaseActivity
import id.kodesumsi.telkompengmas.databinding.ActivityTambahDataAnakBinding

class TambahDataAnakActivity : BaseActivity<ActivityTambahDataAnakBinding>() {
    override fun setupViewBinding(): (LayoutInflater) -> ActivityTambahDataAnakBinding {
        return ActivityTambahDataAnakBinding::inflate
    }

    override fun setupViewInstance(savedInstanceState: Bundle?) {

    }

}