package com.example.geographicalprofilling

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.geographicalprofilling.Admin.Admin_MainActivity
import com.example.geographicalprofilling.User.DashBoard

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val image=findViewById<ImageView>(R.id.splash)
        image.alpha=0f
        val type=getSharedPreferences("user", MODE_PRIVATE).getString("type","")
        image.animate().alpha(1f).setDuration(1000).withEndAction {
            finishAffinity()
            when (type) {
                "user" -> {
                    startActivity(Intent(this,DashBoard::class.java))
                }
                "admin" -> {
                    startActivity(Intent(this,Admin_MainActivity::class.java))
                }
                else -> {
                    startActivity(Intent(this, LoginPage::class.java))
                }
            }
            overridePendingTransition(androidx.appcompat.R.anim.abc_fade_in,
                androidx.appcompat.R.anim.abc_fade_out)

        }

    }
}