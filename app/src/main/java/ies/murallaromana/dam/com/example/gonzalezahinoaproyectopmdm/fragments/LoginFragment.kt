package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.R
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities.ListaActivity
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.Dao.DataBasePeliculas
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.DatosPreferences
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.retrofit.UserService
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.entities.Token
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginFragment : Fragment() {

    private lateinit var pre: DatosPreferences
    private lateinit var tieUser: TextInputEditText
    private lateinit var tieCont: TextInputEditText
    private lateinit var tvPantallaRegistro: TextView
    private lateinit var btAceptar: Button
    private lateinit var vi: View
    private lateinit var progreso: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vi = inflater.inflate(R.layout.fragment_login, container, false)

        pre = DatosPreferences(vi.context)

        tieUser = vi.findViewById(R.id.tieUser)
        tieCont = vi.findViewById(R.id.tieCont)
        tvPantallaRegistro = vi.findViewById(R.id.tvPantallaRegistro)
        btAceptar = vi.findViewById(R.id.btAceptar)
        progreso = vi.findViewById(R.id.progreso)

        btAceptar.isEnabled = true

        if (!pre.recuperarToken().isNullOrEmpty()) {
            val lista = Intent(vi.context, ListaActivity::class.java)
            startActivity(lista)
        }
        tvPantallaRegistro.setOnClickListener {
            val ft = activity?.supportFragmentManager?.beginTransaction()
            ft?.setCustomAnimations(
                R.anim.enter_from_left,
                R.anim.exit_to_right,
                R.anim.enter_from_right,
                R.anim.exit_to_left
            )
            ft?.replace(R.id.contenedorFragment, RegistroFragment())
            ft?.addToBackStack(null)
            ft?.commit()
        }

        btAceptar.setOnClickListener {
            btAceptar.isEnabled = false
            progreso.visibility = View.VISIBLE
            val email = tieUser.text.toString().trim()
            val contraseña = tieCont.text.toString().trim()

            val u = User(null, email, contraseña)
            iniciarSesion(u)
        }
        return vi
    }

    fun iniciarSesion(u: User) {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://damapi.herokuapp.com/api/v1/")
            .build()

        val service: UserService = retrofit.create(UserService::class.java)
        val loginCall = service.login(u)

        loginCall.enqueue(object : Callback<Token> {
            override fun onFailure(call: Call<Token>, t: Throwable) {
                Log.e("respuesta: onFailure", t.toString())
                progreso.visibility = View.GONE
                btAceptar.isEnabled = true
            }

            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                Log.e("respuesta: onResponse", response.toString())

                if (response.code() > 299 || response.code() < 200) {
                    Toast.makeText(
                        vi.context,
                        R.string.errorSesion,
                        Toast.LENGTH_SHORT
                    ).show()
                    progreso.visibility = View.GONE
                    btAceptar.isEnabled = true
                } else {
                    val token = response.body()?.token
                    Log.d("respuesta: token:", token.orEmpty())
                    pre.guardarToken(token)
                    progreso.visibility = View.GONE

                    val lista = Intent(vi.context, ListaActivity::class.java)
                    startActivity(lista)
                }
            }
        })
    }
}