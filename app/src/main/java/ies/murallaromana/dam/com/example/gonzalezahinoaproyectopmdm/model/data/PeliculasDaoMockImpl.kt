package ies.murallaromana.dam.com.example.pruebalistas.model.data

import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula

class PeliculasDaoMockImpl : PeliculasDao {

    val list = ArrayList<Pelicula>()

    override fun getTodos(): ArrayList<Pelicula>{
        list.addAll(listOf(
            Pelicula(
                "0",
                "Expediente Warren: The Conjuring",
                "Terror",
                "James Wan",
                "4",
                "https://playmax.xyz/img/c/400/9/1441459554/4448.jpg",
                "112 min",
                "2013",
                "Basada en una historia real documentada por los reputados demonólogos Ed y " +
                        "Lorraine Warren. Narra los encuentros sobrenaturales que vivió la familia Perron en " +
                        "su casa de Rhode Island a principios de los 70. El matrimonio Warren, investigadores de " +
                        "renombre en el mundo de los fenómenos paranormales, acudieron a la llamada de esta familia " +
                        "aterrorizada por la presencia en su granja de un ser maligno.",
                "_LeRZFHqYLw"

            ),
            Pelicula(
                "1",
                "La llegada",
                "Ciencia ficción",
                "Denis Villeneuve",
                "4.5",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tbVITeytclB4JKx2LxxasrCCmZx.jpg",
                "116 min",
                "2016",
                "Cuando naves extraterrestres comienzan a llegar a la Tierra, los altos mandos militares piden " +
                        "ayuda a una experta lingüista (Amy Adams) para intentar averiguar si los alienígenas vienen en " +
                        "son de paz o suponen una amenaza. Poco a poco la mujer intentará aprender a comunicarse con los " +
                        "extraños invasores, poseedores de un lenguaje propio, para dar con la verdadera y misteriosa razón " +
                        "de la visita extraterrestre... Adaptación del relato corto \"The Story of Your Life\" del escritor" +
                        " Ted Chiang, ganador de los reconocidos premios de ciencia ficción Hugo y Nebula.",
                "uWs5lsWXLbo"
            ),
            Pelicula(
                "2",
                "Mad Max: Furia en la carretera",
                "Ciencia ficción",
                "George Miller",
                "3.5",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/mnVZLdo9C4X80sJmgcGNpMNCbgk.jpg",
                "120 min",
                "2015",
                "Perseguido por su turbulento pasado, Mad Max cree que la mejor forma de sobrevivir es " +
                        "ir solo por el mundo. Sin embargo, se ve arrastrado a formar parte de un grupo que huye a " +
                        "través del desierto en un War Rig conducido por una Emperatriz de élite: Furiosa. Escapan de una " +
                        "Ciudadela tiranizada por Immortan Joe, a quien han arrebatado algo irreemplazable. Enfurecido, el " +
                        "Señor de la Guerra moviliza a todas sus bandas y persigue implacablemente a los rebeldes en una \"guerra de la carretera\" " +
                        "de altas revoluciones... Cuarta entrega de la saga post-apocalíptica que resucita la trilogía que a principios de los ochenta protagonizó Mel Gibson.",
                "hEJnMQG9ev8"
            ),
            Pelicula(
                "3",
                "Al filo del mañana",
                "Ciencia ficción",
                "Douya Liman",
                "5",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/vNjwi0STOwzmwE9laZSMYYh2JPE.jpg",
                "113 min",
                "2014",
                "En un futuro no muy lejano, invade la Tierra una raza de extraterrestres invencibles. " +
                        "Al Comandante William Cage (Tom Cruise), un oficial que nunca ha entrado en combate, le encargan " +
                        "una misión casi suicida y resulta muerto. Entra entonces en un bucle temporal, en el que se ve obligado a " +
                        "luchar y morir una y otra vez. Pero las múltiples batallas que libra lo hacen cada vez más hábil y eficaz en" +
                        " su lucha contra los alienígenas. Su compañera de combate es Rita Vrataski (Emily Blunt), una guerrera de " +
                        "las Fuerzas Especiales. Adaptación del manga de Hiroshi Sakurazaka.",
                "Qd0_qYIhMZA"
            ),
            Pelicula(
                "4",
                "Venom: Habrá matanza",
                "Ciencia ficción",
                "Andy Serkis",
                "4",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2jVVDtDaeMxmcvrz2SNyhMcYtWc.jpg",
                "1h 37m",
                "2021",
                "Eddie Brock (Tom Hardy) y su simbionte Venom todavía están intentando descubrir " +
                        "cómo vivir juntos cuando un preso que está en el corredor de la muerte (Woody " +
                        "Harrelson) se infecta con un simbionte propio.",
                "hbfadtw-fjs"
            ),
            Pelicula(
                "5",
                "La familia Addams 2: La gran escapada",
                "Aniación, Comedia",
                "Conrad Vernon",
                "3",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qetczjArlkPb4EnB6ygyvgbYY2l.jpg",
                "1h 33m",
                "2021",
                "Con la esperanza de acercarse como familia, Gómez, Morticia y el resto del clan Addams " +
                        "se embarcan en un aventurero viaje por carretera en una horrenda y gigantesca caravana.",
                "rfETRi0EP_I"
            )))
        return list
    }
}