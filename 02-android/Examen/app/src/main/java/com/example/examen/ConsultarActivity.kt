package com.example.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import kotlinx.android.synthetic.main.activity_consultar.*

class ConsultarActivity : AppCompatActivity() {
    var usuario :String = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar)
        usuario = intent.getStringExtra("usuario").toString()
        val adapter = ArrayAdapter<Paciente>(
            this,
            android.R.layout.simple_list_item_1,
            BDEPaciente.mostrarPaciente()
        )
        lstView.adapter = adapter;
        lstView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val pacienteSeleccionado = parent.getItemAtPosition(position) as Paciente
            val intentPacienteSeleccionado = Intent(this, ActualizarActivity::class.java)
            intentPacienteSeleccionado.putExtra("Paciente", pacienteSeleccionado)
            intentPacienteSeleccionado.putExtra("usuario", usuario)
            startActivity(intentPacienteSeleccionado)
        }


    }
}

