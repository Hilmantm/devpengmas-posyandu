package id.kodesumsi.telkompengmas.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseAdapter
import id.kodesumsi.telkompengmas.base.BaseFragment
import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.databinding.FragmentPosyanduBinding
import id.kodesumsi.telkompengmas.databinding.ItemPosyanduBinding
import id.kodesumsi.telkompengmas.domain.model.Posyandu

@AndroidEntryPoint
class PosyanduFragment : BaseFragment<FragmentPosyanduBinding>(), OnMapReadyCallback {

    private val viewModel: PosyanduFragmentViewModel by viewModels()

    override fun setupViewBinding(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentPosyanduBinding {
        return FragmentPosyanduBinding::inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter: BaseAdapter<ItemPosyanduBinding, Posyandu> = BaseAdapter(ItemPosyanduBinding::inflate) { posyandu, binding ->
            viewModel.posyanduId.observe(viewLifecycleOwner) {
                if (posyandu.id == it) {
                    viewModel.posyandu.postValue(posyandu)
                }
            }

            if (posyandu.thumbUrl != null) {
                Glide.with(requireContext()).load(posyandu.thumbUrl).into(binding.itemPosyanduImage)
            } else {
                Glide.with(requireContext()).load(R.drawable.posyandu_illustration).into(binding.itemPosyanduImage)
            }
            binding.itemPosyanduName.text = posyandu.name
            if (posyandu.status == true) {
                binding.itemPosyanduStatus.text = "Buka"
            } else {
                binding.itemPosyanduStatus.text = "Tutup"
            }
            binding.itemPosyanduDirection.setOnClickListener {
                val toPosyandu = Intent(Intent.ACTION_VIEW)
                toPosyandu.data = Uri.parse("https://www.google.com/maps/search/?api=1&query=${posyandu.lat},${posyandu.lng}")
                startActivity(toPosyandu)
            }
        }
        binding.posyanduRv.layoutManager = LinearLayoutManager(requireContext())
        binding.posyanduRv.adapter = adapter

        viewModel.getUser()
        viewModel.currentUser.observe(viewLifecycleOwner) { currentUser ->
            if (currentUser != null) {
                viewModel.posyanduId.postValue(currentUser.iDPosyandu)
                viewModel.getPosyandus(currentUser.idDesa!!).observe(viewLifecycleOwner) { posyandus ->
                    when (posyandus) {
                        is Resource.Success -> {
                            if (posyandus.data?.size != 0) {
                                adapter.setData(posyandus.data)
                                adapter.notifyDataSetChanged()
                            }
                        }
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.posyandu_map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        viewModel.posyandu.observe(viewLifecycleOwner) {
            val lat = it.lat!!.toDouble()
            val lng = it.lng!!.toDouble()
            val posyandu = LatLng(lat, lng)
            binding.posyanduClosest.text = it.address.toString()
            binding.posyanduSubtitle.text = it.name.toString()
            p0.addMarker(
                MarkerOptions().position(posyandu).title(it.name)
            )
            p0.moveCamera(CameraUpdateFactory.newLatLng(posyandu))
        }
    }

}