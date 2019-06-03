package com.example.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_consultar.*
import kotlinx.android.synthetic.main.activity_consultar_medicamento.*

class ConsultarMedicamentoActivity : AppCompatActivity() {
    var padreId : Int = 0
    var usuario :String = "";
    var pacientRespaldo : Paciente? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usuario = intent.getStringExtra("usuario").toString()
        pacientRespaldo = intent.getParcelableExtra<Paciente>("PacienteRespaldo")
        padreId = intent.getIntExtra("padreId", -1)
        setContentView(R.layout.activity_consultar_medicamento)
        val adapter = ArrayAdapter<Medicamento>(
            this,
            android.R.layout.simple_list_item_1,
            BDMedicamento.mostrarMedicamento(padreId)
        )
        lstJugador.adapter = adapter;
        lstJugador.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val medicamentoSeleccionado = parent.getItemAtPosition(position) as Medicamento
            val intentMedicamentoSeleccionado = Intent(this, ActualizarMedicamentoActivity::class.java)
            intentMedicamentoSeleccionado.putExtra("usuario", usuario)
            intentMedicamentoSeleccionado.putExtra("Jugador", medicamentoSeleccionado)
            intentMedicamentoSeleccionado.putExtra("EquipoRespaldo", pacientRespaldo)
            startActivity(intentMedicamentoSeleccionado)
        }
    }
}
