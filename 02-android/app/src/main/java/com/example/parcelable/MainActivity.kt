package com.example.parcelable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_rv.setOnClickListener{
            irARecyclerView()
        }
        btnRespuesta.setOnClickListener(){
            irARespuesta()
        }
        btnHttp.setOnClickListener(){
            irAHTTP()
        }
        btnMap.setOnClickListener {
            irAMapa()
        }
        btn_cicloVida.setOnClickListener {
            iraCicloVida()
        }
        btn_fragmentos.setOnClickListener {
            irFragmentos()
        }
    }
    fun irFragmentos(){
        val intentExplicito= Intent(this, PrimerFragment::class.java)
        startActivity(intentExplicito)
    }

    fun iraCicloVida(){
        val intentExplicito= Intent(this, CicloVidaActivity::class.java)
        startActivity(intentExplicito)
    }

    fun irARecyclerView(){
        val intentExplicito= Intent(this, RecicleViewActivity::class.java)
        startActivity(intentExplicito)
    }
    fun irAHTTP(){
        val intentExplicito= Intent(this, ConexionHttpActivity::class.java)
        startActivity(intentExplicito)
    }


    fun irARespuesta(){
        val intentExplicito= Intent(this, IntentRespuesta::class.java)
        startActivity(intentExplicito)
    }

    fun irAMapa(){
        val intentExplicito= Intent(this, MapsActivity::class.java)
        startActivity(intentExplicito)
    }
}
