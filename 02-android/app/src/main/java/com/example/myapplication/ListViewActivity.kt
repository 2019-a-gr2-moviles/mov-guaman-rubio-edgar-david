package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val listaNombres = arrayListOf<String>()
        listaNombres.add("Vicente")
        listaNombres.add("Adrian")
        listaNombres.add("Carolina")
        listaNombres.add("Johanna")
        listaNombres.add("Felipe")

        val adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            listaNombres)

        lv_ejemplo.adapter = adapter

        lv_ejemplo.onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            Log.i("list-view","Posicion $position")
        }



    }
}
