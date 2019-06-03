package com.example.examen

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
        btnPaciente.setOnClickListener {gestionarPaciente() }
        btnCrearPaciente.setOnClickListener{ crearPaciente()}
    }

    fun gestionarPaciente(){
        val intentGestionarPaciente = Intent(this, ConsultarActivity::class.java)
        intentGestionarPaciente.putExtra("usuario", usuario)
        startActivity(intentGestionarPaciente)
    }

    fun crearPaciente(){
        val intentCrearPaciente = Intent(this, IngresarActivity::class.java)
        intentCrearPaciente.putExtra("usuario", usuario)
        startActivity(intentCrearPaciente)
    }
}

