package com.example.mil

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng

import com.google.android.gms.maps.model.MarkerOptions

class MainActivity2 : AppCompatActivity() , OnMapReadyCallback{
    private lateinit var addressEditText:EditText
    private lateinit var addressFindBtn: Button
    private lateinit var latitudeTextView:TextView
    private lateinit var longitudeTextView: TextView

    private lateinit var mapfrag:SupportMapFragment
    private lateinit var googlemap:GoogleMap
    private lateinit var location:MyLocation

    private lateinit var geocoder: Geocoder
    private lateinit var list:List<Address>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        initActivity()
        initLocationFromIntent()
        initMapFragment()
    }
    private fun initActivity(){
        addressEditText = findViewById(R.id.addressEditText)
        addressFindBtn = findViewById(R.id.geoSearchBtn)
        addressFindBtn.setOnClickListener(btnClickListener)
        latitudeTextView = findViewById(R.id.geoLatitudeTextView)
        longitudeTextView = findViewById(R.id.geoLongitudeTextView)
        geocoder = Geocoder(this)
    }

    private val btnClickListener = View.OnClickListener { v->
        if(v.id == R.id.geoSearchBtn){
            var address = getLocationFromText()
            latitudeTextView.text = address.latitude.toString()
            longitudeTextView.text = address.longitude.toString()
            googlemap.animateCamera(CameraUpdateFactory.newLatLng(LatLng(address.latitude,address.longitude)))
        }
    }
    private fun getLocationFromText():Address{
        var addressString:String = addressEditText.text.toString()
        list = geocoder.getFromLocationName(addressString, 10);
        return list[0]
    }

    override fun onMapReady(p0: GoogleMap) {
        initGoogleMap(p0)
//        p0.addMarker(
//            MarkerOptions()
//                .position(LatLng(56.319746, 160.833090))
//                .title("Marker")
//        )
        p0.addMarker(
            MarkerOptions()
                .position(LatLng(location.latitude, location.longitude))
                .title("Marker")
        )
        //zoom.. Min/Max
        p0.setMinZoomPreference(17.0f)
        p0.setMaxZoomPreference(18.0f)
        p0.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude),17.0f))

    }
    private fun initGoogleMap(_map: GoogleMap){
        googlemap = _map
    }

    private fun initMapFragment(){
        mapfrag = SupportMapFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.form_map, mapfrag)
            .commit()
        mapfrag.getMapAsync(this)
    }
    private fun initLocationFromIntent(){
        location = intent.getSerializableExtra("Location") as MyLocation
    }


}