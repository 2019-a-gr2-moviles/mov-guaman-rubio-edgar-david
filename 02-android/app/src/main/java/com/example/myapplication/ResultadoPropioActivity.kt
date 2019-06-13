package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_resultado_propio.*

class ResultadoPropioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_propio)

        btn_devolver_respuesta.setOnClickListener {
            devolverRespuesta()
        }
    }

    fun devolverRespuesta(){
        val nombre = "Edgar"
        val edad = 24

        val intentRespuesta = Intent()

        intentRespuesta.putExtra("nombreUsuario", nombre)
        intentRespuesta.putExtra("edadUsuario", edad)

        this.setResult(RESULT_OK, intentRespuesta)


    }
}
