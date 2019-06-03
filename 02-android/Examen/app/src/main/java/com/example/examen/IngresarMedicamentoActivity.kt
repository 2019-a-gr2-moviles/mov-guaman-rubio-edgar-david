package com.example.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ingresar_medicamento.*

class IngresarMedicamentoActivity : AppCompatActivity() {
    var padreId : Int = 0
    var usuario :String = "";
    var pacienteRespaldo : Paciente? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_medicamento)
        usuario = intent.getStringExtra("usuario").toString()
        pacienteRespaldo = intent.getParcelableExtra<Paciente>("PacienteRespaldo")
        padreId = intent.getIntExtra("padreId", -1)
        btnGuardar.setOnClickListener { guardarJugador() }
    }

    fun guardarJugador(){
        val medicamento = Medicamento(id = null,
            gramosAIngerir = txtGramosIngerir.text.toString().toInt(),
            nombre = txtNombre.text.toString(),
            composicion = txtComposicion.text.toString(),
            usadoPara = txtUsadoPara.text.toString(),
            fechaCaducidad = txtfechaCaducidad.text.toString(),
            numeroPastillas = txtNumeroPastillas.text.toString().toInt(),
            pacienteId = padreId)
        BDMedicamento.agregarMedicamento(medicamento)
        Toast.makeText(this, "Medicamento creado exitosamente "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, ActualizarActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("Paciente", pacienteRespaldo)
        startActivity(retorno)
    }
}

