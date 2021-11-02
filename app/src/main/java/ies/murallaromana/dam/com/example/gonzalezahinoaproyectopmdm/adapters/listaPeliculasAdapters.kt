package ies.murallaromana.dam.com.example.pruebalistas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.R
import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula

class listaPeliculasAdapters(val peliculas : List<Pelicula>) : RecyclerView.Adapter<listaPeliculasAdapters.PersonajesViewHolder>() {

    class PersonajesViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val tvTitulo = itemView.findViewById<TextView>(R.id.tvTitulo)
        val tvGenero = itemView.findViewById<TextView>(R.id.tvGenero)
        val tvDirector = itemView.findViewById<TextView>(R.id.tvDirector)
        val tvPunt = itemView.findViewById<TextView>(R.id.tvPunt)
        val ivFoto = itemView.findViewById<ImageView>(R.id.ivFoto)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_pelicula,parent,false)

        return PersonajesViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: PersonajesViewHolder, position: Int) {
        val personaje = peliculas.get(position)

        holder.tvTitulo.setText(personaje.titulo)
        holder.tvGenero.setText("Género: " +personaje.genero)
        holder.tvDirector.setText("Director: "+personaje.director)
        holder.tvPunt.setText(personaje.puntuacion)
        Picasso.get().load(personaje.url).into(holder.ivFoto);
    }

    override fun getItemCount(): Int {
        return peliculas.size
    }
}