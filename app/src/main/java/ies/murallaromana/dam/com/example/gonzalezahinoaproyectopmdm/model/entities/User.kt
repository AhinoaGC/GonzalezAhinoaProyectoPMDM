package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.entities

import com.google.gson.annotations.SerializedName

class User(
    var id: String?,
    var email: String,
    @SerializedName("password")var contraseña: String)