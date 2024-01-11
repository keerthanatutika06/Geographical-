package com.example.geographicalprofilling.Responses

import com.example.geographicalprofilling.Model.User

data class LoginResponse (
var error:Boolean,
var message:String,
var data:ArrayList<User>
)
