import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class Empleado1 extends JFrame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private Container Contenedor;
    String resultado="";
    private JLabel lblEmpleado,lblNombre, lblApellido, lblCedula,lblCargo;
    private JTextField txtNombre, txtApellido,txtCedula,txtCargo;
    String strCadBD="";
    int auxID;

    String strTitulos[]={"Nombre","Apellido","Cédula","Cargo"};
    private DefaultTableModel modTabla=new DefaultTableModel(strTitulos,0)
    {
        private static final long serialVersionUID = 1L;
        public boolean isCellEditable(int rowIndex, int mColIndex) {
            return false;
        }};
    private final JTable tabla=new JTable(modTabla);

    public Empleado1()
    {
        super("MÓDULO EMPLEADOS");
        setFont(new Font("Arial Black", Font.PLAIN, 12));
        this.setGUI();
        this.setBounds(200, 90, 924, 445);
        this.setVisible(true);
    }

    public void setGUI() {
        Contenedor = this.getContentPane();
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(32, 178, 170));
        panel.setBounds(0, 0, 908, 54);
        getContentPane().add(panel);
        panel.setLayout(null);

        lblEmpleado = new JLabel("EMPLEADO");
        lblEmpleado.setForeground(Color.WHITE);
        lblEmpleado.setFont(new Font("Arial", Font.BOLD, 25));
        lblEmpleado.setBounds(10, 11, 152, 32);
        panel.add(lblEmpleado);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(0, 0, 255));
        panel_1.setBounds(0, 52, 908, 195);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);

        lblApellido = new JLabel("Apellido:");
        lblApellido.setForeground(Color.WHITE);
        lblApellido.setFont(new Font("Arial", Font.PLAIN, 18));
        lblApellido.setBounds(123, 69, 74, 14);
        panel_1.add(lblApellido);

        lblCedula = new JLabel("N.Cédula:");
        lblCedula.setForeground(Color.WHITE);
        lblCedula.setFont(new Font("Arial", Font.PLAIN, 18));
        lblCedula.setBounds(412, 30, 89, 14);
        panel_1.add(lblCedula);

        lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 18));
        lblNombre.setBounds(123, 30, 74, 14);
        panel_1.add(lblNombre);

        lblCargo = new JLabel("Cargo:");
        lblCargo.setForeground(Color.WHITE);
        lblCargo.setFont(new Font("Arial", Font.PLAIN, 18));
        lblCargo.setBounds(440, 69, 57, 14);
        panel_1.add(lblCargo);

        txtApellido = new JTextField();
        txtApellido.setBounds(207, 66, 109, 20);
        panel_1.add(txtApellido);
        txtApellido.setColumns(10);

        txtCedula = new JTextField();
        txtCedula.setBounds(499, 26, 109, 20);
        panel_1.add(txtCedula);
        txtCedula.setColumns(10);

        txtNombre = new JTextField();
        txtNombre.setBounds(208, 29, 108, 20);
        panel_1.add(txtNombre);
        txtNombre.setColumns(10);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setForeground(Color.BLACK);
        btnRegistrar.setBounds(194, 155, 89, 23);
        btnRegistrar.setActionCommand("registrar");
        btnRegistrar.addActionListener(this);
        panel_1.add(btnRegistrar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setForeground(Color.BLACK);
        btnCancelar.setBounds(424, 155, 89, 23);
        btnCancelar.setActionCommand("cancelar");
        btnCancelar.addActionListener(this);
        panel_1.add(btnCancelar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setForeground(Color.BLACK);
        btnActualizar.setBounds(300, 155, 102, 23);
        btnActualizar.setActionCommand("actualizar");
        btnActualizar.addActionListener(this);

        panel_1.add(btnActualizar);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setForeground(Color.BLACK);
        btnSalir.setBounds(532, 155, 89, 23);
        btnSalir.setActionCommand("salir");
        btnSalir.addActionListener(this);
        panel_1.add(btnSalir);

        txtCargo = new JTextField();
        txtCargo.setBounds(500, 68, 102, 20);
        panel_1.add(txtCargo);
        txtCargo.setColumns(10);

        JPanel panel_3 = new JPanel();
        panel_3.setBounds(0, 250, 908, 156);
        getContentPane().add(panel_3);
        panel_3.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(0, 0, 908, 156);
        panel_3.add(scrollPane);
        tabla.getSelectionModel().addListSelectionListener(new EscuchadordeFilaJTable());
        TableColumnModel columnModel = tabla.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setMinWidth(100);
        }
        llenarJTable();

    }

    public void actionPerformed(ActionEvent e)
    {
        String errores="";
        Pattern patron;
        Matcher empata;
        String accion = e.getActionCommand();

        if(accion.equals("cancelar"))
        {
            limpiaGUI();
        }
        if(accion.equals("salir"))
        {
            this.dispose();
        }
        if(accion.equals("registrar"))
        {
            errores="";

            if(txtApellido.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Debe ingresar el apellido del empleado");
                errores+="Debe ingresar el apellido del empleado";
            }
            else
            {
                patron = Pattern.compile("([ ]?([a-zA-ZñÑáéíóúÁÉÍÓÚ])+[ ]?(([a-zA-ZñÑáéíóúÁÉÍÓÚ])*)?)+");
                empata = patron.matcher(txtApellido.getText());
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null,"Debe ingresar sólo letras en apellido del empleado");
                    errores+="Debe ingresar sólo letras en apellido del empleado";
                }
            }
            if(txtCedula.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Debe ingresar el número de cédula del empleado");
                errores+="Debe ingresar el número de cédula";
            }
            else
            {
                patron = Pattern.compile("\\d*");
                empata = patron.matcher(txtCedula.getText());
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null,"Debe ingresar sólo números en el número de cédula");
                    errores+="Debe ingresar solo números en el número de cédula";
                }
            }
            if(txtNombre.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Debe ingresar el nombre del empleado");
                errores+="Debe ingresar el nombre del empleado";
            }
            else
            {
                patron = Pattern.compile("[\\wñÑ\\s]+");
                empata = patron.matcher(txtNombre.getText());
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null,"Debe ingresar sólo letras en nombre del empleado");
                    errores+="Debe ingresar sólo letras en nombre del empleado";
                }
            }
            if(txtCargo.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Debe ingresar el cargo del empleado");
                errores+="Debe ingresar el cargo del empleado";
            }
            else
            {
                patron = Pattern.compile("[\\wñÑ\\s]+");
                empata = patron.matcher(txtCargo.getText());
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null,"Debe ingresar sólo letras en el cargo");
                    errores+="Debe ingresar sólo letras en el cargo";
                }
            }

            if(!errores.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Error en el ingreso de datos");
            }
            else{
                String insercion = txtNombre.getText()+";"+txtApellido.getText()+";"+txtCedula.getText()+";"+txtCargo.getText()+";";
                try (FileWriter writer = new FileWriter("D:/Universidad/Programas Java/PapeleriaKotlin/src/archivos/empleados.txt", true);
                     BufferedWriter bw = new BufferedWriter(writer)) {
                    bw.write(insercion);
                } catch (IOException a) {
                    System.out.println("Error al momento de insertar un empleado");
                }
                RemoverElementosJtable();
                llenarJTable();
            }
        }
        if(accion.equals("actualizar"))
        {


        }


    }

    public void limpiaGUI()
    {
        txtApellido.setText("");
        txtCedula.setText("");
        txtNombre.setText("");
        txtCargo.setText("");

    }

    public void llenarJTable(){
        try {
            File file = new File("D:/Universidad/Programas Java/PapeleriaKotlin/src/archivos/empleados.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            // get lines from txt file
            Object[] tableLines = br.lines().toArray();

            // extract data from lines
            // set data to jtable model
            for(int i = 0; i < tableLines.length; i++)
            {
                String line = tableLines[i].toString().trim();
                String[] dataRow = line.split(";");
                modTabla.addRow(dataRow);
            }
        } catch (Exception ex) {
            System.out.println("Error al llenar la tabla");
        }
    }

    public void RemoverElementosJtable()
    {
        int filas=tabla.getRowCount();
        for (int i = 0;filas>i; i++) {
            modTabla.removeRow(0);
        }
    }

    public class EscuchadordeFilaJTable implements ListSelectionListener
    {
        public void valueChanged(ListSelectionEvent event)
        {
            if(tabla.getSelectedRow()>=0){
                int fil=tabla.getSelectedRow();
                txtNombre.setText(String.valueOf(tabla.getValueAt(fil,0)));
                txtApellido.setText(String.valueOf(tabla.getValueAt(fil,1)).trim());
                txtCedula.setText(String.valueOf(tabla.getValueAt(fil,2)).trim());
                txtCargo.setText(String.valueOf(tabla.getValueAt(fil,3)).trim());
            }
        }
    }

    public static void main(String[] args)
    {
        Empleado1 aplicattion =  new Empleado1();
        aplicattion.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    }
}
