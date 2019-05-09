import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuListener;

import javax.swing.JButton;

public class MenuAdmin1 extends JFrame implements ActionListener{

    private static final long serialVersionUID = 1L;
    protected Principal escritorio=new Principal();
    private JButton btnEmpleados, btnProductos, btnSalir;

    public static void main(String[] args)
    {
        MenuAdmin main=new MenuAdmin();
        main.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }

    public MenuAdmin1() {
        super("Papeleria");
        setFont(new Font("Franklin Gothic Book", Font.BOLD | Font.ITALIC, 13));
        this.setGui();
        this.setBounds(200, 30, 964, 720);
        this.setVisible(true);
    }

    private void setGui() {
        Container contenedor = this.getContentPane();
        getContentPane().setLayout(null);
        escritorio.setBackground(new Color(51, 204, 204));
        escritorio.setBounds(0, 0, 947, 710);
        contenedor.add(escritorio);

        btnEmpleados = new JButton("Empleados");
        btnEmpleados.setFont(new Font("Consolas", Font.BOLD, 16));
        btnEmpleados.setIcon(new ImageIcon(MenuAdmin.class.getResource("/imagenes/cliente.png")));
        btnEmpleados.setBounds(0, 0, 156, 35);
        btnEmpleados.setActionCommand("regcliente");
        btnEmpleados.addActionListener(this);
        escritorio.add(btnEmpleados);

        btnProductos = new JButton("Inventario");
        btnProductos.setFont(new Font("Consolas", Font.BOLD, 16));
        btnProductos.setIcon(new ImageIcon(MenuAdmin.class.getResource("/imagenes/products.png")));
        btnProductos.setBounds(156, 0, 166, 35);
        btnProductos.setActionCommand("reginventario");
        btnProductos.addActionListener(this);
        escritorio.add(btnProductos);

        btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Consolas", Font.BOLD, 16));
        btnSalir.setIcon(new ImageIcon(MenuAdmin.class.getResource("/imagenes/Close.png")));
        btnSalir.setBounds(818, 0, 126, 35);
        btnSalir.setActionCommand("salir");
        btnSalir.addActionListener(this);
        escritorio.add(btnSalir);
    }

    public void actionPerformed(ActionEvent evento) {
        String accion=evento.getActionCommand();

        if(accion.equals("regcliente"))
        {
            Empleado emp = new Empleado();
            //escritorio.add(cli);
        }
        else if(accion.equals("reginventario"))
        {
            Producto pro = new Producto();
            //escritorio.add(ReplicaCliente);
        }

        else if(accion.equals("salir")) {
            System.exit(0);
        }

    }
}