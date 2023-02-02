
package Vistas;
import Modelo.Eventos;
import Modelo.Config;
import Modelo.Detalle;
import Modelo.Eventos;
import Modelo.ProductoDAO;
import Modelo.Productos;
import Modelo.cliente;
import Modelo.clienteDAO;
import Modelo.Proveedor;
import Modelo.ProveedorDAO;
import Modelo.Venta;
import Modelo.VentaDAO;
import Modelo.login;
import Reportes.ExcelClientes;
import Reportes.ExcelProductos;
import Reportes.ExcelProveedores;
import Reportes.ExcelVentas;
import Reportes.Grafico;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class Sistema extends javax.swing.JFrame {
    
    Date fechaVenta = new Date();
    String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(fechaVenta);
    cliente cl = new cliente();
    clienteDAO cli = new clienteDAO();
    Proveedor pr = new Proveedor();
    ProveedorDAO prDAO = new ProveedorDAO();
    Productos pro = new Productos();
    ProductoDAO proDAO = new ProductoDAO();
    Venta v = new Venta();
    VentaDAO ven = new VentaDAO();
    Detalle de = new Detalle();
    Eventos even = new Eventos();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel tmp = new DefaultTableModel();
    int item;
    double TotalPagar = 0.00;
    Config conf =  new Config();

    
    public Sistema(login priv){
        String bien = "BIENVENIDO:";
        initComponents();
        this.setLocationRelativeTo(null);
        txtIDCliente.setVisible(false);
        txtIDProveedor.setVisible(false);
        txtIDProducto.setVisible(false);
        txtIDVentas.setVisible(false);
        txtIDNuevaPro.setVisible(false);
        txtTelefonoClienteVenta.setVisible(true);
        txtDireccionClienteVenta.setVisible(true);
        txtRazonClienteVenta.setVisible(false);
        AutoCompleteDecorator.decorate(cbxProveedoProducto);
        proDAO.ConsultarProveedor(cbxProveedoProducto);
        //LabelVendedor.setLocation(null);
        LabelVendedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelVendedor.setVerticalAlignment(javax.swing.SwingConstants.CENTER); 
        LabelVendedor.setVisible(true);
        txtIdConfi.setVisible(false);
        txtIDNuevaPro.setVisible(false);
        txtRazonClienteVenta.setVisible(false);
        txtIDCliente.setVisible(false);
        txtIDProveedor.setVisible(false);
        txtIDProducto.setVisible(false);
        txtIDVentas.setVisible(false);
        txtIdConfi.setVisible(false);
        ListarConfig();
        if (priv.getRol().equals("Supervisor")) {
            btnProveedor.setEnabled(false);
            btnProductos.setEnabled(false);
            LabelVendedor.setText(priv.getCorreo().toUpperCase());
            txtIDNuevaPro.setVisible(false); 
            txtRazonClienteVenta.setVisible(false);
            txtIDCliente.setVisible(false); 
            txtIDProveedor.setVisible(false);
            txtIDProducto.setVisible(false); 
            txtIDVentas.setVisible(false);
            txtIdConfi.setVisible(false);
            btnConfiguracion.setEnabled(false);
            btnRegistrar_user.setEnabled(false);
            
            
        }else{
            LabelVendedor.setText(priv.getCorreo());
        }
    }
    
    
    public void LimpiarTabla(){
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(0);
            i-= 1;
        }
    }
    
    public Sistema() {
        initComponents();
        this.setLocationRelativeTo(null);
        txtIDCliente.setVisible(false);
        txtIDProveedor.setVisible(false);
        txtIDProducto.setVisible(false);
        txtIDVentas.setVisible(false);
        txtIDNuevaPro.setVisible(false);
        txtTelefonoClienteVenta.setVisible(true);
        txtDireccionClienteVenta.setVisible(true);
        txtRazonClienteVenta.setVisible(false);
        AutoCompleteDecorator.decorate(cbxProveedoProducto);
        proDAO.ConsultarProveedor(cbxProveedoProducto);
        //LabelVendedor.setVisible(false);
        txtIdConfi.setVisible(false);
        ListarConfig();
    }
    
    public void ListarCliente(){
        List<cliente> Listarcliente = cli.listarcliente();
        modelo = (DefaultTableModel) TableCliente.getModel();
        Object[] obj = new Object[6];
        for (int i = 0; i < Listarcliente.size(); i++) {
            obj[0] = Listarcliente.get(i).getId();
            obj[1] = Listarcliente.get(i).getDni();
            obj[2] = Listarcliente.get(i).getNombre();
            obj[3] = Listarcliente.get(i).getTelefono();
            obj[4] = Listarcliente.get(i).getDireccion();
            obj[5] = Listarcliente.get(i).getRazon();
            modelo.addRow(obj);
            
        }
        TableCliente.setModel(modelo);
    }
    
    public void listarProveedor(){
        List<Proveedor> ListaPR = prDAO.listarProveedor();
        modelo = (DefaultTableModel) TableProveedor.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < ListaPR.size(); i++) {
            ob[0] = ListaPR.get(i).getId();
            ob[1] = ListaPR.get(i).getRuc();
            ob[2] = ListaPR.get(i).getNombre();
            ob[3] = ListaPR.get(i).getTelefono();
            ob[4] = ListaPR.get(i).getDireccion();
            ob[5] = ListaPR.get(i).getRazon();
            modelo.addRow(ob);
           
        }
        TableProveedor.setModel(modelo);
    }
    
    public void listarProductos(){
        List<Productos> ListarPro = proDAO.listarproductos();
        modelo = (DefaultTableModel) TableProducto.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < ListarPro.size(); i++) {
            ob[0] = ListarPro.get(i).getId();
            ob[1] = ListarPro.get(i).getCodigo();
            ob[2] = ListarPro.get(i).getDescripcion();
            ob[3] = ListarPro.get(i).getProveedor();
            ob[4] = ListarPro.get(i).getCantidad();
            ob[5] = ListarPro.get(i).getPrecio();
            modelo.addRow(ob);
           
        }
        TableProducto.setModel(modelo);
    }
    
    public void ListarConfig(){
        conf = proDAO.BuscarDatos();
        txtIdConfi.setText(""+conf.getId());
        txtIdEmpresa.setText(""+conf.getRud());
        txtNombreEmpresa.setText(""+conf.getNombre());
        txtTelefonoEmpresa.setText(""+conf.getTelefono());
        txtDireccionEmpresa.setText(""+conf.getDireccion());
        txtRazonsocialEmpresa.setText(""+conf.getRazon());
        
    }
    
      public void ListarVentas(){
        List<Venta> ListarVenta = ven.Listarventas();
        modelo = (DefaultTableModel) TableVentas.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < ListarVenta.size(); i++) {
            ob[0] = ListarVenta.get(i).getId();
            ob[1] = ListarVenta.get(i).getCliente();
            ob[2] = ListarVenta.get(i).getVendedor();
            ob[3] = ListarVenta.get(i).getTotal();
            modelo.addRow(ob);
           
        }
        TableVentas.setModel(modelo);
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnNuevaVenta = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnProveedor = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        bntVentas = new javax.swing.JButton();
        btnConfiguracion = new javax.swing.JButton();
        LabelVendedor = new javax.swing.JLabel();
        btnRegistrar_user = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        LISTADO = new javax.swing.JTabbedPane();
        nuevaVenta = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCodigoVenta = new javax.swing.JTextField();
        txtDescripcionVenta = new javax.swing.JTextField();
        txtCantidadVenta = new javax.swing.JTextField();
        txtPrecioVenta = new javax.swing.JTextField();
        txtStockDisponible = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnEliminarVenta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableVenta = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtRucVenta = new javax.swing.JTextField();
        txtNombeClienteVenta = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        LabelTotal = new javax.swing.JLabel();
        txtTelefonoClienteVenta = new javax.swing.JTextField();
        txtDireccionClienteVenta = new javax.swing.JTextField();
        txtRazonClienteVenta = new javax.swing.JTextField();
        txtIDNuevaPro = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        btnGenerarVenta2 = new javax.swing.JButton();
        Midate = new com.toedter.calendar.JDateChooser();
        jLabel39 = new javax.swing.JLabel();
        btnTortas = new javax.swing.JButton();
        clientes = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        txtTelefonoCliente = new javax.swing.JTextField();
        txtDireccionCliente = new javax.swing.JTextField();
        txtRazonSocialCliente = new javax.swing.JTextField();
        txtDNICliente = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableCliente = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        btnEliminarCliente = new javax.swing.JButton();
        btnGuardarCliente = new javax.swing.JButton();
        btnActualizarCliente = new javax.swing.JButton();
        btnNuevoCliente = new javax.swing.JButton();
        txtIDCliente = new javax.swing.JTextField();
        btnExcelClientes = new javax.swing.JButton();
        proveedor = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtDNIProveedor = new javax.swing.JTextField();
        txtNombreProveedor = new javax.swing.JTextField();
        txtTelefonoProveedor = new javax.swing.JTextField();
        txtDireccionProveedor = new javax.swing.JTextField();
        txtRazonSocialProveedor = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableProveedor = new javax.swing.JTable();
        btnNuevoProveedor = new javax.swing.JButton();
        btnGuardarProveedor = new javax.swing.JButton();
        btnActualziarProveedor = new javax.swing.JButton();
        btnEliminarProveedor = new javax.swing.JButton();
        txtIDProveedor = new javax.swing.JTextField();
        btnExcelProveedor1 = new javax.swing.JButton();
        producto = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        txtDescripcionProducto = new javax.swing.JTextField();
        txtCantidadProducto = new javax.swing.JTextField();
        txtPrecioProducto = new javax.swing.JTextField();
        cbxProveedoProducto = new javax.swing.JComboBox();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableProducto = new javax.swing.JTable();
        btnNuevoProducto = new javax.swing.JButton();
        btnGuardarProducto = new javax.swing.JButton();
        btnActualizarProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        btnExcelProducto = new javax.swing.JButton();
        btnPDFProducto = new javax.swing.JButton();
        txtIDProducto = new javax.swing.JTextField();
        ventasRegistradas = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TableVentas = new javax.swing.JTable();
        btnPDFVentas = new javax.swing.JButton();
        txtIDVentas = new javax.swing.JTextField();
        btnExcelVentas = new javax.swing.JButton();
        configuracion = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        txtIdEmpresa = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtNombreEmpresa = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtTelefonoEmpresa = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtDireccionEmpresa = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtRazonsocialEmpresa = new javax.swing.JTextField();
        btnActualizarEmpresa = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        txtIdConfi = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

        btnNuevaVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Nventa.png"))); // NOI18N
        btnNuevaVenta.setText("Nueva Venta");
        btnNuevaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaVentaActionPerformed(evt);
            }
        });

        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Clientes.png"))); // NOI18N
        btnClientes.setText("Clientes");
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        btnProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/proveedor.png"))); // NOI18N
        btnProveedor.setText("Proveedor");
        btnProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedorActionPerformed(evt);
            }
        });

        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/producto.png"))); // NOI18N
        btnProductos.setText("Productos");
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });

        bntVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/compras.png"))); // NOI18N
        bntVentas.setText("Ventas");
        bntVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntVentasActionPerformed(evt);
            }
        });

        btnConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/config.png"))); // NOI18N
        btnConfiguracion.setText("Configuracion");
        btnConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracionActionPerformed(evt);
            }
        });

        LabelVendedor.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        LabelVendedor.setForeground(new java.awt.Color(255, 255, 255));
        LabelVendedor.setToolTipText("");

        btnRegistrar_user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Clientes.png"))); // NOI18N
        btnRegistrar_user.setText("Register");
        btnRegistrar_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrar_userActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevaVenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(btnClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bntVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConfiguracion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LabelVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistrar_user, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(LabelVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(btnNuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRegistrar_user, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bntVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 610));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/encabezado.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 820, 210));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("CODIGO");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("DESCRIPCION");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("CANTIDAD");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 0));
        jLabel6.setText("PRECIO");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 255));
        jLabel7.setText("CANT BODEGA");

        txtCodigoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoVentaActionPerformed(evt);
            }
        });
        txtCodigoVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoVentaKeyTyped(evt);
            }
        });

        txtDescripcionVenta.setEditable(false);
        txtDescripcionVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionVentaActionPerformed(evt);
            }
        });

        txtCantidadVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadVentaKeyTyped(evt);
            }
        });

        txtPrecioVenta.setEditable(false);

        txtStockDisponible.setEditable(false);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 0, 0));
        jLabel9.setText("ELIMINAR");

        btnEliminarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarVentaActionPerformed(evt);
            }
        });

        TableVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ));
        jScrollPane1.setViewportView(TableVenta);
        if (TableVenta.getColumnModel().getColumnCount() > 0) {
            TableVenta.getColumnModel().getColumn(0).setPreferredWidth(30);
            TableVenta.getColumnModel().getColumn(1).setPreferredWidth(100);
            TableVenta.getColumnModel().getColumn(2).setPreferredWidth(30);
            TableVenta.getColumnModel().getColumn(3).setPreferredWidth(30);
            TableVenta.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("ID:");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("NOMBRE:");

        txtRucVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRucVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRucVentaKeyTyped(evt);
            }
        });

        txtNombeClienteVenta.setEditable(false);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/money.png"))); // NOI18N
        jLabel11.setText("TOTAL A PAGAR:");

        LabelTotal.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        LabelTotal.setForeground(new java.awt.Color(255, 0, 0));
        LabelTotal.setText("........\n");

        txtTelefonoClienteVenta.setEditable(false);

        txtDireccionClienteVenta.setEditable(false);

        txtRazonClienteVenta.setEditable(false);

        txtIDNuevaPro.setEditable(false);

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setText("DIRECCION:");

        jLabel38.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel38.setText("TELEFONO:");

        btnGenerarVenta2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/print.png"))); // NOI18N
        btnGenerarVenta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarVenta2ActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel39.setText("RANGO DE FECHA:");

        btnTortas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/torta.png"))); // NOI18N
        btnTortas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTortasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout nuevaVentaLayout = new javax.swing.GroupLayout(nuevaVenta);
        nuevaVenta.setLayout(nuevaVentaLayout);
        nuevaVentaLayout.setHorizontalGroup(
            nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nuevaVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(nuevaVentaLayout.createSequentialGroup()
                        .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCodigoVenta))
                        .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(nuevaVentaLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtIDNuevaPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtRazonClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel4))
                            .addGroup(nuevaVentaLayout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(txtDescripcionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(nuevaVentaLayout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jLabel6)
                                .addGap(66, 66, 66))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nuevaVentaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)))
                        .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtStockDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminarVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nuevaVentaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nuevaVentaLayout.createSequentialGroup()
                                .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(nuevaVentaLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtRucVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNombeClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(nuevaVentaLayout.createSequentialGroup()
                                        .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(nuevaVentaLayout.createSequentialGroup()
                                                .addComponent(jLabel38)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtTelefonoClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(nuevaVentaLayout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtDireccionClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(200, 200, 200)))
                                .addGap(27, 27, 27)
                                .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(nuevaVentaLayout.createSequentialGroup()
                                        .addComponent(btnGenerarVenta2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnTortas, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(nuevaVentaLayout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(LabelTotal)))
                                .addGap(125, 125, 125))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nuevaVentaLayout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Midate, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)))))
                .addContainerGap())
        );
        nuevaVentaLayout.setVerticalGroup(
            nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nuevaVentaLayout.createSequentialGroup()
                .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(nuevaVentaLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescripcionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminarVenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtStockDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCodigoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtRazonClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIDNuevaPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(nuevaVentaLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtRucVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txtNombeClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(nuevaVentaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(LabelTotal))))
                .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(nuevaVentaLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtDireccionClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(txtTelefonoClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(nuevaVentaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTortas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGenerarVenta2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(nuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Midate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39))))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        LISTADO.addTab("VENTA", nuevaVenta);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setText("RUC:");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setText("NOMBRE:");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setText("TELEFONO:");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setText("DIRECCION:");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("RAZON SOCIAL:");

        txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyTyped(evt);
            }
        });

        txtTelefonoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoClienteKeyTyped(evt);
            }
        });

        txtRazonSocialCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRazonSocialClienteKeyTyped(evt);
            }
        });

        txtDNICliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDNIClienteKeyTyped(evt);
            }
        });

        TableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "RUC", "NOMBRE", "TELEFONO", "DIRECCION", "RAZON SOCIAL"
            }
        ));
        TableCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TableCliente);
        if (TableCliente.getColumnModel().getColumnCount() > 0) {
            TableCliente.getColumnModel().getColumn(0).setPreferredWidth(20);
            TableCliente.getColumnModel().getColumn(0).setHeaderValue("ID");
            TableCliente.getColumnModel().getColumn(1).setPreferredWidth(20);
            TableCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
            TableCliente.getColumnModel().getColumn(3).setPreferredWidth(50);
            TableCliente.getColumnModel().getColumn(4).setPreferredWidth(80);
            TableCliente.getColumnModel().getColumn(5).setPreferredWidth(80);
        }

        jLabel18.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel18.setText("CLIENTES REGISTRADOS.");

        btnEliminarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });

        btnGuardarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnGuardarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarClienteActionPerformed(evt);
            }
        });

        btnActualizarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnActualizarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarClienteActionPerformed(evt);
            }
        });

        btnNuevoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btnNuevoCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });

        btnExcelClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/excel.png"))); // NOI18N
        btnExcelClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcelClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelClientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout clientesLayout = new javax.swing.GroupLayout(clientes);
        clientes.setLayout(clientesLayout);
        clientesLayout.setHorizontalGroup(
            clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(clientesLayout.createSequentialGroup()
                        .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(clientesLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefonoCliente))
                            .addGroup(clientesLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDireccionCliente))
                            .addGroup(clientesLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRazonSocialCliente))
                            .addGroup(clientesLayout.createSequentialGroup()
                                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(clientesLayout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDNICliente, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(clientesLayout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))
                    .addGroup(clientesLayout.createSequentialGroup()
                        .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(clientesLayout.createSequentialGroup()
                                .addComponent(btnNuevoCliente)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardarCliente)
                                .addGap(18, 18, 18)
                                .addComponent(btnActualizarCliente)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarCliente))
                            .addComponent(btnExcelClientes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, clientesLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, clientesLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(120, 120, 120))))
        );
        clientesLayout.setVerticalGroup(
            clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, clientesLayout.createSequentialGroup()
                .addGap(0, 7, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, clientesLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtDNICliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtRazonSocialCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExcelClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        LISTADO.addTab("CLIENTE", clientes);

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setText("ID:");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setText("NOMBRE:");

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setText("TELEFONO:");

        jLabel22.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel22.setText("DIRECCION:");

        jLabel23.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel23.setText("RAZON SOCIAL:");

        txtDNIProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDNIProveedorKeyTyped(evt);
            }
        });

        txtNombreProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreProveedorKeyTyped(evt);
            }
        });

        txtTelefonoProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoProveedorKeyTyped(evt);
            }
        });

        txtRazonSocialProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRazonSocialProveedorActionPerformed(evt);
            }
        });
        txtRazonSocialProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRazonSocialProveedorKeyTyped(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel24.setText("PROVEEDORES REGISTRADOS.");

        TableProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "RUC", "NOMBRE", "TELEFONO", "DIRECCION", "RAZON SOCIAL"
            }
        ));
        TableProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableProveedorMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TableProveedor);
        if (TableProveedor.getColumnModel().getColumnCount() > 0) {
            TableProveedor.getColumnModel().getColumn(0).setPreferredWidth(20);
            TableProveedor.getColumnModel().getColumn(1).setPreferredWidth(50);
            TableProveedor.getColumnModel().getColumn(2).setPreferredWidth(100);
            TableProveedor.getColumnModel().getColumn(3).setPreferredWidth(80);
            TableProveedor.getColumnModel().getColumn(4).setPreferredWidth(80);
            TableProveedor.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        btnNuevoProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btnNuevoProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProveedorActionPerformed(evt);
            }
        });

        btnGuardarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnGuardarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProveedorActionPerformed(evt);
            }
        });

        btnActualziarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnActualziarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualziarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualziarProveedorActionPerformed(evt);
            }
        });

        btnEliminarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProveedorActionPerformed(evt);
            }
        });

        btnExcelProveedor1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/excel.png"))); // NOI18N
        btnExcelProveedor1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcelProveedor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelProveedor1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout proveedorLayout = new javax.swing.GroupLayout(proveedor);
        proveedor.setLayout(proveedorLayout);
        proveedorLayout.setHorizontalGroup(
            proveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proveedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(proveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(proveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(proveedorLayout.createSequentialGroup()
                            .addComponent(jLabel19)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtDNIProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtIDProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(proveedorLayout.createSequentialGroup()
                            .addComponent(jLabel21)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTelefonoProveedor))
                        .addGroup(proveedorLayout.createSequentialGroup()
                            .addComponent(jLabel22)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtDireccionProveedor))
                        .addGroup(proveedorLayout.createSequentialGroup()
                            .addComponent(jLabel23)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtRazonSocialProveedor))
                        .addGroup(proveedorLayout.createSequentialGroup()
                            .addComponent(jLabel20)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(proveedorLayout.createSequentialGroup()
                        .addComponent(btnNuevoProveedor)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardarProveedor)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualziarProveedor)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarProveedor))
                    .addComponent(btnExcelProveedor1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(proveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, proveedorLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, proveedorLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(76, 76, 76))))
        );
        proveedorLayout.setVerticalGroup(
            proveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proveedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addGap(5, 5, 5)
                .addGroup(proveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(proveedorLayout.createSequentialGroup()
                        .addGroup(proveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtDNIProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIDProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(proveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(proveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtTelefonoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(proveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtDireccionProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(proveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtRazonSocialProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(proveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNuevoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualziarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcelProveedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        LISTADO.addTab("PROVEEDOR", proveedor);

        jLabel25.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel25.setText("CODIGO:");

        jLabel26.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel26.setText("DESCRIPCION:");

        jLabel27.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel27.setText("CANTIDAD:");

        jLabel28.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel28.setText("PRECIO:");

        jLabel29.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel29.setText("PROVEEDOR:");

        txtCodigoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoProductoKeyTyped(evt);
            }
        });

        txtDescripcionProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionProductoKeyTyped(evt);
            }
        });

        txtCantidadProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadProductoKeyTyped(evt);
            }
        });

        txtPrecioProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioProductoKeyTyped(evt);
            }
        });

        cbxProveedoProducto.setEditable(true);

        jLabel30.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel30.setText("PRODUCTOS REGISTRADOS.");

        TableProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CODIGO", "DESCRIPCION", "PROVEEDOR", "CANTIDAD", "PRECIO"
            }
        ));
        TableProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableProductoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TableProducto);
        if (TableProducto.getColumnModel().getColumnCount() > 0) {
            TableProducto.getColumnModel().getColumn(0).setPreferredWidth(30);
            TableProducto.getColumnModel().getColumn(1).setPreferredWidth(30);
            TableProducto.getColumnModel().getColumn(2).setPreferredWidth(100);
            TableProducto.getColumnModel().getColumn(3).setPreferredWidth(100);
            TableProducto.getColumnModel().getColumn(4).setPreferredWidth(30);
            TableProducto.getColumnModel().getColumn(5).setPreferredWidth(30);
        }

        btnNuevoProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btnNuevoProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProductoActionPerformed(evt);
            }
        });

        btnGuardarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnGuardarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProductoActionPerformed(evt);
            }
        });

        btnActualizarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnActualizarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarProductoActionPerformed(evt);
            }
        });

        btnEliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });

        btnExcelProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/excel.png"))); // NOI18N
        btnExcelProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcelProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelProductoActionPerformed(evt);
            }
        });

        btnPDFProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/pdf.png"))); // NOI18N
        btnPDFProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPDFProducto.setEnabled(false);
        btnPDFProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout productoLayout = new javax.swing.GroupLayout(producto);
        producto.setLayout(productoLayout);
        productoLayout.setHorizontalGroup(
            productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(productoLayout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxProveedoProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(productoLayout.createSequentialGroup()
                        .addGroup(productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(productoLayout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDescripcionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(productoLayout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(productoLayout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIDProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(productoLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(productoLayout.createSequentialGroup()
                                        .addComponent(btnExcelProducto)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnPDFProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(productoLayout.createSequentialGroup()
                                        .addComponent(btnNuevoProducto)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnGuardarProducto)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnActualizarProducto)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnEliminarProducto))))
                            .addGroup(productoLayout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productoLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productoLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(109, 109, 109))))
        );
        productoLayout.setVerticalGroup(
            productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productoLayout.createSequentialGroup()
                .addGroup(productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(productoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(productoLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIDProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(txtDescripcionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(txtCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(txtPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(cbxProveedoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnExcelProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPDFProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        LISTADO.addTab("PRODUCTO", producto);

        jLabel31.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel31.setText("VENTAS REGISTRADAS.");

        TableVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CLIENTE", "VENDEDOR", "TOTAL"
            }
        ));
        TableVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableVentasMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TableVentas);
        if (TableVentas.getColumnModel().getColumnCount() > 0) {
            TableVentas.getColumnModel().getColumn(0).setPreferredWidth(30);
            TableVentas.getColumnModel().getColumn(1).setPreferredWidth(50);
            TableVentas.getColumnModel().getColumn(2).setPreferredWidth(50);
            TableVentas.getColumnModel().getColumn(3).setPreferredWidth(200);
        }

        btnPDFVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/pdf.png"))); // NOI18N
        btnPDFVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPDFVentas.setEnabled(false);
        btnPDFVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFVentasActionPerformed(evt);
            }
        });

        btnExcelVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/excel.png"))); // NOI18N
        btnExcelVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcelVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelVentasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ventasRegistradasLayout = new javax.swing.GroupLayout(ventasRegistradas);
        ventasRegistradas.setLayout(ventasRegistradasLayout);
        ventasRegistradasLayout.setHorizontalGroup(
            ventasRegistradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventasRegistradasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ventasRegistradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
                    .addGroup(ventasRegistradasLayout.createSequentialGroup()
                        .addGroup(ventasRegistradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ventasRegistradasLayout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addGap(41, 41, 41)
                                .addComponent(txtIDVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ventasRegistradasLayout.createSequentialGroup()
                                .addComponent(btnPDFVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnExcelVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        ventasRegistradasLayout.setVerticalGroup(
            ventasRegistradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventasRegistradasLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(ventasRegistradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtIDVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(ventasRegistradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPDFVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExcelVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        LISTADO.addTab("LISTADO", ventasRegistradas);

        jLabel32.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel32.setText("ID:");

        txtIdEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdEmpresaKeyTyped(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel33.setText("NOMBRE:");

        txtNombreEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreEmpresaKeyTyped(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel34.setText("TELEFONO:");

        txtTelefonoEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoEmpresaKeyTyped(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel35.setText("DIRECCION:");

        txtDireccionEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionEmpresaActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel36.setText("RAZON SOCIAL:");

        txtRazonsocialEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRazonsocialEmpresaKeyTyped(evt);
            }
        });

        btnActualizarEmpresa.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnActualizarEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnActualizarEmpresa.setText("ACTUALIZAR");
        btnActualizarEmpresa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarEmpresaActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel37.setText("DATOS EMPRESARIALES.");

        txtIdConfi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdConfiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout configuracionLayout = new javax.swing.GroupLayout(configuracion);
        configuracion.setLayout(configuracionLayout);
        configuracionLayout.setHorizontalGroup(
            configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configuracionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(configuracionLayout.createSequentialGroup()
                            .addComponent(jLabel34)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTelefonoEmpresa))
                        .addGroup(configuracionLayout.createSequentialGroup()
                            .addComponent(jLabel35)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtDireccionEmpresa))
                        .addGroup(configuracionLayout.createSequentialGroup()
                            .addComponent(jLabel36)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtRazonsocialEmpresa))
                        .addGroup(configuracionLayout.createSequentialGroup()
                            .addComponent(jLabel33)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(configuracionLayout.createSequentialGroup()
                            .addComponent(jLabel32)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtIdEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnActualizarEmpresa)
                    .addComponent(jLabel37)
                    .addComponent(txtIdConfi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(509, Short.MAX_VALUE))
        );
        configuracionLayout.setVerticalGroup(
            configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, configuracionLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtIdConfi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtIdEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtNombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtTelefonoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtDireccionEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txtRazonsocialEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnActualizarEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        LISTADO.addTab("CONFIGURACION", configuracion);

        getContentPane().add(LISTADO, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 820, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        // TODO add your handling code here:
        LimpiarTabla();
        ListarCliente();
        LimpiarCliente();
        LISTADO.setSelectedIndex(1);
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedorActionPerformed
        // TODO add your handling code here:
        LimpiarTabla();
        listarProveedor();
        LimpiarProveedor();
        LISTADO.setSelectedIndex(2);
    }//GEN-LAST:event_btnProveedorActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        // TODO add your handling code here:
        LimpiarTabla();
        listarProductos();
        LimpiarProducto();
        LISTADO.setSelectedIndex(3);
    }//GEN-LAST:event_btnProductosActionPerformed

    private void bntVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntVentasActionPerformed
        // TODO add your handling code here:
        LimpiarTabla();
        ListarVentas();
        LISTADO.setSelectedIndex(4);
       
    }//GEN-LAST:event_bntVentasActionPerformed

    private void btnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracionActionPerformed
        // TODO add your handling code here:
        LimpiarCliente();
        LISTADO.setSelectedIndex(5);
    }//GEN-LAST:event_btnConfiguracionActionPerformed

    private void btnNuevaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaVentaActionPerformed
        // TODO add your handling code here:
        LimpiarCliente();
        LISTADO.setSelectedIndex(0);
    }//GEN-LAST:event_btnNuevaVentaActionPerformed

    private void txtIdConfiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdConfiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdConfiActionPerformed

    private void btnActualizarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarEmpresaActionPerformed
        // TODO add your handling code here:
        if(!"".equals(txtIdEmpresa.getText()) || !"".equals(txtNombreEmpresa.getText()) || !"".equals(txtTelefonoEmpresa.getText()) || !"".equals(txtDireccionEmpresa.getText()) || !"".equals(txtRazonsocialEmpresa.getText())){
            conf.setRud(txtIdEmpresa.getText());
            conf.setNombre(txtNombreEmpresa.getText());
            conf.setTelefono(Integer.parseInt(txtTelefonoEmpresa.getText()));
            conf.setDireccion(txtDireccionEmpresa.getText());
            conf.setRazon(txtRazonsocialEmpresa.getText());
            conf.setId(Integer.parseInt(txtIdConfi.getText()));

            proDAO.ActualizarDatos(conf);

            JOptionPane.showMessageDialog(null, "EMPRESA ACTUALIZADA CORRECTAMENTE");
            ListarConfig();
        }else{
            JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON REQUERIDOS");
        }
    }//GEN-LAST:event_btnActualizarEmpresaActionPerformed

    private void txtRazonsocialEmpresaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonsocialEmpresaKeyTyped
        // TODO add your handling code here:
        even.Caracteres(evt);
    }//GEN-LAST:event_txtRazonsocialEmpresaKeyTyped

    private void txtDireccionEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionEmpresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionEmpresaActionPerformed

    private void txtTelefonoEmpresaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoEmpresaKeyTyped
        // TODO add your handling code here:
        even.Numeros(evt);
    }//GEN-LAST:event_txtTelefonoEmpresaKeyTyped

    private void txtNombreEmpresaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreEmpresaKeyTyped
        // TODO add your handling code here:
        even.Caracteres(evt);
    }//GEN-LAST:event_txtNombreEmpresaKeyTyped

    private void txtIdEmpresaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdEmpresaKeyTyped
        // TODO add your handling code here:
        even.Numeros(evt);
    }//GEN-LAST:event_txtIdEmpresaKeyTyped

    private void btnExcelVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelVentasActionPerformed
        // TODO add your handling code here:
        ExcelVentas.reporte();
    }//GEN-LAST:event_btnExcelVentasActionPerformed

    private void btnPDFVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFVentasActionPerformed
        // TODO add your handling code here:
        try {
            int id = Integer.parseInt(txtIDVentas.getText());
            File file = new File("src/PDF/venta.pdf");

            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPDFVentasActionPerformed

    private void TableVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableVentasMouseClicked
        // TODO add your handling code here:
        int fila = TableVentas.rowAtPoint(evt.getPoint());
        txtIDVentas.setText(TableVentas.getValueAt(fila, 0).toString());
    }//GEN-LAST:event_TableVentasMouseClicked

    private void btnExcelProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelProductoActionPerformed
        // TODO add your handling code here:
        ExcelProductos.reporte();
    }//GEN-LAST:event_btnExcelProductoActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtIDProducto.getText())) {
            JOptionPane.showMessageDialog(null, "SELECCIONE UN PRODUCTO DE LA TABLA PARA SER ELIMINADO");
        } else {
            if (!"".equals(txtIDProducto.getText())) {
                int pregunta = JOptionPane.showConfirmDialog(null, "ESTAS SEGURO DE ELIMINAR ESTE PRODUCTO?");
                if (pregunta == 0) {
                    int id = Integer.parseInt(txtIDProducto.getText());
                    proDAO.EliminarProductos(id);
                    LimpiarProducto();
                    LimpiarTabla();
                    listarProductos();
                    JOptionPane.showMessageDialog(null, "PRODUCTO ELIMINADO CORRECTAMENTE");
                }
            }
        }
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnActualizarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarProductoActionPerformed
        // TODO add your handling code here:
        if("".equals(txtIDProducto.getText())){
            JOptionPane.showMessageDialog(null, "SELECCIONE UN PRODUCTO DE LA TABLA PARA SER ACTUALIZADO");
        }else{

            if(!"".equals(txtCodigoProducto.getText()) || !"".equals(txtDescripcionProducto.getText()) || !"".equals(cbxProveedoProducto.getSelectedItem()) || !"".equals(txtCantidadProducto.getText()) || !"".equals(txtPrecioProducto.getText())){
                pro.setCodigo(txtCodigoProducto.getText());
                pro.setDescripcion(txtDescripcionProducto.getText());
                pro.setProveedor(cbxProveedoProducto.getSelectedItem().toString());
                pro.setCantidad(Integer.parseInt(txtCantidadProducto.getText()));
                pro.setPrecio(Double.parseDouble(txtPrecioProducto.getText()));
                pro.setId(Integer.parseInt(txtIDProducto.getText()));

                proDAO.ActualizarProductos(pro);
                LimpiarTabla();
                LimpiarProducto();
                listarProductos();

                JOptionPane.showMessageDialog(null, "PRODUCTO ACTUALIZADO CORRECTAMENTE");
            }else{
                JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON REQUERIDOS");
            }
        }
    }//GEN-LAST:event_btnActualizarProductoActionPerformed

    private void btnGuardarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProductoActionPerformed
        // TODO add your handling code here:
        if(!"".equals(txtCodigoProducto.getText()) || !"".equals(txtDescripcionProducto.getText()) || !"".equals(txtCantidadProducto.getText()) || !"".equals(txtPrecioProducto.getText()) || !"".equals(cbxProveedoProducto.getSelectedItem())){
            pro.setCodigo(txtCodigoProducto.getText());
            pro.setDescripcion(txtDescripcionProducto.getText());
            pro.setCantidad(Integer.parseInt(txtCantidadProducto.getText()));
            pro.setPrecio(Double.parseDouble(txtPrecioProducto.getText()));
            pro.setProveedor(cbxProveedoProducto.getSelectedItem().toString());
            proDAO.registrarProducto(pro);
            LimpiarProducto();
            listarProductos();
            //LimpiarTabla();
            //LimpiarTablaPRO();
            JOptionPane.showMessageDialog(null, "PRODUCTO REGISTRADO EXITOSAMENTE");
        }else{
            JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON REQUERIDOS");
        }

    }//GEN-LAST:event_btnGuardarProductoActionPerformed

    private void btnNuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProductoActionPerformed
        // TODO add your handling code here:
        LimpiarProducto();
    }//GEN-LAST:event_btnNuevoProductoActionPerformed

    private void TableProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableProductoMouseClicked
        // TODO add your handling code here:
        int fila = TableProducto.rowAtPoint(evt.getPoint());
        txtIDProducto.setText(TableProducto.getValueAt(fila,0).toString());
        txtCodigoProducto.setText(TableProducto.getValueAt(fila,1).toString());
        txtDescripcionProducto.setText(TableProducto.getValueAt(fila,2).toString());
        cbxProveedoProducto.setSelectedItem(TableProducto.getValueAt(fila,3).toString());
        txtCantidadProducto.setText(TableProducto.getValueAt(fila,4).toString());
        txtPrecioProducto.setText(TableProducto.getValueAt(fila,5).toString());
    }//GEN-LAST:event_TableProductoMouseClicked

    private void txtPrecioProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioProductoKeyTyped
        // TODO add your handling code here:
        even.Decimales(evt,txtPrecioProducto);
    }//GEN-LAST:event_txtPrecioProductoKeyTyped

    private void txtCantidadProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadProductoKeyTyped
        // TODO add your handling code here:
        even.Numeros(evt);
    }//GEN-LAST:event_txtCantidadProductoKeyTyped

    private void txtDescripcionProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionProductoKeyTyped
        // TODO add your handling code here:
        even.Caracteres(evt);
    }//GEN-LAST:event_txtDescripcionProductoKeyTyped

    private void txtCodigoProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProductoKeyTyped
        // TODO add your handling code here:
        even.Numeros(evt);
    }//GEN-LAST:event_txtCodigoProductoKeyTyped

    private void btnExcelProveedor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelProveedor1ActionPerformed
        // TODO add your handling code here:
        ExcelProveedores.reporte();
    }//GEN-LAST:event_btnExcelProveedor1ActionPerformed

    private void btnEliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProveedorActionPerformed
        // TODO add your handling code here:
        if(!"".equals(txtIDProveedor.getText())){
            int  pregunta = JOptionPane.showConfirmDialog(null, "ESTAS SEGURO DE ELIMINAR ESTE PROVEEDOR?");
            if(pregunta == 0){
                int id = Integer.parseInt(txtIDProveedor.getText());
                prDAO.EliminarProveedor(id);
                LimpiarTabla();
                LimpiarProveedor();
                listarProveedor();
                JOptionPane.showMessageDialog(null, "PROVEEDOR ELIMINADO CORRECTAMENTE");
            }
        }else{
            JOptionPane.showMessageDialog(null, "SELECCIONE UN PROVEEDOR DE LA TABLA PARA SER ELIMINADO");
        }
    }//GEN-LAST:event_btnEliminarProveedorActionPerformed

    private void btnActualziarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualziarProveedorActionPerformed
        // TODO add your handling code here:
        if("".equals(txtIDProveedor.getText())){
            JOptionPane.showMessageDialog(null, "SELECCIONE UN PROVEEDOR DE LA TABLA PARA SER ACTUALIZADO");
        }else{

            if(!"".equals(txtDNIProveedor.getText()) || !"".equals(txtNombreProveedor.getText()) || !"".equals(txtTelefonoProveedor.getText()) || !"".equals(txtDireccionProveedor.getText()) || !"".equals(txtRazonSocialProveedor.getText())){
                pr.setRuc(Integer.parseInt(txtDNIProveedor.getText()));
                pr.setNombre(txtNombreProveedor.getText());
                pr.setTelefono(txtTelefonoProveedor.getText());
                pr.setDireccion(txtDireccionProveedor.getText());
                pr.setRazon(txtRazonSocialProveedor.getText());
                pr.setId(Integer.parseInt(txtIDProveedor.getText()));

                prDAO.ActualizarProveedor(pr);
                LimpiarTabla();
                LimpiarProveedor();
                listarProveedor();

                JOptionPane.showMessageDialog(null, "PROVEEDOR ACTUALIZADO CORRECTAMENTE");
            }else{
                JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON REQUERIDOS");
            }
        }
    }//GEN-LAST:event_btnActualziarProveedorActionPerformed

    private void btnGuardarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProveedorActionPerformed
        // TODO add your handling code here:

        if(!"".equals(txtDNIProveedor.getText()) || !"".equals(txtNombreProveedor.getText()) || !"".equals(txtTelefonoProveedor.getText()) || !"".equals(txtDireccionProveedor.getText()) || !"".equals(txtRazonSocialProveedor.getText())){
            pr.setRuc(Integer.parseInt(txtDNIProveedor.getText()));
            pr.setNombre(txtNombreProveedor.getText());
            pr.setTelefono(txtTelefonoProveedor.getText());
            pr.setDireccion(txtDireccionProveedor.getText());
            pr.setRazon(txtRazonSocialProveedor.getText());
            prDAO.registrarProveedor(pr);
            //LimpiarTabla();
            LimpiarProveedor();
            listarProveedor();

            JOptionPane.showMessageDialog(null, "PROVEEDOR REGISTRADO EXITOSAMENTE");
        }else{
            JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON REQUERIDOS");
        }
    }//GEN-LAST:event_btnGuardarProveedorActionPerformed

    private void btnNuevoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProveedorActionPerformed
        // TODO add your handling code here:
        LimpiarProveedor();
    }//GEN-LAST:event_btnNuevoProveedorActionPerformed

    private void TableProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableProveedorMouseClicked
        // TODO add your handling code here:

        int fila = TableProveedor.rowAtPoint(evt.getPoint());
        txtIDProveedor.setText(TableProveedor.getValueAt(fila, 0).toString());
        txtDNIProveedor.setText(TableProveedor.getValueAt(fila,1).toString());
        txtNombreProveedor.setText(TableProveedor.getValueAt(fila,2).toString());
        txtTelefonoProveedor.setText(TableProveedor.getValueAt(fila,3).toString());
        txtDireccionProveedor.setText(TableProveedor.getValueAt(fila,4).toString());
        txtRazonSocialProveedor.setText(TableProveedor.getValueAt(fila,5).toString());
        //txtRazonSocialCliente.setText(TableProveedor.getValueAt(fila,5).toString());
    }//GEN-LAST:event_TableProveedorMouseClicked

    private void txtRazonSocialProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonSocialProveedorKeyTyped
        // TODO add your handling code here:
        even.Caracteres(evt);
    }//GEN-LAST:event_txtRazonSocialProveedorKeyTyped

    private void txtRazonSocialProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRazonSocialProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazonSocialProveedorActionPerformed

    private void txtTelefonoProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoProveedorKeyTyped
        // TODO add your handling code here:
        even.Numeros(evt);
    }//GEN-LAST:event_txtTelefonoProveedorKeyTyped

    private void txtNombreProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProveedorKeyTyped
        // TODO add your handling code here:
        even.Caracteres(evt);
    }//GEN-LAST:event_txtNombreProveedorKeyTyped

    private void txtDNIProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNIProveedorKeyTyped
        // TODO add your handling code here:
        even.Numeros(evt);
    }//GEN-LAST:event_txtDNIProveedorKeyTyped

    private void btnExcelClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelClientesActionPerformed
        // TODO add your handling code here:
        ExcelClientes.reporte();
    }//GEN-LAST:event_btnExcelClientesActionPerformed

    private void btnNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClienteActionPerformed
        // TODO add your handling code here:
        LimpiarCliente();
    }//GEN-LAST:event_btnNuevoClienteActionPerformed

    private void btnActualizarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarClienteActionPerformed
        // TODO add your handling code here:
        if("".equals(txtIDCliente.getText())){
            JOptionPane.showMessageDialog(null, "SELECCIONE UN CLIENTE DE LA TABLA PARA SER ACTUALIZADO");
        }else{

            if(!"".equals(txtDNICliente.getText()) || !"".equals(txtNombreCliente.getText()) || !"".equals(txtTelefonoCliente.getText()) || !"".equals(txtDireccionCliente.getText()) || !"".equals(txtRazonSocialCliente.getText())){
                cl.setDni(Integer.parseInt(txtDNICliente.getText()));
                cl.setNombre(txtNombreCliente.getText());
                cl.setTelefono(txtTelefonoCliente.getText());
                cl.setDireccion(txtDireccionCliente.getText());
                cl.setRazon(txtRazonSocialCliente.getText());
                cl.setId(Integer.parseInt(txtIDCliente.getText()));

                cli.ActualizarCliente(cl);
                LimpiarTabla();
                LimpiarCliente();
                ListarCliente();

                JOptionPane.showMessageDialog(null, "CLIENTE ACTUALIZADO CORRECTAMENTE");
            }else{
                JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON REQUERIDOS");
            }
        }
    }//GEN-LAST:event_btnActualizarClienteActionPerformed

    private void btnGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarClienteActionPerformed
        // TODO add your handling code here:
        if(!"".equals(txtDNICliente.getText()) || !"".equals(txtNombreCliente.getText()) || !"".equals(txtTelefonoCliente.getText()) || !"".equals(txtDireccionCliente.getText()) || !"".equals(txtRazonSocialCliente.getText())){
            cl.setDni(Integer.parseInt(txtDNICliente.getText()));
            cl.setNombre(txtNombreCliente.getText());
            cl.setTelefono(txtTelefonoCliente.getText());
            cl.setDireccion(txtDireccionCliente.getText());
            cl.setRazon(txtRazonSocialCliente.getText());
            cli.registrarCliente(cl);
            ListarCliente();
            LimpiarCliente();
            //LimpiarTabla();
            JOptionPane.showMessageDialog(null, "CLIENTE REGISTRADO EXITOSAMENTE");

        }else{
            JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON REQUERIDOS");
        }
    }//GEN-LAST:event_btnGuardarClienteActionPerformed

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        // TODO add your handling code here:
        if ("".equals((txtIDCliente.getText()))) {
            JOptionPane.showMessageDialog(null, "SELECCIONE UN CLIENTE DE LA TABLA PARA SER ELIMINADO");
        } else {
            if (!"".equals(txtIDCliente.getText())) {
                int pregunta = JOptionPane.showConfirmDialog(null, "ESTAS SEGURO DE ELIMINAR ESTE CLIENTE?");
                if (pregunta == 0) {
                    int id = Integer.parseInt(txtIDCliente.getText());
                    cli.EliminarCliente(id);
                    LimpiarCliente();
                    LimpiarTabla();
                    ListarCliente();

                    JOptionPane.showMessageDialog(null, "CLIENTE ELIMINADO CORRECTAMENTE");
                }
            }
        }
    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void TableClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableClienteMouseClicked
        // TODO add your handling code here:
        int fila = TableCliente.rowAtPoint(evt.getPoint());
        txtIDCliente.setText(TableCliente.getValueAt(fila,0).toString());
        txtDNICliente.setText(TableCliente.getValueAt(fila,1).toString());
        txtNombreCliente.setText(TableCliente.getValueAt(fila,2).toString());
        txtTelefonoCliente.setText(TableCliente.getValueAt(fila,3).toString());
        txtDireccionCliente.setText(TableCliente.getValueAt(fila,4).toString());
        txtRazonSocialCliente.setText(TableCliente.getValueAt(fila,5).toString());
        //txtDNICliente.setText(TableCliente.getValueAt(fila,0).toString());
    }//GEN-LAST:event_TableClienteMouseClicked

    private void txtDNIClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNIClienteKeyTyped
        // TODO add your handling code here:
        even.Numeros(evt);
    }//GEN-LAST:event_txtDNIClienteKeyTyped

    private void txtRazonSocialClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonSocialClienteKeyTyped
        // TODO add your handling code here:
        even.Caracteres(evt);
    }//GEN-LAST:event_txtRazonSocialClienteKeyTyped

    private void txtTelefonoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoClienteKeyTyped
        // TODO add your handling code here:
        even.Numeros(evt);
    }//GEN-LAST:event_txtTelefonoClienteKeyTyped

    private void txtNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyTyped
        // TODO add your handling code here:
        even.Caracteres(evt);
    }//GEN-LAST:event_txtNombreClienteKeyTyped

    private void btnTortasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTortasActionPerformed
        // TODO add your handling code here:
        String fechaReporte = new SimpleDateFormat("dd/MM/yyyy").format(Midate.getDate());
        Grafico.Graficar(fechaReporte);
    }//GEN-LAST:event_btnTortasActionPerformed

    private void btnGenerarVenta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarVenta2ActionPerformed
        // TODO add your handling code here:
        if (TableVenta.getRowCount() > 0) {
            if (!"".equals(txtRucVenta.getText()) || !"".equals(txtNombeClienteVenta.getText())) {
                RegistrarVenta();
                RegistrarDetalle();
                ActualizarBodega();
                PDF();
                limpiarTableVenta();
                LimpiarVenta();
                txtRucVenta.setText("");
                txtNombeClienteVenta.setText("");
                txtDireccionClienteVenta.setText("");
                txtTelefonoClienteVenta.setText("");
                LabelTotal.setText("......");
            }else{
                JOptionPane.showMessageDialog(null, "PARA GENERAR LA VENTA DEBES INGRESAR UN CLIENTE");
            }
        }else{
            JOptionPane.showMessageDialog(null, "NO HAY PRODUCTOS PARA GENERAR LA VENTA");
        }
    }//GEN-LAST:event_btnGenerarVenta2ActionPerformed

    private void txtRucVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucVentaKeyTyped
        // TODO add your handling code here:
        even.Numeros(evt);
    }//GEN-LAST:event_txtRucVentaKeyTyped

    private void txtRucVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucVentaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER ){
            if(!"".equals(txtRucVenta.getText())){
                int dni = Integer.parseInt(txtRucVenta.getText());
                cl = cli.BuscarCliente(dni);
                if(cl.getNombre() != null){
                    txtNombeClienteVenta.setText(""+cl.getNombre());
                    txtTelefonoClienteVenta.setText(""+cl.getTelefono());
                    txtDireccionClienteVenta.setText(""+cl.getDireccion());
                    txtRazonClienteVenta.setText(""+cl.getRazon());
                }else{
                    txtRucVenta.setText("");
                    txtNombeClienteVenta.setText("");
                    JOptionPane.showMessageDialog(null, "EL CLIENTE NO EXISTE");
                }
            }
        }
    }//GEN-LAST:event_txtRucVentaKeyPressed

    private void btnEliminarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarVentaActionPerformed
        // TODO add your handling code here:

        if (TableVenta.getRowCount() > 0){
            modelo = (DefaultTableModel) TableVenta.getModel();
            modelo.removeRow(TableVenta.getSelectedRow());
            TotalPagar();
            txtCodigoVenta.requestFocus();
        }else{
            JOptionPane.showMessageDialog(null, "NO HAY PRODUCTOS REGISTRADOS PARA SER ELIMINADOS");
        }

    }//GEN-LAST:event_btnEliminarVentaActionPerformed

    private void txtCantidadVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVentaKeyTyped
        // TODO add your handling code here:
        even.Numeros(evt);
    }//GEN-LAST:event_txtCantidadVentaKeyTyped

    private void txtCantidadVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVentaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(!"".equals(txtCantidadVenta.getText())){
                String cod = txtCodigoVenta.getText();
                String descripcion = txtDescripcionVenta.getText();
                int cant = Integer.parseInt(txtCantidadVenta.getText());
                double precio = Double.parseDouble(txtPrecioVenta.getText());
                double total = (cant * precio);
                int cantidad = Integer.parseInt(txtStockDisponible.getText());

                if(cantidad >= cant){
                    item = item + 1;
                    tmp = (DefaultTableModel) TableVenta.getModel();

                    for (int i = 0; i < TableVenta.getRowCount(); i++) {
                        if(TableVenta.getValueAt(i,1).equals(txtDescripcionVenta.getText())){
                            JOptionPane.showMessageDialog(null, "EL PRODUCTO YA ESTA REGISTRADO");
                            return;
                        }

                    }
                    ArrayList lista = new ArrayList();
                    lista.add(item);
                    lista.add(cod);
                    lista.add(descripcion);
                    lista.add(cant);
                    lista.add(precio);
                    lista.add(total);
                    //lista.add((String.format("%,.3f", total)));
                    Object[] O = new Object[5];
                    O[0] = lista.get(1);
                    O[1] = lista.get(2);
                    O[2] = lista.get(3);
                    O[3] = String.format("%,.0f", lista.get(4));
                    O[4] = lista.get(5);
                    //O[4] = String.format("%,.0f",lista.get(5));
                    tmp.addRow(O);
                    TableVenta.setModel(tmp);
                    TotalPagar();
                    LimpiarVenta();
                    txtCodigoVenta.requestFocus();
                }else{
                    JOptionPane.showMessageDialog(null, "CANTIDAD NO DISPONIBLE");
                }
            }else{
                JOptionPane.showMessageDialog(null, "INGRESE LA CANTIDAD");
            }
        }
    }//GEN-LAST:event_txtCantidadVentaKeyPressed

    private void txtDescripcionVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionVentaActionPerformed

    private void txtCodigoVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoVentaKeyTyped
        // TODO add your handling code here:
        even.Numeros(evt);
    }//GEN-LAST:event_txtCodigoVentaKeyTyped

    private void txtCodigoVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoVentaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(!"".equals(txtCodigoVenta.getText())){
                String cod = txtCodigoVenta.getText();
                pro = proDAO.BuscarPro(cod);
                if(pro.getDescripcion() != null){
                    txtDescripcionVenta.setText(""+pro.getDescripcion());
                    txtPrecioVenta.setText(""+pro.getPrecio());
                    txtStockDisponible.setText(""+pro.getCantidad());
                    txtCantidadVenta.requestFocus();
                }else{
                    LimpiarVenta();
                    txtCodigoVenta.requestFocus();
                }
            }else{
                JOptionPane.showMessageDialog(null, "INGRESE EL CODIGO DEL PRODUCTO A BUSCAR");
                txtCodigoVenta.requestFocus();
            }
        }
    }//GEN-LAST:event_txtCodigoVentaKeyPressed

    private void txtCodigoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoVentaActionPerformed

    private void btnPDFProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPDFProductoActionPerformed

    private void btnRegistrar_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrar_userActionPerformed
        // TODO add your handling code here:
        Registro reg = new Registro();
        reg.setVisible(true);
    }//GEN-LAST:event_btnRegistrar_userActionPerformed

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane LISTADO;
    private javax.swing.JLabel LabelTotal;
    private javax.swing.JLabel LabelVendedor;
    private com.toedter.calendar.JDateChooser Midate;
    private javax.swing.JTable TableCliente;
    private javax.swing.JTable TableProducto;
    private javax.swing.JTable TableProveedor;
    private javax.swing.JTable TableVenta;
    private javax.swing.JTable TableVentas;
    private javax.swing.JButton bntVentas;
    private javax.swing.JButton btnActualizarCliente;
    private javax.swing.JButton btnActualizarEmpresa;
    private javax.swing.JButton btnActualizarProducto;
    private javax.swing.JButton btnActualziarProveedor;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnEliminarProveedor;
    private javax.swing.JButton btnEliminarVenta;
    private javax.swing.JButton btnExcelClientes;
    private javax.swing.JButton btnExcelProducto;
    private javax.swing.JButton btnExcelProveedor1;
    private javax.swing.JButton btnExcelVentas;
    private javax.swing.JButton btnGenerarVenta2;
    private javax.swing.JButton btnGuardarCliente;
    private javax.swing.JButton btnGuardarProducto;
    private javax.swing.JButton btnGuardarProveedor;
    private javax.swing.JButton btnNuevaVenta;
    private javax.swing.JButton btnNuevoCliente;
    private javax.swing.JButton btnNuevoProducto;
    private javax.swing.JButton btnNuevoProveedor;
    private javax.swing.JButton btnPDFProducto;
    private javax.swing.JButton btnPDFVentas;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProveedor;
    private javax.swing.JButton btnRegistrar_user;
    private javax.swing.JButton btnTortas;
    private javax.swing.JComboBox cbxProveedoProducto;
    private javax.swing.JPanel clientes;
    private javax.swing.JPanel configuracion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel nuevaVenta;
    private javax.swing.JPanel producto;
    private javax.swing.JPanel proveedor;
    private javax.swing.JTextField txtCantidadProducto;
    private javax.swing.JTextField txtCantidadVenta;
    private javax.swing.JTextField txtCodigoProducto;
    private javax.swing.JTextField txtCodigoVenta;
    private javax.swing.JTextField txtDNICliente;
    private javax.swing.JTextField txtDNIProveedor;
    private javax.swing.JTextField txtDescripcionProducto;
    private javax.swing.JTextField txtDescripcionVenta;
    private javax.swing.JTextField txtDireccionCliente;
    private javax.swing.JTextField txtDireccionClienteVenta;
    private javax.swing.JTextField txtDireccionEmpresa;
    private javax.swing.JTextField txtDireccionProveedor;
    private javax.swing.JTextField txtIDCliente;
    private javax.swing.JTextField txtIDNuevaPro;
    private javax.swing.JTextField txtIDProducto;
    private javax.swing.JTextField txtIDProveedor;
    private javax.swing.JTextField txtIDVentas;
    private javax.swing.JTextField txtIdConfi;
    private javax.swing.JTextField txtIdEmpresa;
    private javax.swing.JTextField txtNombeClienteVenta;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreEmpresa;
    private javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtPrecioProducto;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtRazonClienteVenta;
    private javax.swing.JTextField txtRazonSocialCliente;
    private javax.swing.JTextField txtRazonSocialProveedor;
    private javax.swing.JTextField txtRazonsocialEmpresa;
    private javax.swing.JTextField txtRucVenta;
    private javax.swing.JTextField txtStockDisponible;
    private javax.swing.JTextField txtTelefonoCliente;
    private javax.swing.JTextField txtTelefonoClienteVenta;
    private javax.swing.JTextField txtTelefonoEmpresa;
    private javax.swing.JTextField txtTelefonoProveedor;
    private javax.swing.JPanel ventasRegistradas;
    // End of variables declaration//GEN-END:variables

    private void LimpiarCliente(){
        txtIDCliente.setText("");
        txtDNICliente.setText("");
        txtNombreCliente.setText("");
        txtTelefonoCliente.setText("");
        txtDireccionCliente.setText("");
        txtRazonSocialCliente.setText("");
    }
    
    private void LimpiarProveedor(){
        txtIDProveedor.setText("");
        txtDNIProveedor.setText("");
        txtNombreProveedor.setText("");
        txtTelefonoProveedor.setText("");
        txtDireccionProveedor.setText("");
        txtRazonSocialProveedor.setText("");
        
    }
    
     private void LimpiarProducto(){
        txtIDProducto.setText("");
        txtCodigoProducto.setText("");
        txtDescripcionProducto.setText("");
        txtCantidadProducto.setText("");
        txtPrecioProducto.setText("");
        cbxProveedoProducto.setSelectedItem("");
        
    }
     
    private void TotalPagar(){
        TotalPagar = 0.00;
        int numFila = TableVenta.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double cal = Double.parseDouble(String.valueOf(TableVenta.getModel().getValueAt(i, 4)));
            TotalPagar = TotalPagar + cal;
        }
        LabelTotal.setText(String.format("%,.0f", TotalPagar));
        //LabelTotal.setText(TotalPagar);
    }
    
     private void LimpiarVenta(){
        txtCodigoVenta.setText("");
        txtDescripcionVenta.setText("");
        txtCantidadVenta.setText("");
        txtPrecioVenta.setText("");
        txtStockDisponible.setText("");
        txtIDVentas.setText("");
        //cbxProveedoProducto.setSelectedItem("");  
    }
     
     private void RegistrarVenta(){
         String cliente = txtNombeClienteVenta.getText();
         String vendedor = LabelVendedor.getText();
         double monto = TotalPagar;
         v.setCliente(cliente);
         v.setVendedor(vendedor);
         v.setTotal(monto);
         v.setFecha(fechaActual);
         ven.RegistrarVenta(v);
         JOptionPane.showMessageDialog(null, "VENTA REALIZADA EXITOSAMENTE");
     }
     
 
     
     private void RegistrarDetalle(){
         int id = ven.idVenta();
         for (int i = 0; i < TableVenta.getRowCount(); i++) {
             String cod = TableVenta.getValueAt(i, 0).toString();
             int cant = Integer.parseInt(TableVenta.getValueAt(i, 2).toString());
             double precio = Double.parseDouble(TableVenta.getValueAt(i, 3).toString());
             de.setCod_pro(cod);
             de.setCantidad(cant);
             de.setPrecio(precio);
             de.setId(id);
             ven.RegistrarDetalle(de);
             
             //int total = Integer.parseInt(TableVenta.getValueAt(i, 4).toString());
         }
     }
     
     private void ActualizarBodega(){
         for (int i = 0; i < TableVenta.getRowCount(); i++) {
             String cod = TableVenta.getValueAt(i, 0).toString();
             int cant = Integer.parseInt(TableVenta.getValueAt(i, 2).toString());
             pro = proDAO.BuscarPro(cod);
             //pro = proDAO.BuscarPro(cod);
             int CantidadActual = pro.getCantidad() - cant;
             ven.ActualizarBodega(CantidadActual, cod);
         }
     }
     
     private void limpiarTableVenta(){
         tmp = (DefaultTableModel) TableVenta.getModel();
         int fila =  TableVenta.getRowCount();
         for (int i = 0; i < fila; i++) {
             tmp.removeRow(0);
         }
     }
     
     private void PDF(){
        try {
            FileOutputStream archivo;
            File file = new File("src/PDF/venta.pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            Image img = Image.getInstance("src/img/logo_pdf.png");

            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
            fecha.add(Chunk.NEWLINE);
            Date date = new Date();
            fecha.add("Factura: 1\n" + "Fecha: " + new SimpleDateFormat("dd/MM/yyyy").format(date) + "\n\n");

            PdfPTable encabezado = new PdfPTable(4);
            encabezado.setWidthPercentage(100);
            encabezado.getDefaultCell().setBorder(0);
            float[] columnaEncabezado = new float[]{20f, 40f, 70f, 40f};
            encabezado.setWidths(columnaEncabezado);
            encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

            encabezado.addCell(img);
            String ruc = txtIdEmpresa.getText();
            String nombre = txtNombreEmpresa.getText();
            String tel = txtTelefonoEmpresa.getText();
            String dir = txtDireccionEmpresa.getText();
            String razon = txtRazonsocialEmpresa.getText();

            encabezado.addCell("");
            encabezado.addCell("Ruc: " + ruc + "\nNombre: " + nombre + "\nTelefono: " + tel  + "\nDireccion: " + dir + "\nRazon: " + razon);
            encabezado.addCell(fecha);
            doc.add(encabezado);

            Paragraph cli = new Paragraph();
            cli.add(Chunk.NEWLINE);
            cli.add("DATOS DE LOS CLIENTES"+ "\n\n");
            doc.add(cli);

            //TABLA DE LOS CLIENTES
            PdfPTable tablacli = new PdfPTable(4);
            tablacli.setWidthPercentage(100);
            tablacli.getDefaultCell().setBorder(0);
            float[] columnaCli = new float[]{20f, 50f, 30f, 40f};
            tablacli.setWidths(columnaCli);

            tablacli.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cliente1 = new PdfPCell(new Phrase("CEDULA", negrita));
            PdfPCell cliente2 = new PdfPCell(new Phrase("NOMBRE", negrita));
            PdfPCell cliente3 = new PdfPCell(new Phrase("TELEFONO", negrita));
            PdfPCell cliente4 = new PdfPCell(new Phrase("DIRECCION", negrita));

            cliente1.setBorder(0);
            cliente2.setBorder(0);
            cliente3.setBorder(0);
            cliente4.setBorder(0);
            
            cliente1.setBackgroundColor(BaseColor.YELLOW);
            cliente2.setBackgroundColor(BaseColor.YELLOW);
            cliente3.setBackgroundColor(BaseColor.YELLOW);
            cliente4.setBackgroundColor(BaseColor.YELLOW);
            
            

            tablacli.addCell(cliente1);
            tablacli.addCell(cliente2);
            tablacli.addCell(cliente3);
            tablacli.addCell(cliente4);
            
            

            tablacli.addCell(txtRucVenta.getText());
            tablacli.addCell(txtNombeClienteVenta.getText());
            tablacli.addCell(txtTelefonoClienteVenta.getText());
            tablacli.addCell(txtDireccionClienteVenta.getText());
            tablacli.addCell(txtRazonClienteVenta.getText());

            doc.add(tablacli);

            //TABLA DE LOS PRODUCTOS
            PdfPTable tablapro = new PdfPTable(4);
            tablapro.setWidthPercentage(100);
            tablapro.getDefaultCell().setBorder(0);
            float[] columnaPro = new float[]{20f, 50f, 30f, 40f};
            tablapro.setWidths(columnaPro);

            tablapro.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell producto1 = new PdfPCell(new Phrase("NOMBRE", negrita));
            PdfPCell producto2 = new PdfPCell(new Phrase("CANTIDAD", negrita));
            PdfPCell producto3 = new PdfPCell(new Phrase("PRECIO C/U", negrita));
            PdfPCell producto4 = new PdfPCell(new Phrase("TOTAL", negrita));

            producto1.setBorder(0);
            producto2.setBorder(0);
            producto3.setBorder(0);
            producto4.setBorder(0);
            
            producto1.setBackgroundColor(BaseColor.YELLOW);
            producto2.setBackgroundColor(BaseColor.YELLOW);
            producto3.setBackgroundColor(BaseColor.YELLOW);
            producto4.setBackgroundColor(BaseColor.YELLOW);

            tablapro.addCell(producto1);
            tablapro.addCell(producto2);
            tablapro.addCell(producto3);
            tablapro.addCell(producto4);
            

            for (int i = 0; i < TableVenta.getRowCount(); i++) {
                String producto = TableVenta.getValueAt(i, 1).toString();
                String cantidad = TableVenta.getValueAt(i, 2).toString();
                String precio = TableVenta.getValueAt(i, 3).toString();
                String total = TableVenta.getValueAt(i, 4).toString();
                //String cantidad = TableVenta.getValueAt(i, 2).toString();
                tablapro.addCell(producto);
                tablapro.addCell(cantidad);
                tablapro.addCell(precio);
                tablapro.addCell(total);
                //tablapro.addCell(txtRazonClienteVenta.getText());
            }
            
            doc.add(tablapro);
            
            Paragraph info  = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("TOTAL A PAGAR:\n" + TotalPagar);
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);
            
            Paragraph firma  = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add("CANCELACION Y FIRMA\n\n");
            firma.add("________________________");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);
            
            Paragraph mensaje  = new Paragraph();
            mensaje.add(Chunk.NEWLINE);
            mensaje.add("GRACIAS POR SU COMPRA");
            //mensaje.add("________________________");
            mensaje.setAlignment(Element.ALIGN_CENTER);
            doc.add(mensaje);

            doc.close();
            archivo.close();
            
            Desktop.getDesktop().open(file);
        } catch (DocumentException | IOException e) {
            System.out.println(e.toString());
        }
    }
}
