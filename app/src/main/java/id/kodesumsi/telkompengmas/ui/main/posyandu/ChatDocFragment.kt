package id.kodesumsi.telkompengmas.ui.main.posyandu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.kodesumsi.telkompengmas.base.BaseAdapter
import id.kodesumsi.telkompengmas.base.BaseFragment
import id.kodesumsi.telkompengmas.databinding.FragmentChatDocBinding
import id.kodesumsi.telkompengmas.databinding.ItemChatDocBinding
import id.kodesumsi.telkompengmas.domain.model.Doctor
import id.kodesumsi.telkompengmas.ui.main.BottomSheetAddDoctor

@AndroidEntryPoint
class ChatDocFragment : BaseFragment<FragmentChatDocBinding>() {

    private val viewModel: ChatDocFragmentViewModel by viewModels()

    override fun setupViewBinding(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentChatDocBinding {
        return FragmentChatDocBinding::inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter: BaseAdapter<ItemChatDocBinding, Doctor> = BaseAdapter(ItemChatDocBinding::inflate) { item, binding ->
            binding.chatDocName.text = item.name
            binding.chatDocBtn.setOnClickListener {
                val toWhatsapp = Intent(Intent.ACTION_VIEW)
                toWhatsapp.data = Uri.parse("https://api.whatsapp.com/send?phone=${item.phone}")
                startActivity(toWhatsapp)
            }
        }
        binding.chatDocRv.layoutManager = LinearLayoutManager(requireContext())
        binding.chatDocRv.adapter = adapter

        binding.addDoctor.setOnClickListener {
            val addDoctor = BottomSheetAddDoctor()
            addDoctor.show(childFragmentManager, "Add Doctor Bottom Sheet")
        }

        viewModel.getDoctors()
        viewModel.doctors.observe(viewLifecycleOwner) { doctors ->
            if (doctors.isNotEmpty()) {
                adapter.setData(doctors)
                adapter.notifyDataSetChanged()
            }
        }
    }

}