package id.kodesumsi.telkompengmas.ui.main.parents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseFragment
import id.kodesumsi.telkompengmas.databinding.FragmentBabyDocBinding

class BabyDocFragment : BaseFragment<FragmentBabyDocBinding>() {
    override fun setupViewBinding(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentBabyDocBinding {
        return FragmentBabyDocBinding::inflate
    }

}