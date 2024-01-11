package com.example.geographicalprofilling.Responses

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST("user.php")
    fun createUser(
        @Field("name")name:String,
        @Field("mail")mail:String,
        @Field("password")password:String,
        @Field("mobile")mobile:String

    ):Call<CommonResponse>
    @FormUrlEncoded
    @POST("user.php")
    fun login(
        @Field("mail")mail:String,
        @Field("password")password:String,
        @Field("con")con:String
    ):Call<LoginResponse>
@FormUrlEncoded
@POST("addingLocation.php")
fun addingLocations(
    @Field("location")location:String,
    @Field("record")record:String,
    @Field("zone")zone:String,
    @Field("num")num:Int,
    @Field("size")size:Int
):Call<CommonResponse>
@FormUrlEncoded
@POST("update.php")
fun gettingPlaces(
    @Field("condition")condition:String
):Call<LocationResopnse>
@FormUrlEncoded
@POST("update.php")
fun updatingPlaces(
    @Field("id")id:Int,
    @Field("record")record:String,
    @Field("selected")selected:String
):Call<CommonResponse>

@FormUrlEncoded
@POST("getplaces.php")
     fun getnearme(
    @Field("first")first: String,
    @Field("second")second: String):Call<LocationResopnse>
}


