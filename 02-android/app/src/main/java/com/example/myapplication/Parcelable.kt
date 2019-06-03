package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class Parcelable : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parcelable)

        val edgar: Usuario? = this.intent
            .getParcelableExtra<Usuario>("usuario")


        val manchas: Mascota? = this.intent
            .getParcelableExtra<Mascota>("mascota")
        Log.i("parcelable","Nombre ${edgar?.nombre}")
        Log.i("parcelable","Nombre ${edgar?.edad}")
        Log.i("parcelable","Nombre ${edgar?.fechaNacimiento.toString()}")
        Log.i("parcelable","Nombre ${edgar?.sueldo}")

        Log.i("parcelable","Nombre ${manchas?.nombre}")
        Log.i("parcelable","Nombre ${manchas?.duenio?.nombre}")

    }

    fun regresarAMenu(){
        val intentExplicito = Intent(
            this,
            MainActivity::class.java
        )
        startActivity(intentExplicito)
    }

}
