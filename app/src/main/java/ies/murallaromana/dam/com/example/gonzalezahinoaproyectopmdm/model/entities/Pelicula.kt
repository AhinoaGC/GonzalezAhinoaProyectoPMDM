package ies.murallaromana.dam.com.example.pruebalistas.model.entities

import java.io.Serializable

data class Pelicula(
    var numero: String,
    var titulo: String,
    var genero: String,
    var director: String,
    var puntuacion: String,
    var url: String,
    var duracion: String,
    var ano: String,
    var resumen: String,
    var urlVideo: String

): Serializable {
    //fun getNombreApellidos(): String{
    //   return nombre+" "+apellidos
    //}

    //fun getNombreApellidos(): String{
    //    return "El nombre es $nombre y el apellido es $apellidos"
    //}


}
