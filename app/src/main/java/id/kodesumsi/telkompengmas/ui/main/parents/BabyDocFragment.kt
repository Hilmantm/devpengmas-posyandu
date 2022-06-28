package id.kodesumsi.telkompengmas.ui.main.parents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseAdapter
import id.kodesumsi.telkompengmas.base.BaseFragment
import id.kodesumsi.telkompengmas.databinding.FragmentBabyDocBinding
import id.kodesumsi.telkompengmas.databinding.ItemChatDocBinding
import id.kodesumsi.telkompengmas.domain.model.Doctor

@AndroidEntryPoint
class BabyDocFragment : BaseFragment<FragmentBabyDocBinding>() {

    private val viewModel: BabyDocFragmentViewModel by viewModels()

    override fun setupViewBinding(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentBabyDocBinding {
        return FragmentBabyDocBinding::inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter: BaseAdapter<ItemChatDocBinding, Doctor> = BaseAdapter(ItemChatDocBinding::inflate) { item, binding ->
            binding.chatDocName.text = item.name
            binding.chatDocBtn.setOnClickListener {
                val toWhatsapp = Intent(Intent.ACTION_VIEW)
                toWhatsapp.data = Uri.parse(item.phone)
                startActivity(toWhatsapp)
            }
        }
        binding.chatDocRv.layoutManager = LinearLayoutManager(requireContext())
        binding.chatDocRv.adapter = adapter

        viewModel.getDoctors()
        viewModel.doctors.observe(viewLifecycleOwner) { doctors ->
            if (doctors.isNotEmpty()) {
                adapter.setData(doctors)
                adapter.notifyDataSetChanged()
            }
        }
    }

}