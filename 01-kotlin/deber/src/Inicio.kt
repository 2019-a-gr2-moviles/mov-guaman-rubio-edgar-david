import java.awt.Container
import java.awt.Font
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JOptionPane
import javax.swing.JPanel
import java.awt.Color
import javax.swing.JPasswordField
import javax.swing.JComboBox

class Inicio : JFrame("INGRESO AL SISTEMA"), ActionListener {
    private var Contenedor: Container? = null
    internal var resultado = ""
    private var panelCombos: JPanel? = null
    private var panelTexto: JPanel? = null
    private var lblNombreEmpresa: JLabel? = null
    private var lblInicioSesion: JLabel? = null
    private var lblNombreUsuario: JLabel? = null
    private var lblContraseña: JLabel? = null
    private var btnIngresar: JButton? = null
    private var btnCancelar: JButton? = null
    private var btnSalir: JButton? = null
    private var cmbTipoUsuario: JComboBox<String>? = null
    private var campoContraseña: JPasswordField? = null
    internal var contraseña: CharArray? = null

    init {
        font = Font("Arial Black", Font.PLAIN, 12)
        this.setGUI()
        this.setBounds(200, 30, 611, 336)
        this.isVisible = true
    }

    fun setGUI() {
        Contenedor = this.contentPane
        contentPane.layout = null
        panelCombos = JPanel()
        panelCombos!!.background = Color(70, 130, 180)
        panelCombos!!.setBounds(0, 0, 268, 297)
        Contenedor!!.add(panelCombos)
        panelCombos!!.layout = null

        lblNombreEmpresa = JLabel("PAPELERIA")
        lblNombreEmpresa!!.font = Font("Lucida Sans Unicode", Font.BOLD, 22)
        lblNombreEmpresa!!.foreground = Color(255, 255, 255)
        lblNombreEmpresa!!.setBounds(67, 11, 137, 25)
        panelCombos!!.add(lblNombreEmpresa)

        lblInicioSesion = JLabel("INICIO DE SESIÓN")
        lblInicioSesion!!.font = Font("Lucida Sans", Font.BOLD, 20)
        lblInicioSesion!!.foreground = Color(255, 255, 255)
        lblInicioSesion!!.setBounds(44, 232, 191, 25)
        panelCombos!!.add(lblInicioSesion)

        val lblLogo = JLabel("")
        lblLogo.setBounds(67, 47, 137, 174)
        lblLogo.icon = ImageIcon(Inicio::class.java.getResource("/imagenes/Logo.jpg"))
        panelCombos!!.add(lblLogo)

        panelTexto = JPanel()
        panelTexto!!.background = Color(60, 179, 113)
        panelTexto!!.setBounds(268, 0, 315, 297)
        Contenedor!!.add(panelTexto)
        panelTexto!!.layout = null

        lblNombreUsuario = JLabel("TIPO DE USUARIO")
        lblNombreUsuario!!.font = Font("Arial", Font.BOLD, 18)
        lblNombreUsuario!!.foreground = Color(255, 255, 255)
        lblNombreUsuario!!.setBounds(20, 44, 216, 14)
        panelTexto!!.add(lblNombreUsuario)

        lblContraseña = JLabel("CONTRASEÑA")
        lblContraseña!!.font = Font("Arial", Font.BOLD, 18)
        lblContraseña!!.foreground = Color(255, 255, 255)
        lblContraseña!!.setBounds(20, 111, 134, 20)
        panelTexto!!.add(lblContraseña)

        btnSalir = JButton("SALIR")
        btnSalir!!.font = Font("Tahoma", Font.PLAIN, 14)
        btnSalir!!.setBounds(102, 252, 99, 29)
        panelTexto!!.add(btnSalir)
        btnSalir!!.actionCommand = "salir"
        btnSalir!!.addActionListener(this)

        btnCancelar = JButton("CANCELAR")
        btnCancelar!!.font = Font("Tahoma", Font.PLAIN, 14)
        btnCancelar!!.setBounds(170, 202, 110, 29)
        panelTexto!!.add(btnCancelar)
        btnCancelar!!.actionCommand = "cancelar"
        btnCancelar!!.addActionListener(this)

        btnIngresar = JButton("INGRESAR")
        btnIngresar!!.font = Font("Tahoma", Font.PLAIN, 14)
        btnIngresar!!.setBounds(20, 202, 99, 29)
        panelTexto!!.add(btnIngresar)
        btnIngresar!!.actionCommand = "ingresar"
        btnIngresar!!.addActionListener(this)

        cmbTipoUsuario = JComboBox()
        cmbTipoUsuario!!.setBounds(20, 69, 129, 20)
        cmbTipoUsuario!!.maximumRowCount = 3
        cmbTipoUsuario!!.selectedIndex = -1
        cmbTipoUsuario!!.addItem("Elija una opción")
        cmbTipoUsuario!!.addItem("Administrador")
        cmbTipoUsuario!!.addItem("Empleado")
        panelTexto!!.add(cmbTipoUsuario)

        campoContraseña = JPasswordField()
        campoContraseña!!.setBounds(20, 142, 129, 20)
        panelTexto!!.add(campoContraseña)


    }

    override fun actionPerformed(e: ActionEvent) {
        var errores = ""
        val accion = e.actionCommand
        if (accion == "cancelar") {
            limpiaGUI()
        }
        if (accion == "salir") {
            this.dispose()
        }
        if (accion == "ingresar") {
            errores = ""

            if (cmbTipoUsuario!!.selectedIndex < 1) {
                JOptionPane.showMessageDialog(null, "Debe elegir un tipo de usuario")
                errores += "\nUsuario vacío"
            } else if (cmbTipoUsuario!!.selectedIndex == 1) {
                val myPass = String(campoContraseña!!.password)
                if (myPass != "admin") {
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas")
                    errores += "\nContraseña de admin incorrecta"
                } else {
                    JOptionPane.showMessageDialog(null, "Bienvenido Administrador")
                    val menu = MenuAdmin()
                    this.dispose()

                }
            } else if (cmbTipoUsuario!!.selectedIndex == 2) {
                val myPass = String(campoContraseña!!.password)
                if (myPass != "12345") {
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas")
                    errores += "\nContraseña de empleado incorrecta"
                } else {
                    JOptionPane.showMessageDialog(null, "Bienvenido Empleado")
                    val pro = Producto()
                    this.dispose()
                }
            }

            if (campoContraseña!!.text == "") {
                JOptionPane.showMessageDialog(null, "Debe ingresar la contraseña")
                errores += "\nContraseña vacía"
            }
            if (errores != "") {
                println("Errores:$errores")
            }
        }

    }

    fun limpiaGUI() {

        cmbTipoUsuario!!.selectedIndex = -1
        campoContraseña!!.text = ""
    }

    companion object {
        private val serialVersionUID = 1L


        @JvmStatic
        fun main(args: Array<String>) {
            val aplicattion = Inicio()
            aplicattion.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        }
    }
}
