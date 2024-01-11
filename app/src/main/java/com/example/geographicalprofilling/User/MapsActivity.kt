package com.example.geographicalprofilling.User

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.geographicalprofilling.Model.Location
import com.example.geographicalprofilling.R
import com.example.geographicalprofilling.Responses.LocationResopnse
import com.example.geographicalprofilling.Retrofit.RetrofitIn
import com.example.geographicalprofilling.databinding.ActivityMapsBinding
import com.example.geographicalprofilling.logfail
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    lateinit var fused: FusedLocationProviderClient
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
fused= LocationServices.getFusedLocationProviderClient(this)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
try {
    intent.getStringArrayListExtra("locations")?.apply {
        if(size==6){
            val latlon=LatLng(this[0].toDouble(),this[1].toDouble())
            val latlon2=LatLng(this[3].toDouble(),this[4].toDouble())
/*            Toast.makeText(this@MapsActivity, "${this[2]},${this[5]}", Toast.LENGTH_SHORT).show()
            */mMap.addMarker(MarkerOptions().position(latlon))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlon,16f))
            mMap.addMarker(MarkerOptions().position(latlon2))
            mMap.addPolygon(PolygonOptions().add(latlon,latlon2).strokeColor(Color.RED).fillColor(Color.GREEN))
        getlocations(this[2],this[5])
        }
    }

}catch (_:Exception){ }



if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED
    &&ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
ActivityCompat.requestPermissions(this, arrayOf(
    android.Manifest.permission.ACCESS_FINE_LOCATION,
    android.Manifest.permission.ACCESS_COARSE_LOCATION
),100)
    runingg()
}else {
    runingg()
}


    }

    private fun getlocations(first: String, second: String) {
        RetrofitIn.instance.getnearme(first,second).enqueue(object :Callback<LocationResopnse>{
            override fun onResponse(
                call: Call<LocationResopnse>,
                response: Response<LocationResopnse>
            ) {
                response.body()?.let { it ->
                    logfail(it.toString())
                    it.data.forEach {
                        try {
                            setmemorial(it)

                        }catch (e:Exception){
Log.i("Nothign","${e.message}")
                        }
                    }
                }

            }

            override fun onFailure(call: Call<LocationResopnse>, t: Throwable) {

            }
        })
    }

    private fun setmemorial(it: Location) {
        val marker=MarkerOptions().apply {
            title(it.record)
        }
        when(it.zone){
                 "High"->{
                     marker.icon(BitmapDescriptorFactory.defaultMarker(
                         BitmapDescriptorFactory.HUE_RED
                     ))
                         }
                    "Medium"->{
marker.icon(
    BitmapDescriptorFactory.defaultMarker(
        BitmapDescriptorFactory.HUE_ORANGE
    )
)
                    }
                    "Low"->{
marker.icon(
    BitmapDescriptorFactory.defaultMarker(
        BitmapDescriptorFactory.HUE_GREEN
    )
)
                    }
        }

        Geocoder(this).getFromLocationName(it.location,1)?.get(0)?.apply {
            marker.position(LatLng(latitude,longitude))
            mMap.addMarker(marker)
        }
    }





    @SuppressLint("MissingPermission")

    private fun runingg() {

fused.lastLocation.addOnSuccessListener {
if(it!=null){
    val latlon=LatLng(it.latitude,it.longitude)
    mMap.addMarker(MarkerOptions().icon(functions(R.drawable.placeholder)).title("Current location").position(latlon))
}
}


    }

    private fun functions(drawable:Int)= BitmapDescriptorFactory.fromResource(drawable)


}