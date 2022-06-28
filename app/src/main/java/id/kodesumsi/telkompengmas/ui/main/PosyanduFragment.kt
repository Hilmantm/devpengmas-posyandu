package id.kodesumsi.telkompengmas.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseFragment
import id.kodesumsi.telkompengmas.databinding.FragmentPosyanduBinding


class PosyanduFragment : BaseFragment<FragmentPosyanduBinding>(), OnMapReadyCallback {
    override fun setupViewBinding(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentPosyanduBinding {
        return FragmentPosyanduBinding::inflate
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