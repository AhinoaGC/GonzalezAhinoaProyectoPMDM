package ies.murallaromana.dam.com.example.pruebalistas.model.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "peliculas")

data class Pelicula(
    var id: String?,
    @SerializedName("directorPhone") var numero: String?,
    @SerializedName("title") var titulo: String?,
    @SerializedName("genre") var genero: String?,
    @SerializedName("directorFullname") var director: String?,
    @SerializedName("rating") var puntuacion: String?,
    @SerializedName("imageUrl") var url: String?,
    @SerializedName("runtimeMinutes") var duracion: String?,
    @SerializedName("releaseYear") var ano: String?,
    @SerializedName("description") var resumen: String?,
    @SerializedName("trailerUrl") var urlVideo: String?

) : Serializable {

    @PrimaryKey
    @ColumnInfo(name = "idRoom")
    @Transient var idRoom: String = ""
}
