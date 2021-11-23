package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data

import android.app.Application
import ies.murallaromana.dam.com.example.pruebalistas.model.data.PeliculasDaoMockImpl
import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula

class App : Application() {

    companion object{
        var peliculas: ArrayList<Pelicula> = ArrayList()
    }

    override fun onCreate() {
        super.onCreate()

        val dao = PeliculasDaoMockImpl()

        peliculas = dao.getTodos()
    }
}