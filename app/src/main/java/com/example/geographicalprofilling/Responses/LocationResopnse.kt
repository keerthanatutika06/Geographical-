package com.example.geographicalprofilling.Responses

import com.example.geographicalprofilling.Model.Location

data class LocationResopnse (val error:Boolean,val message:String,val data:ArrayList<Location>)