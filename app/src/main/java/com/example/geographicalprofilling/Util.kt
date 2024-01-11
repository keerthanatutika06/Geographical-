package com.example.geographicalprofilling

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.text.HtmlCompat

fun View.toast(message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
fun logsuc(message:String){
    Log.i("gographinsucce",message)
}
fun logfail(message:String){
    Log.i("gographinfaile",message)
}
fun spanned(message: String)=HtmlCompat.fromHtml(message,HtmlCompat.FROM_HTML_OPTION_USE_CSS_COLORS)
