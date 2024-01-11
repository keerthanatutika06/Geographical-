package com.example.geographicalprofilling.User

import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geographicalprofilling.MainActivity
import com.example.geographicalprofilling.databinding.ActivityDashBoardBinding
import com.example.geographicalprofilling.spanned
import com.example.geographicalprofilling.toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.DecimalFormat

class DashBoard : AppCompatActivity() {
    private val bind by lazy {
        ActivityDashBoardBinding.inflate(layoutInflater)
    }

    val decimal=DecimalFormat("##.########")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
        getSharedPreferences("user", MODE_PRIVATE).apply {
            bind.create.text=spanned("<b>Name : </b>${getString("name","")}<br>" +
                    "<b>Mail : </b>${getString("mail","")}<br>" +
                    "<b>Mobile Number : </b>${getString("mobile","")}")
        }
bind.logout.setOnClickListener {
    dialog()
}
        bind.getlatlon.setOnClickListener {
            val from=bind.from.text.toString().trim()
            val to=bind.tolocation.text.toString().trim()
if(from.isEmpty()){
    it.toast("Please enter from location !!")
}else if(to.isEmpty()){
    it.toast("Please enter to location !!")
}else{

        val array = ArrayList<String>()
        try {
            Geocoder(this@DashBoard).getFromLocationName(from, 1)[0].apply {
                bind.textInputLayout.helperText = spanned("✔")
                array.add(decimal.format(latitude))
                array.add(decimal.format(longitude))
                array.add(adminArea)
            }
        } catch (e: Exception) {
            bind.textInputLayout.helperText = spanned("Invalid From Location!!")
        }
        try {
            Geocoder(this@DashBoard).getFromLocationName(to, 1)[0].apply {
                bind.textInputLayout2.helperText = spanned("✔")
                array.add(decimal.format(latitude))
                array.add(decimal.format(longitude))
                array.add(adminArea)
            }
        } catch (e: Exception) {
            bind.textInputLayout2.helperText = spanned("Invalid To Location !!")
        }

        if (array.size == 6) {
            Intent(this@DashBoard, MapsActivity::class.java).apply {
                putExtra("locations", array)
                startActivity(this)
            }
        }



}

        }
 }

    private fun dialog() {
        MaterialAlertDialogBuilder(this).apply {
            setTitle("Do you want  to logout")
            setPositiveButton("Yes"){m,_->
                m.dismiss()
                getSharedPreferences("user", MODE_PRIVATE).edit().clear().apply()
                finishAffinity()
                startActivity(Intent(this@DashBoard,MainActivity::class.java))
            }
                .setNegativeButton("No"){p,_->
                    p.dismiss()
                }
        show()
        }
    }

    override fun onResume() {
        super.onResume()
        bind.from.text?.clear()
        bind.tolocation.text?.clear()
        bind.textInputLayout.helperText=""
        bind.textInputLayout2.helperText=""
    }
}