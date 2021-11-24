package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
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
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.databinding.ActivityDetalleBinding
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.App.Companion.peliculas


class PeliculaActivity :  AppCompatActivity(), YouTubePlayer.OnInitializedListener {


    private lateinit var pelicula: Pelicula
    private lateinit var binding: ActivityDetalleBinding
    val api_key =  "AIzaSyDloPeo-4_YVthgz5zeOUakEesajpYItrI"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pelicula = intent.extras?.get("pelicula") as Pelicula
        setTitle(pelicula.titulo)

//        binding.videoYoutube.initialize(api_key, this)

       val youTubePlayerFragment = fragmentManager.findFragmentById(R.id.youtubeplayer_fragment) as YouTubePlayerFragment
        youTubePlayerFragment.initialize(api_key, this)

        binding.tvNombre.text = pelicula.titulo
        binding.tvGeneroPelicula.text = "Género: " + pelicula.genero
        binding.tvDirectorPelicula.text = "Director: " + pelicula.director
        binding.tvAno.text = "Año: " + pelicula.ano
        binding.tvDuracion.text = "Duración: " + pelicula.duracion
        binding.tvResumen.text = "Sinopsis:\n" + pelicula.resumen
        binding.tvResumen.setMovementMethod(ScrollingMovementMethod())
        binding.estrellas.rating = pelicula.puntuacion.toFloat()
        Picasso.get().load(pelicula.url).into(binding.imPelicula)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalle_pelicula, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.action_save_or_update -> {
//            // Hago cosas y al final retorno un valor
//                return false
//            }
//            R.id.action_delete -> {
            peliculas.remove(pelicula)
                Toast.makeText(this, "Pelicula eliminada", Toast.LENGTH_SHORT).show()
                finish()
        return super.onOptionsItemSelected(item)
//                return false
//            }
//            else -> super.onOptionsItemSelected(item)
        }
//    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        p2: Boolean
    ) {
        player?.loadVideo(pelicula.urlVideo)
        player?.play()
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        Toast.makeText(this@PeliculaActivity, "Error al cargar video.", Toast.LENGTH_SHORT).show()
    }
}