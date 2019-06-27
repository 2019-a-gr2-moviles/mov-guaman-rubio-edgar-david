package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_parcelable.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_parcelable.setOnClickListener {
            irAParcelable()
        }

        btn_adapter.setOnClickListener {
            irAListView()
        }

        btn_recycler_view.setOnClickListener {
            irArecyclerView()
        }

        btn_intent_respuesta.setOnClickListener {
            irAIntentRespuesta() }

        btn_http.setOnClickListener {
            irAHttp()
        }
    }

    fun irAHttp(){
        val intentExplicito = Intent(this, ConexionHttpActivity::class.java)
        startActivity(intentExplicito)
    }
    fun irAIntentRespuesta(){
        val intentExplicito = Intent(
            this,
            IntentRespuestaActivity::class.java
        )
        startActivity(intentExplicito)
    }

    fun irArecyclerView(){
        val intentExplicito = Intent(
            this,
            ReciclerViewActivity::class.java
        )
        startActivity(intentExplicito)
    }


    fun irAParcelable(){
        val intentExplicito = Intent(
            this,
            Parcelable::class.java
        )
        val edgar = Usuario("Edgar",
            23,
            Date(),
            11.142)
        intentExplicito.putExtra("usuario",edgar)

        val manchas = Mascota("Cachetes", edgar)
        intentExplicito.putExtra("mascota", manchas)


        startActivity(intentExplicito)
    }

    fun irAListView(){
        val intentExplicito = Intent(this, ListViewActivity::class.java)
        startActivity(intentExplicito)
    }




}
