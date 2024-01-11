package com.example.geographicalprofilling

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.geographicalprofilling.Admin.Admin_MainActivity
import com.example.geographicalprofilling.Responses.LoginResponse
import com.example.geographicalprofilling.Retrofit.RetrofitIn
import com.example.geographicalprofilling.User.DashBoard
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        val email=findViewById<TextInputEditText>(R.id.email)
        val pass=findViewById<TextInputEditText>(R.id.pass)
        val login=findViewById<Button>(R.id.login)
        login.setOnClickListener {
            val email1=email.text.toString()
            val pass1=pass.text.toString()
            if(email1.isEmpty()){it.toast("Please Enter Email")}else if(pass1.isEmpty()){it.toast("Please Enter Password")}
            else if(email1.equals("admin")&&pass1.equals("admin")){
getSharedPreferences("user", MODE_PRIVATE).edit().putString("type","admin").apply()
                finishAffinity()

                startActivity(Intent(this,Admin_MainActivity::class.java))

            }else{
val o=ProgressDialog(this).apply {
    setTitle("Loding...........")
    setMessage("Wait a minute \uD83D\uDE42")
    setCancelable(false)
    show()
}
               CoroutineScope(IO).launch {
                   RetrofitIn.instance.login(email1,pass1,"login").enqueue(object :Callback<LoginResponse>{
                       override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                           val data=response.body()!!.data
                           val message=response.body()!!.message
                           o.dismiss()
           if(message=="Success"){
                               it.toast(message)
               if(data.isNotEmpty()){
                   val j=data[0]
                   getSharedPreferences("user", MODE_PRIVATE).edit().apply {
                       putString("id","${j.id}")
                       putString("name", j.name)
                       putString("mail", j.mail)
                       putString("password", j.password)
                       putString("mobile", j.mobile)
                       putString("type","user")
                       apply()
                   }
                   finishAffinity()
                   startActivity(Intent(this@LoginPage, DashBoard::class.java))
               }

                           }else{
                               it.toast(message)
                           }



                       }

                       override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
it.toast("${t.message}")
                           o.dismiss()
                       }
                   })
               }
            }


        }
    val signup=findViewById<TextView>(R.id.next)
        signup.setOnClickListener {
            val int=Intent(this,SignUp::class.java)
            startActivity(int)
        }
    }
}