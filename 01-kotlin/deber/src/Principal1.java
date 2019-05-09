import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class Principal1 extends JDesktopPane {

    private static final long serialVersionUID = 1L;
    JInternalFrame[] ventanas;


    public Principal1() {
        super();
        this.putClientProperty("JDesktopPane.dragMode", "outline");
    }
    public void adicionar(JInternalFrame comp) {
        add(comp, null, 0);
    }
}