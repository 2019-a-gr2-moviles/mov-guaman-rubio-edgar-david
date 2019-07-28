package com.example.mjg70.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_actualizar_medicamento.*

class ActualizarMedicamentoActivity : AppCompatActivity() {
    var usuario: String = "";
    var id: String = "";
    var idMedicamentoRecibido: String = ""
    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_medicamento)

        usuario = intent.getStringExtra("usuario").toString()
        id = intent.getStringExtra("id").toString()
        idMedicamentoRecibido = intent.getStringExtra("idMedicamentoRecibido").toString()

        ref = FirebaseDatabase.getInstance().getReference("medicamentos").child(id)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    for (medicamento in p0.children){
                        val medicine = medicamento.getValue(Medicamento::class.java)
                        if(medicine!=null){
                            if(TextUtils.equals(medicine.idMedicamento,idMedicamentoRecibido)){
                                txtGramosIngerir.setText(medicine.gramosIngerir.toString())
                                txtNombreMedicamento.setText(medicine.nombreMedicamento)
                                txtComposicion.setText(medicine.composicion)
                                txtUso.setText(medicine.uso)
                                txtFechaCaducidad.setText(medicine.fechaCaducidad)
                                txtNumeroPastillas.setText(medicine.numeroPastillas.toString())
                                txtLatitud.setText(medicine.latitud.toString())
                                txtLongitud.setText(medicine.longitud.toString())
                            }

                        }
                    }
                }
            }
        })

        btnActualizarMedicamento.setOnClickListener { actualizarMedicamento() }
        btnEliminarMedicamento.setOnClickListener { eliminarMedicamento() }
    }

    fun actualizarMedicamento() {

        val gramos = txtGramosIngerir.text.toString().toInt()
        val nombre = txtNombreMedicamento.text.toString()
        val composicion = txtComposicion.text.toString()
        val uso = txtUso.text.toString()
        val fecha = txtFechaCaducidad.text.toString()
        val numPastillas = txtNumeroPastillas.text.toString().toInt()
        val latitud = txtLatitud.text.toString().toFloat()
        val longitud = txtLongitud.text.toString().toFloat()

        val pacienteId = id
        val idMedicamentoa = idMedicamentoRecibido
        val referenciaDatos = FirebaseDatabase.getInstance().getReference("medicamentos").child(pacienteId)

        if(idMedicamentoa!=null){
            val medicamentoIngresado = Medicamento(idMedicamentoa,gramos,nombre,composicion,uso,fecha,numPastillas,latitud,longitud)
            referenciaDatos.child(idMedicamentoa).setValue(medicamentoIngresado).addOnCompleteListener {
                Toast.makeText(this, "Actualización realizada "+usuario, Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "El Id es nulo", Toast.LENGTH_SHORT).show()
        }

        val retorno = Intent(this, ActualizarActivity::class.java)
        retorno.putExtra("usuario",usuario)
        retorno.putExtra("id",id)
        startActivity(retorno)
    }

    fun eliminarMedicamento() {

        val medicamentoE : DatabaseReference = FirebaseDatabase.getInstance().getReference("medicamentos").child(id).child(idMedicamentoRecibido)
        medicamentoE.removeValue()
        Toast.makeText(this, "Se ha eliminado el medicamento"+usuario,Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, ConsultarMedicamento::class.java)
        retorno.putExtra("usuario",usuario)
        retorno.putExtra("idMedicamentoRecibido",idMedicamentoRecibido)
        retorno.putExtra("id",id)

        startActivity(retorno)
    }
}
