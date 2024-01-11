package com.example.geographicalprofilling.Admin

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geographicalprofilling.R
import com.example.geographicalprofilling.Responses.LocationResopnse
import com.example.geographicalprofilling.Retrofit.RetrofitIn
import com.example.geographicalprofilling.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewPaces : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_paces)
val cycle=findViewById<RecyclerView>(R.id.thus)
        cycle.layoutManager=LinearLayoutManager(this)
        val p=ProgressDialog(this)
        p.setCancelable(false)
        p.setTitle("Loading....")
        p.show()
CoroutineScope(IO).launch {
    RetrofitIn.instance.gettingPlaces("list").enqueue(object :Callback<LocationResopnse>{
        override fun onResponse(call: Call<LocationResopnse>, response: Response<LocationResopnse>) {

            val adapter=AdapterForUpdater(this@ViewPaces,response.body()!!.data)
            cycle.adapter=adapter
            p.dismiss()
        }

        override fun onFailure(call: Call<LocationResopnse>, t: Throwable) {
            cycle.toast("${t.message}")
        p.dismiss()
        }
    })
}

    }
}