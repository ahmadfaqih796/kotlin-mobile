package com.project.latihan.ui.screen.layer

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.compose.runtime.*
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

@Composable
fun MapScreen(context: Context) {
    var mapView: MapView? by remember { mutableStateOf(null) }
    var currentLocation by remember { mutableStateOf<LatLng?>(null) }

    // Initialize Location Services
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    // Get real-time location
    LaunchedEffect(Unit) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                currentLocation = LatLng(it.latitude, it.longitude)
            }
        }
    }

    // Show Map
    AndroidView(factory = { ctx ->
        mapView = MapView(ctx)
        mapView?.apply {
            onCreate(null)
            getMapAsync(OnMapReadyCallback { googleMap ->
                currentLocation?.let {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(it, 15f))
                    googleMap.addMarker(MarkerOptions().position(it).title("Your Location"))
                }
            })
        }
        mapView!!
    }, update = {
        mapView?.onResume()
    })
}