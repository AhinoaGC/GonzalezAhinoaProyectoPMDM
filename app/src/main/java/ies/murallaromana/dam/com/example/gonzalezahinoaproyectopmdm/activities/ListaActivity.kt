package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.R
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.databinding.ActivityListaBinding
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.App.Companion.peliculas
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.retrofit.ApiService
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.retrofit.ClienteRetrofit
import ies.murallaromana.dam.com.example.pruebalistas.adapters.listaPeliculasAdapters
import ies.murallaromana.dam.com.example.pruebalistas.model.data.PeliculasDaoMockImpl
import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

class ListaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaBinding
    private lateinit var adapters: listaPeliculasAdapters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setTitle("PopFilms")

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://damapi.herokuapp.com/api/v1/")
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        //token de prueba
        apiService.getAll("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYxZjc5YmY4ODgxM2Q2ZTRlNDVmZWQzMSIsImlhdCI6MTY0MzYxNzI5NCwiZXhwIjoxNjQzNzAzNjk0fQ.DNwkZoMY88o9N3bQ2vNWNVHJmA2bQTJLATVfdcT2zx4")

        val llamadaApi: Call<List<Pelicula>> = ClienteRetrofit.apiRetrofit.getPeliculas()
        llamadaApi.enqueue(object: Callback<List<Pelicula>> {
            override fun onResponse(
                call: Call<List<Pelicula>>,
                response: Response<List<Pelicula>>
            ) {
                Toast.makeText(applicationContext,response.body().toString(),Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<List<Pelicula>>, t: Throwable) {
                Log.d("PRUEBA",t.message.toString())
            }
        })



        val layoutManager = LinearLayoutManager(this)
        adapters = listaPeliculasAdapters(peliculas, this)

        binding.rvListaPeliculas.layoutManager = layoutManager
        binding.rvListaPeliculas.adapter = adapters

        binding.fbMas.setOnClickListener {
            if(binding.fbAdd.visibility==View.GONE){
                binding.fbSalir.show()
                binding.fbAdd.show()
                binding.fbMas.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_remove_24))
            }else{
                binding.fbSalir.hide()
                binding.fbAdd.hide()
                binding.fbMas.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_add_24))

            }

        }

        binding.fbAdd.setOnClickListener {
            val intent = Intent(this, CrearPeliculaActivity::class.java)
            startActivity(intent)
        }

        binding.fbSalir.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("¿Quieres cerrar sesión?")
            builder.setIcon(R.drawable.ic_baseline_login_24)
            builder.setPositiveButton("Cancelar") { dialog, which ->
                Toast.makeText(this, "No se ha cerrado la sesión.", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Salir") { dialog, which ->
                Toast.makeText(this, "Sesión cerrrada.", Toast.LENGTH_SHORT).show()
                finish()
            }
            builder.show()
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
        adapters!!.notifyDataSetChanged()
    }
}