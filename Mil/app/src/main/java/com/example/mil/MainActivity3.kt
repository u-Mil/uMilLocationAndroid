package com.example.mil

import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity3 : AppCompatActivity() {

    private lateinit var geocoder: Geocoder
    private lateinit var txt1:TextView
    private lateinit var txt2:TextView
    private lateinit var edittxt1:EditText
    private lateinit var transGeoBtn:Button
    private lateinit var list:List<Address>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        txt1 = findViewById(R.id.latTextView)
        txt2 = findViewById(R.id.lonTextView)
        edittxt1 = findViewById(R.id.editTextTextAddress)
        transGeoBtn = findViewById(R.id.geoButton)
        transGeoBtn.setOnClickListener(btnListener)
        geocoder = Geocoder(this)
        //
    }

    private fun getLocation(){
        var addressString:String = edittxt1.text.toString()
        list = geocoder.getFromLocationName(addressString, 10);
        var address = list[0]
        txt1.text = address.latitude.toString()
        txt2.text = address.longitude.toString()
    }

    private val btnListener = View.OnClickListener { v->
        if(v.id == R.id.geoButton){
            getLocation()
        }
    }
}