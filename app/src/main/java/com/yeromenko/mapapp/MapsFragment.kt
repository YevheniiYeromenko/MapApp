package com.yeromenko.mapapp

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.yeromenko.mapapp.adapter.Adapter
import com.yeromenko.mapapp.model.Place
import com.yeromenko.mapapp.rest.RetrofitModule
import kotlinx.android.synthetic.main.fragment_maps.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapsFragment : Fragment() {

    lateinit var adapter: Adapter
    lateinit var mapFragment:SupportMapFragment
    var email: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString(EMAIL_EXTRA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment = (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?)!!

        (activity as MainActivity).supportActionBar?.title = email

        adapter = Adapter()
        rvPlaces.adapter = adapter

        CoroutineScope(IO).launch {
            val response = RetrofitModule().apiService().getPlaces()
            updatePlaces(response.places)
        }
    }

    suspend fun updatePlaces(places: MutableList<Place>) {
        withContext(Main){
            pbLoading.visibility = View.GONE
            adapter.setList(places)
            mapFragment.getMapAsync {googleMap ->
                places.forEach {
                    googleMap.addMarker(MarkerOptions().position(LatLng(it.lat, it.lng)).title(it.name))
                }
                val focus = LatLng(places[0].lat, places[0].lng)
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(focus, 14.5f))
            }
        }
    }
}