package id.kodesumsi.telkompengmas.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseActivity
import id.kodesumsi.telkompengmas.databinding.ActivityMainBinding
import id.kodesumsi.telkompengmas.ui.auth.ChooseRoleFragment
import id.kodesumsi.telkompengmas.ui.forms.TambahDataAnakActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var bnvMenu: Menu? = null

    override fun setupViewBinding(): (LayoutInflater) -> ActivityMainBinding {
        return ActivityMainBinding::inflate
    }

    override fun setupViewInstance(savedInstanceState: Bundle?) {
        // get current role
        val role = intent.getIntExtra(ChooseRoleFragment.ROLE, 1)

        binding.bnv.menu.clear()
        if (role == ChooseRoleFragment.POSYANDU_ROLE) {
            binding.bnv.inflateMenu(R.menu.bnv_main_menu_posyandu)
        } else if (role == ChooseRoleFragment.PARENT_ROLE) {
            binding.bnv.inflateMenu(R.menu.bnv_main_menu_parents)
        }
        val navController = findNavController(R.id.main_nav)
        binding.bnv.setupWithNavController(navController)

        binding.fab.setOnClickListener {
            val toAddChild = Intent(this, TambahDataAnakActivity::class.java)
            startActivity(toAddChild)
        }
    }

}