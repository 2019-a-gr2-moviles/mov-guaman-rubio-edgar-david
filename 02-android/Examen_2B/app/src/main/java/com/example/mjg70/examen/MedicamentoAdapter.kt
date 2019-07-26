package com.example.mjg70.examen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MedicamentoAdapter(val contexto: Context, val layoutResId: Int, val MedicamentoList: List<Medicamento>)
    :ArrayAdapter<Medicamento>(contexto,layoutResId, MedicamentoList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(contexto)
        val view: View = layoutInflater.inflate(layoutResId,null);
        val list_view = view.findViewById<TextView>(R.id.txv_Medicamento);

        val medicamento = MedicamentoList[position]
        val datosMedicamentos = "Gramos a ingerir: \n" +medicamento.gramosIngerir+ "\n" +
                "Nombre del medicamento:\n" + medicamento.nombreMedicamento + "\n" +
                "Composición:\n" + medicamento.composicion + "\n" +
                "Uso:\n" + medicamento.uso + "\n" +
                "Fecha de caducidad:\n" + medicamento.fechaCaducidad + "\n" +
                "Numero de pastillas:\n" + medicamento.numeroPastillas + "\n" +
                "Latitud:\n" + medicamento.latitud + "\n" +
                "Longitud:\n" + medicamento.longitud + "\n"
        list_view.text = datosMedicamentos
        return view;
    }
}