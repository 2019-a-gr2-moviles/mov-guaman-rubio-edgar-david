import java.awt.Container
import java.awt.Font
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JOptionPane
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.event.ListSelectionEvent
import javax.swing.event.ListSelectionListener
import javax.swing.table.DefaultTableModel
import javax.swing.table.TableColumnModel
import java.awt.Color
import javax.swing.JScrollPane
import javax.swing.JTable


class Empleado : JFrame("MÓDULO EMPLEADOS"), ActionListener {
    private var Contenedor: Container? = null
    internal var resultado = ""
    private var lblEmpleado: JLabel? = null
    private var lblNombre: JLabel? = null
    private var lblApellido: JLabel? = null
    private var lblCedula: JLabel? = null
    private var lblCargo: JLabel? = null
    private var txtNombre: JTextField? = null
    private var txtApellido: JTextField? = null
    private var txtCedula: JTextField? = null
    private var txtCargo: JTextField? = null
    internal var strCadBD = ""
    internal var auxID: Int = 0

    internal var strTitulos = arrayOf("Nombre", "Apellido", "Cédula", "Cargo")
    private val modTabla = object : DefaultTableModel(strTitulos, 0) {
        private val serialVersionUID = 1L
        override fun isCellEditable(rowIndex: Int, mColIndex: Int): Boolean {
            return false
        }
    }
    private val tabla = JTable(modTabla)

    init {
        font = Font("Arial Black", Font.PLAIN, 12)
        this.setGUI()
        this.setBounds(200, 90, 924, 445)
        this.isVisible = true
    }

    fun setGUI() {
        Contenedor = this.contentPane
        contentPane.layout = null

        val panel = JPanel()
        panel.background = Color(32, 178, 170)
        panel.setBounds(0, 0, 908, 54)
        contentPane.add(panel)
        panel.layout = null

        lblEmpleado = JLabel("EMPLEADO")
        lblEmpleado!!.foreground = Color.WHITE
        lblEmpleado!!.font = Font("Arial", Font.BOLD, 25)
        lblEmpleado!!.setBounds(10, 11, 152, 32)
        panel.add(lblEmpleado)

        val panel_1 = JPanel()
        panel_1.background = Color(0, 0, 255)
        panel_1.setBounds(0, 52, 908, 195)
        contentPane.add(panel_1)
        panel_1.layout = null

        lblApellido = JLabel("Apellido:")
        lblApellido!!.foreground = Color.WHITE
        lblApellido!!.font = Font("Arial", Font.PLAIN, 18)
        lblApellido!!.setBounds(123, 69, 74, 14)
        panel_1.add(lblApellido)

        lblCedula = JLabel("N.Cédula:")
        lblCedula!!.foreground = Color.WHITE
        lblCedula!!.font = Font("Arial", Font.PLAIN, 18)
        lblCedula!!.setBounds(412, 30, 89, 14)
        panel_1.add(lblCedula)

        lblNombre = JLabel("Nombre:")
        lblNombre!!.foreground = Color.WHITE
        lblNombre!!.font = Font("Arial", Font.PLAIN, 18)
        lblNombre!!.setBounds(123, 30, 74, 14)
        panel_1.add(lblNombre)

        lblCargo = JLabel("Cargo:")
        lblCargo!!.foreground = Color.WHITE
        lblCargo!!.font = Font("Arial", Font.PLAIN, 18)
        lblCargo!!.setBounds(440, 69, 57, 14)
        panel_1.add(lblCargo)

        txtApellido = JTextField()
        txtApellido!!.setBounds(207, 66, 109, 20)
        panel_1.add(txtApellido)
        txtApellido!!.columns = 10

        txtCedula = JTextField()
        txtCedula!!.setBounds(499, 26, 109, 20)
        panel_1.add(txtCedula)
        txtCedula!!.columns = 10

        txtNombre = JTextField()
        txtNombre!!.setBounds(208, 29, 108, 20)
        panel_1.add(txtNombre)
        txtNombre!!.columns = 10

        val btnRegistrar = JButton("Registrar")
        btnRegistrar.foreground = Color.BLACK
        btnRegistrar.setBounds(194, 155, 89, 23)
        btnRegistrar.actionCommand = "registrar"
        btnRegistrar.addActionListener(this)
        panel_1.add(btnRegistrar)

        val btnCancelar = JButton("Eliminar")
        btnCancelar.foreground = Color.BLACK
        btnCancelar.setBounds(424, 155, 89, 23)
        btnCancelar.actionCommand = "eliminar"
        btnCancelar.addActionListener(this)
        panel_1.add(btnCancelar)

        val btnActualizar = JButton("Actualizar")
        btnActualizar.foreground = Color.BLACK
        btnActualizar.setBounds(300, 155, 102, 23)
        btnActualizar.actionCommand = "actualizar"
        btnActualizar.addActionListener(this)

        panel_1.add(btnActualizar)

        val btnSalir = JButton("Salir")
        btnSalir.foreground = Color.BLACK
        btnSalir.setBounds(532, 155, 89, 23)
        btnSalir.actionCommand = "salir"
        btnSalir.addActionListener(this)
        panel_1.add(btnSalir)

        txtCargo = JTextField()
        txtCargo!!.setBounds(500, 68, 102, 20)
        panel_1.add(txtCargo)
        txtCargo!!.columns = 10

        val panel_3 = JPanel()
        panel_3.setBounds(0, 250, 908, 156)
        contentPane.add(panel_3)
        panel_3.layout = null

        val scrollPane = JScrollPane(tabla)
        scrollPane.setBounds(0, 0, 908, 156)
        panel_3.add(scrollPane)
        tabla.selectionModel.addListSelectionListener(EscuchadordeFilaJTable())
        val columnModel = tabla.columnModel
        for (i in 0 until columnModel.columnCount) {
            columnModel.getColumn(i).minWidth = 100
        }
        llenarJTable()

    }

