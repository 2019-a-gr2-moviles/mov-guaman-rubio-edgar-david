package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent_respuesta.*

class IntentRespuestaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_respuesta)

        btn_enviar_intent_respuesta
            .setOnClickListener {
                enviarIntentConRespuesta()
            }

        btn_enviar_respuesta_propia.setOnClickListener {
            enviarIntentConRespuestaPropia()
        }

    }

    fun enviarIntentConRespuestaPropia() {

        val intentPropio = Intent(
            this,
            ResultadoPropioActivity::class.java
        )

        this.startActivityForResult(intentPropio, 305)
    }

    fun enviarIntentConRespuesta() {
        val intentConRespuesta = Intent(
            Intent.ACTION_PICK,
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        )
        this.startActivityForResult(intentConRespuesta, 304)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        when (resultCode) {
            RESULT_OK -> {
                Log.i("intent-respuesta", "LO LOGRAMOS! ${Activity.RESULT_OK}")

                when (requestCode) {
                    304 -> {
                        Log.i("intent-respuesta", "CONTACTO LLEGO!!")

                        val uri = data?.data

                        val cursor = contentResolver.query(
                            uri,
                            null,
                            null,
                            null,
                            null
                        )

                        cursor?.moveToFirst()

                        val indiceTelefono = cursor?.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )

                        val telefono = cursor?.getString(indiceTelefono!!)

                        cursor?.close()

                        Log.i("intent-respuesta", "El telefono es: $telefono")


                    }
                    305 -> {
                        val nombre = data?.getStringExtra("nombreUsuario")
                        val edad = data?.getIntExtra("edadUsuario",0)
                        Log.i("intent-respuesta", "Nombre: $nombre Edad: $edad")

                    }
                }


            }
            RESULT_CANCELED -> {
                Log.i("intent-respuesta", "No escogio =( ")
            }
        }
    }


}
