package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ingresar_jugador.*

class IngresarMedicamentoActivity : AppCompatActivity() {
    var padreId : Int = 0
    var usuario :String = "";
    var pacienteRespaldo : Paciente? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_jugador)
        usuario = intent.getStringExtra("usuario").toString()
        pacienteRespaldo = intent.getParcelableExtra<Paciente>("PacienteRespaldo")
        padreId = intent.getIntExtra("padreId", -1)
        btnGuardar.setOnClickListener { guardarMedicamento() }
    }

    fun guardarMedicamento(){
        val medicamento = Medicamento(id = null,
            gramosIngerir = txtnumeroCamiseta.text.toString().toInt(),
            nombreMedicamento = txtNombreCamiseta.text.toString(),
            composicion = txtNombreJugador.text.toString(),
            uso = txtpoderEspecialDos.text.toString(),
            fechaCaducidad = txtfechaIngresoEquipo.text.toString(),
            numeroPastillas = txtGoles.text.toString().toInt(),
            pacienteId = padreId)
        BDMedicamento.agregarMedicamento(medicamento)
        Toast.makeText(this, "Medicamento ingresado "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, ActualizarActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("Paciente", pacienteRespaldo)
        startActivity(retorno)
    }
}
