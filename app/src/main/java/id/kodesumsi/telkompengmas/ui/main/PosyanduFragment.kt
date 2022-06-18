package id.kodesumsi.telkompengmas.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseFragment
import id.kodesumsi.telkompengmas.databinding.FragmentPosyanduBinding


class PosyanduFragment : BaseFragment<FragmentPosyanduBinding>() {
    override fun setupViewBinding(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentPosyanduBinding {
        return FragmentPosyanduBinding::inflate
    }

}