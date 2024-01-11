package com.example.geographicalprofilling

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.geographicalprofilling.Responses.CommonResponse
import com.example.geographicalprofilling.Retrofit.RetrofitIn
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val name=findViewById<TextInputEditText>(R.id.name)
        val mobile=findViewById<TextInputEditText>(R.id.mobile)
        val mail=findViewById<TextInputEditText>(R.id.mail)
        val password=findViewById<TextInputEditText>(R.id.password)
        val sign=findViewById<Button>(R.id.sign)
        val already=findViewById<TextView>(R.id.already)
        already.setOnClickListener {
            finish()
            startActivity(Intent(this,LoginPage::class.java))
        }
sign.setOnClickListener {
    val name1=name.text.toString()
    val mobile1=mobile.text.toString()
    val mail1=mail.text.toString()
    val password1=password.text.toString()
    if(name1.isEmpty()){
it.toast("Please Enter name")
    }else if(mobile1.isEmpty()){
        it.toast("Please Enter mobile")
    }else if(mail1.isEmpty()){
        it.toast("Please Enter mail")
    }else if(password1.isEmpty()){
        it.toast("Please Enter password")
    }else{
CoroutineScope(IO).launch {
    RetrofitIn.instance.createUser(name1,mail1,password1,mobile1).enqueue(object :Callback<CommonResponse>{
        override fun onResponse(call: Call<CommonResponse>, response: Response<CommonResponse>) {
            val k=response.body()!!.message
            it.toast(k)
            if(k=="Created"){
                startActivity(Intent(this@SignUp,LoginPage::class.java))
                finishAffinity()
            }
        }

        override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
            Toast.makeText(this@SignUp, "${t.message}", Toast.LENGTH_SHORT).show()
        }
    })
}
    }
}
    }
}