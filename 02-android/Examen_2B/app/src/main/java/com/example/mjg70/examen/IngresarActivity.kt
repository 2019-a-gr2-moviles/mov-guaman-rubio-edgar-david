package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
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
            val nombres = txtNombres.text.toString()
            val apellidos = txtApellidos.text.toString()
            val fechaNacimiento = txtFechaNacimiento.text.toString()
            val numeroHijos = txtNumeroHijos.text.toString().toInt()
            val tieneSeguro = txtCampeonActual.text.toString()
        //BDPaciente.agregarPaciente(paciente)

        val referenceData = FirebaseDatabase.getInstance().getReference("pacientes")
        val pacienteId = referenceData.push().key
        if(pacienteId!=null){
            val pacienteNuevo = Paciente(pacienteId, nombres, apellidos, fechaNacimiento, numeroHijos, tieneSeguro)
            referenceData.child(pacienteId).setValue(pacienteNuevo).addOnCompleteListener {
                Toast.makeText(this, "Paciente creado "+usuario, Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "id de Paciente nulo", Toast.LENGTH_SHORT).show()
        }

        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }
}
