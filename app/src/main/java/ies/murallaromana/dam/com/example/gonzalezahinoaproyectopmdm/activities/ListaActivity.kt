package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.R
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.databinding.ActivityListaBinding
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.App.Companion.peliculas
import ies.murallaromana.dam.com.example.pruebalistas.adapters.listaPeliculasAdapters
import ies.murallaromana.dam.com.example.pruebalistas.model.data.PeliculasDaoMockImpl
import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula
import java.util.*
import kotlin.collections.ArrayList

class ListaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaBinding
    private lateinit var adapters: listaPeliculasAdapters
    private lateinit var lista: ArrayList<Pelicula>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle("PopFilms")
//        supportActionBar?.hide()

//        val peliculasDao = PeliculasDaoMockImpl()
//        val listaPeliculas = peliculasDao.getTodos()
        lista = peliculas
        val layoutManager = LinearLayoutManager(this)
        adapters = listaPeliculasAdapters(lista, this)

        binding.rvListaPeliculas.layoutManager = layoutManager
        binding.rvListaPeliculas.adapter = adapters

        val intent = Intent(this, CrearPeliculaActivity::class.java)
        binding.fbMas.setOnClickListener {
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lista, menu)
        val item = menu?.findItem(R.id.search)
        var sv=item?.actionView as SearchView
        sv.setImeOptions(EditorInfo.IME_ACTION_DONE)
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(txt: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapters.getFilter().filter(newText)
                adapters!!.notifyDataSetChanged()
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
    }

    override fun onResume() {
        super.onResume()
//        val adapter = listaPeliculasAdapters(peliculas, this)
        adapters!!.notifyDataSetChanged()
        binding.rvListaPeliculas.adapter = adapters
    }
}