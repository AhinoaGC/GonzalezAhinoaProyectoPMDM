package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {

    private lateinit var btGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val intent = Intent(this, LoginActivity::class.java)

        btGuardar=this.findViewById(R.id.btGuardar)

        btGuardar.setOnClickListener{
            onBackPressed()
        }
    }
}