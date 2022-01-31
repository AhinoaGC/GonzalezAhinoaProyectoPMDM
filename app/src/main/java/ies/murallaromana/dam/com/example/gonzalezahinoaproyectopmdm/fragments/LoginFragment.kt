package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Selection
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.R
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities.ListaActivity
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities.RegistroActivity
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.DatosPreferences
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.retrofit.UserService
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.entities.Token
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.entities.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

        //val nombre = pre.recuperarDatos("nombre")
        //val contraseña = pre.recuperarDatos("contraseña")

        //tieUser.setText(nombre)
        //tieCont.setText(contraseña)

        tvPantallaRegistro.setOnClickListener{
            val ft= activity?.supportFragmentManager?.beginTransaction()
            ft?.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
            ft?.replace(R.id.contenedorFragment,RegistroFragment())
            ft?.addToBackStack(null)
            ft?.commit()
        }

        btAceptar.setOnClickListener{

            val email = tieUser.text.toString().trim()
            val contraseña = tieCont.text.toString().trim()

            val u = User(null,email, contraseña)

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://damapi.herokuapp.com/api/v1/")
                .build()

            val service: UserService = retrofit.create(UserService::class.java)
            val loginCall = service.login(u)

            loginCall.enqueue(object: Callback<Token> {
                override fun onFailure(call: Call<Token>, t: Throwable) {
                    Log.d("respuesta: onFailure", t.toString())

                }

                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    Log.d("respuesta: onResponse", response.toString())

                    if (response.code() > 299 || response.code() < 200) {
                        // Muestro alerta: no se ha podido crear el usuario
                        Toast.makeText(vi.context, "No se ha podido crear la cuenta.", Toast.LENGTH_SHORT).show()
                    } else {
                        val token = response.body()?.token
                        Log.d("respuesta: token:", token.orEmpty())

                        //Muestro mensaje de usuario creado correctamente.
                        Toast.makeText(vi.context, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show()
                        //Guardo en sharedPreferences el token
                        pre.guardarToken(token)
                        //Inicio nueva activity
                        val lista = Intent(vi.context, ListaActivity::class.java)
                        startActivity(lista)
                    }

                }
            })

        }

        return vi
    }

    override fun onResume() {
        super.onResume()
        //val nombre = pre.recuperarDatos("nombre")
        //val contraseña = pre.recuperarDatos("contraseña")

        //tieUser.setText(nombre)
        //tieCont.setText(contraseña)


        tvPantallaRegistro.setOnClickListener{
            val ft= activity?.supportFragmentManager?.beginTransaction()
            ft?.replace(R.id.contenedorFragment,RegistroFragment())
            ft?.addToBackStack(null)
            ft?.commit()
        }

        btAceptar.setOnClickListener{

        }
    }
}