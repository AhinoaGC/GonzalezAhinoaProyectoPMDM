package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuItem
import android.view.Window
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
import androidx.constraintlayout.widget.ConstraintLayout
import android.widget.TextView





class PeliculaActivity :  AppCompatActivity(), YouTubePlayer.OnInitializedListener {


    private lateinit var pelicula: Pelicula
    private lateinit var binding: ActivityDetallePeliculaBinding
    val api_key =  "AIzaSyDloPeo-4_YVthgz5zeOUakEesajpYItrI"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallePeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        pelicula = intent.extras?.get("pelicula") as Pelicula
        setTitle(pelicula.titulo)

       val youTubePlayerFragment = fragmentManager.findFragmentById(R.id.youtubeplayer_fragment) as YouTubePlayerFragment
        youTubePlayerFragment.initialize(api_key, this)

        binding.tvGeneroPelicula.text = pelicula.genero
        binding.tvDirectorPelicula.text = pelicula.director
        binding.tvAno.text = "Año: " + pelicula.ano
//        binding.tvDuracion.text = "Duración: " + pelicula.duracion
        binding.tvResumen.text = pelicula.resumen
        binding.estrellas.rating = pelicula.puntuacion.toFloat()
//        Picasso.get().load(pelicula.url).into(binding.imPelicula)

//        binding.tcMostrarMas.setOnClickListener{
//            val height = "wrap_content" //your textview height
//
//            binding.tvResumen.layoutParams.height = height
//        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalle_pelicula, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_call -> {
                val phoneNo: String = pelicula.numero
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
                    peliculas.remove(pelicula)
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