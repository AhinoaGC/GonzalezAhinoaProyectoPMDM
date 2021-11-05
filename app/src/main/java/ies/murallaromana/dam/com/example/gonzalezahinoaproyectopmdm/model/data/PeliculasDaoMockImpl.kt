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
            "https://playmax.xyz/img/c/400/9/1441459554/4448.jpg",
            "112 min",
            "2013",
        "Basada en una historia real documentada por los reputados demonólogos Ed y Lorraine Warren. Narra los encuentros sobrenaturales que vivió la familia Perron en su casa de Rhode Island a principios de los 70. El matrimonio Warren, investigadores de renombre en el mundo de los fenómenos paranormales, acudieron a la llamada de esta familia aterrorizada por la presencia en su granja de un ser maligno."

        ),
        Pelicula(
            1,
            "La llegada",
            "Ciencia ficción",
            "Denis Villeneuve",
            "7.3",
            "https://pics.filmaffinity.com/La_llegada-150207636-large.jpg",
            "116 min",
            "2016",
            "Cuando naves extraterrestres comienzan a llegar a la Tierra, los altos mandos militares piden ayuda a una experta lingüista (Amy Adams) para intentar averiguar si los alienígenas vienen en son de paz o suponen una amenaza. Poco a poco la mujer intentará aprender a comunicarse con los extraños invasores, poseedores de un lenguaje propio, para dar con la verdadera y misteriosa razón de la visita extraterrestre... Adaptación del relato corto \"The Story of Your Life\" del escritor Ted Chiang, ganador de los reconocidos premios de ciencia ficción Hugo y Nebula."
        ),
        Pelicula(
            2,
            "Mad Max: Furia en la carretera",
            "Ciencia ficción",
            "George Miller",
            "7.1",
            "https://pics.filmaffinity.com/Mad_Max_Furia_en_la_carretera-397817030-large.jpg",
            "120 min",
            "2015",
            "Perseguido por su turbulento pasado, Mad Max cree que la mejor forma de sobrevivir es ir solo por el mundo. Sin embargo, se ve arrastrado a formar parte de un grupo que huye a través del desierto en un War Rig conducido por una Emperatriz de élite: Furiosa. Escapan de una Ciudadela tiranizada por Immortan Joe, a quien han arrebatado algo irreemplazable. Enfurecido, el Señor de la Guerra moviliza a todas sus bandas y persigue implacablemente a los rebeldes en una \"guerra de la carretera\" de altas revoluciones... Cuarta entrega de la saga post-apocalíptica que resucita la trilogía que a principios de los ochenta protagonizó Mel Gibson."
        ),
        Pelicula(
            3,
            "Al filo del mañana",
            "Ciencia ficción",
            "Douya Liman",
            "8.2",
            "https://pics.filmaffinity.com/Al_filo_del_ma_ana-632023834-large.jpg",
            "113 min",
            "2014",
            "En un futuro no muy lejano, invade la Tierra una raza de extraterrestres invencibles. Al Comandante William Cage (Tom Cruise), un oficial que nunca ha entrado en combate, le encargan una misión casi suicida y resulta muerto. Entra entonces en un bucle temporal, en el que se ve obligado a luchar y morir una y otra vez. Pero las múltiples batallas que libra lo hacen cada vez más hábil y eficaz en su lucha contra los alienígenas. Su compañera de combate es Rita Vrataski (Emily Blunt), una guerrera de las Fuerzas Especiales. Adaptación del manga de Hiroshi Sakurazaka."
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