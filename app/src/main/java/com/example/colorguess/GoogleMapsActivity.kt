package com.example.colorguess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.colorguess.databinding.ActivityGoogleMapsBinding

class GoogleMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityGoogleMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGoogleMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        binding.btnSidney.setOnClickListener {
            googleMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(-33.889539002677374, 151.23822796877718), 12f
                )
            )

        }

        binding.btnAuckland.setOnClickListener {

            googleMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(-36.8485, 174.7633), 12f
                )
            )
        }

        binding.btnOmsk.setOnClickListener {

            googleMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(54.99819438626811, 73.36135326864773), 12f
                )
            )
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()

        }

    }


}