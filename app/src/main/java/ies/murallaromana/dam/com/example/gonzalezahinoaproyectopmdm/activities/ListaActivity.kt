package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.R
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.databinding.ActivityListaBinding
import ies.murallaromana.dam.com.example.pruebalistas.adapters.listaPeliculasAdapters
import ies.murallaromana.dam.com.example.pruebalistas.model.data.PeliculasDaoMockImpl

class ListaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val peliculasDao = PeliculasDaoMockImpl()
        peliculasDao.añadirPeliculasIniciales()
        val listaPeliculas = peliculasDao.getTodos()

//        val layoutManager = GridLayoutManager(this,2)
        val layoutManager = LinearLayoutManager(this)
        val adapter = listaPeliculasAdapters(listaPeliculas, this)

        binding.rvListaPeliculas.layoutManager = layoutManager
        binding.rvListaPeliculas.adapter = adapter


        val intent = Intent(this, CrearPeliculaActivity::class.java)
        binding.fbAdd.setOnClickListener{
            startActivity(intent)
        }
    }
}