package id.kodesumsi.telkompengmas.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import id.kodesumsi.telkompengmas.base.BaseAdapter
import id.kodesumsi.telkompengmas.base.BaseFragment
import id.kodesumsi.telkompengmas.data.source.dummy.DummyData
import id.kodesumsi.telkompengmas.databinding.FragmentBerandaBinding
import id.kodesumsi.telkompengmas.databinding.ItemChildParentBinding
import id.kodesumsi.telkompengmas.databinding.ItemChildPosyanduBinding
import id.kodesumsi.telkompengmas.domain.model.Child
import id.kodesumsi.telkompengmas.ui.detail.ChildDetailActivity
import id.kodesumsi.telkompengmas.utils.Constant.Companion.ROLE_PARENT
import id.kodesumsi.telkompengmas.utils.Constant.Companion.ROLE_POSYANDU
import id.kodesumsi.telkompengmas.utils.Utility
import java.time.LocalDate
import java.time.Period
import java.time.temporal.ChronoUnit
import javax.xml.datatype.DatatypeConstants.MONTHS


class BerandaFragment : BaseFragment<FragmentBerandaBinding>() {

    override fun setupViewBinding(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentBerandaBinding {
        return FragmentBerandaBinding::inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userRole = ROLE_PARENT
        if (userRole == ROLE_PARENT) {
            val childAdapterParent: BaseAdapter<ItemChildParentBinding, Child> = BaseAdapter(ItemChildParentBinding::inflate) { childItem, itemBinding ->
                itemBinding.itemChildParentRoot.setOnClickListener {
                    val toDetail = Intent(requireContext(), ChildDetailActivity::class.java)
                    startActivity(toDetail)
                }
                itemBinding.itemChildParentName.text = childItem.name
            }
            childAdapterParent.setData(DummyData.getChilds())
            binding.dataAnakRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.dataAnakRv.adapter = childAdapterParent
        } else if (userRole == ROLE_POSYANDU) {
            val childAdapterPosyandu: BaseAdapter<ItemChildPosyanduBinding, Child> = BaseAdapter(ItemChildPosyanduBinding::inflate) { childItem, itemBinding ->
                itemBinding.itemChildPosyanduRoot.setOnClickListener {
                    val toDetail = Intent(requireContext(), ChildDetailActivity::class.java)
                    startActivity(toDetail)
                }
                itemBinding.itemChildPosyanduName.text = childItem.name
            }
            childAdapterPosyandu.setData(DummyData.getChilds())
            binding.dataAnakRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.dataAnakRv.adapter = childAdapterPosyandu
        }

//        val dataDate = "2022-06-27"
//        val birthDate = "2021-01-20"
//        Log.d("BerandaActivity", "date count: ${Utility.countMonthDiff(birthDate, dataDate)} ")
    }

}