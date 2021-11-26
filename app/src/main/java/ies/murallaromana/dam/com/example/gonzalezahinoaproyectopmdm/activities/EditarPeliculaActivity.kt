package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.method.ScrollingMovementMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.squareup.picasso.Picasso
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.databinding.ActivityCrearPeliculaBinding
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.databinding.ActivityEditarPeliculaBinding
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.App.Companion.peliculas
import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula

class EditarPeliculaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditarPeliculaBinding
    private val pickImage = 100
    private var imageUri: Uri? = null
    private lateinit var pelicula: Pelicula
    private lateinit var peliculaNueva: Pelicula

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarPeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        //Intent para coger una imagen de la galería
        binding.btImagen.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
        pelicula = intent.extras?.get("pelicula") as Pelicula

        binding.etTitulo.setText(pelicula.titulo)
        binding.edGenero.setText(pelicula.genero)
        binding.edDirector.setText("Director: " + pelicula.director)
        binding.etAno.setText("Año: " + pelicula.ano)
        binding.etDuracion.setText("Duración: " + pelicula.duracion)
        binding.etResumen.setText("Sinopsis:\n" + pelicula.resumen)
        binding.etResumen.setMovementMethod(ScrollingMovementMethod())
        binding.estrellas2.rating = pelicula.puntuacion.toFloat()
        binding.eUrlVideo.setText(pelicula.urlVideo);
        Picasso.get().load(pelicula.url).into(binding.imP)

        binding.brGuardarPelicula.setOnClickListener {
            val titulo = binding.etTitulo.text.toString()
            val genero = binding.edGenero.text.toString()
            val director = binding.edDirector.text.toString()
            val puntuacion = binding.estrellas2.rating.toString()
            val imagen = imageUri.toString()
            val duracion = binding.etDuracion.text.toString()
            val ano = binding.etAno.text.toString()
            val resumen = binding.etResumen.text.toString()
            val video = binding.eUrlVideo.text.toString()
            peliculaNueva=Pelicula("75757",titulo,genero,director,puntuacion,imagen,duracion,ano,resumen,video)
            peliculas.remove(pelicula)
            peliculas.add(peliculaNueva)
            Toast.makeText(this, "Pelicula guardada", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            Picasso.get().load(imageUri).into(binding.imP)
        }
    }

}
