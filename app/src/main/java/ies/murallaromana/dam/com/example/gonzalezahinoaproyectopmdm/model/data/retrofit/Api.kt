package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.retrofit

import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("movies")
    fun getPeliculas(): Call<List<Pelicula>>

    /*
    TODO: declarar todos los metadatos de la API siguiendo la documentacion
     */
}