package com.example.mjg70.examen

import android.os.Parcel
import android.os.Parcelable


class Paciente(var id:Int?,
              var nombres:String,
              var apellidos:String,
              var fechaNacimiento:String,
              var numeroHijos:Int,
              var tieneSeguro:String) :Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(nombres)
        parcel.writeString(apellidos)
        parcel.writeString(fechaNacimiento)
        parcel.writeInt(numeroHijos)
        parcel.writeString(tieneSeguro)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Paciente> {
        override fun createFromParcel(parcel: Parcel): Paciente {
            return Paciente(parcel)
        }

        override fun newArray(size: Int): Array<Paciente?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "Nombres: ${nombres} Apellidos: ${apellidos} Fecha de nacimiento: ${fechaNacimiento} Numero de hijos:${numeroHijos} Tiene seguro:${tieneSeguro}"
    }


}
