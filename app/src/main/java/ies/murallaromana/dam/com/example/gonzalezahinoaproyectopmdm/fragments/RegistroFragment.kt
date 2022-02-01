package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities.ListaActivity
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.databinding.ActivityRegistroBinding
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.DatosPreferences
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.retrofit.UserService
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.entities.Token
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.entities.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
                val email = editCorreo.text.toString().trim()
                val contraseña = editContrasenha.text.toString().trim()

            val u = User(null,email, contraseña)

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://damapi.herokuapp.com/api/v1/")
                .build()

            val service: UserService = retrofit.create(UserService::class.java)
            val signupCall = service.signup(u)

            signupCall.enqueue(object: Callback<User> {

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("respuesta: onFailure", t.toString())
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    Log.d("respuesta: onResponse", response.toString())

                    if (response.code() > 299 || response.code() < 200) {
                        // Muestro alerta: no se ha podido crear el usuario
                        Toast.makeText(vi.context, "No se ha podido crear la cuenta.", Toast.LENGTH_SHORT).show()
                    } else {
                        //Muestro mensaje de usuario creado correctamente.
                        Toast.makeText(vi.context, "Cuenta creada correctamente", Toast.LENGTH_SHORT).show()
                    }
                }
            })
                val ft= activity?.supportFragmentManager?.beginTransaction()
                ft?.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                ft?.replace(R.id.contenedorFragment,LoginFragment())
                ft?.commit()
        }
        return vi
    }
}