import java.util.*

fun main(args: Array<String>){
    /*
    * Comentario
    * */

    //Variables

    //Mutables

    var nombre = "Edgar"
    nombre = "David"

    //Inmutables

    val nombreI = "Edgar"
    //nombreI = "David"
    
    //Tipos de datos
    val apellido = "Guaman"
    val edad = 23
    val sueldo = 1.21
    val casado = false
    val estudiante = true
    val hijos = null

    //Duck typing
    //Si parece un pato
    //Si camina como pato
    //Si vuela como pato
    //Es un pato

    //Condicionales

    /*if(apellido == "Guaman" || nombre == "Edgar"){
        println("Verdadero")
    }else{
        println("Falso")
    }

    val tieneNombreyApellido = if(apellido!=null && nombre!=null) "ok" else "no"
    print(tieneNombreyApellido)

    estaJalado(1.0)
    estaJalado(8.0)
    estaJalado(0.0)
    estaJalado(7.0)
    estaJalado(10.0)*/

    //holaMundo("Edgar")
    //holaMundoAvanzado(2)
    //val total = sumarDosNumeros(1,3)
    //println(total)
    //sumarDosNumeros(numUno:1,numDos:3)
    //las palabras numUno y numDos las pone el editor

    val arregloCumpleanos = arrayOf(1,2,3,4)
    val arregloTodo = arrayOf(1,"abcde",10.2,true)
    arregloCumpleanos[0] = 5

    //val notas = arrayListOf<Int>(1,2,3,4,5,6)
    val notas = arrayListOf(1,2,3,4,5)

    //MAP -> itera y modifica el arreglo
    val notasDos = notas.map { nota -> nota+1 }

    //FOR EACH -> Itera sobre el arreglo
    notasDos.forEach{
        //println("Notas 2: $it")
    }

    val notasTres = notas.map {nota ->
        val modulo = nota % 2
        val esPar = 0
        when(modulo){
            0 -> {
            nota+1
            }
            else -> {
                nota+2
            }
        }}

    notasTres.forEach{
        //println("Notas 3: $it")
    }

    //FILTER -> Filtra el arreglo
    val respuestaFilter = notas.filter {
        it>2
    }

    val respuestaFilter1 = notas.filter {
        it in 2..5
    }

    val respuestaFilter2 = notas.filter {
        it>2 && it<5
    }

    //respuestaFilter.forEach { println(it) }
    //respuestaFilter1.forEach { println(it) }
    //respuestaFilter2.forEach { println(it) }

    val respuestaFilter3 = notas.filter {
        it in 2..4
        }
        .map {
            it*2
        }

    //respuestaFilter3.forEach { println(it) }

    val novias = arrayListOf(1,2,2,3,4,5)

    val respuestaNovia = novias.any {
        it ==3
    }

    //println(respuestaNovia)

    val tazos = arrayListOf(1,2,3,4,5,6,7)
    val respuestaTazos = tazos.all {
        it>1
    }
    //println(respuestaTazos) //falso

    val totalTazos = tazos.reduce { valorAcumulado, tazo ->
        valorAcumulado+tazo
    }
    println(totalTazos)


    //FOR EACH INDEXED -> me devuelve el índice y el valor de cada elemento
    notas.forEachIndexed{indice,nota: Int ->
        //println("Indice:$indice")
        //println("Nota:$nota")
    }






}

fun estaJalado(nota:Double){
    when (nota){
        7.0 ->{
            println("Pasaste con las justas")
        }
        10.0 ->{
            println("Genial, felicitaciones")
        }
        0.0 ->{
            println("No mismo mijo")
        }
        else->{
            println("Tu nota es $nota")
        }
    }
}



fun holaMundo (mensaje:String):Unit{
    println("Mensaje: $mensaje")
}

fun holaMundoAvanzado(mensaje: Any):Unit{
    println("Mensaje: $mensaje")
}

fun sumarDosNumeros(numUno: Int, numDos: Int):Int{
    return numUno + numDos
}