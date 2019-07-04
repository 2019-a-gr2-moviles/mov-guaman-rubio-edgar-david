package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        btn_toast.setOnClickListener {
            irAToast()
        }
        btn_adapter.setOnClickListener {
            irAListView()
        }
        btn_recycler_view.setOnClickListener {
            irArecyclerView()
        }
        btn_intent_respuesta.setOnClickListener {
            irAIntentRespuesta()
        }

        btn_http.setOnClickListener {
            irAHttp()
        }

        btn_maps.setOnClickListener {
            iraMapa()
        }

    }

    fun iraMapa(){
        val intentExplicito = Intent(
            this,
            MapsActivity::class.java
        )
        startActivity(intentExplicito)
    }

    fun irAHttp(){
        val intentExplicito = Intent(
            this,
            ConexionHttpActivity::class.java
        )
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

    fun irAToast(){
        val intentExplicito = Intent(
            this,
            Main2Activity::class.java
        )
        startActivity(intentExplicito)
    }

    fun irAListView(){
        val intentExplicito = Intent(
            this,
            ListViewActivity::class.java
        )
        startActivity(intentExplicito)
    }


    fun irAParcelable(){
        val intentExplicito = Intent(
            this,
            Parcelable::class.java
        )
        val adrian = Usuario("Adrian",
            29,
            Date(),
            12.12)
        intentExplicito.putExtra("usuario",adrian)

        val cachetes = Mascota("Cachetes", adrian)
        intentExplicito.putExtra("mascota", cachetes)


        startActivity(intentExplicito)


    }




}
