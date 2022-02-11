package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.entities.User

import androidx.room.OnConflictStrategy




@Dao
interface PeliculasDao {

    @Query("SELECT * from peliculas")
    fun findAll(): List<Pelicula>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(peliculas: List<Pelicula>?)
}