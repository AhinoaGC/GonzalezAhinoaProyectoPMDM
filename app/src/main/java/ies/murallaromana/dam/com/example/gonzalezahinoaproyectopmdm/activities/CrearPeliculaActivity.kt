package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.squareup.picasso.Picasso
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.R
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.databinding.ActivityCrearPeliculaBinding
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.App.Companion.peliculas
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.DatosPreferences
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.retrofit.ApiService
import ies.murallaromana.dam.com.example.pruebalistas.model.data.PeliculasDaoMockImpl
import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CrearPeliculaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrearPeliculaBinding
    private lateinit var pelicula: Pelicula
    private  lateinit var pre: DatosPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearPeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        pre = DatosPreferences(this)


        //Botón para guardar las peliculas creadas
        binding.brGuardarPelicula.setOnClickListener {
            val titulo = binding.etTitulo.text.toString()
            val genero = binding.edGenero.text.toString()
            val director = binding.edDirector.text.toString()
            val puntuacion = binding.estrellas2.rating.toString()
            val imagen = binding.eUrlImg.text.toString()
            val duracion = binding.etDuracion.text.toString()
            val ano = binding.etAno.text.toString()
            val resumen = binding.etResumen.text.toString()
            val video = binding.eUrlVideo.text.toString()
            val numero = "45345453"
            pelicula= Pelicula(null,numero,titulo,genero,director,puntuacion,imagen,duracion,ano,resumen,video)


            val token=pre.recuperarToken()
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://damapi.herokuapp.com/api/v1/")
                .build()
            val service: ApiService = retrofit.create(ApiService::class.java)
            val call = service.crear("Bearer" + token,pelicula)

            call.enqueue(object : Callback<Unit> {
                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.d("respuesta: onFailure", t.toString())
                }

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.code() > 299 || response.code() < 200) {
                        Toast.makeText(applicationContext, "Error en la creación", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(applicationContext, "Pelicula creada", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            })
        }
    }
}
