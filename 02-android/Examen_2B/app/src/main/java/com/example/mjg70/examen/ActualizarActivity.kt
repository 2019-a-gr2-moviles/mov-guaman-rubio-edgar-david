package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_actualizar.*

class ActualizarActivity : AppCompatActivity() {
    var usuario: String = "";
    var id: String = ""
    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar)
        usuario = intent.getStringExtra("usuario").toString()

        id = intent.getStringExtra("id").toString()
        ref = FirebaseDatabase.getInstance().getReference("pacientes")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    for (paciente in p0.children) {
                        val pacient = paciente.getValue(Paciente::class.java)
                        if (pacient!!.id == id) {
                            txtNombres.setText(pacient.nombres.trim())
                            txtApellidos.setText(pacient.apellidos)
                            txtFechaNacimiento.setText(pacient.fechaNacimiento)
                            txtNumeroHijos.setText(pacient.numeroHijos.toString())
                            txtTieneSeguro.setText(pacient.tieneSeguro)
                        }
                    }
                }
            }
        })

        btnActualizar.setOnClickListener { actualizarPaciente() }
        btnEliminar.setOnClickListener { eliminarPaciente() }
        btnCrearJugador.setOnClickListener { crearMedicamento() }
        btnGestionarJugador.setOnClickListener { gestionarMedicamento() }
        btnMenuRetorno.setOnClickListener { retorno() }
    }

    fun actualizarPaciente() {
        val nombres = txtNombres.text.toString()
        val apellidos = txtApellidos.text.toString()
        val fechaNacimiento = txtFechaNacimiento.text.toString()
        val numeroHijos = txtNumeroHijos.text.toString().toInt()
        val tieneSeguro = txtTieneSeguro.text.toString()

        val referenceData = FirebaseDatabase.getInstance().getReference("pacientes")
        val pacienteId = id
        if(pacienteId!=null){
            val pacienteActualizado = Paciente(pacienteId,nombres,apellidos,fechaNacimiento,numeroHijos, tieneSeguro)
            referenceData.child(pacienteId).setValue(pacienteActualizado).addOnCompleteListener{
                Toast.makeText(this, "Paciente actualizado por "+usuario, Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(this, "Valor de ID nulo ", Toast.LENGTH_SHORT).show()
        }

        val retorno = Intent(this, ConsultarActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }

    fun eliminarPaciente() {
        val pacient : DatabaseReference = FirebaseDatabase.getInstance().getReference("pacientes").child(id)
        pacient.removeValue()
        Toast.makeText(this,"Paciente eliminado por ${usuario}",Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, ConsultarActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }

    fun crearMedicamento() {
        val intentCrearMedicamento = Intent(this, IngresarMedicamentoActivity::class.java)
        intentCrearMedicamento.putExtra("usuario", usuario)
        intentCrearMedicamento.putExtra("id", id)
        startActivity(intentCrearMedicamento)
        finish()
    }

    fun gestionarMedicamento() {
        val intentConsultarMedicamento = Intent(this, ConsultarMedicamento::class.java)
        intentConsultarMedicamento.putExtra("usuario", usuario)
        intentConsultarMedicamento.putExtra("id", id)
        startActivity(intentConsultarMedicamento)
    }

    fun retorno() {
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }
}
