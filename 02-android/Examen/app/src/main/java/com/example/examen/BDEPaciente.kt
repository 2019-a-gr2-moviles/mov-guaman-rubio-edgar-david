package com.example.examen

class BDEPaciente{
    companion object {
        val listaPaciente:ArrayList<Paciente> = ArrayList();
        var serial:Int= 1;
        var nombreUsuario:String = "";

        fun guardarUsuario(nombre:String){
            this.nombreUsuario = nombre;
        }

        fun agregarPaciente(equipo: Paciente):ArrayList<Paciente>{
            equipo.id = serial
            serial++
            listaPaciente.add(equipo)
            return listaPaciente
        }

        fun mostrarPaciente(): List<Paciente> {
            return this.listaPaciente
        }

        fun eliminarPaciente(id:Int){
            this.listaPaciente.removeAll{ it.id == id }
        }

        fun actualizarPaciente(equipo: Paciente){
            val indice = this.listaPaciente.indexOfFirst { it.id == equipo.id }
            this.listaPaciente[indice] = equipo
        }

    }

}