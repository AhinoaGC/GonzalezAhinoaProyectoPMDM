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
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.App.Companion.peliculas
import ies.murallaromana.dam.com.example.pruebalistas.model.data.PeliculasDaoMockImpl
import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula


class CrearPeliculaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrearPeliculaBinding
    private val pickImage = 100
    private var imageUri: Uri? = null
    private lateinit var pelicula: Pelicula

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearPeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        //Botón para guardar las peliculas creadas
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

            pelicula= Pelicula("75757","676767676",titulo,genero,director,puntuacion,imagen,duracion,ano,resumen,video)
            peliculas.add(pelicula)
            Toast.makeText(this, "Pelicula creada", Toast.LENGTH_SHORT).show()
            finish()
        }
        //Intent para coger una imagen de la galería
        binding.btImagen.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
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
