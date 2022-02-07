package ies.murallaromana.dam.com.example.pruebalistas.model.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pelicula(
    var id: String?,
    @SerializedName("directorPhone")var numero: String?,
    @SerializedName("title")var titulo: String,
    @SerializedName("genre")var genero: String?,
    @SerializedName("directorFullname")var director: String?,
    @SerializedName("rating")var puntuacion: String,
    @SerializedName("imageUrl")var url: String?,
    @SerializedName("runtimeMinutes")var duracion: String,
    var ano: String,
    @SerializedName("description")var resumen: String,
    @SerializedName("trailerUrl")var urlVideo: String?

): Serializable
