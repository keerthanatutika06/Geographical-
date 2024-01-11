package com.example.geographicalprofilling.Admin

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.geographicalprofilling.R
import com.example.geographicalprofilling.toast

class AddPlaces : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_places)
        val edit = findViewById<EditText>(R.id.edit)
        val card = findViewById<CardView>(R.id.card)
        val text2 = findViewById<TextView>(R.id.texdt2)
        val nextbtn = findViewById<TextView>(R.id.nextbtn)
        val array = ArrayList<String>()
        card.setOnClickListener {
            val text = edit.text.toString()
            if (text.trim().isNotEmpty()) {
                array.add(text)
                if (array.isEmpty() && array.contains(text)) {
                    it.toast("Please Enter a place")
                } else {
                    var num = 1
                    val size = array.size
                    val string = StringBuilder()/*
                    Toast.makeText(this, "$size", Toast.LENGTH_SHORT).show()*/
                    for (item in array) {
                        string.append("$num)" + item + "\n")
                        num++
                    }
                    text2.setText(string)
                    edit.setText("")
                }
            } else {
                it.toast("Please Enter a place name")
            }

        }
        nextbtn.setOnClickListener {
            val text = edit.text.toString()
            if (text.isEmpty() && array.isEmpty()) {
                it.toast("Add Some Places")
            } else {
                val int = Intent(this, Chooser::class.java)
                int.putExtra("data", array)
                startActivity(int)
            }
        }


    }
}