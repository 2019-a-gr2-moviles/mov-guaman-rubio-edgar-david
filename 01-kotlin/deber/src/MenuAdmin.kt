import java.awt.Color
import java.awt.Font
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JButton

class MenuAdmin : JFrame("Papeleria"), ActionListener {
    protected var escritorio = Principal()
    private var btnEmpleados: JButton? = null
    private var btnProductos: JButton? = null
    private var btnSalir: JButton? = null

    init {
        font = Font("Franklin Gothic Book", Font.BOLD or Font.ITALIC, 13)
        this.setGui()
        this.setBounds(200, 30, 964, 720)
        this.isVisible = true
    }

    private fun setGui() {
        val contenedor = this.contentPane
        contentPane.layout = null
        escritorio.background = Color(51, 204, 204)
        escritorio.setBounds(0, 0, 947, 710)
        contenedor.add(escritorio)

        btnEmpleados = JButton("Empleados")
        btnEmpleados!!.font = Font("Consolas", Font.BOLD, 16)
        btnEmpleados!!.icon = ImageIcon(MenuAdmin::class.java.getResource("/imagenes/cliente.png"))
        btnEmpleados!!.setBounds(0, 0, 156, 35)
        btnEmpleados!!.actionCommand = "regcliente"
        btnEmpleados!!.addActionListener(this)
        escritorio.add(btnEmpleados)

        btnProductos = JButton("Inventario")
        btnProductos!!.font = Font("Consolas", Font.BOLD, 16)
        btnProductos!!.icon = ImageIcon(MenuAdmin::class.java.getResource("/imagenes/products.png"))
        btnProductos!!.setBounds(156, 0, 166, 35)
        btnProductos!!.actionCommand = "reginventario"
        btnProductos!!.addActionListener(this)
        escritorio.add(btnProductos)

        btnSalir = JButton("Salir")
        btnSalir!!.font = Font("Consolas", Font.BOLD, 16)
        btnSalir!!.icon = ImageIcon(MenuAdmin::class.java.getResource("/imagenes/Close.png"))
        btnSalir!!.setBounds(818, 0, 126, 35)
        btnSalir!!.actionCommand = "salir"
        btnSalir!!.addActionListener(this)
        escritorio.add(btnSalir)
    }

    override fun actionPerformed(evento: ActionEvent) {
        val accion = evento.actionCommand

        if (accion == "regcliente") {
            val emp = Empleado()
            //escritorio.add(cli);
        } else if (accion == "reginventario") {
            val pro = Producto()
            //escritorio.add(ReplicaCliente);
        } else if (accion == "salir") {
            System.exit(0)
        }

    }

    companion object {

        private val serialVersionUID = 1L

        @JvmStatic
        fun main(args: Array<String>) {
            val main = MenuAdmin()
            main.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        }
    }
}