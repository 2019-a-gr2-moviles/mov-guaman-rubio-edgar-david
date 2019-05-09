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
import java.util.Scanner
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.swing.*
import java.awt.Color
import javax.swing.JScrollPane
import javax.swing.JTable
import javax.swing.event.ListSelectionEvent
import javax.swing.event.ListSelectionListener
import javax.swing.table.DefaultTableModel
import javax.swing.table.TableColumnModel


class Producto : JFrame("MÓDULO PRODUCTOS"), ActionListener {
    private var Contenedor: Container? = null
    internal var resultado = ""
    private var lblInventario: JLabel? = null
    private var lblNombreProducto: JLabel? = null
    private var lblPrecio: JLabel? = null
    private var lblMarca: JLabel? = null
    private var lblStock: JLabel? = null
    private var txtNombreProducto: JTextField? = null
    private var txtPrecio: JTextField? = null
    private var txtMarca: JTextField? = null
    private var txtStock: JTextField? = null
    private var btnAceptar: JButton? = null
    private var btnBorrar: JButton? = null
    private var btnActualizar: JButton? = null
    private var btnSalir: JButton? = null
    internal var b = true

    internal var strTitulos = arrayOf("Marca", "Nombre", "Precio", "Stock")
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
        this.setBounds(200, 90, 793, 445)
        this.isVisible = true
    }

    fun setGUI() {
        Contenedor = this.contentPane
        contentPane.layout = null

        val panel = JPanel()
        panel.background = Color.BLACK
        panel.setBounds(0, 0, 777, 54)
        contentPane.add(panel)
        panel.layout = null

        val panel_1 = JPanel()
        panel_1.background = Color(220, 20, 60)
        panel_1.setBounds(0, 52, 777, 174)
        contentPane.add(panel_1)
        panel_1.layout = null

        val panel_3 = JPanel()
        panel_3.setBounds(0, 232, 777, 174)
        contentPane.add(panel_3)
        panel_3.layout = null

        lblInventario = JLabel("Datos del producto")
        lblInventario!!.foreground = Color.WHITE
        lblInventario!!.font = Font("Arial", Font.BOLD, 20)
        lblInventario!!.setBounds(10, 11, 190, 32)
        panel.add(lblInventario)

        lblNombreProducto = JLabel("Nombre:")
        lblNombreProducto!!.foreground = Color.WHITE
        lblNombreProducto!!.font = Font("Arial", Font.PLAIN, 18)
        lblNombreProducto!!.setBounds(123, 69, 74, 14)
        panel_1.add(lblNombreProducto)

        lblPrecio = JLabel("Precio:")
        lblPrecio!!.foreground = Color.WHITE
        lblPrecio!!.font = Font("Arial", Font.PLAIN, 18)
        lblPrecio!!.setBounds(416, 30, 65, 14)
        panel_1.add(lblPrecio)

        lblMarca = JLabel("Marca:")
        lblMarca!!.foreground = Color.WHITE
        lblMarca!!.font = Font("Arial", Font.PLAIN, 18)
        lblMarca!!.setBounds(134, 30, 57, 14)
        panel_1.add(lblMarca)

        lblStock = JLabel("Stock:")
        lblStock!!.foreground = Color.WHITE
        lblStock!!.font = Font("Arial", Font.PLAIN, 18)
        lblStock!!.setBounds(416, 69, 57, 14)
        panel_1.add(lblStock)

        btnAceptar = JButton("Ingresar")
        btnAceptar!!.foreground = Color.BLACK
        btnAceptar!!.setBounds(169, 129, 89, 23)
        btnAceptar!!.actionCommand = "ingresar"
        btnAceptar!!.addActionListener(this)
        panel_1.add(btnAceptar)

        btnBorrar = JButton("Eliminar")
        btnBorrar!!.foreground = Color.BLACK
        btnBorrar!!.setBounds(408, 129, 89, 23)
        btnBorrar!!.actionCommand = "borrar"
        btnBorrar!!.addActionListener(this)
        panel_1.add(btnBorrar)

        btnSalir = JButton("Salir")
        btnSalir!!.foreground = Color.BLACK
        btnSalir!!.setBounds(528, 129, 89, 23)
        btnSalir!!.actionCommand = "salir"
        btnSalir!!.addActionListener(this)
        panel_1.add(btnSalir)

        btnActualizar = JButton("Actualizar")
        btnActualizar!!.setBounds(279, 129, 101, 23)
        btnActualizar!!.actionCommand = "actualizar"
        btnActualizar!!.addActionListener(this)
        panel_1.add(btnActualizar)

        txtNombreProducto = JTextField()
        txtNombreProducto!!.setBounds(207, 66, 109, 20)
        panel_1.add(txtNombreProducto)
        txtNombreProducto!!.columns = 10

        txtPrecio = JTextField()
        txtPrecio!!.setBounds(491, 27, 109, 20)
        panel_1.add(txtPrecio)
        txtPrecio!!.columns = 10

        txtMarca = JTextField()
        txtMarca!!.setBounds(208, 29, 108, 20)
        panel_1.add(txtMarca)
        txtMarca!!.columns = 10

        txtStock = JTextField()
        txtStock!!.setBounds(491, 68, 109, 20)
        panel_1.add(txtStock)
        txtStock!!.columns = 10

        val scrollPane = JScrollPane(tabla)
        scrollPane.setBounds(41, 0, 687, 163)
        panel_3.add(scrollPane)
        tabla.selectionModel.addListSelectionListener(EscuchadordeFilaJTable())
        val columnModel = tabla.columnModel
        for (i in 0 until columnModel.columnCount) {
            columnModel.getColumn(i).minWidth = 100
        }

        llenarJTable()


    }

    override fun actionPerformed(e: ActionEvent) {
        var erroresIngresar = ""
        var erroresActualizar = ""
        var patron: Pattern
        var empata: Matcher
        val accion = e.actionCommand

        if (accion == "salir") {
            this.dispose()
        }
        if (accion == "ingresar") {
            erroresIngresar = ""

            if (txtNombreProducto!!.text == "") {
                JOptionPane.showMessageDialog(null, "Debe ingresar un nombre del producto")
                erroresIngresar += "Debe ingresar un nombre del producto"
            } else {
                patron = Pattern.compile("([ ]?([a-zA-ZñÑáéíóúÁÉÍÓÚ])+[ ]?(([a-zA-ZñÑáéíóúÁÉÍÓÚ])*)?)+")
                empata = patron.matcher(txtNombreProducto!!.text)
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar sólo letras en nombre del producto")
                    erroresIngresar += "Debe ingresar sólo letras en nombre del material"
                }
            }
            if (txtPrecio!!.text == "") {
                JOptionPane.showMessageDialog(null, "Debe ingresar el precio del producto")
                erroresIngresar += "Debe ingresar el precio del producto"
            } else {
                patron = Pattern.compile("[0-9]+([,.][0-9]{1,2})?")
                empata = patron.matcher(txtPrecio!!.text)
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar sólo números en el precio")
                    erroresIngresar += "Debe ingresar solo números en precio"
                }
            }
            if (txtMarca!!.text == "") {
                JOptionPane.showMessageDialog(null, "Debe ingresar la marca del producto")
                erroresIngresar += "Debe ingresar la marca del producto"
            } else {
                patron = Pattern.compile("[\\wñÑ\\s]+")
                empata = patron.matcher(txtMarca!!.text)
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar sólo letras en marca")
                    erroresIngresar += "Debe ingresar sólo letras en marca"
                }
            }
            if (txtStock!!.text == "") {
                JOptionPane.showMessageDialog(null, "Debe ingresar el stock del producto")
                erroresIngresar += "Debe ingresar el formato del material"
            } else {
                patron = Pattern.compile("\\d*")
                empata = patron.matcher(txtStock!!.text)
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar sólo números en stock")
                    erroresIngresar += "Debe ingresar sólo números en stock"
                }
            }

            if (erroresIngresar != "") {
                JOptionPane.showMessageDialog(null, "Error en el ingreso de datos")
            } else {
                val insercion =
                    "\n" + txtMarca!!.text + ";" + txtNombreProducto!!.text + ";" + txtPrecio!!.text + ";" + txtStock!!.text + ";"
                try {
                    FileWriter(
                        "D:/Universidad/Programas Java/PapeleriaKotlin/src/archivos/productos.txt",
                        true
                    ).use { writer -> BufferedWriter(writer).use { bw -> bw.write(insercion) } }
                } catch (a: IOException) {
                    println("Error al momento de insertar un dato")
                }

                RemoverElementosJtable()
                llenarJTable()
            }
        }

        if (accion == "actualizar") {
            erroresActualizar = ""

            if (txtNombreProducto!!.text == "") {
                JOptionPane.showMessageDialog(null, "Debe ingresar un nombre del producto")
                erroresActualizar += "Debe ingresar un nombre del producto"
            } else {
                patron = Pattern.compile("([ ]?([a-zA-ZñÑáéíóúÁÉÍÓÚ])+[ ]?(([a-zA-ZñÑáéíóúÁÉÍÓÚ])*)?)+")
                empata = patron.matcher(txtNombreProducto!!.text)
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar sólo letras en nombre del producto")
                    erroresActualizar += "Debe ingresar sólo letras en nombre del material"
                }
            }
            if (txtPrecio!!.text == "") {
                JOptionPane.showMessageDialog(null, "Debe ingresar el precio del producto")
                erroresActualizar += "Debe ingresar el precio del producto"
            } else {
                patron = Pattern.compile("[0-9]+([,.][0-9]{1,2})?")
                empata = patron.matcher(txtPrecio!!.text)
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar sólo números en el precio")
                    erroresActualizar += "Debe ingresar solo números en precio"
                }
            }
            if (txtMarca!!.text == "") {
                JOptionPane.showMessageDialog(null, "Debe ingresar la marca del producto")
                erroresActualizar += "Debe ingresar la marca del producto"
            } else {
                patron = Pattern.compile("[\\wñÑ\\s]+")
                empata = patron.matcher(txtMarca!!.text)
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar sólo letras en marca")
                    erroresActualizar += "Debe ingresar sólo letras en marca"
                }
            }
            if (txtStock!!.text == "") {
                JOptionPane.showMessageDialog(null, "Debe ingresar el stock del producto")
                erroresActualizar += "Debe ingresar el formato del material"
            } else {
                patron = Pattern.compile("\\d*")
                empata = patron.matcher(txtStock!!.text)
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar sólo números en stock")
                    erroresActualizar += "Debe ingresar sólo números en stock"
                }
            }

            if (erroresActualizar != "") {
                JOptionPane.showMessageDialog(null, "Error en la actualización de datos")
            } else {
                val insercion =
                    "\n" + txtMarca!!.text + ";" + txtNombreProducto!!.text + ";" + txtPrecio!!.text + ";" + txtStock!!.text + ";"
                try {
                    FileWriter(
                        "D:/Universidad/Programas Java/PapeleriaKotlin/src/archivos/productos.txt",
                        true
                    ).use { writer -> BufferedWriter(writer).use { bw -> bw.write(insercion) } }
                } catch (a: IOException) {
                    println("Error al momento de insertar un dato")
                }

                RemoverElementosJtable()
                llenarJTable()
            }


        }

        if (accion == "borrar") {
            txtNombreProducto!!.text = ""
            txtPrecio!!.text = ""
            txtMarca!!.text = ""
            txtStock!!.text = ""
            try {
                val inputFile = File("D:/Universidad/Programas Java/PapeleriaKotlin/src/archivos/productos.txt")
                val tempFile = File("D:/Universidad/Programas Java/PapeleriaKotlin/src/archivos/temporal.txt")
                val reader = BufferedReader(FileReader(inputFile))
                val writer = BufferedWriter(FileWriter(tempFile))

                val lineToRemove =
                    txtMarca!!.text + ";" + txtNombreProducto!!.text + ";" + txtPrecio!!.text + ";" + txtStock!!.text + ";"
                var currentLine: String? = null;

                while ({currentLine = reader.readLine();currentLine} != null) {
                    // trim newline when comparing with lineToRemove
                    val trimmedLine = currentLine;
                    if (trimmedLine == lineToRemove) continue
                    writer.write(currentLine + System.getProperty("line.separator"))
                }
                writer.close()
                reader.close()
                println("Llegó hasta el final")
            } catch (a: Exception) {
                println("Error al eliminar el archivo")
                println(a.toString())
            }

            txtNombreProducto!!.text = ""
            txtPrecio!!.text = ""
            txtMarca!!.text = ""
            txtStock!!.text = ""

            try {
                val fr = FileReader("D:/Universidad/Programas Java/PapeleriaKotlin/src/archivos/temporal.txt")
                val br = BufferedReader(fr)
                val fw = FileWriter("D:/Universidad/Programas Java/PapeleriaKotlin/src/archivos/productos.txt")
                var s: String? = null;

                while ({s = br.readLine();s} != null) { // read a line
                    fw.write(s + "\n") // write to output file
                    fw.flush()
                }
                br.close()
                fw.close()
                println("file copied")
            } catch (d: IOException) {
                println("Error al copiar el archivo")
                d.printStackTrace()
            }

            RemoverElementosJtable()
            llenarJTable()
        }
    }

    fun limpiaGUI() {
        txtNombreProducto!!.text = ""
        txtPrecio!!.text = ""
        txtMarca!!.text = ""
        txtStock!!.text = ""

    }

    fun llenarJTable() {
        try {
            val file = File("D:/Universidad/Programas Java/PapeleriaKotlin/src/archivos/productos.txt")
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
            println("Error en el archivo")
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
                txtMarca!!.text = tabla.getValueAt(fil, 0).toString().trim { it <= ' ' }
                txtNombreProducto!!.text = tabla.getValueAt(fil, 1).toString().trim { it <= ' ' }
                txtPrecio!!.text = tabla.getValueAt(fil, 2).toString().trim { it <= ' ' }
                txtStock!!.text = tabla.getValueAt(fil, 3).toString().trim { it <= ' ' }
            }
        }
    }

    companion object {
        private val serialVersionUID = 1L

        @JvmStatic
        fun main(args: Array<String>) {
            val aplicattion = Producto()
            aplicattion.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        }
    }
}
