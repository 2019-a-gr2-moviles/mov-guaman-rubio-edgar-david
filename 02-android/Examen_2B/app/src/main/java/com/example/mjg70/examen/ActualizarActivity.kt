package com.example.mjg70.examen

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
        txtNombre.setText(pacienteRecibido.nombres)
        txtLiga.setText(pacienteRecibido.apellidos.toString())
        txtFechaCreacion.setText(pacienteRecibido.fechaNacimiento.toString())
        txtNumCopInter.setText(pacienteRecibido.numeroHijos.toString())
        txtCampeonAct.setText(pacienteRecibido.tieneSeguro.toString())
        padreId = pacienteRecibido.id!!;
        btnActualizar.setOnClickListener { actualizarPaciente() }
        btnEliminar.setOnClickListener { eliminarPaciente() }
        btnCrearJugador.setOnClickListener { crearMedicamento() }
        btnGestionarJugador.setOnClickListener { gestionarMedicamento() }
        btnMenuRetorno.setOnClickListener { retorno() }
    }

    fun actualizarPaciente(){
        val actualizarPaciente = Paciente(id = padreId,
            nombres = txtNombre.text.toString(),
            apellidos = txtLiga.text.toString(),
            fechaNacimiento = txtFechaCreacion.text.toString(),
            numeroHijos = txtNumCopInter.text.toString().toInt(),
            tieneSeguro = txtCampeonAct.text.toString()
        )
        BDPaciente.actualizarPaciente(actualizarPaciente)
        Toast.makeText(this, "Actualización correcta "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }

    fun eliminarPaciente(){
        BDPaciente.eliminarPaciente(padreId);
        Toast.makeText(this, "Eliminación correcta "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }

    fun crearMedicamento(){
        val pacienteRespaldo = Paciente(id = padreId,
            nombres = txtNombre.text.toString(),
            apellidos= txtLiga.text.toString(),
            fechaNacimiento = txtFechaCreacion.text.toString(),
            numeroHijos = txtNumCopInter.text.toString().toInt(),
            tieneSeguro = txtCampeonAct.text.toString()
        )
        val retorno = Intent(this, IngresarMedicamentoActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("padreId", padreId)
        retorno.putExtra("PacienteRespaldo", pacienteRespaldo)
        startActivity(retorno)
    }

    fun gestionarMedicamento(){
        val pacienteRespaldo = Paciente(id = padreId,
            nombres = txtNombre.text.toString(),
            apellidos = txtLiga.text.toString(),
            fechaNacimiento = txtFechaCreacion.text.toString(),
            numeroHijos = txtNumCopInter.text.toString().toInt(),
            tieneSeguro = txtCampeonAct.text.toString()
        )
        val retorno = Intent(this, ConsultarMedicamento::class.java)
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
