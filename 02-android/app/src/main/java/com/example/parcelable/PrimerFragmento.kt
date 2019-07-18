package com.example.parcelable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_primer_fragmento.*

class PrimerFragmento : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primer_fragmento)
    }

    fun abrirPrimerFragmento(){
        //1) Manager
        val fragmentManager = supportFragmentManager
        //2) Empezar la transaccion
        val transaccion = fragmentManager.beginTransaction()
        //3) Definir la instancia del fragmento
        val primerFragmento = PrimerFragment()
        //4) Reemplazar el fragmento
        transaccion.replace(R.id.constraintLayout2,primerFragmento)
        //5) terminar la transaccion
        transaccion.commit()
    }
}
