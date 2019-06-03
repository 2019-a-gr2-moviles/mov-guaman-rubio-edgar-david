package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_list_view.*
import android.support.design.widget.Snackbar
import android.view.View

class ListViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val listaNombres = arrayListOf<String>();
        listaNombres.add("Juan")
        listaNombres.add("Pedro")
        listaNombres.add("David")
        listaNombres.add("Jose")
        listaNombres.add("Edgar")
        listaNombres.add("Raul")

        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,listaNombres)

        lv_ejemplo.adapter = adapter
        
        lv_ejemplo.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            Log.i("list-view","Posicion $position")
            mostrarSnack(view,"Snack detectado")
        }
   }
    fun mostrarSnack(view: View, texto:String){
        Snackbar.make(view, texto, Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }
}
