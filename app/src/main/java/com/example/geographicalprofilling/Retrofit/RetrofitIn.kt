package com.example.geographicalprofilling.Retrofit

import android.util.Base64
import com.example.geographicalprofilling.Responses.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitIn {
 private val AUTH="Basic"+ Base64.encodeToString("sss".toByteArray(),Base64.NO_WRAP)
    private val BASEURL="https://www.wizzie.online/GeoGraphical/"
    private val okhttps=okhttp3.OkHttpClient.Builder()
        .addInterceptor {
chain->
val original=chain.request()
            val requestbuil=original.newBuilder()
                .method(original.method(),original.body())
                .addHeader("Authorization", AUTH)
val request=requestbuil.build()
chain.proceed(request)
        }.build()
    val instance:Api by lazy {
val retrofit= Retrofit.Builder()
    .client(okhttps)
    .baseUrl(BASEURL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
        retrofit.create(Api::class.java)
    }

}