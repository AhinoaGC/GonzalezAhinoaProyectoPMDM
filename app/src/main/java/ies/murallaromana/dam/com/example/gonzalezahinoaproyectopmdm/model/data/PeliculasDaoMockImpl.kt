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
            ),
            Pelicula(
                "0",
                "Encanto",
                "Animación, Aventura",
                "Jared Bush",
                "4.5",
                "https://es.web.img2.acsta.net/pictures/21/09/29/16/57/5761354.jpg",
                "99 min",
                "2021",
                "Cuenta la historia de una familia extraordinaria, los Madrigal, que viven escondidos en las montañas de Colombia, en una casa mágica de un pueblo situado en un enclave maravilloso llamado Encanto. La magia de Encanto ha dotado a todos los niños de la familia de un don único, desde la súper fuerza hasta el poder de curar... pero se ha olvidado de una niña llamada Mirabel. Cuando Mirabel descubre que la magia que rodea Encanto está en peligro, decide que ella, la única Madrigal normal, podría ser la última esperanza de su extraordinaria familia.",
                "0qvWul3smJ0"

            ),Pelicula(
                "5",
                "House of Gucci",
                "Drama",
                "Conrad Vernon",
                "3",
                "https://ca-times.brightspotcdn.com/dims4/default/2245912/2147483647/strip/true/crop/2765x4096+0+0/resize/840x1244!/quality/90/?url=https%3A%2F%2Fcalifornia-times-brightspot.s3.amazonaws.com%2F14%2F62%2Fd8bbe2f2464c8dab79234ade9274%2Fhouseofgucci-characterposter-ladygaga.jpg",
                "150 min",
                "2021",
                "Drama criminal en torno al asesinato en 1995 de Maurizio Gucci, nieto del fundador del imperio de la moda Gucci, que apareció asesinado por orden de su exmujer Patrizia Reggiani, conocida como la \"viuda negra de Italia\". Adaptación del libro de Sara Gay Forden, publicado en 2001, 'The House of Gucci: A Sensational Story of Murder, Madness, Glamour, and Greed'.",
                "s1rOhrqGYjw"
            ),Pelicula(
                "232323",
                "El refugio",
                "Comedia",
                "Macarena Astorga",
                "2.5",
                "https://es.web.img3.acsta.net/pictures/21/10/19/15/42/1284711.jpg",
                "91 min.",
                "2021",
                "Una estrella del cine en horas bajas y un galán emergente (muy bien acompañado) que amenaza con desplazar al anterior gracias a las artimañas del agente de ambos; la dueña del hotel de alta montaña y su alocada hermana, propulsora de una nueva y peculiar disciplina mezcla de yoga y fengshui; un grupo de niños con ganas de divertirse, pero también de descubrir secretos ocultos; un botones taciturno que todo lo ve; y un tremendo temporal que les dejará aislados días antes de Navidad... Un fin de semana en el que todo puede pasar... ¡y en el que pasará de todo!",
                "u4HkKHPV9Q8"

            ),Pelicula(
                "",
                "El chico que salvó la Navidad",
                "Cine familiar. Navidad",
                "Gil Kenan",
                "3.5",
                "https://static.wikia.nocookie.net/doblaje/images/d/d8/El_chico_que_salvo_la_navidad_poster.jpg/revision/latest?cb=20211108012552&path-prefix=es",
                "106 min",
                "2021",
                "Un chico normal llamado Nikolas se embarca en una extraordinaria aventura por el nevado norte en busca de su padre, que se halla en una misión para descubrir el legendario pueblo de los elfos, Elfhem. Junto a un testarudo reno llamado Blitzen y un leal ratón mascota, Nikolas pronto encuentra su destino en esta historia mágica que demuestra que nada es imposible.",
                "VGp2sZY0mw0"

            ),Pelicula(
                "8787",
                "La puerta de al lado",
                "Drama,Comedia",
                "Daniel Brühl",
                "2",
                "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/cartel-la-puerta-de-al-lado-2-scaled-1637160072.jpeg?resize=320:*",
                "92 min",
                "2021",
                "Daniel es un conocido actor que una mañana deja atrás su elegante ático en Berlín, su preciosa esposa y a la niñera que cuida de sus dos hijos pequeños para volar a Londres para el casting de una película de superhéroes. De camino al aeropuerto se acerca al bar de siempre para repasar el texto y tomar un café tranquilamente sin darse cuenta de que su vecino Bruno, al que nunca antes había visto, viene siguiéndole de cerca. En el bar, Bruno comienza a conversar con Daniel en lo que queda claro que no es un encuentro casual. El vecino de la puerta de al lado tiene información íntima sobre la vida de Daniel y su familia que hace que el encuentro se convierta en una incómoda experiencia.",
                "5a2kqx2bqMs"

            ),Pelicula(
                "4343",
                "Shang-Chi y la leyenda de los diez anillos",
                "Fantástico, Acción",
                "Destin Cretton",
                "4.5",
                "https://lumiere-a.akamaihd.net/v1/images/image_2752f0d6.jpeg?region=0,0,540,810",
                "132 min",
                "2021",
                "Adaptación cinematográfica del héroe creado por Steve Englehart y Jim Starlin en 1973, un personaje mitad chino, mitad americano, cuyo característico estilo de combate mezclaba kung-fu, nunchacos y armas de fuego.",
                "MQRF_YezCgA"

            )))
        return list
    }
}