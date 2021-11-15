package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.R

class RegistroActivity : AppCompatActivity() {

    private lateinit var btGuardar: Button

    private lateinit var editNombre: TextInputEditText

    private lateinit var tilNombre: TextInputLayout
    private lateinit var editApellidos: TextInputEditText

    private lateinit var tilApellidos: TextInputLayout
    private lateinit var editCorreo: TextInputEditText

    private lateinit var tilCorreo: TextInputLayout
    private lateinit var editContrasenha: TextInputEditText

    private lateinit var tilContrasenha: TextInputLayout
    private lateinit var editTelefono: TextInputEditText

    private lateinit var tilTelefono: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val intent = Intent(this, LoginActivity::class.java)

        btGuardar=this.findViewById(R.id.btGuardar)

        btGuardar.setOnClickListener{
            Toast.makeText(this, "Cuenta creada correctamente", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }

    }
}