package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.squareup.picasso.Picasso
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.databinding.ActivityCrearPeliculaBinding


class CrearPeliculaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrearPeliculaBinding
    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearPeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lista = Intent(this, ListaActivity::class.java)

        //Botón para guardar las peliculas creadas
        binding.brGuardarPelicula.setOnClickListener {
            Toast.makeText(this, "Pelicula creada", Toast.LENGTH_SHORT).show()
            startActivity(lista)
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
