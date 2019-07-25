package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_consultar.*
import kotlinx.android.synthetic.main.activity_consultar_jugador.*

class ConsultarMedicamento : AppCompatActivity() {
    var padreId : Int = 0
    var usuario :String = "";
    var pacienteRespaldo : Paciente? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usuario = intent.getStringExtra("usuario").toString()
        pacienteRespaldo = intent.getParcelableExtra<Paciente>("PacienteRespaldo")
        padreId = intent.getIntExtra("padreId", -1)
        setContentView(R.layout.activity_consultar_jugador)
        val adapter = ArrayAdapter<Medicamento>(
            this,
            android.R.layout.simple_list_item_1,
            BDMedicamento.mostrarMedicamento(padreId)
        )
        lstJugador.adapter = adapter;
        lstJugador.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val jugadorSeleccionado = parent.getItemAtPosition(position) as Medicamento
            val intentJugadorSeleccionado = Intent(this, ActualizarMedicamentoActivity::class.java)
            intentJugadorSeleccionado.putExtra("usuario", usuario)
            intentJugadorSeleccionado.putExtra("Medicamento", jugadorSeleccionado)
            intentJugadorSeleccionado.putExtra("PacienteRespaldo", pacienteRespaldo)
            startActivity(intentJugadorSeleccionado)
        }
    }
}
