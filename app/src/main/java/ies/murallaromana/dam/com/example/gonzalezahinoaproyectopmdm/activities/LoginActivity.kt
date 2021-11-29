package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.R
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.databinding.ActivityLoginBinding
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.DatosPreferences

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private  lateinit var pre: DatosPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("Iniciar Sesión")
        pre = DatosPreferences(applicationContext)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val nombre = pre.recuperarDatos("nombre")
        val contraseña = pre.recuperarDatos("contraseña")

        binding.tieUser.setText(nombre)
        binding.tieCont.setText(contraseña)


        binding.tvPantallaRegistro.setOnClickListener{
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        binding.btAceptar.setOnClickListener{
            if (binding.tieUser.text.toString().length==0 || binding.tieCont.text.toString().length==0 ){
                val build = AlertDialog.Builder(this)
                build.setTitle("Datos incorrectos")
                build.setIcon(R.drawable.ic_baseline_error_24)
                build.setMessage("Usuario y/o contraseña vacíos.")
                build.setPositiveButton("Aceptar") { dialog, which ->}
                build.show()
            }
            else if (!nombre.equals(binding.tieUser.text.toString())) {
                binding.tieUser.setError("Usuario incorrecto")
            } else if (!contraseña.equals(binding.tieCont.text.toString())) {
                binding.tieCont.setError("Contraseña incorrecta")
            } else {
                val lista = Intent(this, ListaActivity::class.java)
                startActivity(lista)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val nombre = pre.recuperarDatos("nombre")
        val contraseña = pre.recuperarDatos("contraseña")

        binding.tieUser.setText(nombre)
        binding.tieCont.setText(contraseña)


        binding.tvPantallaRegistro.setOnClickListener{
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        binding.btAceptar.setOnClickListener{

            if (binding.tieUser.text.toString().length==0 || binding.tieCont.text.toString().length==0 ){

                val adb = AlertDialog.Builder(this)
                adb.setTitle("Datos incorrectos")
                adb.setMessage("El usuario y/o la contraseña están vacíos.")
                adb.setPositiveButton("Aceptar") { dialog, which ->}
                adb.show()
            }
            else if (!nombre.equals(binding.tieUser.text.toString())) {
                binding.tieUser.setError("El usuario no existe")
            } else if (!contraseña.equals(binding.tieCont.text.toString())) {
                binding.tieCont.setError("La contraseña no es correcta")
            } else {
                val lista = Intent(this, ListaActivity::class.java)
                startActivity(lista)
            }
        }
    }
}