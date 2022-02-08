package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.retrofit

import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.entities.Token
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.entities.User
import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("movies")
    fun getAll(@Header("Authorization:") token:String): Call<List<Pelicula>>

    @DELETE("movies")
    fun delete(@Header("Authorization:") token: String,
               @Body pelicula: Pelicula): Call<Unit>
    @POST("movies")
    fun crear(@Header("Authorization:") token: String,
              @Body pelicula: Pelicula): Call<Unit>

    @DELETE("movies/{id}")
    fun delete(@Header("Authorization:") token: String,
               @Path("id") id:String):Call<Pelicula>

    @GET("movies/{id}?")
    fun getId(@Header("Authorization:") token: String,
              @Path("id") id:String?):Call<Pelicula>

    @PUT("movies")
    fun editar(@Header("Authorization:") token: String,
               @Body pelicula: Pelicula): Call<Pelicula>
}