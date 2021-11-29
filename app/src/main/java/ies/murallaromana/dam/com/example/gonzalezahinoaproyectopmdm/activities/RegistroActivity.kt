package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.R
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.databinding.ActivityLoginBinding
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.databinding.ActivityRegistroBinding
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.DatosPreferences
import java.util.regex.Pattern

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private  lateinit var pre: DatosPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("Registrarse")
        pre = DatosPreferences(applicationContext)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding.btGuardar.setOnClickListener{
            val validacion = validarDatos()

            if (validacion) {
                val usuario = binding.editNombre.text.toString().trim()
                val contraseña = binding.editContrasenha.text.toString().trim()

                pre.guardar(usuario, contraseña)
                Toast.makeText(this, "Cuenta creada correctamente", Toast.LENGTH_SHORT).show()
                onBackPressed()
            }
        }
    }

    //validar datos
    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun validarDatos(): Boolean {
        val contraseña = binding.editContrasenha.text.toString()

        if (validarEmail(binding.editCorreo.text.toString()) == false) {
            Toast.makeText(this, "Email incorrecto.", Toast.LENGTH_SHORT)
                .show()
            return false
        } else if (contraseña.length < 5) {
            Toast.makeText(this, "Contraseña demasiado corta.", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }
}