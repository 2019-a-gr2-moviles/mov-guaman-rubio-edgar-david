package com.example.examen

import android.os.Parcel
import android.os.Parcelable

class Medicamento(var id:Int?,
              var gramosAIngerir:Int,
              var nombre:String,
              var composicion:String,
              var usadoPara:String,
              var fechaCaducidad:String,
              var numeroPastillas:Int,
              var pacienteId:Int): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun toString(): String {
        return "Gramos ingesta: ${gramosAIngerir} Nombre: ${nombre} Composicion: ${composicion} Usado para: ${usadoPara} Fecha caducidad:${fechaCaducidad} N.Pastillas:${numeroPastillas}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeInt(gramosAIngerir)
        parcel.writeString(nombre)
        parcel.writeString(composicion)
        parcel.writeString(usadoPara)
        parcel.writeString(fechaCaducidad)
        parcel.writeInt(numeroPastillas)
        parcel.writeInt(pacienteId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Medicamento> {
        override fun createFromParcel(parcel: Parcel): Medicamento {
            return Medicamento(parcel)
        }

        override fun newArray(size: Int): Array<Medicamento?> {
            return arrayOfNulls(size)
        }
    }
}