    override fun actionPerformed(e: ActionEvent) {
        var errores = ""
        var patron: Pattern
        var empata: Matcher
        val accion = e.actionCommand

        if (accion == "salir") {
            this.dispose()
        }
        if (accion == "registrar") {
            errores = ""

            if (txtApellido!!.text == "") {
                JOptionPane.showMessageDialog(null, "Debe ingresar el apellido del empleado")
                errores += "Debe ingresar el apellido del empleado"
            } else {
                patron = Pattern.compile("([ ]?([a-zA-ZñÑáéíóúÁÉÍÓÚ])+[ ]?(([a-zA-ZñÑáéíóúÁÉÍÓÚ])*)?)+")
                empata = patron.matcher(txtApellido!!.text)
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar sólo letras en apellido del empleado")
                    errores += "Debe ingresar sólo letras en apellido del empleado"
                }
            }
            if (txtCedula!!.text == "") {
                JOptionPane.showMessageDialog(null, "Debe ingresar el número de cédula del empleado")
                errores += "Debe ingresar el número de cédula"
            } else {
                patron = Pattern.compile("\\d*")
                empata = patron.matcher(txtCedula!!.text)
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar sólo números en el número de cédula")
                    errores += "Debe ingresar solo números en el número de cédula"
                }
            }
            if (txtNombre!!.text == "") {
                JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del empleado")
                errores += "Debe ingresar el nombre del empleado"
            } else {
                patron = Pattern.compile("[\\wñÑ\\s]+")
                empata = patron.matcher(txtNombre!!.text)
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar sólo letras en nombre del empleado")
                    errores += "Debe ingresar sólo letras en nombre del empleado"
                }
            }
            if (txtCargo!!.text == "") {
                JOptionPane.showMessageDialog(null, "Debe ingresar el cargo del empleado")
                errores += "Debe ingresar el cargo del empleado"
            } else {
                patron = Pattern.compile("[\\wñÑ\\s]+")
                empata = patron.matcher(txtCargo!!.text)
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar sólo letras en el cargo")
                    errores += "Debe ingresar sólo letras en el cargo"
                }
            }

            if (errores != "") {
                JOptionPane.showMessageDialog(null, "Error en el ingreso de datos")
            } else {
                val insercion =
                    txtNombre!!.text + ";" + txtApellido!!.text + ";" + txtCedula!!.text + ";" + txtCargo!!.text + ";"
                try {
                    FileWriter(
                        "C:/Users/Edgar/Documents/IntelliJ/archivos/empleados.txt",
                        true
                    ).use { writer -> BufferedWriter(writer).use { bw -> bw.write(insercion) } }
                } catch (a: IOException) {
                    println("Error al momento de insertar un empleado")
                }

                RemoverElementosJtable()
                llenarJTable()
            }
        }
        if (accion == "actualizar") {
            var archivo:File? = null
            var fr:FileReader? = null
            var br:BufferedReader? = null
            val lstRespaldo = ArrayList<String>()
            archivo = File("C:/Users/Edgar/Documents/IntelliJ/archivos/empleados.txt")
            fr = FileReader(archivo!!)
            br = BufferedReader(fr!!)
            for(line in br.lines()){
                lstRespaldo.add(line)
            }
            br!!.close()

            val bw = BufferedWriter(FileWriter("C:/Users/Edgar/Documents/IntelliJ/archivos/empleados.txt"))
            bw.write("")
            bw.close()
            var reemplazo = ""
            val productoActualizar:String = txtNombre!!.text

            for (i in lstRespaldo.indices)
            {
                val lstValues: List<String> = lstRespaldo[i].split(";").map {it.trim() }
                if (lstValues.get(0) == productoActualizar)
                {
                    reemplazo = (txtNombre!!.text + ";"
                            + txtApellido!!.text + ";"
                            + txtCedula!!.text + ";"
                            + txtCargo!!.text + ";")
                    lstRespaldo.removeAt(i)
                }
            }
            lstRespaldo.add(reemplazo)
            val bw1 = BufferedWriter(FileWriter("C:/Users/Edgar/Documents/IntelliJ/archivos/empleados.txt"))
            for (valor in lstRespaldo)
            {
                bw1.write(valor)
                bw1.write("\n")
            }
            bw1.close()

        }
        if(accion == "borrar"){
            var archivo:File? = null
            var fr:FileReader? = null
            var br:BufferedReader? = null
            val lstRespaldo = ArrayList<String>()
            archivo = File("C:/Users/Edgar/Documents/IntelliJ/archivos/empleados.txt")
            fr = FileReader(archivo!!)
            br = BufferedReader(fr!!)
            for(line in br.lines()){
                lstRespaldo.add(line)
            }
            for(index in lstRespaldo){
                println(index)
            }
            br!!.close()

            val bw = BufferedWriter(FileWriter("C:/Users/Edgar/Documents/IntelliJ/archivos/empleados.txt"))
            bw.write("")
            bw.close()

            val productoBorrar:String = txtNombre!!.text
            for (i in lstRespaldo.indices)
            {
                val lstValues: List<String> = lstRespaldo[i].split(";").map { it.trim() }
                if (lstValues.get(0) == productoBorrar)
                {
                    lstRespaldo.removeAt(i)
                }
            }
            val bw1 = BufferedWriter(FileWriter("C:/Users/Edgar/Documents/IntelliJ/archivos/empleados.txt"))
            for (valor in lstRespaldo)
            {
                bw1.write(valor)
                bw1.write("\n")
            }
            bw1.close()
        }
        RemoverElementosJtable()
        llenarJTable()

        }

    fun limpiaGUI() {
        txtNombre!!.text = ""
        txtCedula!!.text = ""
        txtNombre!!.text = ""
        txtCargo!!.text = ""

    }

    fun llenarJTable() {
        try {
            val file = File("D:/Universidad/Programas Java/PapeleriaKotlin/src/archivos/empleados.txt")
            val br = BufferedReader(FileReader(file))
            // get lines from txt file
            val tableLines = br.lines().toArray()

            // extract data from lines
            // set data to jtable model
            for (i in tableLines.indices) {
                val line = tableLines[i].toString().trim { it <= ' ' }
                val dataRow = line.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                modTabla.addRow(dataRow)
            }
        } catch (ex: Exception) {
            println("Error al llenar la tabla")
        }

    }

    fun RemoverElementosJtable() {
        val filas = tabla.rowCount
        var i = 0
        while (filas > i) {
            modTabla.removeRow(0)
            i++
        }
    }

    inner class EscuchadordeFilaJTable : ListSelectionListener {
        override fun valueChanged(event: ListSelectionEvent) {
            if (tabla.selectedRow >= 0) {
                val fil = tabla.selectedRow
                txtNombre!!.text = tabla.getValueAt(fil, 0).toString()
                txtApellido!!.text = tabla.getValueAt(fil, 1).toString().trim { it <= ' ' }
                txtCedula!!.text = tabla.getValueAt(fil, 2).toString().trim { it <= ' ' }
                txtCargo!!.text = tabla.getValueAt(fil, 3).toString().trim { it <= ' ' }
            }
        }
    }

    companion object {
        private val serialVersionUID = 1L

        @JvmStatic
        fun main(args: Array<String>) {
            val aplicattion = Empleado()
            aplicattion.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        }
    }
}
