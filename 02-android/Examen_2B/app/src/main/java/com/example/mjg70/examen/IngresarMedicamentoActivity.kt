package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_ingresar_medicamento.*

class IngresarMedicamentoActivity : AppCompatActivity() {
    var usuario :String = "";
    var id:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_medicamento)
        usuario = intent.getStringExtra("usuario").toString()
        id = intent.getStringExtra("id")
        btnGuardar.setOnClickListener { guardarMedicamento() }
    }

    fun guardarMedicamento(){
        val gramosIngerir = txtGramosIngerir.text.toString().toInt()
        val nombreMedicamento = txtNombreMedicamento.text.toString()
        val composicion = txtComposicion.text.toString()
        val uso = txtUso.text.toString()
        val fechaCaducidad = txtFechaCaducidad.text.toString()
        val numeroPastillas = txtNumeroPastillas.text.toString().toInt()
        val latitud = txtLatitud.text.toString().toFloat()
        val longitud = txtLongitud.text.toString().toFloat()
        val pacienteId = id

        val referenceData = FirebaseDatabase.getInstance().getReference("medicamentos").child(pacienteId)
        val medicamentoId = referenceData.push().key
        if(medicamentoId!=null){
            val medicamentoNuevo = Medicamento(medicamentoId,gramosIngerir,nombreMedicamento,composicion,uso,fechaCaducidad,numeroPastillas,latitud,longitud)
            referenceData.child(medicamentoId).setValue(medicamentoNuevo).addOnCompleteListener {
                Toast.makeText(this, "Medicamento ingresado por"+usuario,Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "id tiene valor nulo",Toast.LENGTH_SHORT).show()
        }

        val retorno = Intent(this, ConsultarActivity::class.java)
        retorno.putExtra("usuario",usuario)
        retorno.putExtra("id",id)
        startActivity(retorno)

    }
}
