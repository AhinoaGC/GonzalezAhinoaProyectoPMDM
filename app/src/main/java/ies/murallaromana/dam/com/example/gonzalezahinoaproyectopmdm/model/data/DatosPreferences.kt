package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data

import android.content.Context

class DatosPreferences (val context: Context) {

    val archivo = "BaseDeDatos"
    val pref = context.getSharedPreferences(archivo, 0)

    fun guardarToken(token: String?) {
        pref.edit().putString("Token", token).commit()
        }


    fun recuperarToken(): String? {
            return pref.getString("Token", "")
        }
    }