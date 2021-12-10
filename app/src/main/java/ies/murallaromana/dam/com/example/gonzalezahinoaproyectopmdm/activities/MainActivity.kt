package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.R
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.fragments.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.contenedorFragment, LoginFragment())
        fragmentTransaction.commit()
    }
}