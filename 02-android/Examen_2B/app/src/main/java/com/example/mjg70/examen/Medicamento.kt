package com.example.mjg70.examen

import android.os.Parcel
import android.os.Parcelable

class Medicamento(var id:Int?,
              var gramosIngerir:Int,
              var nombreMedicamento:String,
              var composicion:String,
              var uso:String,
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
        return "Gramos a ingerir: ${gramosIngerir} Nombre medicamento: ${nombreMedicamento} Composicion: ${composicion} Uso:${uso} Fecha de Caducidad:${fechaCaducidad} Numero de pastillas:${numeroPastillas}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeInt(gramosIngerir)
        parcel.writeString(nombreMedicamento)
        parcel.writeString(composicion)
        parcel.writeString(uso)
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
