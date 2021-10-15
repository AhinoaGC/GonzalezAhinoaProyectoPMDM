package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {

    private lateinit var btRegistro: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val intent = Intent(this, RegistroActivity::class.java)

        btRegistro=this.findViewById(R.id.btRegistro)

        btRegistro.setOnClickListener{
            startActivity(intent)
        }


    }
}