package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recicler_view.*
import java.util.ArrayList

class ReciclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recicler_view)

        val lista = arrayListOf<Persona>()
        //val recycler_view = rv_personas
        //val actividad = this
        lista.add(Persona("Edgar","1725489657"))
        lista.add(Persona("David","1578468952"))
        lista.add(Persona("Pablo","1458769852"))

        iniciarRecyclerView(lista,this,rv_personas)
    }

    fun iniciarRecyclerView(lista:ArrayList<Persona>, actividad:ReciclerViewActivity, recycler_view:RecyclerView){
        val adaptadorPersona = AdaptadorPersona(lista,actividad,recycler_view)

        rv_personas.adapter = adaptadorPersona
        rv_personas.itemAnimator = DefaultItemAnimator()
        rv_personas.layoutManager = LinearLayoutManager(this)

        adaptadorPersona.notifyDataSetChanged()
    }

    fun cambiarNombreTextView(texto:String){
        txv_titulo_rv.text=texto;
    }


}
