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
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class Producto1 extends JFrame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private Container Contenedor;
    String resultado="";
    private JLabel lblInventario,lblNombreProducto,lblPrecio,lblMarca,lblStock;
    private JTextField txtNombreProducto,txtPrecio,txtMarca,txtStock;
    private JButton btnAceptar, btnBorrar, btnActualizar, btnSalir;
    boolean b = true;

    String strTitulos[]={"Marca","Nombre","Precio","Stock"};
    private DefaultTableModel modTabla=new DefaultTableModel(strTitulos,0)
    {
        private static final long serialVersionUID = 1L;
        public boolean isCellEditable(int rowIndex, int mColIndex) {
            return false;
        }};
    private final JTable tabla=new JTable(modTabla);

    public Producto1()
    {
        super("MÓDULO PRODUCTOS");
        setFont(new Font("Arial Black", Font.PLAIN, 12));
        this.setGUI();
        this.setBounds(200, 90, 793, 445);
        this.setVisible(true);
    }

    public void setGUI() {
        Contenedor = this.getContentPane();
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setBounds(0, 0, 777, 54);
        getContentPane().add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(220, 20, 60));
        panel_1.setBounds(0, 52, 777, 174);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JPanel panel_3 = new JPanel();
        panel_3.setBounds(0, 232, 777, 174);
        getContentPane().add(panel_3);
        panel_3.setLayout(null);

        lblInventario = new JLabel("Datos del producto");
        lblInventario.setForeground(Color.WHITE);
        lblInventario.setFont(new Font("Arial", Font.BOLD, 20));
        lblInventario.setBounds(10, 11, 190, 32);
        panel.add(lblInventario);

        lblNombreProducto = new JLabel("Nombre:");
        lblNombreProducto.setForeground(Color.WHITE);
        lblNombreProducto.setFont(new Font("Arial", Font.PLAIN, 18));
        lblNombreProducto.setBounds(123, 69, 74, 14);
        panel_1.add(lblNombreProducto);

        lblPrecio = new JLabel("Precio:");
        lblPrecio.setForeground(Color.WHITE);
        lblPrecio.setFont(new Font("Arial", Font.PLAIN, 18));
        lblPrecio.setBounds(416, 30, 65, 14);
        panel_1.add(lblPrecio);

        lblMarca = new JLabel("Marca:");
        lblMarca.setForeground(Color.WHITE);
        lblMarca.setFont(new Font("Arial", Font.PLAIN, 18));
        lblMarca.setBounds(134, 30, 57, 14);
        panel_1.add(lblMarca);

        lblStock = new JLabel("Stock:");
        lblStock.setForeground(Color.WHITE);
        lblStock.setFont(new Font("Arial", Font.PLAIN, 18));
        lblStock.setBounds(416, 69, 57, 14);
        panel_1.add(lblStock);

        btnAceptar = new JButton("Ingresar");
        btnAceptar.setForeground(Color.BLACK);
        btnAceptar.setBounds(169, 129, 89, 23);
        btnAceptar.setActionCommand("ingresar");
        btnAceptar.addActionListener(this);
        panel_1.add(btnAceptar);

        btnBorrar = new JButton("Eliminar");
        btnBorrar.setForeground(Color.BLACK);
        btnBorrar.setBounds(408, 129, 89, 23);
        btnBorrar.setActionCommand("borrar");
        btnBorrar.addActionListener(this);
        panel_1.add(btnBorrar);

        btnSalir = new JButton("Salir");
        btnSalir.setForeground(Color.BLACK);
        btnSalir.setBounds(528, 129, 89, 23);
        btnSalir.setActionCommand("salir");
        btnSalir.addActionListener(this);
        panel_1.add(btnSalir);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(279, 129, 101, 23);
        btnActualizar.setActionCommand("actualizar");
        btnActualizar.addActionListener(this);
        panel_1.add(btnActualizar);

        txtNombreProducto = new JTextField();
        txtNombreProducto.setBounds(207, 66, 109, 20);
        panel_1.add(txtNombreProducto);
        txtNombreProducto.setColumns(10);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(491, 27, 109, 20);
        panel_1.add(txtPrecio);
        txtPrecio.setColumns(10);

        txtMarca = new JTextField();
        txtMarca.setBounds(208, 29, 108, 20);
        panel_1.add(txtMarca);
        txtMarca.setColumns(10);

        txtStock = new JTextField();
        txtStock.setBounds(491, 68, 109, 20);
        panel_1.add(txtStock);
        txtStock.setColumns(10);

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(41, 0, 687, 163);
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
        String erroresIngresar="";
        String erroresActualizar="";
        Pattern patron;
        Matcher empata;
        String accion = e.getActionCommand();

        if(accion.equals("salir"))
        {
            this.dispose();
        }
        if(accion.equals("ingresar"))
        {
            erroresIngresar="";

            if(txtNombreProducto.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Debe ingresar un nombre del producto");
                erroresIngresar+="Debe ingresar un nombre del producto";
            }
            else
            {
                patron = Pattern.compile("([ ]?([a-zA-ZñÑáéíóúÁÉÍÓÚ])+[ ]?(([a-zA-ZñÑáéíóúÁÉÍÓÚ])*)?)+");
                empata = patron.matcher(txtNombreProducto.getText());
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null,"Debe ingresar sólo letras en nombre del producto");
                    erroresIngresar+="Debe ingresar sólo letras en nombre del material";
                }
            }
            if(txtPrecio.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Debe ingresar el precio del producto");
                erroresIngresar+="Debe ingresar el precio del producto";
            }
            else
            {
                patron = Pattern.compile("[0-9]+([,.][0-9]{1,2})?");
                empata = patron.matcher(txtPrecio.getText());
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null,"Debe ingresar sólo números en el precio");
                    erroresIngresar+="Debe ingresar solo números en precio";
                }
            }
            if(txtMarca.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Debe ingresar la marca del producto");
                erroresIngresar+="Debe ingresar la marca del producto";
            }
            else
            {
                patron = Pattern.compile("[\\wñÑ\\s]+");
                empata = patron.matcher(txtMarca.getText());
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null,"Debe ingresar sólo letras en marca");
                    erroresIngresar+="Debe ingresar sólo letras en marca";
                }
            }
            if(txtStock.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Debe ingresar el stock del producto");
                erroresIngresar+="Debe ingresar el formato del material";
            }
            else
            {
                patron = Pattern.compile("\\d*");
                empata = patron.matcher(txtStock.getText());
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null,"Debe ingresar sólo números en stock");
                    erroresIngresar+="Debe ingresar sólo números en stock";
                }
            }

            if(!erroresIngresar.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Error en el ingreso de datos");
            }
            else{
                String insercion = "\n"+txtMarca.getText()+";"+txtNombreProducto.getText()+";"+txtPrecio.getText()+";"+txtStock.getText()+";";
                try (FileWriter writer = new FileWriter("D:/Universidad/Programas Java/PapeleriaKotlin/src/archivos/productos.txt", true);
                     BufferedWriter bw = new BufferedWriter(writer)) {
                    bw.write(insercion);
                } catch (IOException a) {
                    System.out.println("Error al momento de insertar un dato");
                }
                RemoverElementosJtable();
                llenarJTable();
            }
        }

        if(accion.equals("actualizar"))
        {
            erroresActualizar="";

            if(txtNombreProducto.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Debe ingresar un nombre del producto");
                erroresActualizar+="Debe ingresar un nombre del producto";
            }
            else
            {
                patron = Pattern.compile("([ ]?([a-zA-ZñÑáéíóúÁÉÍÓÚ])+[ ]?(([a-zA-ZñÑáéíóúÁÉÍÓÚ])*)?)+");
                empata = patron.matcher(txtNombreProducto.getText());
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null,"Debe ingresar sólo letras en nombre del producto");
                    erroresActualizar+="Debe ingresar sólo letras en nombre del material";
                }
            }
            if(txtPrecio.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Debe ingresar el precio del producto");
                erroresActualizar+="Debe ingresar el precio del producto";
            }
            else
            {
                patron = Pattern.compile("[0-9]+([,.][0-9]{1,2})?");
                empata = patron.matcher(txtPrecio.getText());
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null,"Debe ingresar sólo números en el precio");
                    erroresActualizar+="Debe ingresar solo números en precio";
                }
            }
            if(txtMarca.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Debe ingresar la marca del producto");
                erroresActualizar+="Debe ingresar la marca del producto";
            }
            else
            {
                patron = Pattern.compile("[\\wñÑ\\s]+");
                empata = patron.matcher(txtMarca.getText());
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null,"Debe ingresar sólo letras en marca");
                    erroresActualizar+="Debe ingresar sólo letras en marca";
                }
            }
            if(txtStock.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Debe ingresar el stock del producto");
                erroresActualizar+="Debe ingresar el formato del material";
            }
            else
            {
                patron = Pattern.compile("\\d*");
                empata = patron.matcher(txtStock.getText());
                if (!empata.matches()) {
                    JOptionPane.showMessageDialog(null,"Debe ingresar sólo números en stock");
                    erroresActualizar+="Debe ingresar sólo números en stock";
                }
            }

            if(!erroresActualizar.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Error en la actualización de datos");
            }
            else{
                String insercion = "\n"+txtMarca.getText()+";"+txtNombreProducto.getText()+";"+txtPrecio.getText()+";"+txtStock.getText()+";";
                try (FileWriter writer = new FileWriter("D:/Universidad/Programas Java/PapeleriaKotlin/src/archivos/productos.txt", true);
                     BufferedWriter bw = new BufferedWriter(writer)) {
                    bw.write(insercion);
                } catch (IOException a) {
                    System.out.println("Error al momento de insertar un dato");
                }
                RemoverElementosJtable();
                llenarJTable();
            }


        }

        if(accion.equals("borrar"))
        {
            txtNombreProducto.setText("");
            txtPrecio.setText("");
            txtMarca.setText("");
            txtStock.setText("");
            try{
                File inputFile = new File("D:/Universidad/Programas Java/PapeleriaKotlin/src/archivos/productos.txt");
                File tempFile = new File("D:/Universidad/Programas Java/PapeleriaKotlin/src/archivos/temporal.txt");
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                String lineToRemove = txtMarca.getText()+";"+txtNombreProducto.getText()+";"+txtPrecio.getText()+";"+txtStock.getText()+";";
                String currentLine;

                while((currentLine = reader.readLine()) != null) {
                    // trim newline when comparing with lineToRemove
                    String trimmedLine = currentLine.trim();
                    if(trimmedLine.equals(lineToRemove)) continue;
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                writer.close();
                reader.close();
                System.out.println("Llegó hasta el final");
            }catch(Exception a){
                System.out.println("Error al eliminar el archivo");
                System.out.println(a.toString());
            }
            txtNombreProducto.setText("");
            txtPrecio.setText("");
            txtMarca.setText("");
            txtStock.setText("");

            try {
                FileReader fr = new FileReader("D:/Universidad/Programas Java/PapeleriaKotlin/src/archivos/temporal.txt");
                BufferedReader br = new BufferedReader(fr);
                FileWriter fw = new FileWriter("D:/Universidad/Programas Java/PapeleriaKotlin/src/archivos/productos.txt");
                String s;

                while ((s = br.readLine()) != null) { // read a line
                    fw.write(s+"\n"); // write to output file
                    fw.flush();
                }
                br.close();
                fw.close();
                System.out.println("file copied");
            } catch (IOException d) {
                System.out.println("Error al copiar el archivo");
                d.printStackTrace();
            }
            RemoverElementosJtable();
            llenarJTable();
        }
    }

    public void limpiaGUI()
    {
        txtNombreProducto.setText("");
        txtPrecio.setText("");
        txtMarca.setText("");
        txtStock.setText("");

    }

    public void llenarJTable(){
        try {
            File file = new File("D:/Universidad/Programas Java/PapeleriaKotlin/src/archivos/productos.txt");
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
            System.out.println("Error en el archivo");
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
                txtMarca.setText(String.valueOf(tabla.getValueAt(fil,0)).trim());
                txtNombreProducto.setText(String.valueOf(tabla.getValueAt(fil,1)).trim());
                txtPrecio.setText(String.valueOf(tabla.getValueAt(fil,2)).trim());
                txtStock.setText(String.valueOf(tabla.getValueAt(fil,3)).trim());
            }
        }
    }

    public static void main(String[] args)
    {
        Producto aplicattion =  new Producto();
        aplicattion.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    }
}
