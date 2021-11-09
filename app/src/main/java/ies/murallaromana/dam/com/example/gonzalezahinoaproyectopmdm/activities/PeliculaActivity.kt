package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.webkit.WebViewClient
import android.widget.ScrollView
import com.squareup.picasso.Picasso
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.R
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.databinding.ActivityListaBinding
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.databinding.ActivityPeliculaBinding
import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula

class PeliculaActivity : AppCompatActivity() {

    companion object{
        lateinit var pelicula: Pelicula
    }
    private lateinit var binding: ActivityPeliculaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pelicula = intent.extras?.get("pelicula") as Pelicula

        binding.tvNombre.text =pelicula.titulo
        binding.tvGeneroPelicula.text ="Género: "+pelicula.genero
        binding.tvDirectorPelicula.text ="Director: "+pelicula.director
        binding.tvAno.text ="Año: "+pelicula.ano
        binding.tvDuracion.text ="Duración: "+pelicula.duracion
        binding.tvResumen.text ="Sinopsis:\n"+pelicula.resumen
        binding.tvResumen.setMovementMethod(ScrollingMovementMethod())
        binding.estrellas.rating= pelicula.puntuacion.toFloat()
        binding.WebView.webViewClient = WebViewClient()
        binding.WebView.settings.javaScriptEnabled=true
        binding.WebView.loadUrl(pelicula.urlVideo)
        binding.WebView.settings.setSupportZoom(true)
        Picasso.get().load(pelicula.url).into(binding.imPelicula)


    }
}