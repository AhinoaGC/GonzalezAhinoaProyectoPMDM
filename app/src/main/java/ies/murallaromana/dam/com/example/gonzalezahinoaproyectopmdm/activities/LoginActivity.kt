package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.R

class LoginActivity : AppCompatActivity() {

    private lateinit var tvPantallaRegistro: TextView
    private lateinit var btAceptar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val intent = Intent(this, RegistroActivity::class.java)

        tvPantallaRegistro=this.findViewById(R.id.tvPantallaRegistro)

        tvPantallaRegistro.setOnClickListener{
            //tvPantallaRegistro.setTextColor(resources.getColor(R.color.white))
            startActivity(intent)


        }

        btAceptar=this.findViewById(R.id.btAceptar)

        val lista = Intent(this, ListaActivity::class.java)

        btAceptar.setOnClickListener{
            //tvPantallaRegistro.setTextColor(resources.getColor(R.color.white))
            startActivity(lista)


        }


    }
}