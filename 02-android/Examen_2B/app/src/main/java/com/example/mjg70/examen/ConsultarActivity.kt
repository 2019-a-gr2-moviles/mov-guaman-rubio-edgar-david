package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import com.google.firebase.database.*

class ConsultarActivity : AppCompatActivity() {
    var usuario :String = "";
    lateinit var pacientesList : MutableList<Paciente>
    lateinit var ref : DatabaseReference
    lateinit var listview : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar)
        pacientesList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("pacientes")
        listview = findViewById(R.id.listView_Pacientes)
        usuario = intent.getStringExtra("usuario").toString()

        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }
            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    pacientesList.clear()
                    for (paciente in p0.children){
                        val pacient = paciente.getValue(Paciente::class.java)
                        pacientesList.add(pacient!!)
                    }
                val adapter = PacienteAdapter(applicationContext, R.layout.pacientes,pacientesList);
                listview.adapter = adapter
                
                listview.onItemClickListener=AdapterView.OnItemClickListener { parent, _, position, _ ->
                    val idPacienteSeleccionado = pacientesList[position].id
                    val intentPacienteSeleccionado = Intent(this@ConsultarActivity,ActualizarActivity::class.java)
                    intentPacienteSeleccionado.putExtra("id",idPacienteSeleccionado)
                    intentPacienteSeleccionado.putExtra("usuario",usuario)
                    startActivity(intentPacienteSeleccionado)
                }
                }
            }
        });

        }
    }
