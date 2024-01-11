package com.example.geographicalprofilling.Admin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.geographicalprofilling.MainActivity
import com.example.geographicalprofilling.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class Admin_MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)
        val addplaces=findViewById<CardView>(R.id.addplaces)
        val image=findViewById<ImageView>(R.id.image1)
val viewplaces=findViewById<CardView>(R.id.places)
        val image2=findViewById<ImageView>(R.id.image2)
        val image20=findViewById<ImageView>(R.id.image20)

findViewById<CardView>(R.id.cardv).setOnClickListener {
    animate(it,image20)
}
        addplaces.setOnClickListener {
         animate(addplaces,image)
        }
        viewplaces.setOnClickListener{
animate(viewplaces,image2)
        }

    }
    fun animate(view:View,image:ImageView){
        image.alpha=0f
        image.animate().setDuration(500).alpha(1f).withEndAction {
            when (view.id) {
                R.id.addplaces -> {
                    startActivity(Intent(this, AddPlaces::class.java))
                }
                R.id.places -> {
                    startActivity(Intent(this,ViewPaces::class.java))
                }
                R.id.cardv -> { diloag() }
            }

        }
            .withStartAction {
                overridePendingTransition(androidx.appcompat.R.anim.abc_fade_in,
                    androidx.appcompat.R.anim.abc_fade_in)
            }
    }

    private fun diloag() {
        MaterialAlertDialogBuilder(this).apply {
            setTitle("Do you want  to logout")
            setPositiveButton("Yes"){m,_->
                m.dismiss()
                getSharedPreferences("user", MODE_PRIVATE).edit().clear().apply()
                finishAffinity()
                startActivity(Intent(this@Admin_MainActivity, MainActivity::class.java))
            }
                .setNegativeButton("No"){p,_->
                    p.dismiss()
                }
            show()
        }
    }
}