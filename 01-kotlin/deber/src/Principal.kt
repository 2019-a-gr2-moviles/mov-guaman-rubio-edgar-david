import javax.swing.JDesktopPane
import javax.swing.JInternalFrame

class Principal : JDesktopPane() {
    internal var ventanas: Array<JInternalFrame>? = null

    init {
        this.putClientProperty("JDesktopPane.dragMode", "outline")
    }

    fun adicionar(comp: JInternalFrame) {
        add(comp, null, 0)
    }

    companion object {

        private val serialVersionUID = 1L
    }
}