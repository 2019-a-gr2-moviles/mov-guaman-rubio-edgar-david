package com.example.mjg70.examen

class BDPaciente{
    companion object {
        val LST_PACIENTE:ArrayList<Paciente> = ArrayList();
        var serial:Int= 1;
        var nombreUsuario:String = "";

        fun guardarUsuario(nombre:String){
            this.nombreUsuario = nombre;
        }

        fun agregarPaciente(paciente: Paciente):ArrayList<Paciente>{
            //paciente.id = serial
            serial++
            LST_PACIENTE.add(paciente)
            return LST_PACIENTE
        }

        fun mostrarPaciente(): List<Paciente> {
            return this.LST_PACIENTE
        }

        fun eliminarPaciente(id:Int){
            //this.LST_PACIENTE.removeAll{ it.id == id }
        }

        fun actualizarPaciente(paciente: Paciente){
            val indice = this.LST_PACIENTE.indexOfFirst { it.id == paciente.id }
            this.LST_PACIENTE[indice] = paciente
        }
    }
}