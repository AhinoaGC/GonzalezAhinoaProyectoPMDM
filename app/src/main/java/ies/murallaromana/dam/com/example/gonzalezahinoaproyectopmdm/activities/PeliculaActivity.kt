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
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.google.gson.annotations.SerializedName
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.Utils.ValidacionesUtils
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
                    Toast.makeText(this@PeliculaActivity, R.string.errorLlamada, Toast.LENGTH_SHORT)
                        .show()
                }
                return false
            }
            R.id.action_delete -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(R.string.borrarPelicula)
                builder.setIcon(R.drawable.ic_baseline_movie_filter_24)
                builder.setPositiveButton(R.string.cancelar) { dialog, which ->
                    Toast.makeText(this, R.string.cancelarAccion, Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton(R.string.borrar) { dialog, which ->
                    borrarPelicula()
                    finish()
                }
                builder.show()
                return false
            }
            R.id.action_edit -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(R.string.editarPelicula)
                builder.setIcon(R.drawable.ic_baseline_movie_filter_24)
                builder.setPositiveButton(R.string.cancelar) { dialog, which ->
                    Toast.makeText(this, R.string.cancelarAccion, Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton(R.string.editar) { dialog, which ->
                    val intent = Intent(this, CrearPeliculaActivity::class.java)
                    intent.putExtra("id", pelicula?.id)
                    startActivity(intent)
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
            Toast.makeText(this, R.string.errorVideo, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        Toast.makeText(this@PeliculaActivity, R.string.errorVideo, Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(applicationContext,R.string.errorBorrado,Toast.LENGTH_SHORT).show()
                    if (response.code() > 401 || response.code() < 500) {
                        Toast.makeText(
                            applicationContext,
                            R.string.inicioSesionCaducado,
                            Toast.LENGTH_SHORT
                        ).show()
                        ValidacionesUtils().reiniciarApp(pre, applicationContext)
                    }
                }else{
                    Toast.makeText(applicationContext, R.string.borrarPeliculaConf, Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        })

    }

    override fun onResume() {
        super.onResume()
        pre = DatosPreferences(this)
        val id = intent.extras?.get("id") as String?
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

                    pelicula = Pelicula(id,numero,titulo,genero,director,nota,imagen,tiempo,ano,descripcion,video)

                    val youTubePlayerFragment = fragmentManager.findFragmentById(R.id.youtubeplayer_fragment) as YouTubePlayerFragment
                    youTubePlayerFragment.initialize(api_key, context)

                    setTitle(pelicula.titulo)
                    binding.tvGeneroPelicula.text = pelicula.genero
                    binding.tvDirectorPelicula.text = pelicula.director
                    binding.tvAno.text = pelicula.ano
                    binding.tvResumen.text = pelicula.resumen
                    if(pelicula.puntuacion==null){
                        binding.estrellas.rating = 0F
                    }else{
                        try {
                            binding.estrellas.rating = pelicula.puntuacion!!.toFloat()
                        }
                        catch (e: NumberFormatException) {
                            binding.estrellas.rating = 0F
                            Toast.makeText(applicationContext, R.string.errorFormato, Toast.LENGTH_SHORT).show()
                        }

                    }
                    Picasso.get().load(pelicula.url).into(binding.imP)
                }
            }
        })
    }
}