package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginActivity : AppCompatActivity() {

    private lateinit var tvPantallaRegistro: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val intent = Intent(this, RegistroActivity::class.java)

        tvPantallaRegistro=this.findViewById(R.id.tvPantallaRegistro)

        tvPantallaRegistro.setOnClickListener{
            //tvPantallaRegistro.setTextColor(resources.getColor(R.color.white))
            startActivity(intent)


        }


    }
}