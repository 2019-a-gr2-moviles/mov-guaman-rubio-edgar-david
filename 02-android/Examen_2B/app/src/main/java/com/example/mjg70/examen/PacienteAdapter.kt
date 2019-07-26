package com.example.mjg70.examen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PacienteAdapter(val contexto: Context, val layoutResId: Int, val PacienteList: List<Paciente>)
    :ArrayAdapter<Paciente>(contexto,layoutResId, PacienteList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(contexto)
        val view: View = layoutInflater.inflate(layoutResId,null);
        val list_view = view.findViewById<TextView>(R.id.txv_Paciente);
        val paciente = PacienteList[position]
        val datos = "Nombres del paciente: \n" +paciente.nombres+ "\n" +
                "Apellidos del paciente:\n" + paciente.apellidos + "\n" +
                "Fecha de Nacimiento:\n" + paciente.fechaNacimiento  + "\n" +
                "Número de hijos:\n" + paciente.numeroHijos + "\n" +
                "¿Tiene seguro? \n" + paciente.tieneSeguro
        list_view.text = datos
        return view;

    }
}