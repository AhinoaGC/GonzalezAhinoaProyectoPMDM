package ies.murallaromana.dam.com.example.pruebalistas.model.data

import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula

interface PeliculasDao {

    fun getTodos(): List<Pelicula>
}