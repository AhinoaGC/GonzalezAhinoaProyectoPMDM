package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.Utils

import android.content.Context
import android.content.Intent
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities.MainActivity
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.model.data.DatosPreferences

class ValidacionesUtils {

    fun reiniciarApp(pre: DatosPreferences, context: Context){
        pre.guardarToken("")
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }
}