package com.example.examen

class BDMedicamento {
    companion object {
        val lista_Medicamento:ArrayList<Medicamento> = ArrayList();
        var serial:Int= 1;

        fun agregarMedicamento(medicamento: Medicamento):ArrayList<Medicamento>{
            medicamento.id = serial
            serial++
            lista_Medicamento.add(medicamento)
            return lista_Medicamento
        }

        fun mostrarMedicamento(padreId:Int): List<Medicamento> {
            val listaFiltradaMedicamento = this.lista_Medicamento.filter { it.pacienteId ==  padreId}
            return listaFiltradaMedicamento
        }

        fun eliminarMedicamento(id:Int){
            this.lista_Medicamento.removeAll{ it.id == id }
        }

        fun actualizarMedicamento(medicamento: Medicamento){
            val indice = this.lista_Medicamento.indexOfFirst { it.id == medicamento.id }
            this.lista_Medicamento[indice] = medicamento
        }
    }
}