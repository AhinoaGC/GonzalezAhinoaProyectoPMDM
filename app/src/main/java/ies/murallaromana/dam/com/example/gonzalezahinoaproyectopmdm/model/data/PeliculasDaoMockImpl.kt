package ies.murallaromana.dam.com.example.pruebalistas.model.data

import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula

class PeliculasDaoMockImpl : PeliculasDao {

    override fun getTodos(): List<Pelicula> = listOf(
        Pelicula(
            0,
            "Expediente Warren: The Conjuring",
            "Terror",
            "James Wan",
            "6.8",
            "https://playmax.xyz/img/c/400/9/1441459554/4448.jpg"
        ),
        Pelicula(
            1,
            "La llegada",
            "Ciencia ficción",
            "Denis Villeneuve",
            "7.3",
            "https://thronesapi.com/assets/images/sam.jpg"
        ),
        Pelicula(
            2,
            "Mad Max: Furia en la carretera",
            "Ciencia ficción",
            "George Miller",
            "7.1",
            "https://thronesapi.com/assets/images/arya-stark.jpg"
        ),
        Pelicula(
            3,
            "Al filo del mañana",
            "Ciencia ficción",
            "Douya Liman",
            "8.2",
            "https://thronesapi.com/assets/images/jon-snow.jpg"
        )
    )
//    forma 1 de hacerlo
//        val p1 = Personaje(0,"Daenerys","Targaryen","Mother of Dragons","Targaryen","http")
//        val p2 = Personaje(1,"Samwell", "Tarly", "Maester", "House Tarly","http")
//        val p3 = Personaje(2,"Arya", "Stark", "No One", "House Stark","http")
//        val p4 = Personaje(3,"Brienne", "Tarth", "Briene of Tarth", "Tarth","http")
//
//        val listaPersonaje = listOf(p1,p2,p3,p4)
//        return listaPersonaje

//    forma 2 de hacerlo
//        return listOf(
//            Personaje(0, "Daenerys", "Targaryen", "Mother of Dragons", "Targaryen", "http"),
//            Personaje(1, "Samwell", "Tarly", "Maester", "House Tarly", "http"),
//            Personaje(2, "Arya", "Stark", "No One", "House Stark", "http"),
//            Personaje(3, "Brienne", "Tarth", "Briene of Tarth", "Tarth", "http")
//        )

//    fun addPelicula() : Pelicula{
//        val p1 = Pelicula(0,"Daenerys","Targaryen","Mother of Dragons","Targaryen","http")
//    }
}