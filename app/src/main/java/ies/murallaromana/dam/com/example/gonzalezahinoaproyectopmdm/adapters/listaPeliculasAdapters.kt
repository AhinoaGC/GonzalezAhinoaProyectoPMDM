package ies.murallaromana.dam.com.example.pruebalistas.adapters

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.R
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities.PeliculaActivity
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities.RegistroActivity
import ies.murallaromana.dam.com.example.pruebalistas.model.entities.Pelicula
import android.webkit.WebView

import android.webkit.WebChromeClient

import android.graphics.Bitmap
import android.provider.Settings.Global.getString

import android.webkit.WebViewClient
import android.widget.RatingBar
import android.widget.Toast


class listaPeliculasAdapters(val peliculas: List<Pelicula>, val context: Context) :
    RecyclerView.Adapter<listaPeliculasAdapters.PersonajesViewHolder>() {

    class PersonajesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo = itemView.findViewById<TextView>(R.id.tvTitulo)
        val tvGenero = itemView.findViewById<TextView>(R.id.tvGenero)

        //        val tvDirector = itemView.findViewById<TextView>(R.id.tvDirector)
//        val tvPunt = itemView.findViewById<TextView>(R.id.tvPunt)
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

        holder.cardView.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("¿Que quieres hacer con la pelicula?")
            builder.setPositiveButton("Editar") { dialog, which ->

            }
            builder.setNegativeButton("Eliminar") { dialog, which ->
                val eliminar = AlertDialog.Builder(context)
                eliminar.setTitle("¿Estás seguro que quieres eliminar la pelicula?")
                eliminar.setNegativeButton("Cancelar") { dialog, which ->
                }
                eliminar.setPositiveButton("Eliminar") { dialog, which ->
                    Toast.makeText(context, "Pelicula eliminada", Toast.LENGTH_SHORT).show()
                }
                eliminar.show()
            }

            builder.setNeutralButton("Ver") { dialog, which ->
                val intent = Intent(context, PeliculaActivity::class.java)
                intent.putExtra("pelicula", pelicula)

                context.startActivity(intent)
            }
            builder.show()
        }
    }

    override fun getItemCount(): Int {
        return peliculas.size
    }
}