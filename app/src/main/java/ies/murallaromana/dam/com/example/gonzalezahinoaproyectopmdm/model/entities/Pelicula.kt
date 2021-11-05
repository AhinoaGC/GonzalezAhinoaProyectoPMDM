package ies.murallaromana.dam.com.example.pruebalistas.model.entities

import java.io.Serializable

class Pelicula(
    var id: Long,
    var titulo: String,
    var genero: String,
    var director: String,
    var puntuacion: String,
    var url: String,
    var duracion: String,
    var ano: String,
    var resumen: String

): Serializable {
    //fun getNombreApellidos(): String{
    //   return nombre+" "+apellidos
    //}

    //fun getNombreApellidos(): String{
    //    return "El nombre es $nombre y el apellido es $apellidos"
    //}


}
