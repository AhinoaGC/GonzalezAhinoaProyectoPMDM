package ies.murallaromana.dam.com.example.pruebalistas.model.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pelicula(
    var id: String,
    var numero: String,
    @SerializedName("title")var titulo: String,
    var genero: String,
    var director: String,
    @SerializedName("rating")var puntuacion: String,
    var url: String,
    @SerializedName("runtimeMinutes")var duracion: String,
    var ano: String,
    var resumen: String,
    var urlVideo: String

): Serializable
