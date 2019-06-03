package com.example.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_actualizar_medicamento.*

class ActualizarMedicamentoActivity : AppCompatActivity() {
    var id :Int = 0;
    var idPadre :Int = 0
    var usuario :String = "";
    var pacienteRespaldo : Paciente? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_medicamento)
        usuario = intent.getStringExtra("usuario").toString()
        val medicamentoRecibido = intent.getParcelableExtra<Medicamento>("Jugador")
        pacienteRespaldo = intent.getParcelableExtra<Paciente>("EquipoRespaldo")
        txtGramosIngerir.setText(medicamentoRecibido.gramosAIngerir.toString())
        txtNombre.setText(medicamentoRecibido.nombre.toString())
        txtComposicion.setText(medicamentoRecibido.composicion.toString())
        txtUsadoPara.setText(medicamentoRecibido.usadoPara.toString())
        txtfechaCaducidad.setText(medicamentoRecibido.fechaCaducidad.toString())
        txtNumeroPastillas.setText(medicamentoRecibido.numeroPastillas.toString())
        id = medicamentoRecibido.id.toString().toInt()
        idPadre = medicamentoRecibido.pacienteId.toString().toInt()
        btnActualizarMedicamento.setOnClickListener { actualizarJugador() }
        btnEliminarMedicamento.setOnClickListener { eliminarJugador() }
    }

    fun actualizarJugador(){
        val medicamento = Medicamento(id = id,
            gramosAIngerir = txtGramosIngerir.text.toString().toInt(),
            nombre = txtNombre.text.toString(),
            composicion = txtComposicion.text.toString(),
            usadoPara = txtUsadoPara.text.toString(),
            fechaCaducidad = txtfechaCaducidad.text.toString(),
            numeroPastillas = txtNumeroPastillas.text.toString().toInt(),
            pacienteId = idPadre)
        BDMedicamento.actualizarMedicamento(medicamento)
        Toast.makeText(this, "Medicamento actualizado "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, ActualizarActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("Paciente", pacienteRespaldo)
        startActivity(retorno)
    }

    fun eliminarJugador(){
        BDMedicamento.eliminarMedicamento(id)
        Toast.makeText(this, "Medicamento eliminado "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, ActualizarActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("Paciente", pacienteRespaldo)
        startActivity(retorno)
    }
}
