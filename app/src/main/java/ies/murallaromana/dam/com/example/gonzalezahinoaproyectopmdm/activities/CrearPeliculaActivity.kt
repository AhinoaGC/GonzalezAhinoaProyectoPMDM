package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.youtube.player.YouTubePlayerFragment
import com.squareup.picasso.Picasso
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.R
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.Utils.ValidacionesUtils
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.databinding.ActivityCrearPeliculaBinding
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.DatosPreferences
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.retrofit.ApiService
import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CrearPeliculaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrearPeliculaBinding
    private lateinit var pelicula: Pelicula
    private lateinit var pre: DatosPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearPeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        pre = DatosPreferences(this)
        val id = intent.extras?.get("id") as String?
        binding.brGuardarPelicula.isEnabled = true
        if (id == null) {
            binding.brGuardarPelicula.setOnClickListener {
                binding.progressBar.visibility = View.VISIBLE
                binding.brGuardarPelicula.isEnabled = false
                val titulo = binding.etTitulo.text.toString()
                val genero = binding.edGenero.text.toString()
                val director = binding.edDirector.text.toString()
                val puntuacion = binding.estrellas2.rating.toString()
                val imagen = binding.eUrlImg.text.toString()
                val duracion = binding.etDuracion.text.toString()
                val ano = binding.etAno.text.toString()
                val resumen = binding.etResumen.text.toString()
                val video = binding.eUrlVideo.text.toString()
                val numero = binding.eNumero.text.toString()
                pelicula = Pelicula(
                    null,
                    numero,
                    titulo,
                    genero,
                    director,
                    puntuacion,
                    imagen,
                    duracion,
                    ano,
                    resumen,
                    video
                )
                val token = pre.recuperarToken()
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://damapi.herokuapp.com/api/v1/")
                    .build()
                val service: ApiService = retrofit.create(ApiService::class.java)
                val call = service.crear("Bearer" + token, pelicula)

                call.enqueue(object : Callback<Unit> {
                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        Log.d("respuesta: onFailure", t.toString())
                        binding.progressBar.visibility = View.GONE
                        binding.brGuardarPelicula.isEnabled = true
                    }

                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        if (response.code() > 299 || response.code() < 200) {
                            Toast.makeText(
                                applicationContext,
                                R.string.errorCreacion,
                                Toast.LENGTH_SHORT
                            ).show()
                            binding.progressBar.visibility = View.GONE
                            binding.brGuardarPelicula.isEnabled = true
                            if (response.code() == 401) {
                                Toast.makeText(
                                    applicationContext,
                                    R.string.inicioSesionCaducado,
                                    Toast.LENGTH_SHORT
                                ).show()
                                ValidacionesUtils().reiniciarApp(pre, applicationContext)
                            }
                        } else {
                            Toast.makeText(
                                applicationContext,
                                R.string.peliculaCreada,
                                Toast.LENGTH_SHORT
                            ).show()
                            binding.progressBar.visibility = View.GONE
                            binding.brGuardarPelicula.isEnabled = true
                            finish()
                        }
                    }
                })
            }
            binding.btImagen.setOnClickListener {
                if(binding.eUrlImg.text.toString().isNullOrEmpty()){
                    Toast.makeText(
                        applicationContext,
                        R.string.errorImagen,
                        Toast.LENGTH_SHORT
                    ).show()
                }else{
                    Picasso.get().load(binding.eUrlImg.text.toString()).into(binding.imP)
                }
            }
        } else {
            val token = pre.recuperarToken()
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://damapi.herokuapp.com/api/v1/")
                .build()

            val service: ApiService = retrofit.create(ApiService::class.java)
            val call = service.getId("Bearer" + token, id)
            val context = this
            call.enqueue(object : Callback<Pelicula> {
                override fun onFailure(call: Call<Pelicula>, t: Throwable) {
                    Log.d("respuesta: onFailure", t.toString())
                }

                override fun onResponse(call: Call<Pelicula>, response: Response<Pelicula>) {
                    if (response.code() > 299 || response.code() < 200) {
                        Toast.makeText(
                            context,
                            R.string.errorRecuperación,
                            Toast.LENGTH_SHORT
                        ).show()
                        if (response.code() > 401 || response.code() < 500) {
                            Toast.makeText(
                                applicationContext,
                                R.string.inicioSesionCaducado,
                                Toast.LENGTH_SHORT
                            ).show()
                            ValidacionesUtils().reiniciarApp(pre, applicationContext)
                        }

                    } else {
                        val titulo = response.body()?.titulo.toString()
                        val imagen = response.body()?.url.toString()
                        val descripcion = response.body()?.resumen.toString()
                        val director = response.body()?.director.toString()
                        val genero = response.body()?.genero.toString()
                        val nota = response.body()?.puntuacion.toString()
                        val numero = response.body()?.numero.toString()
                        val tiempo = response.body()?.duracion.toString()
                        val id = response.body()?.id
                        val video = response.body()?.urlVideo
                        val ano = response.body()?.ano

                        pelicula = Pelicula(
                            id,
                            numero,
                            titulo,
                            genero,
                            director,
                            nota,
                            imagen,
                            tiempo,
                            ano,
                            descripcion,
                            video
                        )
                        binding.etTitulo.setText(pelicula.titulo)
                        binding.edGenero.setText(pelicula.genero)
                        binding.edDirector.setText(pelicula.director)
                        binding.etAno.setText(pelicula.ano)
                        binding.etDuracion.setText(pelicula.duracion)
                        binding.etResumen.setText(pelicula.resumen)
                        if(pelicula?.puntuacion==null){
                            binding.estrellas2.rating = 0F
                        }else{
                            binding.estrellas2.rating = pelicula?.puntuacion!!.toFloat()
                        }
                        binding.eUrlVideo.setText(pelicula.urlVideo)
                        binding.eUrlImg.setText(pelicula.url)
                        binding.eNumero.setText(pelicula.numero)
                        Picasso.get().load(pelicula.url).into(binding.imP)

                        binding.btImagen.setOnClickListener {
                            Picasso.get().load(binding.eUrlImg.text.toString()).into(binding.imP)
                        }

                        binding.brGuardarPelicula.setOnClickListener {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.brGuardarPelicula.isEnabled = false
                            val titulo = binding.etTitulo.text.toString()
                            val genero = binding.edGenero.text.toString()
                            val director = binding.edDirector.text.toString()
                            val puntuacion = binding.estrellas2.rating.toString()
                            val imagen = binding.eUrlImg.toString()
                            val duracion = binding.etDuracion.text.toString()
                            val ano = binding.etAno.text.toString()
                            val resumen = binding.etResumen.text.toString()
                            val video = binding.eUrlVideo.text.toString()
                            val numero = binding.eNumero.text.toString()

                            val peli = Pelicula(
                                pelicula.id,
                                numero,
                                titulo,
                                genero,
                                director,
                                puntuacion,
                                imagen,
                                duracion,
                                ano,
                                resumen,
                                video
                            )
                            val token = pre.recuperarToken()
                            val retrofit = Retrofit.Builder()
                                .addConverterFactory(GsonConverterFactory.create())
                                .baseUrl("https://damapi.herokuapp.com/api/v1/")
                                .build()

                            val service: ApiService = retrofit.create(ApiService::class.java)
                            val call = service.editar("Bearer" + token, peli)
                            call.enqueue(object : Callback<Pelicula> {
                                override fun onFailure(call: Call<Pelicula>, t: Throwable) {
                                    Log.d("respuesta: onFailure", t.toString())
                                    binding.progressBar.visibility = View.GONE
                                    binding.brGuardarPelicula.isEnabled = true
                                }

                                override fun onResponse(
                                    call: Call<Pelicula>,
                                    response: Response<Pelicula>
                                ) {
                                    if (response.code() > 299 || response.code() < 200) {
                                        Toast.makeText(context, R.string.errorEditar, Toast.LENGTH_SHORT).show()
                                        binding.progressBar.visibility = View.GONE
                                        binding.brGuardarPelicula.isEnabled = true
                                        if (response.code() == 401 || response.code() == 500) {
                                            ValidacionesUtils().reiniciarApp(pre, applicationContext)
                                        }
                                    } else {
                                        Toast.makeText(context, R.string.editarCorrectamente, Toast.LENGTH_SHORT).show()
                                        binding.progressBar.visibility = View.GONE
                                        finish()
                                    }
                                }
                            })
                        }
                    }
                }
            })
        }
    }
}
