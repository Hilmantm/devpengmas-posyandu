package id.kodesumsi.telkompengmas.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseAdapter
import id.kodesumsi.telkompengmas.base.BaseFragment
import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.data.source.dummy.DummyData
import id.kodesumsi.telkompengmas.databinding.FragmentBerandaBinding
import id.kodesumsi.telkompengmas.databinding.ItemChildParentBinding
import id.kodesumsi.telkompengmas.databinding.ItemChildPosyanduBinding
import id.kodesumsi.telkompengmas.domain.model.Child
import id.kodesumsi.telkompengmas.ui.auth.AuthActivity
import id.kodesumsi.telkompengmas.ui.auth.ChooseRoleFragment
import id.kodesumsi.telkompengmas.ui.detail.ChildDetailActivity
import id.kodesumsi.telkompengmas.utils.Constant.Companion.MAN
import id.kodesumsi.telkompengmas.utils.Constant.Companion.ROLE_PARENT
import id.kodesumsi.telkompengmas.utils.Constant.Companion.ROLE_POSYANDU
import id.kodesumsi.telkompengmas.utils.Constant.Companion.WOMAN
import id.kodesumsi.telkompengmas.utils.Utility
import java.time.LocalDate
import java.time.Period
import java.time.temporal.ChronoUnit
import javax.xml.datatype.DatatypeConstants.MONTHS

@AndroidEntryPoint
class BerandaFragment : BaseFragment<FragmentBerandaBinding>() {

    private val viewModel: BerandaFragmentViewModel by viewModels()

    override fun setupViewBinding(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentBerandaBinding {
        return FragmentBerandaBinding::inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getUser()
        viewModel.currentUser.observe(viewLifecycleOwner) { currentUser ->
            if (currentUser != null) {
                binding.nameHome.text = currentUser.name

                // setAdapterBasedOnRole(currentUser.role ?: ROLE_PARENT)

                binding.btnLogout.setOnClickListener {
                    viewModel.logout(currentUser)
                }

                viewModel.getChildList(token = currentUser.token ?: "", userRole = currentUser.role ?: ROLE_PARENT).observe(viewLifecycleOwner) { listChild ->
                    when (listChild) {
                        is Resource.Success -> {
                            when (currentUser.role) {
                                ROLE_PARENT -> {
                                    val childAdapterParent: BaseAdapter<ItemChildParentBinding, Child> = BaseAdapter(ItemChildParentBinding::inflate) { childItem, itemBinding ->
                                        itemBinding.itemChildParentRoot.setOnClickListener {
                                            val toDetail = Intent(requireContext(), ChildDetailActivity::class.java)
                                            startActivity(toDetail)
                                        }
                                        itemBinding.itemChildParentName.text = childItem.name

                                        if (childItem.image == null) {
                                            when (childItem.gender) {
                                                MAN -> Glide.with(requireContext()).load(R.drawable.boy_illustration).into(itemBinding.itemChildParentImage)
                                                WOMAN -> Glide.with(requireContext()).load(R.drawable.girl_illustration).into(itemBinding.itemChildParentImage)
                                            }
                                        }
                                    }
                                    childAdapterParent.setData(listChild.data)
                                    binding.dataAnakRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                                    binding.dataAnakRv.adapter = childAdapterParent
                                }
                                ROLE_POSYANDU -> {
                                    val childAdapterPosyandu: BaseAdapter<ItemChildPosyanduBinding, Child> = BaseAdapter(ItemChildPosyanduBinding::inflate) { childItem, itemBinding ->
                                        itemBinding.itemChildPosyanduRoot.setOnClickListener {
                                            val toDetail = Intent(requireContext(), ChildDetailActivity::class.java)
                                            startActivity(toDetail)
                                        }
                                        itemBinding.itemChildPosyanduName.text = childItem.name

                                        if (childItem.image == null) {
                                            when (childItem.gender) {
                                                MAN -> Glide.with(requireContext()).load(R.drawable.boy_illustration).into(itemBinding.itemChildPosyanduImage)
                                                WOMAN -> Glide.with(requireContext()).load(R.drawable.girl_illustration).into(itemBinding.itemChildPosyanduImage)
                                            }
                                        }
                                    }
                                    childAdapterPosyandu.setData(listChild.data)
                                    binding.dataAnakRv.layoutManager = LinearLayoutManager(requireContext())
                                    binding.dataAnakRv.adapter = childAdapterPosyandu
                                }
                            }
                        }
                        is Resource.Error -> {
                            Toast.makeText(
                                requireContext(),
                                "Error ${listChild.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                            Log.e("BerandaFragment", "onViewCreated: ${listChild.message}", )
                        }
                        is Resource.Loading -> {

                        }
                    }
                }
            }
        }

        viewModel.logoutResult.observe(viewLifecycleOwner) { logoutSuccess ->
            if (logoutSuccess) {
                val toChooseRole = Intent(requireContext(), AuthActivity::class.java)
                startActivity(toChooseRole)
                requireActivity().finish()
            }
        }



//        val dataDate = "2022-06-27"
//        val birthDate = "2021-01-20"
//        Log.d("BerandaActivity", "date count: ${Utility.countMonthDiff(birthDate, dataDate)} ")
    }

    private fun setAdapterBasedOnRole(userRole: Int) {
        if (userRole == ROLE_PARENT) {
            val childAdapterParent: BaseAdapter<ItemChildParentBinding, Child> = BaseAdapter(ItemChildParentBinding::inflate) { childItem, itemBinding ->
                itemBinding.itemChildParentRoot.setOnClickListener {
                    val toDetail = Intent(requireContext(), ChildDetailActivity::class.java)
                    startActivity(toDetail)
                }
                itemBinding.itemChildParentName.text = childItem.name

                if (childItem.image == null) {
                    when (childItem.gender) {
                        MAN -> Glide.with(requireContext()).load(R.drawable.boy_illustration).into(itemBinding.itemChildParentImage)
                        WOMAN -> Glide.with(requireContext()).load(R.drawable.girl_illustration).into(itemBinding.itemChildParentImage)
                    }
                }
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

                if (childItem.image == null) {
                    when (childItem.gender) {
                        MAN -> Glide.with(requireContext()).load(R.drawable.boy_illustration).into(itemBinding.itemChildPosyanduImage)
                        WOMAN -> Glide.with(requireContext()).load(R.drawable.girl_illustration).into(itemBinding.itemChildPosyanduImage)
                    }
                }
            }
            childAdapterPosyandu.setData(DummyData.getChilds())
            binding.dataAnakRv.layoutManager = LinearLayoutManager(requireContext())
            binding.dataAnakRv.adapter = childAdapterPosyandu
        }
    }

}