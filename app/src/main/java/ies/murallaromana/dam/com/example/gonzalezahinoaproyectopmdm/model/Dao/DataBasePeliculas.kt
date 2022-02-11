package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.Dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula

@Database(entities = [Pelicula::class], version = 1)

abstract class DataBasePeliculas : RoomDatabase() {

    abstract fun peliculasDao(): PeliculasDao

    companion object {
        private var database: DataBasePeliculas? = null
        fun getDatabase(context: Context): DataBasePeliculas? {
            database ?: synchronized(this) {
                database = Room.databaseBuilder(
                    context,
                    DataBasePeliculas::class.java,
                    "my-imdb"
                ).build()
            }
            return database
        }
    }
}
