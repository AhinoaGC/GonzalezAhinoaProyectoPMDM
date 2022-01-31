package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.retrofit

import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.entities.Token
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.entities.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("users/signup")
    fun signup(@Body user: User): Call<User>

    @POST("users/login")
    fun login(@Body user: User): Call<Token>
}