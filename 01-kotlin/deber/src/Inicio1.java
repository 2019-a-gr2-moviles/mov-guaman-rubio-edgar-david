import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class Inicio1 extends JFrame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private Container Contenedor;
    String resultado="";
    private JPanel panelCombos,panelTexto;
    private JLabel lblNombreEmpresa,lblInicioSesion,lblNombreUsuario,lblContraseña;
    private JButton btnIngresar,btnCancelar,btnSalir;
    private JComboBox<String> cmbTipoUsuario;
    private JPasswordField campoContraseña;
    char [] contraseña;

    public Inicio1()
    {
        super("INGRESO AL SISTEMA");
        setFont(new Font("Arial Black", Font.PLAIN, 12));
        this.setGUI();
        this.setBounds(200, 30, 611, 336);
        this.setVisible(true);
    }

    public void setGUI() {
        Contenedor = this.getContentPane();
        getContentPane().setLayout(null);
        panelCombos = new JPanel();
        panelCombos.setBackground(new Color(70, 130, 180));
        panelCombos.setBounds(0, 0, 268, 297);
        Contenedor.add(panelCombos);
        panelCombos.setLayout(null);

        lblNombreEmpresa = new JLabel("PAPELERIA");
        lblNombreEmpresa.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 22));
        lblNombreEmpresa.setForeground(new Color(255, 255, 255));
        lblNombreEmpresa.setBounds(67, 11, 137, 25);
        panelCombos.add(lblNombreEmpresa);

        lblInicioSesion = new JLabel("INICIO DE SESIÓN");
        lblInicioSesion.setFont(new Font("Lucida Sans", Font.BOLD, 20));
        lblInicioSesion.setForeground(new Color(255, 255, 255));
        lblInicioSesion.setBounds(44, 232, 191, 25);
        panelCombos.add(lblInicioSesion);

        JLabel lblLogo = new JLabel("");
        lblLogo.setBounds(67, 47, 137, 174);
        lblLogo.setIcon(new ImageIcon(Inicio.class.getResource("/imagenes/Logo.jpg")));
        panelCombos.add(lblLogo);

        panelTexto = new JPanel();
        panelTexto.setBackground(new Color(60, 179, 113));
        panelTexto.setBounds(268, 0, 315, 297);
        Contenedor.add(panelTexto);
        panelTexto.setLayout(null);

        lblNombreUsuario = new JLabel("TIPO DE USUARIO");
        lblNombreUsuario.setFont(new Font("Arial", Font.BOLD, 18));
        lblNombreUsuario.setForeground(new Color(255, 255, 255));
        lblNombreUsuario.setBounds(20, 44, 216, 14);
        panelTexto.add(lblNombreUsuario);

        lblContraseña = new JLabel("CONTRASEÑA");
        lblContraseña.setFont(new Font("Arial", Font.BOLD, 18));
        lblContraseña.setForeground(new Color(255, 255, 255));
        lblContraseña.setBounds(20, 111, 134, 20);
        panelTexto.add(lblContraseña);

        btnSalir = new JButton("SALIR");
        btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSalir.setBounds(102, 252, 99, 29);
        panelTexto.add(btnSalir);
        btnSalir.setActionCommand("salir");
        btnSalir.addActionListener(this);

        btnCancelar = new JButton("CANCELAR");
        btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCancelar.setBounds(170, 202, 110, 29);
        panelTexto.add(btnCancelar);
        btnCancelar.setActionCommand("cancelar");
        btnCancelar.addActionListener(this);

        btnIngresar = new JButton("INGRESAR");
        btnIngresar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnIngresar.setBounds(20, 202, 99, 29);
        panelTexto.add(btnIngresar);
        btnIngresar.setActionCommand("ingresar");
        btnIngresar.addActionListener(this);

        cmbTipoUsuario = new JComboBox<String>();
        cmbTipoUsuario.setBounds(20, 69, 129, 20);
        cmbTipoUsuario.setMaximumRowCount(3);
        cmbTipoUsuario.setSelectedIndex(-1);
        cmbTipoUsuario.addItem("Elija una opción");
        cmbTipoUsuario.addItem("Administrador");
        cmbTipoUsuario.addItem("Empleado");
        panelTexto.add(cmbTipoUsuario);

        campoContraseña = new JPasswordField();
        campoContraseña.setBounds(20, 142, 129, 20);
        panelTexto.add(campoContraseña);


    }

    public void actionPerformed(ActionEvent e)
    {
        String errores="";
        String accion = e.getActionCommand();
        if(accion.equals("cancelar"))
        {
            limpiaGUI();
        }
        if(accion.equals("salir"))
        {
            this.dispose();
        }
        if(accion.equals("ingresar"))
        {
            errores="";

            if(cmbTipoUsuario.getSelectedIndex()<1)
            {
                JOptionPane.showMessageDialog(null,"Debe elegir un tipo de usuario");
                errores+="\nUsuario vacío";
            }
            else if(cmbTipoUsuario.getSelectedIndex()==1)
            {
                String myPass=String.valueOf(campoContraseña.getPassword());
                if(!myPass.equals("admin"))
                {
                    JOptionPane.showMessageDialog(null,"Credenciales incorrectas");
                    errores+="\nContraseña de admin incorrecta";
                }else
                {
                    JOptionPane.showMessageDialog(null,"Bienvenido Administrador");
                    MenuAdmin menu = new MenuAdmin();
                    this.dispose();

                }
            }
            else if(cmbTipoUsuario.getSelectedIndex()==2)
            {
                String myPass=String.valueOf(campoContraseña.getPassword());
                if(!myPass.equals("12345"))
                {
                    JOptionPane.showMessageDialog(null,"Credenciales incorrectas");
                    errores+="\nContraseña de empleado incorrecta";
                }else
                {
                    JOptionPane.showMessageDialog(null,"Bienvenido Empleado");
                    Producto pro = new Producto();
                    this.dispose();
                }
            }

            if(campoContraseña.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Debe ingresar la contraseña");
                errores+="\nContraseña vacía";
            }
            if(!errores.equals(""))
            {
                System.out.println("Errores:" +errores);
            }
        }

    }

    public void limpiaGUI()
    {

        cmbTipoUsuario.setSelectedIndex(-1);
        campoContraseña.setText("");
    }


    public static void main(String[] args)
    {
        Inicio aplicattion =  new Inicio();
        aplicattion.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    }
}
