package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class Parcelable : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parcelable)

        val adrian: Usuario? = this.intent
            .getParcelableExtra<Usuario>("usuario")

        Log.i("parcelable","Nombre ${adrian?.nombre}")
        Log.i("parcelable","Nombre ${adrian?.edad}")
        Log.i("parcelable","Nombre ${adrian?.fechaNacimiento.toString()}")
        Log.i("parcelable","Nombre ${adrian?.sueldo}")

    }

    fun regresarAMenu(){
        val intentExplicito = Intent(
            this,
            MainActivity::class.java
        )
        startActivity(intentExplicito)
    }

}
