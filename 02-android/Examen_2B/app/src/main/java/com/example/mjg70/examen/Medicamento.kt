package com.example.mjg70.examen

class Medicamento(
    val idMedicamento: String,
    val gramosIngerir: Int,
    val nombreMedicamento: String,
    val composicion: String,
    val uso: String,
    val fechaCaducidad: String,
    val numeroPastillas: Int,
    val latitud: Float,
    val longitud: Float
) {
    constructor() : this("",0,"","","","",0, 0F, 0F)

}
