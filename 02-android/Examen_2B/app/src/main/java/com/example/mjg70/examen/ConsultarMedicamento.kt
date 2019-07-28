package com.example.mjg70.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import com.google.firebase.database.*

class ConsultarMedicamento : AppCompatActivity() {
    var usuario :String = "";
    var id:String=""
    lateinit var ref: DatabaseReference
    lateinit var medicamentoList: MutableList<Medicamento>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar_medicamento)

        usuario = intent.getStringExtra("usuario").toString()
        id = intent.getStringExtra("id").toString()

        listView = findViewById(R.id.listMedicamento)
        medicamentoList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("medicamentos").child(id)
        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    for (medicine in p0.children){
                        val medicina = medicine.getValue(Medicamento::class.java)
                        medicamentoList.add(medicina!!)
                    }

                    val adapter = MedicamentoAdapter(applicationContext,R.layout.medicamentos,medicamentoList)
                    listView.adapter = adapter
                    listView.onItemClickListener = AdapterView.OnItemClickListener { parent, _ , position, _ ->
                        val idMedicamentoSeleccionado = medicamentoList[position].idMedicamento
                        val intentMedicamentoSeleccionado = Intent(this@ConsultarMedicamento,ActualizarMedicamentoActivity::class.java)
                        intentMedicamentoSeleccionado.putExtra("idMedicamentoRecibido",idMedicamentoSeleccionado)
                        intentMedicamentoSeleccionado.putExtra("usuario",usuario)
                        intentMedicamentoSeleccionado.putExtra("id",id)

                        startActivity(intentMedicamentoSeleccionado)
                    }
                }
            }
        })
    }
}
