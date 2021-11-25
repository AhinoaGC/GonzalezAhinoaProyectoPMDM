package ies.murallaromana.dam.com.example.pruebalistas.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.R
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities.PeliculaActivity
import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula

import android.os.Build
import android.view.View.OnLongClickListener
import android.widget.*

import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities.EditarPeliculaActivity
import java.util.stream.Collectors


class listaPeliculasAdapters(val peliculas: ArrayList<Pelicula>, val context: Context) :
    RecyclerView.Adapter<listaPeliculasAdapters.PersonajesViewHolder>(), Filterable {

    var list:ArrayList<Pelicula>

    init {
        list= ArrayList()
        list.addAll(peliculas)
    }

    class PersonajesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo = itemView.findViewById<TextView>(R.id.tvTitulo)
        val tvGenero = itemView.findViewById<TextView>(R.id.tvGenero)
        val ivFoto = itemView.findViewById<ImageView>(R.id.ivFoto)
        val cardView = itemView.findViewById<CardView>(R.id.cardView)
        val estrellas = itemView.findViewById<RatingBar>(R.id.ratingBar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajesViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_pelicula, parent, false)
        return PersonajesViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: PersonajesViewHolder, position: Int) {
        val pelicula = peliculas.get(position)

        holder.tvTitulo.setText(pelicula.titulo)
        holder.tvGenero.setText("Género: " + pelicula.genero)
        holder.estrellas.rating = pelicula.puntuacion.toFloat()
        Picasso.get().load(pelicula.url).into(holder.ivFoto)

        holder.cardView.setOnClickListener{
            val intent = Intent(context, PeliculaActivity::class.java)
            intent.putExtra("pelicula", pelicula)
            context.startActivity(intent)
        }

        holder.cardView.setOnLongClickListener(OnLongClickListener {
        val builder = AlertDialog.Builder(context)
            builder.setTitle("¿Que quieres hacer con la pelicula?")
            builder.setIcon(R.drawable.ic_baseline_movie_filter_24)
            builder.setPositiveButton("Editar") { dialog, which ->
                val intent = Intent(context, EditarPeliculaActivity::class.java)
                intent.putExtra("pelicula", pelicula)
                context.startActivity(intent)
            }
            builder.setNeutralButton("Ver") { dialog, which ->
                val intent = Intent(context, PeliculaActivity::class.java)
                intent.putExtra("pelicula", pelicula)

                context.startActivity(intent)
            }
            builder.show()
            false
        })
    }

    override fun getItemCount(): Int {
        return peliculas.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                var listFilter=ArrayList<Pelicula>();
                if(p0==null||p0.isEmpty())
                {
                    listFilter.addAll(peliculas)
                }else
                {
                    val filterPattern: String = p0.toString().toLowerCase().trim()
                    for (item in peliculas) {
                        if (item.titulo.toLowerCase().contains(filterPattern)) {
                            listFilter.add(item)
                        }
                    }
                }
                val results = FilterResults()
                results.values = listFilter
                return  results;
            }

            override fun publishResults(p0: CharSequence?, p1: Filter.FilterResults?) {

                list.clear()
                list.addAll(p1?.values as Collection<Pelicula>)

                notifyDataSetChanged()
            }
        }
    }
}