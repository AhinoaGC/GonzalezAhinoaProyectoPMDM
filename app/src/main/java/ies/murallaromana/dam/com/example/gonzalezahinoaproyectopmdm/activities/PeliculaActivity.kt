package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.squareup.picasso.Picasso
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.R
import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import android.widget.Toolbar

import com.google.android.material.tabs.TabLayout
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.databinding.ActivityDetallePeliculaBinding
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities.fragments.Detalle_Fragment
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities.fragments.Sinopsis_Fragment
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.adapters.detallePageAdapters
import com.google.android.youtube.player.YouTubePlayerView

import com.google.android.youtube.player.YouTubePlayerFragment





class PeliculaActivity :  AppCompatActivity(), YouTubePlayer.OnInitializedListener {


    private lateinit var pelicula: Pelicula
    private lateinit var binding: ActivityDetallePeliculaBinding
    val api_key =  "AIzaSyDloPeo-4_YVthgz5zeOUakEesajpYItrI"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallePeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pelicula = intent.extras?.get("pelicula") as Pelicula
        val youTubePlayerFragment = fragmentManager.findFragmentById(R.id.youtubeplayer_fragment) as YouTubePlayerFragment

        youTubePlayerFragment.initialize(api_key, this)
        //Fragments cambiar de detalle a sinopsis
        val pagerAdapter = detallePageAdapters(this)
        pagerAdapter.addFragment(Detalle_Fragment())
        pagerAdapter.addFragment(Sinopsis_Fragment())

        binding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) {
                tab, position ->
            if (position == 1)tab.setText("Sinopsis")
            if (position == 0)tab.setText("Detalle")}.attach()

        val bundle = Bundle()
        bundle.putString("titulo", pelicula.titulo)
        bundle.putString("ano", pelicula.ano)
        bundle.putString("director", pelicula.director)
        bundle.putString("duracion", pelicula.duracion)
        bundle.putString("puntuacion", pelicula.puntuacion)
        bundle.putString("foto", pelicula.url)
        var fragInfo: Fragment = Detalle_Fragment()
        fragInfo.setArguments(bundle)
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add( fragInfo, "R.id.fragment_container").commit();
//        binding.tvNombre.text = pelicula.titulo
//        binding.tvGeneroPelicula.text = "Género: " + pelicula.genero
//        binding.tvDirectorPelicula.text = "Director: " + pelicula.director
//        binding.tvAno.text = "Año: " + pelicula.ano
//        binding.tvDuracion.text = "Duración: " + pelicula.duracion
//        binding.tvResumen.text = "Sinopsis:\n" + pelicula.resumen
//        binding.tvResumen.setMovementMethod(ScrollingMovementMethod())
//        binding.estrellas.rating = pelicula.puntuacion.toFloat()
//        Picasso.get().load(pelicula.url).into(binding.imPelicula)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalle_pelicula, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save_or_update -> {
            // Hago cosas y al final retorno un valor
                return false
            }
            R.id.action_delete -> {
            // Hago cosas y al final pongo el valor a retornar
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