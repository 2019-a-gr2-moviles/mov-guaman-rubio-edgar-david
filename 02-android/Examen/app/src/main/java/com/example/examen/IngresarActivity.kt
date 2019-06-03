package com.example.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ingresar.*

class IngresarActivity : AppCompatActivity() {
    var usuario:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar)
        usuario = intent.getStringExtra("usuario").toString()
        btnAceptar.setOnClickListener { aceptarIngreso() }
        btnCancelar.setOnClickListener { cancelarIngreso()}
    }

    fun cancelarIngreso(){
        val intentCancelar= Intent(this, MenuActivity::class.java)
        intentCancelar.putExtra("usuario", usuario)
        startActivity(intentCancelar)
    }

    fun aceptarIngreso(){
        val paciente= Paciente(id = null,
            nombres = txtNombres.text.toString(),
            apellidos = txtApellidos.text.toString(),
            fechaNacimiento = txtFechaNacimiento.text.toString(),
            numeroHijos = txtNumeroHijos.text.toString().toInt(),
            tieneSeguro = txtTieneSeguro.text.toString())
        BDEPaciente.agregarPaciente(paciente)
        Toast.makeText(this, "Paciente ingresado "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }
}
