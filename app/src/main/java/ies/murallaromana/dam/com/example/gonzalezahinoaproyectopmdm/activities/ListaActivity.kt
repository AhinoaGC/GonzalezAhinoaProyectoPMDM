package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Database
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.R
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.Utils.ValidacionesUtils
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.databinding.ActivityListaBinding
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.Dao.DataBasePeliculas
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.DatosPreferences
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.retrofit.ApiService
import ies.murallaromana.dam.com.example.pruebalistas.adapters.listaPeliculasAdapters
import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class ListaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaBinding
    private lateinit var adapters: listaPeliculasAdapters
    private lateinit var pre: DatosPreferences
    private var listaPelicula: ArrayList<Pelicula>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setTitle(R.string.nombreApp)
        activarBotones()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lista, menu)
        val item = menu?.findItem(R.id.search)
        var sv = item?.actionView as SearchView
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
        recargarPeliculas()
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                //for check internet over Bluetooth
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.load -> {
               recargarPeliculas()
                return false
            }else -> super.onOptionsItemSelected(item)
        }
    }

    fun getPeliculas(){
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://damapi.herokuapp.com/api/v1/")
            .build()

        val token = pre.recuperarToken()
        val service: ApiService = retrofit.create(ApiService::class.java)
        val call = service.getAll("Bearer" + token)

        call.enqueue(object : Callback<List<Pelicula>> {
            override fun onFailure(call: Call<List<Pelicula>>, t: Throwable) {
                Log.d("respuesta: onFailure", t.toString())

            }

            override fun onResponse(
                call: Call<List<Pelicula>>,
                response: Response<List<Pelicula>>
            ) {
                if (response.code() > 299 || response.code() < 200) {
                    Toast.makeText(
                        applicationContext,
                        R.string.errorLista,
                        Toast.LENGTH_SHORT
                    ).show()
                    if (response.code() > 401 || response.code() < 500) {
                        Toast.makeText(
                            applicationContext,
                            R.string.inicioSesionCaducado,
                            Toast.LENGTH_SHORT
                        ).show()
                        ValidacionesUtils().reiniciarApp(pre, applicationContext)
                    }
                } else {
                    val db = DataBasePeliculas.getDatabase(applicationContext)
                    val peliculasDao = db?.peliculasDao()
                    GlobalScope.launch(Dispatchers.IO) {
                        peliculasDao?.delete()
                        response.body()?.forEach { it -> it.idRoom = it.id!! }
                        peliculasDao?.insert(response.body())
                    }
                    val layoutManager = LinearLayoutManager(applicationContext)
                    val listaPelicula: ArrayList<Pelicula>? =
                        response.body()?.toCollection(ArrayList())
                    adapters = listaPeliculasAdapters(listaPelicula, applicationContext,true)
                    binding.rvListaPeliculas.layoutManager = layoutManager
                    binding.rvListaPeliculas.adapter = adapters
                }
            }
        })
    }

    fun recargarPeliculas(){
        if (!isNetworkAvailable(this)) {
            Toast.makeText(applicationContext, R.string.errorInternet, Toast.LENGTH_SHORT).show()
            val db = DataBasePeliculas.getDatabase(this)
            val peliculasDao = db?.peliculasDao()
            GlobalScope.launch(Dispatchers.IO) {
                listaPelicula = peliculasDao?.findAll()?.toCollection(ArrayList())
                runOnUiThread {
                    Toast.makeText(applicationContext, R.string.errorInternet, Toast.LENGTH_SHORT)
                        .show()
                    val layoutManager = LinearLayoutManager(applicationContext)
                    adapters = listaPeliculasAdapters(listaPelicula, applicationContext,false)
                    binding.rvListaPeliculas.layoutManager = layoutManager
                    binding.rvListaPeliculas.adapter = adapters
                    binding.fbAdd.setOnClickListener {
                        Toast.makeText(applicationContext, R.string.noInternet, Toast.LENGTH_SHORT)
                            .show()
                    }
                    binding.fbSalir.setOnClickListener {
                        Toast.makeText(applicationContext, R.string.noInternet, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

        } else {
            pre = DatosPreferences(this)
            Toast.makeText(applicationContext, R.string.hayInternet, Toast.LENGTH_SHORT)
                .show()
            activarBotones()
            getPeliculas()
        }
    }

    fun activarBotones(){
        if (isNetworkAvailable(this)) {
            binding.fbAdd.setOnClickListener {
                val intent = Intent(this, CrearPeliculaActivity::class.java)
                startActivity(intent)
            }
        }
        binding.fbMas.setOnClickListener {
            if (binding.fbAdd.visibility == View.GONE) {
                binding.fbSalir.show()
                binding.fbAdd.show()
                binding.fbMas.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_remove_24))
            } else {
                binding.fbSalir.hide()
                binding.fbAdd.hide()
                binding.fbMas.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_add_24))
            }
        }
        binding.fbSalir.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.cerrarSesion)
            builder.setIcon(R.drawable.ic_baseline_login_24)
            builder.setPositiveButton(R.string.cancelar) { dialog, which ->
                Toast.makeText(this, R.string.cancelarAccion, Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton(R.string.salir) { dialog, which ->
                Toast.makeText(this, R.string.sesionCerrada, Toast.LENGTH_SHORT).show()
                val validar = ValidacionesUtils()
                validar.reiniciarApp(pre, this)
            }
            builder.show()
        }
    }
}