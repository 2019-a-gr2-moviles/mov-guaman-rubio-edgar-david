package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_actualizar_jugador.*

class ActualizarMedicamentoActivity : AppCompatActivity() {
    var id :Int = 0;
    var idPadre :Int = 0
    var usuario :String = "";
    var pacienteRespaldo : Paciente? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_jugador)
        usuario = intent.getStringExtra("usuario").toString()
        val medicamentoRecibido = intent.getParcelableExtra<Medicamento>("Medicamento")
        pacienteRespaldo = intent.getParcelableExtra<Paciente>("PacienteRespaldo")
        txtnumeroCamiseta.setText(medicamentoRecibido.gramosIngerir.toString())
        txtNombreCamiseta.setText(medicamentoRecibido.nombreMedicamento.toString())
        txtNombreJugador.setText(medicamentoRecibido.composicion.toString())
        txtpoderEspecialDos.setText(medicamentoRecibido.uso.toString())
        txtfechaIngresoEquipo.setText(medicamentoRecibido.fechaCaducidad.toString())
        txtGoles.setText(medicamentoRecibido.numeroPastillas.toString())
        id = medicamentoRecibido.id.toString().toInt()
        idPadre = medicamentoRecibido.pacienteId.toString().toInt()
        btnActualizarJugador.setOnClickListener { actualizarMedicamento() }
        btnEliminarJugador.setOnClickListener { eliminarMedicamento() }
    }

    fun actualizarMedicamento(){
        val medicamento = Medicamento(id = id,
            gramosIngerir = txtnumeroCamiseta.text.toString().toInt(),
            nombreMedicamento = txtNombreCamiseta.text.toString(),
            composicion = txtNombreJugador.text.toString(),
            uso = txtpoderEspecialDos.text.toString(),
            fechaCaducidad = txtfechaIngresoEquipo.text.toString(),
            numeroPastillas = txtGoles.text.toString().toInt(),
            pacienteId = idPadre)
        BDMedicamento.actualizarMedicamento(medicamento)
        Toast.makeText(this, "Medicamento actualizado "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, ActualizarActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("Paciente", pacienteRespaldo)
        startActivity(retorno)
    }

    fun eliminarMedicamento(){
        BDMedicamento.eliminarMedicamento(id)
        Toast.makeText(this, "Medicamento eliminado "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, ActualizarActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("Paciente", pacienteRespaldo)
        startActivity(retorno)
    }
}
