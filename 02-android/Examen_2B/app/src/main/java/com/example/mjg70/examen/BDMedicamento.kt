package com.example.mjg70.examen

class BDMedicamento {
    companion object {
        val LST_MEDICAMENTO:ArrayList<Medicamento> = ArrayList();
        var serial:Int= 1;

        fun agregarMedicamento(medicamento: Medicamento):ArrayList<Medicamento>{
            //medicamento.id = serial
            serial++
            LST_MEDICAMENTO.add(medicamento)
            return LST_MEDICAMENTO
        }

        //fun mostrarMedicamento(padreId:Int): List<Medicamento> {
            //val listaFiltradaMedicamento = this.LST_MEDICAMENTO.filter { it.pacienteId ==  padreId}
            //return listaFiltradaMedicamento
        //}

        //fun eliminarMedicamento(id:Int){
            //this.LST_MEDICAMENTO.removeAll{ it.id == id }
        //}

        //fun actualizarMedicamento(medicamento: Medicamento){
            //val indice = this.LST_MEDICAMENTO.indexOfFirst { it.id == medicamento.id }
            //this.LST_MEDICAMENTO[indice] = medicamento
        //}
    }
}