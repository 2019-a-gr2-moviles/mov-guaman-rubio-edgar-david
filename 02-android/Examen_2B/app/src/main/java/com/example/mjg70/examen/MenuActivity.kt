package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {
    var usuario :String = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        usuario = intent.getStringExtra("usuario").toString()
        btnEquipo.setOnClickListener {gestionarPaciente() }
        btnCrearEquipo.setOnClickListener{ crearPaciente()}
    }

    fun gestionarPaciente(){
        val intentGestionarEquipo = Intent(this, ConsultarActivity::class.java)
        intentGestionarEquipo.putExtra("usuario", usuario)
        startActivity(intentGestionarEquipo)
    }

    fun crearPaciente(){
        val intentCrearEquipo = Intent(this, IngresarActivity::class.java)
        intentCrearEquipo.putExtra("usuario", usuario)
        startActivity(intentCrearEquipo)
    }
}
