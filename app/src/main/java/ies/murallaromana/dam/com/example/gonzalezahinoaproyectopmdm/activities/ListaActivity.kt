package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.R
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.databinding.ActivityListaBinding
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.App.Companion.peliculas
import ies.murallaromana.dam.com.example.pruebalistas.adapters.listaPeliculasAdapters
import ies.murallaromana.dam.com.example.pruebalistas.model.data.PeliculasDaoMockImpl

class ListaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle("PopFilms")

        val peliculasDao = PeliculasDaoMockImpl()
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lista, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save_or_update -> {
                // Hago cosas y al final retorno un valor
                return false
            }
            R.id.action_delete -> {
                return false
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
    }

    override fun onResume() {
        super.onResume()
        val adapter = listaPeliculasAdapters(peliculas, this)
        binding.rvListaPeliculas.adapter = adapter
    }
}