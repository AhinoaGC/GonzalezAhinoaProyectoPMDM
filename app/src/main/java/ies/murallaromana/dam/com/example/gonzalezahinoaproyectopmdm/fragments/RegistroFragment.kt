package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.fragments

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.textfield.TextInputEditText
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.R
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.databinding.ActivityRegistroBinding
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.DatosPreferences
import java.util.regex.Pattern

class RegistroFragment : Fragment() {
    private  lateinit var pre: DatosPreferences
    private lateinit var vi : View
    private lateinit var btGuardar: Button
    private lateinit var editNombre: TextInputEditText
    private lateinit var editContrasenha: TextInputEditText
    private lateinit var editCorreo: TextInputEditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        vi= inflater.inflate(R.layout.fragment_registro, container, false)
        pre = DatosPreferences(vi.context)

        btGuardar=vi.findViewById(R.id.btGuardar)
        editContrasenha=vi.findViewById(R.id.editContrasenha)
        editNombre=vi.findViewById(R.id.editNombre)
        editCorreo=vi.findViewById(R.id.editCorreo)

        btGuardar.setOnClickListener{
            val validacion = validarDatos()

            if (validacion) {
                val usuario = editNombre.text.toString().trim()
                val contraseña = editContrasenha.text.toString().trim()

                pre.guardar(usuario, contraseña)
                Toast.makeText(vi.context, "Cuenta creada correctamente", Toast.LENGTH_SHORT).show()
            }
        }
        return vi
    }

    //validar datos
    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun validarDatos(): Boolean {
        val contraseña = editContrasenha.text.toString()

        if (validarEmail(editCorreo.text.toString()) == false) {
            Toast.makeText(vi.context, "Email incorrecto.", Toast.LENGTH_SHORT)
                .show()
            return false
        } else if (contraseña.length < 5) {
            Toast.makeText(vi.context, "Contraseña demasiado corta.", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }
}