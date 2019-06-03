package com.example.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_actualizar.*

class ActualizarActivity : AppCompatActivity() {
    var padreId : Int = 0
    var usuario :String = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar)
        usuario = intent.getStringExtra("usuario").toString()
        val pacienteRecibido = intent.getParcelableExtra<Paciente>("Paciente")
        txtNombres.setText(pacienteRecibido.nombres.toString())
        txtApellidos.setText(pacienteRecibido.apellidos.toString())
        txtFechaNacimiento.setText(pacienteRecibido.fechaNacimiento.toString())
        txtNumeroHijos.setText(pacienteRecibido.numeroHijos.toString())
        txtTieneSeguro.setText(pacienteRecibido.tieneSeguro.toString())
        padreId = pacienteRecibido.id!!;
        btnActualizar.setOnClickListener { actualizarPaciente() }
        btnEliminar.setOnClickListener { eliminarPaciente() }
        btnCrearMedicamento.setOnClickListener { crearMedicamento() }
        btnGestionarMedicamento.setOnClickListener { gestionarMedicamento() }
        btnMenuRetorno.setOnClickListener { retorno() }
    }

    fun actualizarPaciente(){
        val actualizarPaciente = Paciente(id = padreId,
            nombres = txtNombres.text.toString(),
            apellidos = txtApellidos.text.toString(),
            fechaNacimiento = txtFechaNacimiento.text.toString(),
            numeroHijos = txtNumeroHijos.text.toString().toInt(),
            tieneSeguro = txtTieneSeguro.text.toString()
        )
        BDEPaciente.actualizarPaciente(actualizarPaciente)
        Toast.makeText(this, "Actualización realizada"+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }

    fun eliminarPaciente(){
        BDEPaciente.eliminarPaciente(padreId);
        Toast.makeText(this, "Paciente eliminado "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }

    fun crearMedicamento(){
        val pacienteRespaldo = Paciente(id = padreId,
            nombres = txtNombres.text.toString(),
            apellidos = txtApellidos.text.toString(),
            fechaNacimiento = txtFechaNacimiento.text.toString(),
            numeroHijos = txtNumeroHijos.text.toString().toInt(),
            tieneSeguro = txtTieneSeguro.text.toString()
        )
        val retorno = Intent(this, IngresarMedicamentoActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("padreId", padreId)
        retorno.putExtra("PacienteRespaldo", pacienteRespaldo)
        startActivity(retorno)
    }

    fun gestionarMedicamento(){
        val pacienteRespaldo = Paciente(id = padreId,
            nombres = txtNombres.text.toString(),
            apellidos = txtApellidos.text.toString(),
            fechaNacimiento = txtFechaNacimiento.text.toString(),
            numeroHijos = txtNumeroHijos.text.toString().toInt(),
            tieneSeguro = txtTieneSeguro.text.toString()
        )
        val retorno = Intent(this, ConsultarMedicamentoActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("padreId", padreId)
        retorno.putExtra("PacienteRespaldo", pacienteRespaldo)
        startActivity(retorno)
    }

    fun retorno(){
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }
}
