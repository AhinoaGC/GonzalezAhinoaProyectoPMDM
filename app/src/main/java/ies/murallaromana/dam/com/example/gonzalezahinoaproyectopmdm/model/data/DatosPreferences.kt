package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data

import android.content.Context

class DatosPreferences (val context: Context) {

    val archivo = "BaseDeDatos"
    val pref = context.getSharedPreferences(archivo, 0)

    fun guardar(user: String, psswd: String) {
        pref.edit().putString("usuario", user).commit()
        pref.edit().putString("contraseña", psswd).commit()
    }

    fun recuperarDatos(dato: String): String? {
        if (dato == "nombre") {
            return pref.getString("usuario", "")
        } else if (dato == "contraseña") {
            return pref.getString("contraseña", "")
        }
        return null
    }
    }