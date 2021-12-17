package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Selection
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.R
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities.ListaActivity
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities.RegistroActivity
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.DatosPreferences

class LoginFragment : Fragment() {

    private  lateinit var pre: DatosPreferences
    private lateinit var tieUser: TextInputEditText
    private lateinit var tieCont: TextInputEditText
    private lateinit var tvPantallaRegistro: TextView
    private lateinit var btAceptar: Button
    private lateinit var vi : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        vi = inflater.inflate(R.layout.fragment_login, container, false)

        pre = DatosPreferences(vi.context)

        tieUser= vi.findViewById(R.id.tieUser)
        tieCont= vi.findViewById(R.id.tieCont)
        tvPantallaRegistro= vi.findViewById(R.id.tvPantallaRegistro)
        btAceptar= vi.findViewById(R.id.btAceptar)

        val nombre = pre.recuperarDatos("nombre")
        val contraseña = pre.recuperarDatos("contraseña")

        tieUser.setText(nombre)
        tieCont.setText(contraseña)

        tvPantallaRegistro.setOnClickListener{
            val ft= activity?.supportFragmentManager?.beginTransaction()
            ft?.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
            ft?.replace(R.id.contenedorFragment,RegistroFragment())
            ft?.addToBackStack(null)
            ft?.commit()
        }

        btAceptar.setOnClickListener{
            if (tieUser.text.toString().length==0 || tieCont.text.toString().length==0 ){
                val build = AlertDialog.Builder(vi.context)
                build.setTitle("Datos incorrectos")
                build.setIcon(R.drawable.ic_baseline_error_24)
                build.setMessage("Usuario y/o contraseña vacíos.")
                build.setPositiveButton("Aceptar") { dialog, which ->}
                build.show()
            }
            else if (!nombre.equals(tieUser.text.toString())) {
                tieUser.setError("Usuario incorrecto")
            } else if (!contraseña.equals(tieCont.text.toString())) {
                tieCont.setError("Contraseña incorrecta")
            } else {
                val lista = Intent(vi.context, ListaActivity::class.java)
                startActivity(lista)
            }
        }

        return vi
    }

    override fun onResume() {
        super.onResume()
        val nombre = pre.recuperarDatos("nombre")
        val contraseña = pre.recuperarDatos("contraseña")

        tieUser.setText(nombre)
        tieCont.setText(contraseña)


        tvPantallaRegistro.setOnClickListener{
            val ft= activity?.supportFragmentManager?.beginTransaction()
            ft?.replace(R.id.contenedorFragment,RegistroFragment())
            ft?.addToBackStack(null)
            ft?.commit()
        }

        btAceptar.setOnClickListener{

            if (tieUser.text.toString().length==0 || tieCont.text.toString().length==0 ){

                val adb = AlertDialog.Builder(vi.context)
                adb.setTitle("Datos incorrectos")
                adb.setMessage("El usuario y/o la contraseña están vacíos.")
                adb.setPositiveButton("Aceptar") { dialog, which ->}
                adb.show()
            }
            else if (!nombre.equals(tieUser.text.toString())) {
                tieUser.setError("El usuario no existe")
            } else if (!contraseña.equals(tieCont.text.toString())) {
                tieCont.setError("La contraseña no es correcta")
            } else {
                val lista = Intent(vi.context, ListaActivity::class.java)
                startActivity(lista)
            }
        }
    }
}