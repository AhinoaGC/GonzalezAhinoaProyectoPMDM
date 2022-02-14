package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.Dao

import androidx.room.*
import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.entities.User


@Dao
interface PeliculasDao {

    @Query("SELECT * from peliculas")
    fun findAll(): List<Pelicula>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(peliculas: List<Pelicula>?)

    @Query("Delete from peliculas")
    fun delete()
}