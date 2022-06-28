package id.kodesumsi.telkompengmas.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseAdapter
import id.kodesumsi.telkompengmas.base.BaseFragment
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

        viewModel.getPosyandus()
        viewModel.posyandus.observe(viewLifecycleOwner) { posyandus ->
            if (posyandus.isNotEmpty()) {
                adapter.setData(posyandus)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.posyandu_map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        val lat = -6.971673
        val lng = 107.670082
        val posyandu = LatLng(lat, lng)
        p0.addMarker(
            MarkerOptions().position(posyandu).title("Posyandu Mawar 06")
        )
        p0.moveCamera(CameraUpdateFactory.newLatLng(posyandu));
    }

}