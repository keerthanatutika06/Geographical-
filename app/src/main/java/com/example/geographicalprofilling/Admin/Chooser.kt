package com.example.geographicalprofilling.Admin

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geographicalprofilling.Model.ArraylistData
import com.example.geographicalprofilling.Model.CommonData
import com.example.geographicalprofilling.R
import com.example.geographicalprofilling.Responses.CommonResponse
import com.example.geographicalprofilling.Retrofit.RetrofitIn
import com.example.geographicalprofilling.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Chooser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chooser)
        val int=intent.getStringArrayListExtra("data")
val cycle=findViewById<RecyclerView>(R.id.blute)
val btn=findViewById<Button>(R.id.submit)
        val adapterg=ArrayList<ArraylistData>()
        val data=ArrayList<CommonData>()

        cycle.layoutManager=LinearLayoutManager(this)
        if(int!=null){
            val adapter=AdapterForLoad(this,int,adapterg,data)
            cycle.adapter=adapter
        }
        val size=int!!.size
        btn.setOnClickListener {
var num=0
            p.show()

CoroutineScope(IO).launch {

    for (item in adapterg) {
        RetrofitIn.instance.addingLocations(int.get(num), item.data.get(num).data, item.data.get(num).chose, num, size).enqueue(object : Callback<CommonResponse> {
            override fun onResponse(call: Call<CommonResponse>, response: Response<CommonResponse>  ) {
                p.dismiss()
                it.toast(response.body()!!.message)
        finishAffinity()
                startActivity(Intent(this@Chooser,Admin_MainActivity::class.java))
                  }

            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                it.toast("${t.message}")

                p.dismiss()
            }
        })
        num++
    }
}



        }

    }
    private val p by lazy {
        ProgressDialog(this).apply {
            setTitle("Uploading...")
            setMessage("(\uD83D\uDE42)")
            setCancelable(false)
        }
    }
}