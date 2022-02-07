package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.squareup.picasso.Picasso
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.R
import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import androidx.appcompat.app.AppCompatActivity

import com.google.android.youtube.player.YouTubePlayerFragment
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.databinding.ActivityDetallePeliculaBinding
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.App.Companion.peliculas
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.DatosPreferences
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PeliculaActivity :  AppCompatActivity(), YouTubePlayer.OnInitializedListener {


    private lateinit var pelicula: Pelicula
    private lateinit var binding: ActivityDetallePeliculaBinding
    private  lateinit var pre: DatosPreferences
    val api_key =  "AIzaSyDloPeo-4_YVthgz5zeOUakEesajpYItrI"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallePeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        pelicula = intent.extras?.get("pelicula") as Pelicula
        pre = DatosPreferences(this)
        setTitle(pelicula.titulo)

       val youTubePlayerFragment = fragmentManager.findFragmentById(R.id.youtubeplayer_fragment) as YouTubePlayerFragment
        youTubePlayerFragment.initialize(api_key, this)


        binding.tvGeneroPelicula.text = "Género: "+pelicula.genero
        binding.tvDirectorPelicula.text = "Director: "+pelicula.director
        binding.tvAno.text = "Año: " + pelicula.ano
        binding.tvResumen.text = pelicula.resumen
        if(pelicula.puntuacion==null){
            binding.estrellas.rating = 0F
        }else{
            binding.estrellas.rating = pelicula.puntuacion!!.toFloat()
        }
        Picasso.get().load(pelicula.url).into(binding.imP)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalle_pelicula, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_call -> {
                val phoneNo: String? = pelicula.numero
                if (!TextUtils.isEmpty(phoneNo)) {
                    val dial = "tel:$phoneNo"
                    startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(dial)))
                } else {
                    Toast.makeText(this@PeliculaActivity, "Error", Toast.LENGTH_SHORT)
                        .show()
                }
                return false
            }
            R.id.action_delete -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("¿Quieres borrar la pelicula?")
                builder.setIcon(R.drawable.ic_baseline_movie_filter_24)
                builder.setPositiveButton("Cancelar") { dialog, which ->
                    Toast.makeText(this, "La pelicula no se ha borrado.", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("Borrar") { dialog, which ->
                    borrarPelicula()
                    Toast.makeText(this, "Pelicula eliminada", Toast.LENGTH_SHORT).show()
                    finish()
                }
                builder.show()
                return false
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        p2: Boolean
    ) {
        if(pelicula.urlVideo!=null){
            val urlVi = pelicula.urlVideo
            player?.loadVideo(urlVi)
            player?.play()
        }else{
            Toast.makeText(this, "No se pueded cargar el video", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        Toast.makeText(this@PeliculaActivity, "Error al cargar video.", Toast.LENGTH_SHORT).show()
    }

    fun borrarPelicula(){

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://damapi.herokuapp.com/api/v1/")
            .build()

        val token=pre.recuperarToken()
        val service: ApiService = retrofit.create(ApiService::class.java)
        val call = service.delete("Bearer" + token,pelicula.id!!)

        call.enqueue(object : Callback<Pelicula> {
            override fun onFailure(call: Call<Pelicula>, t: Throwable) {
                Log.d("respuesta: onFailure", t.toString())
            }

            override fun onResponse(call: Call<Pelicula>, response: Response<Pelicula>) {
                if (response.code() > 299 || response.code() < 200) {
                    Toast.makeText(applicationContext,"La pelicula no se ha podido borrar.",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(applicationContext,"La pelicula ha sido eliminada correctamente.",Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        })

    }
}