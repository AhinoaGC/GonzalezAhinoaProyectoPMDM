package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.entities

import com.google.gson.annotations.SerializedName

class Usuario(
    var id: Int?,
    var email: String,
    @SerializedName("password")var contraseña: String)