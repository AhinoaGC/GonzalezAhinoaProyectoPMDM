package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.method.ScrollingMovementMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.databinding.ActivityCrearPeliculaBinding
import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula

class EditarPeliculaActivity: AppCompatActivity() {

    private lateinit var binding: ActivityCrearPeliculaBinding
    private val pickImage = 100
    private var imageUri: Uri? = null
    private lateinit var pelicula: Pelicula

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearPeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        Picasso.get().load(pelicula.url).into(binding.imP)

        binding.brGuardarPelicula.setOnClickListener {

            pelicula.titulo= binding.etTitulo.text.toString()
            pelicula.genero= binding.edGenero.text.toString()
            pelicula.director= binding.edDirector.text.toString()
            pelicula.ano= binding.etAno.text.toString()
            pelicula.duracion= binding.etDuracion.text.toString()
            pelicula.resumen= binding.etResumen.text.toString()
            pelicula.puntuacion= binding.estrellas2.rating.toString()
            pelicula.url = imageUri.toString()
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
