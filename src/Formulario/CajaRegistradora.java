/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formulario;

import static Formulario.IngresoSistema.passw;
import static Formulario.IngresoSistema.usuario;
import claseConectar.conectar;
import com.sun.glass.events.KeyEvent;
import java.awt.HeadlessException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Luis Ramos
 */
public final class CajaRegistradora extends javax.swing.JInternalFrame {

    /**
     * Creates new form prueba
     */
    public static DefaultTableModel tabla;
    public CajaRegistradora() {
        initComponents();
        sesion();
    }
    public static boolean TipoPrecio = true;
    public boolean cuadro=true;
    public double subtotal=0;
    public int contador=0;
    public static int contTipoPrecio=0;
    public String[]  Tventa= new String [100]; 
    public double vuelto=0;
    ConsultasEnEspera espera = new ConsultasEnEspera();
    private void bloquear(){
        txtFecha.setEnabled(false);
        txtFecha.setText(fechaact());
        txtTicket.setEnabled(false);
        txtVen.setEnabled(false);
        txtVen.setText(Consultavendedor("nom_ven"));
        numeros();
        txtClient.setEnabled(false);
        txtProd.setEnabled(false);
        btnAgregar.setEnabled(false);
        btnCobrar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnEspera.setEnabled(false);
        rbtnPorMenor.setEnabled(false);
        rbtnPorMayor.setEnabled(false);
        txtSubTotal.setEnabled(false);
        txtTotal.setEnabled(false);
        txtVuelto.setEnabled(false); 
        txtEfect.setEnabled(false); 
        txtDesc.setEnabled(false);
        tbProd.setEnabled(false); 
        btnAbrirCaja.setEnabled(true); 
        
        btnCerrarCaja.setEnabled(false); 
        //btnFEfectivo.setEnabled(false); 
       // btnFProducto.setEnabled(false); 
      //  btnLVentas.setEnabled(false); 
    }
        private void Desbloquear(){
        txtFecha.setEnabled(true);
        txtFecha.setText(fechaact());
        txtTicket.setEnabled(true);
        txtVen.setEnabled(true);
        txtVen.setText(Consultavendedor("nom_ven"));
        numeros();
        txtClient.setEnabled(true);
        txtProd.setEnabled(true);
        btnAgregar.setEnabled(true);
        btnCobrar.setEnabled(true);
        btnEliminar.setEnabled(true);;
        btnEspera.setEnabled(true);;
        rbtnPorMenor.setEnabled(true);
        rbtnPorMayor.setEnabled(true);
        txtSubTotal.setEnabled(true);
        txtTotal.setEnabled(true);
        txtVuelto.setEnabled(true);
        txtEfect.setEnabled(true); 
        txtDesc.setEnabled(true);
        tbProd.setEnabled(true); 
        
        btnAbrirCaja.setEnabled(false); 
        btnCerrarCaja.setEnabled(true); 
    }
    private void IniciarCaja(){
        Desbloquear();
        txtFecha.setEditable(false);
        txtFecha.setText(fechaact());
        txtTicket.setEditable(false);
        txtVen.setEditable(false);
        txtVen.setText(Consultavendedor("nom_ven"));
        numeros();
        rbtnPorMenor.setSelected(true);
        cargarlistaproductosMenor("");
        txtSubTotal.setEditable(false);
        txtTotal.setEditable(false);
        txtVuelto.setEditable(false);
        txtProd.requestFocus(); 
    }
    private void sesion(){
        String sql3="select * from sesion";
        String cod2="";
        try{
            Statement st2= cn.createStatement();
            ResultSet rs = st2.executeQuery(sql3);
            while(rs.next())
            {              
                cod2=rs.getString(1); 
            }        
        }catch(SQLException ex){ 
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } 
       // System.out.println("Formulario.CajaRegistradora.sesion() "+cod2);
        if(cod2.equals("CERRADO")){
              bloquear();
        }else{
            if(cod2.equals("ABIERTO")){
                IniciarCaja();
            }
        }
       
    }        
    private void completar(String producto){
        String sql3="select * from producto WHERE descripcion like '%"+producto+"%'";
        String cod2="";
        int cont=0;
        try{
            Statement st2= cn.createStatement();
            ResultSet rs = st2.executeQuery(sql3);
            while(rs.next())
            {              
                cod2=rs.getString(2); 
                cont=cont+1;
             
            }        
        }catch(SQLException ex){          
        }  
        if(cont==1){
            txtProd.setText(cod2+" ");
            txtProd.requestFocus();
        }
       
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jpEnviar = new javax.swing.JPopupMenu();
        mnElegir = new javax.swing.JMenuItem();
        jpeliminar = new javax.swing.JPopupMenu();
        mnEliminar = new javax.swing.JMenuItem();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnCerrarCaja = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btnFProducto = new javax.swing.JButton();
        btnLVentas = new javax.swing.JButton();
        btnFEfectivo = new javax.swing.JButton();
        btnAbrirCaja = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProd = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        rbtnPorMenor = new javax.swing.JRadioButton();
        rbtnPorMayor = new javax.swing.JRadioButton();
        txtVen = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtProd = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtClient = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbRegistro = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnEspera = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        txtDesc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtEfect = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtVuelto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnCobrar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtTicket = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        jFormattedTextField1.setText("jFormattedTextField1");

        mnElegir.setText("Elegir");
        mnElegir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnElegirActionPerformed(evt);
            }
        });
        jpEnviar.add(mnElegir);

        mnEliminar.setText("Eliminar");
        mnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnEliminarActionPerformed(evt);
            }
        });
        jpeliminar.add(mnEliminar);

        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Caja Registradora");
        setToolTipText("");
        setMaximumSize(new java.awt.Dimension(1035, 870));
        setMinimumSize(new java.awt.Dimension(832, 655));
        setPreferredSize(new java.awt.Dimension(832, 655));

        jPanel7.setBackground(new java.awt.Color(0, 204, 204));

        jPanel5.setBackground(new java.awt.Color(0, 102, 102));

        btnCerrarCaja.setBackground(new java.awt.Color(51, 204, 255));
        btnCerrarCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrarCaja.png"))); // NOI18N
        btnCerrarCaja.setText("Cerrar Caja");
        btnCerrarCaja.setHideActionText(true);
        btnCerrarCaja.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCerrarCaja.setInheritsPopupMenu(true);
        btnCerrarCaja.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCerrarCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarCajaActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(0, 102, 102));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)), "INFORMES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel6.setToolTipText("");

        btnFProducto.setBackground(new java.awt.Color(51, 204, 255));
        btnFProducto.setText("F. Producto");
        btnFProducto.setFocusable(false);

        btnLVentas.setBackground(new java.awt.Color(51, 204, 255));
        btnLVentas.setText("L. Ventas");
        btnLVentas.setFocusable(false);

        btnFEfectivo.setBackground(new java.awt.Color(51, 204, 255));
        btnFEfectivo.setText("F. Efectivo");
        btnFEfectivo.setFocusable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnFProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addComponent(btnLVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnAbrirCaja.setBackground(new java.awt.Color(51, 204, 255));
        btnAbrirCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/caja-registradora.png"))); // NOI18N
        btnAbrirCaja.setText("Abrir Caja");
        btnAbrirCaja.setToolTipText("");
        btnAbrirCaja.setAlignmentX(2.0F);
        btnAbrirCaja.setAlignmentY(1.0F);
        btnAbrirCaja.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAbrirCaja.setIconTextGap(3);
        btnAbrirCaja.setMaximumSize(new java.awt.Dimension(89, 59));
        btnAbrirCaja.setMinimumSize(new java.awt.Dimension(89, 59));
        btnAbrirCaja.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAbrirCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirCajaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnCerrarCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnAbrirCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(111, 111, 111)
                    .addComponent(btnCerrarCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(443, Short.MAX_VALUE)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(btnAbrirCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(531, Short.MAX_VALUE)))
        );

        jPanel1.setBackground(new java.awt.Color(227, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos Venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Yu Gothic UI Light", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        tbProd.setBackground(new java.awt.Color(102, 102, 255));
        tbProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbProd.setComponentPopupMenu(jpEnviar);
        jScrollPane1.setViewportView(tbProd);

        btnAgregar.setBackground(new java.awt.Color(51, 204, 255));
        btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/agregar.jpg"))); // NOI18N
        btnAgregar.setText("Agregar cliente");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        rbtnPorMenor.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        rbtnPorMenor.setForeground(new java.awt.Color(255, 255, 255));
        rbtnPorMenor.setSelected(true);
        rbtnPorMenor.setText("Por Menor");
        rbtnPorMenor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnPorMenorActionPerformed(evt);
            }
        });

        rbtnPorMayor.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        rbtnPorMayor.setForeground(new java.awt.Color(255, 255, 255));
        rbtnPorMayor.setText("Por Mayor");
        rbtnPorMayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnPorMayorActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Bodoni MT", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Vendedor");

        txtProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtProdMouseClicked(evt);
            }
        });
        txtProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProdActionPerformed(evt);
            }
        });
        txtProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProdKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Bodoni MT", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Producto");

        jLabel3.setFont(new java.awt.Font("Bodoni MT", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cliente");

        txtClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtClientMouseClicked(evt);
            }
        });
        txtClient.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClientKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtVen)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProd, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtnPorMenor)
                            .addComponent(rbtnPorMayor))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtClient, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rbtnPorMenor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbtnPorMayor))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 153, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbRegistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descripción", "Talla", "Color", "Precio unit", "Cantidad", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbRegistro.setComponentPopupMenu(jpeliminar);
        jScrollPane2.setViewportView(tbRegistro);
        if (tbRegistro.getColumnModel().getColumnCount() > 0) {
            tbRegistro.getColumnModel().getColumn(0).setMaxWidth(80);
            tbRegistro.getColumnModel().getColumn(2).setMaxWidth(40);
            tbRegistro.getColumnModel().getColumn(3).setMaxWidth(70);
            tbRegistro.getColumnModel().getColumn(4).setMaxWidth(70);
            tbRegistro.getColumnModel().getColumn(5).setMaxWidth(60);
        }

        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/16 (Delete).jpg"))); // NOI18N
        btnEliminar.setText("Cancelar");
        btnEliminar.setMaximumSize(new java.awt.Dimension(119, 31));
        btnEliminar.setMinimumSize(new java.awt.Dimension(119, 31));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnEspera.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEspera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/espera.jpg"))); // NOI18N
        btnEspera.setText("En espera");
        btnEspera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEsperaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Subtotal");

        txtSubTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtSubTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtDesc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtDesc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDesc.setText("0.00");
        txtDesc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Descuento");

        jLabel6.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Total");

        txtTotal.setBackground(new java.awt.Color(62, 10, 10));
        txtTotal.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(255, 255, 0));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtEfect.setBackground(new java.awt.Color(62, 10, 10));
        txtEfect.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        txtEfect.setForeground(new java.awt.Color(255, 255, 0));
        txtEfect.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEfect.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEfectKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Efectivo");

        txtVuelto.setBackground(new java.awt.Color(62, 10, 10));
        txtVuelto.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        txtVuelto.setForeground(new java.awt.Color(255, 255, 0));
        txtVuelto.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Vuelto");

        btnCobrar.setBackground(new java.awt.Color(51, 51, 255));
        btnCobrar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnCobrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/efectivoNormal.png"))); // NOI18N
        btnCobrar.setText("Cobrar");
        btnCobrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCobrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/efectivoSelected.png"))); // NOI18N
        btnCobrar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/efectivoPressed.png"))); // NOI18N
        btnCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrarActionPerformed(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(51, 51, 0));
        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 0, 102));
        jLabel11.setText("Detalle Productos");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(btnEspera)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(8, 8, 8)
                                        .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnCobrar, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtEfect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEfect, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEspera, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(7, 7, 7))
        );

        jPanel4.setBackground(new java.awt.Color(227, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 102, 102), null, null));
        jPanel4.setToolTipText("");

        txtTicket.setEditable(false);
        txtTicket.setBackground(new java.awt.Color(255, 153, 51));
        txtTicket.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTicketActionPerformed(evt);
            }
        });

        txtFecha.setEditable(false);
        txtFecha.setBackground(new java.awt.Color(255, 153, 51));
        txtFecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(255, 0, 0));

        jLabel9.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N
        jLabel9.setText("Ticket N°");

        jLabel10.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N
        jLabel10.setText("Fecha");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTicket, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addComponent(txtFecha))
                .addGap(79, 79, 79))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTicket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jLabel12.setFont(new java.awt.Font("Harrington", 1, 36)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("      G&H Company");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 668, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
public static void cargarlistaproductosMenor(String dato){
        String [] Titulo = {"Codigo","Descripcion","Talla","Color","Stock","Pre.Menor S/."};
        tabla=new DefaultTableModel(null,Titulo)
        {
              @Override
                public boolean isCellEditable(int row, int col)
                {
                    return false;
                }
        };
    String []Registro= new String[6];
    String mostrar0="select producto.cod_pro, producto.descripcion, talla.des_talla,color.des_color,talla_producto.Stock,talla_producto.Precio_talla";
    String mostrar1="from talla,producto,talla_producto,color";
    String mostrar2="WHERE( talla_producto.cod_talla=talla.cod_talla) and (talla_producto.cod_pro=producto.cod_pro) and ( talla_producto.cod_color=color.cod_color)";
    String mostrar3="and (CONCAT (producto.cod_pro,' ',producto.descripcion,' ', talla.des_talla,' ', color.des_color,' ',talla_producto.Stock,talla_producto.Precio_talla) LIKE"+"'%"+dato+"%')";
    String mostrar4="order by producto.descripcion ASC,talla_producto.Precio_talla  asc, color.des_color  ASC";
    String mostrar=mostrar0+" "+mostrar1+" "+mostrar2+" "+mostrar3+" "+mostrar4;

    Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs =st.executeQuery(mostrar);
            while(rs.next())
            {
                Registro[0]=rs.getString(1);
                Registro[1]=rs.getString(2);
                Registro[2]=rs.getString(3);
                Registro[3]=rs.getString(4);
                Registro[4]=rs.getString(5);
                Registro[5]=rs.getString(6);
                tabla.addRow(Registro);
                if(Registro[4].equals("0")){
                    JOptionPane.showMessageDialog(null, "No hay suficiente Stock de "+Registro[1]+"!", "ALERTA", 2);
                }
            }
            tbProd.setModel(tabla);
        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    
    }
public static void cargarlistaproductosMayor(String dato){
        String [] Titulo = {"Codigo","Descripcion","Talla","Color","Stock","Pre.Mayor S/."};
        tabla=new DefaultTableModel(null,Titulo)
        {
              @Override
                public boolean isCellEditable(int row, int col)
                {
                    return false;
                }
        };
    String []Registro= new String[6];
    String mostrar0="select producto.cod_pro, producto.descripcion, talla.des_talla,color.des_color,talla_producto.Stock,talla_producto.Precio_talla_mayor";
    String mostrar1="from talla,producto,talla_producto,color";
    String mostrar2="WHERE( talla_producto.cod_talla=talla.cod_talla) and (talla_producto.cod_pro=producto.cod_pro) and ( talla_producto.cod_color=color.cod_color)";
    String mostrar3="and (CONCAT (producto.cod_pro,' ',producto.descripcion,' ', talla.des_talla,' ', color.des_color,' ',talla_producto.Stock,talla_producto.Precio_talla_mayor) LIKE"+"'%"+dato+"%')";
    String mostrar4="order by producto.descripcion ASC,talla_producto.Precio_talla  asc, color.des_color  ASC";
    String mostrar=mostrar0+" "+mostrar1+" "+mostrar2+" "+mostrar3+" "+mostrar4;

    Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs =st.executeQuery(mostrar);
            while(rs.next())
            {
                Registro[0]=rs.getString(1);
                Registro[1]=rs.getString(2);
                Registro[2]=rs.getString(3);
                Registro[3]=rs.getString(4);
                Registro[4]=rs.getString(5);
                 Registro[5]=rs.getString(6);
                tabla.addRow(Registro);
                if(Registro[4].equals("0")){
                    JOptionPane.showMessageDialog(null, "No hay suficiente Stock de "+Registro[1]+"!", "ALERTA", 2);
                }
            }
            tbProd.setModel(tabla);
        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

void mostrarclientes(String valor)
    {
        String[]titulos={"Codigo","Nombres","Apellidos","Sexo","DNI","Telefono","RUC","Email","Direccion"} ;  
        String []Registros= new String[9];
        DefaultTableModel modelo=new DefaultTableModel(null,titulos)
        {
              @Override
                public boolean isCellEditable(int row, int col)
                {
                    return false;
                }
        };
        String Sql="SELECT * FROM cliente WHERE CONCAT(cod_cli,nom_cli,ape_cli,dni_cli) LIKE '%"+valor+"%'";
       
        try {
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(Sql);
             while(rs.next())
             {
                 Registros[0]=rs.getString("cod_cli");  
                 Registros[1]=rs.getString("nom_cli");  
                 Registros[2]=rs.getString("ape_cli");  
                 Registros[3]=rs.getString("sexo_cli");  
                 Registros[4]=rs.getString("dni_cli");  
                 Registros[5]=rs.getString("tel_cli");  
                 Registros[6]=rs.getString("ruc_cli");  
                 Registros[7]=rs.getString("email_cli");  
                 Registros[8]=rs.getString("dir_cli");  
                 modelo.addRow(Registros);
             } 
             tbProd.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        
    }
    
    
    
    
    
    
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        IngresoCliente nuevo = new IngresoCliente();
        Principal.jdpescritorio.add(nuevo);
        nuevo.toFront();
        nuevo.setLocation(660, 10);
        nuevo.setVisible(true);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void rbtnPorMenorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnPorMenorActionPerformed
        // TODO add your handling code here:
            if(rbtnPorMenor.isSelected()){
            TipoPrecio=true;
            rbtnPorMayor.setSelected(false);
            cargarlistaproductosMenor(""+txtProd.getText());
            //System.out.print(TipoPrecio);
        }
    }//GEN-LAST:event_rbtnPorMenorActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea eliminar lista?",
                    "Eliminar Lista Venta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
            DefaultTableModel modelo = (DefaultTableModel) tbRegistro.getModel();
            String cod_pro,des_talla,des_color,cant="0";        
            int a =tbRegistro.getRowCount()-1;
            if(ConsultasEnEspera.enespera){
                for(int i=0;i<tbRegistro.getRowCount();i++){
                    cod_pro=tbRegistro.getValueAt(i,0).toString();
                    des_talla=tbRegistro.getValueAt(i,2).toString();
                    des_color=tbRegistro.getValueAt(i,3).toString();
                    cant=tbRegistro.getValueAt(i,5).toString();
                    devolucion(cod_pro, des_talla, des_color, cant);
                }
                if(TipoPrecio){
                    cargarlistaproductosMenor("");
                }else{
                    cargarlistaproductosMayor("");
                }
                ConsultasEnEspera.enespera=false;
            }else{
                for(int i=0;i<tbRegistro.getRowCount();i++){
                    cod_pro=tbRegistro.getValueAt(i,0).toString();
                    des_talla=tbRegistro.getValueAt(i,2).toString();
                    des_color=tbRegistro.getValueAt(i,3).toString();
                    cant=tbRegistro.getValueAt(i,5).toString();
                    devolucion(cod_pro, des_talla, des_color, cant);
                }
                if(TipoPrecio){
                    cargarlistaproductosMenor("");
                }else{
                    cargarlistaproductosMayor("");
                }
            }
            int i;
            for(i=a;i>=0;i--)
            {
                modelo.removeRow(i);
                ConsultasEnEspera.tipoPrecio[i]="";
                ConsultasEnEspera.contador=ConsultasEnEspera.contador-1;
            }
            subtotal=0;
            vuelto=0;
            txtSubTotal.setText("");
            txtDesc.setText("0.00");
            txtTotal.setText("");
            txtEfect.setText("");
            txtVuelto.setText("0.0");
        }  
    }//GEN-LAST:event_btnEliminarActionPerformed
    private void devolucion(String codPro,String des_talla,String des_color,String cant){
        int suma=SumStock(codPro,des_talla,des_color)+Integer.parseInt(cant);
        String finalsuma=Integer.toString(suma);
            String sql2="select cod_talla from talla WHERE des_talla='"+des_talla+"'";
                    String cod="";
                    try{
                        Statement st3= cn.createStatement();
                        ResultSet rs1 = st3.executeQuery(sql2);
                    if(rs1.next())
                    {              
                        cod=rs1.getString("cod_talla");
                    }  
                    }catch(SQLException ex){
                    }  
                    sql2="select cod_color from color WHERE des_color='"+des_color+"'";
                    String codColor="";
                    try{
                        Statement st3= cn.createStatement();
                        ResultSet rs1 = st3.executeQuery(sql2);
                    if(rs1.next())
                    {              
                        codColor=rs1.getString("cod_color");
                    }  
                    }catch(SQLException ex){
                    }  
            try {           
                String sql3="update talla_producto set Stock='"+finalsuma+"' where cod_pro = '"+codPro+"' and cod_talla = '"+cod+"' and cod_color = '"+codColor+"'";
                PreparedStatement pst2 = cn.prepareStatement(sql3);
                pst2.executeUpdate();
                System.out.println("total= "+finalsuma);            
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }  
            sql2="select sum(CAST(Stock AS INT) ) from talla_producto\n" +
                "where cod_pro='"+codPro+"'";
            String sum="0";
            try{
                Statement st3= cn.createStatement();
                ResultSet rs1 = st3.executeQuery(sql2);
                if(rs1.next())
                    {              
                        sum=rs1.getString(1);
                    }  
            }catch(SQLException ex){
                 JOptionPane.showMessageDialog(null, ex.getMessage());
            } 
            try {           
                String sql3="update producto set Stock='"+sum+"' where cod_pro = '"+codPro+"'";
                PreparedStatement pst2 = cn.prepareStatement(sql3);
                pst2.executeUpdate();
                            
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }  
    }
    int SumStock(String codPro,String destalla,String descolor){
        String suma="0";
        String sqlsuma="select Stock from talla_producto a\n" +
        "inner join talla b on a.cod_talla=b.cod_talla\n" +
        "inner join color c on a.cod_color=c.cod_color\n" +
        "and a.cod_pro='"+codPro+"' and b.des_talla='"+destalla+"' and c.des_color='"+descolor+"'";
        try{
            Statement st3= cn.createStatement();
            ResultSet rs3 = st3.executeQuery(sqlsuma);
            if(rs3.next())
            {              
                suma=rs3.getString("Stock");
                    
            }
        }catch(NumberFormatException | SQLException e){
        }  
        
        return Integer.parseInt(suma);
    }      
    private void btnEsperaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEsperaActionPerformed
        // TODO add your handling code here:
        String codigo=Esperanumeros();
        if(!espera.isVisible()){
                    Principal.jdpescritorio.add(espera);
                    espera.toFront();
                    espera.setVisible(true);
                    //ConsultasEnEspera.calcular();
        }  
                //System.out.println("visible "+espera.isVisible());
        if((txtTotal.getText().equals(""))||(txtVuelto.getText().equals(""))){
        JOptionPane.showMessageDialog(this, "Ingrese producto o realice operacion");
        }
        else{
           /* String capcod,capcan,captalla;
            for(int i=0;i<tbRegistro.getRowCount();i++)
            {
                capcod=tbRegistro.getValueAt(i, 0).toString();
                capcan=tbRegistro.getValueAt(i, 5).toString();
                captalla=tbRegistro.getValueAt(i, 2).toString();
                descontarstock(capcod, captalla,tbRegistro.getValueAt(i, 3).toString(), capcan);

            }*/
            
                Esperaboleta(codigo);
                Esperadetalleboleta(codigo);
                
                //System.out.println("btnCobrarActionPerformed() = "+contTipoPrecio);
                txtEfect.setText("");
                txtClient.setText("");
                txtDesc.setText("0.00");
                txtProd.setText("");
                txtTotal.setText("");
                txtSubTotal.setText("");
                txtVuelto.setText("");
                
                DefaultTableModel modelo = (DefaultTableModel) tbRegistro.getModel();
                int a =tbRegistro.getRowCount()-1;
                int i;
                for(i=a;i>=0;i--)
                {  
                    modelo.removeRow(i);
                }
                //numeros();
                subtotal=0;
                contador=0;
                vuelto=0; 
                contTipoPrecio=0;
                
                
                /*if(!espera.isVisible()){
                    Principal.jdpescritorio.add(espera);
                    espera.toFront();
                    espera.setVisible(true);
                }  
                System.out.println("visible "+espera.isVisible());*/
        }

        if(TipoPrecio){
            cargarlistaproductosMenor("");
        }else{
            cargarlistaproductosMayor("");
        }
        espera.cargartodasBoletas();
        ConsultasEnEspera.calcular();
        ConsultasEnEspera.enespera=false;
        ConsultasEnEspera.contador=0;
    }//GEN-LAST:event_btnEsperaActionPerformed

    private void btnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrarActionPerformed
        // TODO add your handling code here:
      if((txtEfect.getText().equals(""))||(txtTotal.getText().equals(""))||(txtVuelto.getText().equals(""))){
        JOptionPane.showMessageDialog(this, "Ingrese producto o realice operacion");
        }
        else{
            /*if(!ConsultasEnEspera.enespera){
                String capcod,capcan,captalla;
                for(int i=0;i<tbRegistro.getRowCount();i++)
                {
                    capcod=tbRegistro.getValueAt(i, 0).toString();
                    capcan=tbRegistro.getValueAt(i, 5).toString();
                    captalla=tbRegistro.getValueAt(i, 2).toString();
                    descontarstock(capcod, captalla,tbRegistro.getValueAt(i, 3).toString(), capcan);

                }
            }*/
  
            if(Double.parseDouble(txtEfect.getText())>=Double.parseDouble(txtTotal.getText())){
                
                boleta();
                IngresoCaja();
                detalleboleta();
                
                //int input = JOptionPane.showOptionDialog(null, "Quiere imprimir Boleta?", "G&H", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null); 
                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea imprimir Boleta?",
                    "G&H", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    reporte();
                }
                /*if(input == JOptionPane.OK_OPTION) 
                { 
                    // do something 
                    reporte();
                }*/
                //System.out.println("btnCobrarActionPerformed() = "+contTipoPrecio);
                txtEfect.setText("");
                txtClient.setText("");
                txtDesc.setText("0.00");
                txtProd.setText("");
                txtTotal.setText("");
                txtSubTotal.setText("");
                txtVuelto.setText("");
                
                DefaultTableModel modelo = (DefaultTableModel) tbRegistro.getModel();
                int a =tbRegistro.getRowCount()-1;
                int i;
                for(i=a;i>=0;i--)
                {  
                    modelo.removeRow(i);
                }
                    numeros();
                subtotal=0;
                contador=0;
                vuelto=0; 
                contTipoPrecio=0;
                if(ConsultasEnEspera.enespera){
                    String eliminarSQL="DELETE FROM EsperaCajaRegistradora WHERE num_caj = '"+ConsultasEnEspera.codigo+"'";
                    try {
                    PreparedStatement pst  = cn.prepareStatement(eliminarSQL);
                    pst.executeUpdate();  
                    espera.cargartodasBoletas();
                    ConsultasEnEspera.calcular();
                    } 
                    catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                    ConsultasEnEspera.contador=0;
                }
                ConsultasEnEspera.enespera=false;
                }else{
                    JOptionPane.showMessageDialog(null,"Efectivo a cobrar menor a total!");
                }
        }
        if(TipoPrecio){
            cargarlistaproductosMenor("");
        }else{
            cargarlistaproductosMayor("");
        }
    }//GEN-LAST:event_btnCobrarActionPerformed
    private void IngresoCaja(){
        String InsertarSQL="INSERT INTO FlujoCaja(num_caj,Ingreso,Comentario,Fecha,Hora,TipoOperacion) VALUES (?,?,?,?,?,?)";
        String caj=txtTicket.getText();
        String codven=txtVen.getText();
        String total=txtTotal.getText();    
        String fecha=txtFecha.getText();
        String horaBoleta=horaact();
        String TipoOperacion="ENTRADA";    

        try {
                PreparedStatement pst = cn.prepareStatement(InsertarSQL);
                pst.setString(1,caj);
                pst.setString(2,total);
                pst.setString(3,"Venta Realizada Por "+codven);
                pst.setString(4,fecha);
                pst.setString(5,horaBoleta);
                pst.setString(6,TipoOperacion);
                int n= pst.executeUpdate();
                if(n>0)
                {
                }else{
                   JOptionPane.showMessageDialog(null, "No se registro datos de FlujoCaja!");
                }

            } catch (SQLException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            }  
    }
            
    private void rbtnPorMayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnPorMayorActionPerformed
        // TODO add your handling code here:
         if(rbtnPorMayor.isSelected()){
            TipoPrecio=false;
            rbtnPorMenor.setSelected(false);
            cargarlistaproductosMayor(""+txtProd.getText());
            //System.out.print(TipoPrecio);
        }
    }//GEN-LAST:event_rbtnPorMayorActionPerformed

    private void txtTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTicketActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTicketActionPerformed

    private void txtProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProdActionPerformed

    private void txtProdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProdKeyReleased
        // TODO add your handling code here:
            if(TipoPrecio){
                cargarlistaproductosMenor(txtProd.getText());
                char caracter = evt.getKeyChar();
                if(caracter != KeyEvent.VK_BACKSPACE ){
                    completar(txtProd.getText());
                }
                
            }else{
                cargarlistaproductosMayor(txtProd.getText());
                char caracter = evt.getKeyChar();
                if(caracter != KeyEvent.VK_BACKSPACE ){
                    completar(txtProd.getText());
                }
            }
    }//GEN-LAST:event_txtProdKeyReleased

    private void txtProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtProdMouseClicked
        // TODO add your handling code here:
            if(TipoPrecio){
                cargarlistaproductosMenor(txtProd.getText());
            }else{
                cargarlistaproductosMayor(txtProd.getText());
            }
            cuadro=true;
    }//GEN-LAST:event_txtProdMouseClicked

    private void txtClientKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClientKeyReleased
        // TODO add your handling code here:
        mostrarclientes(""+txtClient.getText());
    }//GEN-LAST:event_txtClientKeyReleased

    private void txtClientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtClientMouseClicked
        // TODO add your handling code here:
        mostrarclientes(""+txtClient.getText());
        cuadro=false;
    }//GEN-LAST:event_txtClientMouseClicked
    private String comparar(String cod,String talla,String color)
    {
        String cant="";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM talla_producto WHERE cod_pro='"+cod+"' and cod_talla='"+talla+"' and cod_color = '"+color+"'");
            while(rs.next())
            {
                cant=rs.getString(3);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CajaRegistradora.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cant;
        
    }    
    private void mnElegirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnElegirActionPerformed
        // TODO add your handling code here:
        if(ConsultasEnEspera.enespera){
            JOptionPane.showMessageDialog(null, "Reserva Pendiente!");
        }else{
            if(cuadro){
                try {
                    DefaultTableModel tabladet = (DefaultTableModel) tbRegistro.getModel();
                    String[]  dato=new String[7];

                    int  fila = tbProd.getSelectedRow();
                    if(fila==-1)
                    {
                        JOptionPane.showMessageDialog(null, "No  ha seleccionado ningun registro");
                    } 
                    else
                    {
                        String codins=tbProd.getValueAt(fila, 0).toString();
                        String desins=tbProd.getValueAt(fila, 1).toString();
                        String talla=tbProd.getValueAt(fila, 2).toString();
                        String preins=tbProd.getValueAt(fila, 5).toString();
                        String color=tbProd.getValueAt(fila, 3).toString();
                        String tipoVenta;
                        if(TipoPrecio){
                            tipoVenta="Minorista";
                        }else{
                            tipoVenta="Mayorista";
                        }
                        int c=0;
                        int j=0;
                        String  cant=JOptionPane.showInputDialog("ingrese cantidad");
                        int cantidad=Integer.parseInt(cant);
                        if((cant.equals("")) || (cant.equals("0")) || cantidad<0)
                        {
                            JOptionPane.showMessageDialog(this, "Debe ingresar algun valor mayor que 0");
                        }
                        else
                        {
                            
                            String sql2="select cod_talla from talla WHERE des_talla='"+talla+"'";
                            String cod="";
                            try{
                                Statement st3= cn.createStatement();
                                ResultSet rs1 = st3.executeQuery(sql2);
                            if(rs1.next())
                            {              
                                cod=rs1.getString("cod_talla");
                            }  
                            }catch(SQLException ex){
                            }  
                            sql2="select cod_color from color WHERE des_color='"+color+"'";
                            String codColor="";
                            try{
                                Statement st3= cn.createStatement();
                                ResultSet rs1 = st3.executeQuery(sql2);
                            if(rs1.next())
                            {              
                                codColor=rs1.getString("cod_color");
                            }  
                            }catch(SQLException ex){
                            }  
                            int canting=Integer.parseInt(cant);
                            int comp=Integer.parseInt(comparar(codins,cod,codColor));
                            if(canting>comp)
                            {
                                JOptionPane.showMessageDialog(this,"Ud. no cuenta con el stock apropiado");
                            }
                            else
                            {
                                if(duplicado(codins, talla,color)==0){
                                    descontarstock(codins, talla, color, cant);
                                    //System.out.println("Formulario.CajaRegistradora.mnElegirActionPerformed() "+duplicado(codins, talla,cod));
                                    for(int i=0;i<tbRegistro.getRowCount();i++)
                                    {
                                        Object com=tbRegistro.getValueAt(i,0);
                                        Object tall=tbRegistro.getValueAt(i,2);
                                        Object col=tbRegistro.getValueAt(i,3);
                                        if(codins.equals(com)&&talla.equals(tall)&&color.equals(col))
                                        {
                                            j=i;
                                            tbRegistro.setValueAt(cant, i, 5);
                                            c=c+1;
                                        }
                                    }
                                    if(c==0)
                                    {
                                        Tventa[contTipoPrecio]=tipoVenta;
                                        ConsultasEnEspera.tipoPrecio[contTipoPrecio]=tipoVenta;
                                        //System.out.println("cont Tipo Precio = "+contTipoPrecio);
                                        dato[0]=codins;
                                        dato[1]=desins;
                                        dato[2]=talla;
                                        dato[3]=color;
                                        dato[4]=preins;
                                        dato[5]=cant;
                                        tabladet.addRow(dato);
                                        tbRegistro.setModel(tabladet);           
                                    }
                                    calcular(cant,fila,tbRegistro.getRowCount());
                                    contTipoPrecio=contTipoPrecio+1;
                                    if(TipoPrecio){
                                        cargarlistaproductosMenor("");
                                        tbProd.setRowSelectionInterval(fila, fila);
                                    }else{
                                        cargarlistaproductosMayor("");
                                        tbProd.setRowSelectionInterval(fila, fila);
                                    }
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Ya ingreso codigo y talla");
                                }
                            }
                        }

                    }

                } catch (HeadlessException | NumberFormatException e) {
                    System.err.println("Error"+e.getMessage());
                }
            }else{
                int  fila = tbProd.getSelectedRow();  
                if(fila==-1)
                {
                    JOptionPane.showMessageDialog(null, "No  ha seleccionado ningun registro");
                } 
                else
                {
                    txtClient.setText(""+tbProd.getValueAt(fila, 0).toString());
                }
            }
        }   
    }//GEN-LAST:event_mnElegirActionPerformed

    private void txtEfectKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectKeyReleased
        // TODO add your handling code here:
        String total=txtTotal.getText();
        if(!total.equals("")){
            String text=txtEfect.getText();
            if(!(text.equals("")||text.equals("0"))){
                if(Double.parseDouble(text)>Double.parseDouble(total)){
                   double efect=Double.parseDouble(text)-Double.parseDouble(total); 
                   txtVuelto.setText(""+Math.rint(efect*100)/100);
                } else{
                   txtVuelto.setText("0.0");
                }                 
            }else{
                txtVuelto.setText("0.0");
            }
        }

    }//GEN-LAST:event_txtEfectKeyReleased

    private void txtDescKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescKeyReleased
        // TODO add your handling code here:
        String descuento=txtDesc.getText();
        String text=txtSubTotal.getText();
        if(!descuento.equals("")){
            if(!(text.equals("")||text.equals("0"))){
                    if(Double.parseDouble(text)>Double.parseDouble(descuento)){
                        double efect=Double.parseDouble(text)-Double.parseDouble(descuento); 
                        txtTotal.setText(""+Math.rint(efect*100)/100);
                        String total=txtTotal.getText();
                        if(!total.equals("")){
                            String text2=txtEfect.getText();
                            if(!(text2.equals("")||text2.equals("0"))){
                                if(Double.parseDouble(text2)>Double.parseDouble(total)){
                                    double efect2=Double.parseDouble(text2)-Double.parseDouble(total); 
                                    txtVuelto.setText(""+Math.rint(efect2*100)/100);
                                } else{
                                   txtVuelto.setText("0.0");
                                                }                 
                            }else{
                                txtVuelto.setText("0.0");
                            }
                        }
                    } else{
                       txtTotal.setText(text);
                    }                 
            }else{
                txtTotal.setText(text);
            }
        }else{
            txtTotal.setText(text);
            String total=txtTotal.getText();
            if(!total.equals("")){
                String text2=txtEfect.getText();
                if(!(text2.equals("")||text2.equals("0"))){
                    if(Double.parseDouble(text2)>Double.parseDouble(total)){
                        double efect2=Double.parseDouble(text2)-Double.parseDouble(total); 
                        txtVuelto.setText(""+Math.rint(efect2*100)/100);
                    } else{
                     txtVuelto.setText("0.0");
                    }                 
                }else{
                    txtVuelto.setText("0.0");
                }
            }            
        }
    }//GEN-LAST:event_txtDescKeyReleased
    
    private void mnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnEliminarActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelo = (DefaultTableModel) tbRegistro.getModel();
        int fila = tbRegistro.getSelectedRow();
        int fil;
        if(fila>=0)
        {
            if(ConsultasEnEspera.enespera){
                String cod_pro,des_talla,des_color,cant="0";        
                cod_pro=tbRegistro.getValueAt(fila,0).toString();
                des_talla=tbRegistro.getValueAt(fila,2).toString();
                des_color=tbRegistro.getValueAt(fila,3).toString();
                cant=tbRegistro.getValueAt(fila,5).toString();
                devolucion(cod_pro, des_talla, des_color, cant);
                for (int i = 0; i < ConsultasEnEspera.contador; i++) {
                    if (i == fila) {
                        for (int j = i; j < ConsultasEnEspera.contador - 1; j++) {
                            ConsultasEnEspera.tipoPrecio[j] = ConsultasEnEspera.tipoPrecio[j+1];
                        }
                        ConsultasEnEspera.tipoPrecio[ConsultasEnEspera.contador - 1] = "";
                        ConsultasEnEspera.contador=ConsultasEnEspera.contador-1;
                    }
                }
            }else{
                String cod_pro,des_talla,des_color,cant="0";        
                cod_pro=tbRegistro.getValueAt(fila,0).toString();
                des_talla=tbRegistro.getValueAt(fila,2).toString();
                des_color=tbRegistro.getValueAt(fila,3).toString();
                cant=tbRegistro.getValueAt(fila,5).toString();
                devolucion(cod_pro, des_talla, des_color, cant);
            }
            modelo.removeRow(fila);
            if(TipoPrecio){
                cargarlistaproductosMenor("");
            }else{
                cargarlistaproductosMayor("");
            }
            calcular();
            fil=tbRegistro.getRowCount();
            if(fil==0&&ConsultasEnEspera.enespera){
                ConsultasEnEspera.enespera=false;
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No Selecciono ninguna fila");
        }
      
        if(tbRegistro.getRowCount()<1)
        {
            JOptionPane.showMessageDialog(this, "Error, no ingreso ningun producto");
        }
        else{
            calcular();
        }  
    }//GEN-LAST:event_mnEliminarActionPerformed

    private void btnAbrirCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirCajaActionPerformed
        // TODO add your handling code here:
        String Salidas[][] = new String [100][10];
        String cons;
        int cont2=0;     
        try {           
            String sql3="update sesion set estado='ABIERTO'";
            PreparedStatement pst2 = cn.prepareStatement(sql3);
            pst2.executeUpdate();         
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }  
        sesion();
        cons = "select cod_pro,cod_talla,cod_color,Stock from talla_producto";
        try {
            Statement st = cn.createStatement();
            ResultSet rs =st.executeQuery(cons);
            while(rs.next())
            {
                Salidas[cont2][0]=rs.getString(1);
                Salidas[cont2][1]=rs.getString(2);
                Salidas[cont2][2]=rs.getString(3);
                Salidas[cont2][3]=rs.getString(4);
                Salidas[cont2][4]=fechaact();
                Salidas[cont2][5]=horaact();
                Salidas[cont2][6]="STOCK_INICIAL";
                cont2=cont2+1;
            }
      
        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(repfecha(fechaact(),"STOCK_INICIAL")==0){
            cons="INSERT INTO FlujoProducto (cod_pro,cod_talla,cod_color,Stock,Fecha,Hora,TipoOperacion) VALUES (?,?,?,?,?,?,?)";
            try {
                for(int i = 0;i<cont2;i++){    
                    PreparedStatement pst  = cn.prepareStatement(cons);
                    pst.setString(1, Salidas[i][0]);
                    pst.setString(2, Salidas[i][1]);
                    pst.setString(3, Salidas[i][2]);
                    pst.setString(4, Salidas[i][3]);
                    pst.setString(5, Salidas[i][4]);
                    pst.setString(6, Salidas[i][5]);
                    pst.setString(7, Salidas[i][6]);
                    int n=pst.executeUpdate();
                    if(n>0){
                        System.out.println("Registro "+i+" Guardado con Exito");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(IngresoProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            String eliminarSQL="DELETE FROM FlujoProducto WHERE Fecha = '"+fechaact()+"' and TipoOperacion='STOCK_INICIAL'";
            try {
                PreparedStatement pst  = cn.prepareStatement(eliminarSQL);
                pst.executeUpdate();
                    //JOptionPane.showMessageDialog(null, "Borrado"); 
                System.out.println("Formulario.CajaRegistradora Borrado!");
            } 
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            cons="INSERT INTO FlujoProducto (cod_pro,cod_talla,cod_color,Stock,Fecha,Hora,TipoOperacion) VALUES (?,?,?,?,?,?,?)";
            try {
                for(int i = 0;i<cont2;i++){    
                    PreparedStatement pst  = cn.prepareStatement(cons);
                    pst.setString(1, Salidas[i][0]);
                    pst.setString(2, Salidas[i][1]);
                    pst.setString(3, Salidas[i][2]);
                    pst.setString(4, Salidas[i][3]);
                    pst.setString(5, Salidas[i][4]);
                    pst.setString(6, Salidas[i][5]);
                    pst.setString(7, Salidas[i][6]);
                    int n=pst.executeUpdate();
                    if(n>0){
                        System.out.println("Registro "+i+" Guardado con Exito");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(IngresoProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
             String Salida[][] = new String [100][10];
        int cont=0;
        cons="select cod_pro,talla.cod_talla,color.cod_color,CajaRegistradora.fecha,sum(Cast(cantidad as INT)) as total from DetalleCajaRegistradora,talla,color,CajaRegistradora\n" +
        "where talla.des_talla=DetalleCajaRegistradora.cod_talla and color.des_color=DetalleCajaRegistradora.cod_color and DetalleCajaRegistradora.num_caj=CajaRegistradora.num_caj\n" +
        "and CajaRegistradora.fecha='"+fechaact()+"'\n" +
        "group by cod_pro,talla.cod_talla,color.cod_color,CajaRegistradora.fecha\n" +
        "order by cod_pro,talla.cod_talla,color.cod_color";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while(rs.next()){
                Salida[cont][0]=rs.getString(1);
                Salida[cont][1]=rs.getString(2);
                Salida[cont][2]=rs.getString(3);
                Salida[cont][3]=rs.getString(4);   
                Salida[cont][4]=rs.getString(5);
                descontarstockFlujo(Salida[cont][0], Salida[cont][1], Salida[cont][2], Salida[cont][4]);
                cont=cont+1;    
            }
        } catch (SQLException ex) {
            Logger.getLogger(CajaRegistradora.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        
    }//GEN-LAST:event_btnAbrirCajaActionPerformed
    
    private void StockFinal(){
        String Salidas[][] = new String [100][10];
        String cons;
        int cont2=0; 
        cons = "select cod_pro,cod_talla,cod_color,Stock from talla_producto";
        try {
            Statement st = cn.createStatement();
            ResultSet rs =st.executeQuery(cons);
            while(rs.next())
            {
                Salidas[cont2][0]=rs.getString(1);
                Salidas[cont2][1]=rs.getString(2);
                Salidas[cont2][2]=rs.getString(3);
                Salidas[cont2][3]=rs.getString(4);
                Salidas[cont2][4]=fechaact();
                Salidas[cont2][5]=horaact();
                Salidas[cont2][6]="STOCK_FINAL";
                cont2=cont2+1;
            }
      
        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(repfecha(fechaact(),"STOCK_FINAL")==0){
            cons="INSERT INTO FlujoProducto (cod_pro,cod_talla,cod_color,Stock,Fecha,Hora,TipoOperacion) VALUES (?,?,?,?,?,?,?)";
            try {
                for(int i = 0;i<cont2;i++){    
                    PreparedStatement pst  = cn.prepareStatement(cons);
                    pst.setString(1, Salidas[i][0]);
                    pst.setString(2, Salidas[i][1]);
                    pst.setString(3, Salidas[i][2]);
                    pst.setString(4, Salidas[i][3]);
                    pst.setString(5, Salidas[i][4]);
                    pst.setString(6, Salidas[i][5]);
                    pst.setString(7, Salidas[i][6]);
                    int n=pst.executeUpdate();
                    if(n>0){
                        System.out.println("Registro "+i+" Guardado con Exito");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(IngresoProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            String eliminarSQL="DELETE FROM FlujoProducto WHERE Fecha = '"+fechaact()+"' and TipoOperacion='STOCK_FINAL'";
            try {
                PreparedStatement pst  = cn.prepareStatement(eliminarSQL);
                pst.executeUpdate();
                    //JOptionPane.showMessageDialog(null, "Borrado"); 
                System.out.println("Formulario.CajaRegistradora Borrado!");
            } 
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            cons="INSERT INTO FlujoProducto (cod_pro,cod_talla,cod_color,Stock,Fecha,Hora,TipoOperacion) VALUES (?,?,?,?,?,?,?)";
            try {
                for(int i = 0;i<cont2;i++){    
                    PreparedStatement pst  = cn.prepareStatement(cons);
                    pst.setString(1, Salidas[i][0]);
                    pst.setString(2, Salidas[i][1]);
                    pst.setString(3, Salidas[i][2]);
                    pst.setString(4, Salidas[i][3]);
                    pst.setString(5, Salidas[i][4]);
                    pst.setString(6, Salidas[i][5]);
                    pst.setString(7, Salidas[i][6]);
                    int n=pst.executeUpdate();
                    if(n>0){
                        System.out.println("Registro "+i+" Guardado con Exito");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(IngresoProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    private void btnCerrarCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarCajaActionPerformed
        // TODO add your handling code here:
        try {           
            String sql3="update sesion set estado='CERRADO'";
            PreparedStatement pst2 = cn.prepareStatement(sql3);
            pst2.executeUpdate();         
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }  
        sesion();
        String Salidas[][] = new String [100][10];
        int cont=0;
        String cons="select cod_pro,talla.cod_talla,color.cod_color,CajaRegistradora.fecha,sum(Cast(cantidad as INT)) as total from DetalleCajaRegistradora,talla,color,CajaRegistradora\n" +
        "where talla.des_talla=DetalleCajaRegistradora.cod_talla and color.des_color=DetalleCajaRegistradora.cod_color and DetalleCajaRegistradora.num_caj=CajaRegistradora.num_caj\n" +
        "and CajaRegistradora.fecha='"+fechaact()+"'\n" +
        "group by cod_pro,talla.cod_talla,color.cod_color,CajaRegistradora.fecha\n" +
        "order by cod_pro,talla.cod_talla,color.cod_color";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while(rs.next()){
                Salidas[cont][0]=rs.getString(1);
                Salidas[cont][1]=rs.getString(2);
                Salidas[cont][2]=rs.getString(3);
                Salidas[cont][3]=rs.getString(4);   
                Salidas[cont][4]=rs.getString(5);
                cont=cont+1;    
            }
        } catch (SQLException ex) {
            Logger.getLogger(CajaRegistradora.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("total de repeticiones = "+repfecha(fechaact(),"SALIDA"));
        if(repfecha(fechaact(),"SALIDA")==0){
            cons="INSERT INTO FlujoProducto (cod_pro,cod_talla,cod_color,Stock,Fecha,Hora,TipoOperacion) VALUES (?,?,?,?,?,?,?)";
            try {
                for(int i = 0;i<cont;i++){    
                    PreparedStatement pst  = cn.prepareStatement(cons);
                    pst.setString(1, Salidas[i][0]);
                    pst.setString(2, Salidas[i][1]);
                    pst.setString(3, Salidas[i][2]);
                    pst.setString(4, Salidas[i][4]);
                    pst.setString(5, Salidas[i][3]);
                    pst.setString(6, horaact());
                    pst.setString(7, "SALIDA");
                    int n=pst.executeUpdate();
                    if(n>0){
                        System.out.println("Registro "+i+" Guardado con Exito");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(IngresoProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            String eliminarSQL="DELETE FROM FlujoProducto WHERE Fecha = '"+fechaact()+"' and TipoOperacion='SALIDA'";
            try {
                PreparedStatement pst  = cn.prepareStatement(eliminarSQL);
                pst.executeUpdate();
                    //JOptionPane.showMessageDialog(null, "Borrado"); 
                System.out.println("Formulario.CajaRegistradora Borrado!");
            } 
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            cons="INSERT INTO FlujoProducto (cod_pro,cod_talla,cod_color,Stock,Fecha,Hora,TipoOperacion) VALUES (?,?,?,?,?,?,?)";
            try {
                for(int i = 0;i<cont;i++){    
                    PreparedStatement pst  = cn.prepareStatement(cons);
                    pst.setString(1, Salidas[i][0]);
                    pst.setString(2, Salidas[i][1]);
                    pst.setString(3, Salidas[i][2]);
                    pst.setString(4, Salidas[i][4]);
                    pst.setString(5, Salidas[i][3]);
                    pst.setString(6, horaact());
                    pst.setString(7, "SALIDA");
                    int n=pst.executeUpdate();
                    if(n>0){
                        System.out.println("Registro "+i+" Guardado con Exito");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(IngresoProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        StockFinal();
    }//GEN-LAST:event_btnCerrarCajaActionPerformed
        void descontarstockFlujo(String codi,String talla,String color,String can)
    {
        int des = Integer.parseInt(can);
        String cap="",ingreso="",devolucion="";
        int desfinal,ing=0,dev=0;
       
        
        String consul="SELECT * FROM FlujoProducto WHERE  cod_pro='"+codi+"' and cod_talla='"+talla+"' and cod_color = '"+color+"' and TipoOperacion = 'STOCK_FINAL' and Fecha = '"+fechaact()+"'";    
        try {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(consul);
            if(rs.next())
            {
                cap= rs.getString("Stock");
            }else{
                cap="0";
            }
        } catch (SQLException e) {
            cap="0";
        }
        consul="SELECT * FROM FlujoProducto WHERE  cod_pro='"+codi+"' and cod_talla='"+talla+"' and cod_color = '"+color+"' and TipoOperacion = 'INGRESO' and Fecha = '"+fechaact()+"'";    
        try {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(consul);
            while(rs.next())
            {
                ingreso= rs.getString("Stock");
                ing=ing+Integer.parseInt(ingreso);
            } 
        } catch (SQLException e) {
            ing=0;
        }
        consul="SELECT * FROM FlujoProducto WHERE  cod_pro='"+codi+"' and cod_talla='"+talla+"' and cod_color = '"+color+"' and TipoOperacion = 'DEVOLUCION' and Fecha = '"+fechaact()+"'";    
        try {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(consul);
            while(rs.next())
            {
                devolucion= rs.getString("Stock");
                dev=dev+Integer.parseInt(devolucion);
            } 
        } catch (SQLException e) {
            dev=0;
        }
        
        desfinal=des+Integer.parseInt(cap)+dev-ing;
        String modi="UPDATE FlujoProducto SET Stock='"+desfinal+"' WHERE cod_pro='"+codi+"' and cod_talla='"+talla+"' and cod_color = '"+color+"'and TipoOperacion = 'STOCK_INICIAL' and Fecha = '"+fechaact()+"'";    
        try {
            PreparedStatement pst = cn.prepareStatement(modi);
            pst.executeUpdate();
        } catch (SQLException e) {
        }  
    }
    
    private int repfecha(String fecha,String Tipo){
        String sql3="select * from FlujoProducto WHERE Fecha='"+fecha+"' and TipoOperacion = '"+Tipo+"'";
        String cod2="";
        int cont=0;
        boolean comp=true;
        try{
            Statement st2= cn.createStatement();
            ResultSet rs = st2.executeQuery(sql3);
            while(rs.next())
            {              
                cod2=rs.getString(1); 
                cont=cont+1;
             
            }        
        }catch(SQLException ex){          
        }  
        
        return cont;
    }
    
    
    private void calcular()
    {
        String pre;
        String can;
        double total=0;
        double Subtotal=0;
        double precio;
        int cantidad;
        double imp=0.0;
        
            /*can=Integer.parseInt(cant);
            imp=pre*can;
            dato[4]=Float.toString(imp);*/
        
        for(int i=0;i<tbRegistro.getRowCount();i++)
        {
            pre=tbRegistro.getValueAt(i, 4).toString();
            can=tbRegistro.getValueAt(i, 5).toString();
            precio=Double.parseDouble(pre);
            cantidad=Integer.parseInt(can);
            imp=precio*cantidad;
            Subtotal=Subtotal+imp;
            tbRegistro.setValueAt(Math.rint(imp*100)/100, i, 6);
            //System.out.println("Formulario.CajaRegistradora.calcular() prcio = "+pre+" cant = "+can);
        }
        
        txtSubTotal.setText(""+Math.rint(Subtotal*100)/100);
        subtotal=Subtotal;
        total=Subtotal-Double.parseDouble(txtDesc.getText());
        txtTotal.setText(""+Math.rint(total*100)/100);
        if(txtEfect.getText().equals("")){vuelto=0;}
        else {vuelto=Double.parseDouble(txtEfect.getText())-total;}
        txtVuelto.setText(""+Math.rint(vuelto*100)/100);
        if(vuelto<0)  txtVuelto.setText("0.0"); 
    }
    private void numeros()
    {
        String c="";
        String SQL="select max(num_caj) from CajaRegistradora";
        //String SQL="select count(*) from boleta";
    //String SQL="SELECT MAX(cod_emp) AS cod_emp FROM empleado";
     //String SQL="SELECT @@identity AS ID";
        try {
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            if(rs.next())
            {              
                 c=rs.getString(1);
            }
            if(c==null){
                txtTicket.setText("00000001");
            }
            else{
            int j=Integer.parseInt(c);
            GenerarNumero gen= new GenerarNumero();
            gen.generar(j);
             txtTicket.setText(gen.serie());
            }
        } catch (SQLException ex) {
           Logger.getLogger(Boleta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private String Esperanumeros()
    {
        String c="";
        String cod="";
        String SQL="select max(num_caj) from EsperaCajaRegistradora";
        //String SQL="select count(*) from boleta";
    //String SQL="SELECT MAX(cod_emp) AS cod_emp FROM empleado";
     //String SQL="SELECT @@identity AS ID";
        try {
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            if(rs.next())
            {              
                 c=rs.getString(1);
            }
            if(c==null){
                cod="00000001";
            }
            else{
            int j=Integer.parseInt(c);
            GenerarNumero gen= new GenerarNumero();
            gen.generar(j);
            cod=gen.serie();
            }
        } catch (SQLException ex) {
           Logger.getLogger(Boleta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cod;
    }
    
    public static String fechaact(){
    Date fecha= new Date();
    SimpleDateFormat formatofecha= new SimpleDateFormat("dd/MM/YYYY");
    return formatofecha.format(fecha);  
}    
public static String horaact(){
    Date fecha= new Date();
    SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
    return hourFormat.format(fecha);  
}  

public String Consultavendedor(String consulta){
    String vendedor="";
    String sql="select * from vendedor where nom_ven='"+usuario+"' and cod_pass='"+passw+"'";
    try{
        
        Statement st3= cn.createStatement();
        ResultSet rs1 = st3.executeQuery(sql);
        if(rs1.next())
        {              
            vendedor=rs1.getString(consulta);
            //para 
        }  
        }catch(SQLException ex){
            System.out.println("Formulario.Boleta.codvendedor()"+ex.getMessage());
        }
    return vendedor;  
} 
    void calcular(String can,int fila,int cont)
    {
        String Stock=tbProd.getValueAt(fila,4).toString();
        if(Integer.parseInt(Stock)>Integer.parseInt(can)){
            String pre;
            double total=0;
            double precio;
            int cantidad;
            double imp=0;
            String cod=tbProd.getValueAt(fila, 0).toString();
            String tall=tbProd.getValueAt(fila, 2).toString();
       
           // double resta = duplicado(cod, tall);
            //System.out.println("valor resta = "+resta);
                /*can=Integer.parseInt(cant);
                imp=pre*can;
                dato[4]=Float.toString(imp);*/

                pre=tbProd.getValueAt(fila, 5).toString();
                precio=Double.parseDouble(pre);
                cantidad=Integer.parseInt(can);
                imp=precio*cantidad;
                subtotal=imp+subtotal;
                //if(duplicado(cod, tall)){subtotal=subtotal-contador;}
                total=subtotal-Double.parseDouble(txtDesc.getText());
                // txtcod.setText(""+Math.rint(c*100)/100)
                tbRegistro.setValueAt(Math.rint(imp*100)/100, cont-1, 6);

                txtSubTotal.setText(""+Math.rint(subtotal*100)/100);
                txtTotal.setText(""+Math.rint(total*100)/100);
                //System.err.println("calculo subtotal = "+subtotal);
                if(txtEfect.getText().equals("")){vuelto=0;}
                else {vuelto=Double.parseDouble(txtEfect.getText())-total;}
                txtVuelto.setText(""+Math.rint(vuelto*100)/100);
                contador=cantidad;
        }else{
            subtotal=0;
            calcular();
        } 
    }
    
    private int duplicado(String cod,String tal,String color){
        String codigo,talla,col;
        int Contador=0;
        //boolean encontro=false;
        
        for(int i=0;i<tbRegistro.getRowCount();i++)
        {
            codigo=tbRegistro.getValueAt(i, 0).toString();
            talla=tbRegistro.getValueAt(i, 2).toString();
            col=tbRegistro.getValueAt(i, 3).toString();
            //System.out.print(codigo+" "+cod+", "+talla+" "+tal+", "+col+" "+color);
            if(codigo.equals(cod)&&talla.equals(tal)&&col.equals(color)){Contador=Contador+1;}
        }  
        //if(Contador > 0) encontro=true;
       // System.out.print("contador = "+Contador);
        //return encontro;
        
        return Contador;
    }
    void descontarstock(String codi,String talla,String color,String can)
    {
        int des = Integer.parseInt(can);
        String cap="";
        int desfinal;
       
        String sql2="select cod_talla from talla WHERE des_talla='"+talla+"'";
        String cod="";
       try{
            Statement st3= cn.createStatement();
            ResultSet rs1 = st3.executeQuery(sql2);
            if(rs1.next())
            {              
                cod=rs1.getString("cod_talla");
            }  
        }catch(SQLException ex){
            
        }
        sql2="select cod_color from color WHERE des_color='"+color+"'";
        String codColor="";
       try{
            Statement st3= cn.createStatement();
            ResultSet rs1 = st3.executeQuery(sql2);
            if(rs1.next())
            {              
                codColor=rs1.getString("cod_color");
            }  
        }catch(SQLException ex){
            
        }
        String consul="SELECT * FROM talla_producto WHERE  cod_pro='"+codi+"' and cod_talla='"+cod+"' and cod_color = '"+codColor+"'";    
        try {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(consul);
            while(rs.next())
            {
                cap= rs.getString("Stock");
            }
            
            
        } catch (SQLException e) {
        }
        desfinal=Integer.parseInt(cap)-des;
        String modi="UPDATE talla_producto SET Stock='"+desfinal+"' WHERE cod_pro='"+codi+"' and cod_talla='"+cod+"' and cod_color = '"+codColor+"'";    
        try {
            PreparedStatement pst = cn.prepareStatement(modi);
            pst.executeUpdate();
        } catch (SQLException e) {
        }
        String mod2="UPDATE producto SET Stock='"+desfinal+"' WHERE cod_pro='"+codi+"'";
        try {
            PreparedStatement pst2 = cn.prepareStatement(mod2);
            pst2.executeUpdate();
        } catch (SQLException e) {
        }     
    }
    public String codvendedor(){
        String vendedor="";
        String sql="select * from vendedor where nom_ven='"+usuario+"' and cod_pass='"+passw+"'";
        try{

            Statement st3= cn.createStatement();
            ResultSet rs1 = st3.executeQuery(sql);
            if(rs1.next())
            {              
                vendedor=rs1.getString("cod_ven");
            }  
            }catch(SQLException ex){
                System.out.println("Formulario.Boleta.codvendedor()"+ex.getMessage());
            }
        return vendedor;  
    }
    
    void boleta(){
    String InsertarSQL="INSERT INTO CajaRegistradora(num_caj,cod_ven,pre_tot,descuento,efect_tot,fecha,hora_boleta,cod_cli) VALUES (?,?,?,?,?,?,?,?)";
    String caj=txtTicket.getText();
    String codven=codvendedor();
    String total=txtTotal.getText();
    String descuento=txtDesc.getText();
    String efectivo=txtEfect.getText();
    String fecha=txtFecha.getText();
    String conclient=txtClient.getText(),horaBoleta=horaact();

    
    try {
            PreparedStatement pst = cn.prepareStatement(InsertarSQL);
            pst.setString(1,caj);
            pst.setString(2,codven);
            pst.setString(3,total);
            pst.setString(4,descuento);
            pst.setString(5,efectivo);
            pst.setString(6,fecha);
            pst.setString(7,horaBoleta);
            pst.setString(8,conclient);
           
            int n= pst.executeUpdate();
           
            if(n>0)
            {
                //JOptionPane.showMessageDialog(null,"Los datos se guardaron correctamente");
                /*int input = JOptionPane.showOptionDialog(null, "Los datos se guardaron correctamente", "The title", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null); 

                if(input == JOptionPane.OK_OPTION) 
                {    
                    
                    if(TipoPrecio){
                        cargarlistaproductosMenor("");
                    }else{
                        cargarlistaproductosMayor("");
                    }
                               
                } */
            }else{
                int input = JOptionPane.showOptionDialog(null, "No se Guardaron los datos correctamente!", "Lo siento :c", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null); 
                
                if(input == JOptionPane.OK_OPTION) 
                {    
                    
                    if(TipoPrecio){
                        cargarlistaproductosMenor("");
                    }else{
                        cargarlistaproductosMayor("");
                    }
                               
                }
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void detalleboleta(){
       
        for(int i=0;i<tbRegistro.getRowCount();i++)
        {
        String InsertarSQL="INSERT INTO DetalleCajaRegistradora(num_caj,cod_pro,cod_talla,precio,cantidad,pre_venta,tipoVenta,cod_color) VALUES (?,?,?,?,?,?,?,?)";
        String numbol=txtTicket.getText();
        String codpro=tbRegistro.getValueAt(i, 0).toString();
        String cantpro=tbRegistro.getValueAt(i, 5).toString();
        String preunit=tbRegistro.getValueAt(i, 4).toString();
        String importe=tbRegistro.getValueAt(i, 6).toString();
        String talla=tbRegistro.getValueAt(i, 2).toString();
        String color=tbRegistro.getValueAt(i, 3).toString();
        try {
            PreparedStatement pst = cn.prepareStatement(InsertarSQL);
            pst.setString(1,numbol);
            pst.setString(2,codpro);
            pst.setString(3,talla);
            pst.setString(4,preunit);
            pst.setString(5,cantpro);
            pst.setString(6,importe);
            System.out.println("Formulario.CajaRegistradora.detalleboleta() "+ConsultasEnEspera.enespera);
            if(ConsultasEnEspera.enespera){
                pst.setString(7,ConsultasEnEspera.tipoPrecio[i]);
            }else{
                 pst.setString(7,Tventa[i]);
            }             
            pst.setString(8,color);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }}
    public void reporte(){
         TicketVentas em;// Instaciamos la clase empleado
        List <TicketVentas>lista = new ArrayList<>(); //Creamos una lista de empleados con ArrayList para obtener cada empleado
        for(int i=0; i<tbRegistro.getRowCount(); i++){ // Iterena cada fila de la tabla
            em = new TicketVentas(tbRegistro.getValueAt(i, 0).toString(),tbRegistro.getValueAt(i,1).toString(), //Tomamos de la tabla el valor de cada columna y creamos un objeto empleado
            tbRegistro.getValueAt(i, 2).toString(),tbRegistro.getValueAt(i, 3).toString(),tbRegistro.getValueAt(i,4).toString(),tbRegistro.getValueAt(i, 5).toString(),tbRegistro.getValueAt(i, 6).toString());
            lista.add(em); //Agregamos el objeto empleado a la lista
           // System.out.println("imprimir "+em.codigo);
        }
        JasperReport reporte; // Instaciamos el objeto reporte
        //String path = "C:\\Users\\Luis Ramos\\Desktop\\ventas2\\Sistema de ventas_3\\ProyectoCompleto\\tienda\\src\\Formulario\\reportTicket.jasper"; //Ponemos la localizacion del reporte creado
        URL path = this.getClass().getResource("reportTicket.jasper");
        try {
            reporte = (JasperReport) JRLoader.loadObject(path); //Se carga el reporte de su localizacion
            Map parametro = new HashMap();
            parametro.put("nroBoleta",txtTicket.getText());
            parametro.put("vendedor",usuario);
            parametro.put("fecha",txtFecha.getText());
            parametro.put("hora",horaact());
            parametro.put("cliente",txtClient.getText());
            parametro.put("subTotal",txtSubTotal.getText());
            parametro.put("desc",txtDesc.getText());
            parametro.put("Total",txtTotal.getText());
            parametro.put("efectivo",txtEfect.getText());
            parametro.put("vuelto",txtVuelto.getText());
            JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, new JRBeanCollectionDataSource(lista)); //Agregamos los parametros para llenar el reporte
            JasperViewer viewer = new JasperViewer(jprint, false); //Se crea la vista del reportes
            //viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Se declara con dispose_on_close para que no se cierre el programa cuando se cierre el reporte
            viewer.setVisible(true); //Se vizualiza el reporte
            viewer.setTitle("Visor");
        } catch (JRException ex) {
            System.out.printf(ex.getMessage());
        }
    }
    void Esperaboleta(String caj){
    String InsertarSQL="INSERT INTO EsperaCajaRegistradora(num_caj,cod_ven,pre_tot,descuento,efect_tot,fecha,hora_boleta,cod_cli) VALUES (?,?,?,?,?,?,?,?)";
   
    //System.out.println("caja = "+caj);
    String codven=codvendedor();
    String total=txtTotal.getText();
    String descuento=txtDesc.getText();
    String efectivo=txtEfect.getText();
    String fecha=txtFecha.getText();
    String conclient=txtClient.getText(),horaBoleta=horaact();

    
    try {
            PreparedStatement pst = cn.prepareStatement(InsertarSQL);
            pst.setString(1,caj);
            pst.setString(2,codven);
            pst.setString(3,total);
            pst.setString(4,descuento);
            pst.setString(5,efectivo);
            pst.setString(6,fecha);
            pst.setString(7,horaBoleta);
            pst.setString(8,conclient);
           
            int n= pst.executeUpdate();
            if(n>0)
            {
                //JOptionPane.showMessageDialog(null,"Los datos se guardaron correctamente");
                /*int input = JOptionPane.showOptionDialog(null, "Los datos se guardaron correctamente", "The title", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null); 

                if(input == JOptionPane.OK_OPTION) 
                {    
                    
                    if(TipoPrecio){
                        cargarlistaproductosMenor("");
                    }else{
                        cargarlistaproductosMayor("");
                    }
                               
                } */
            }else{
                int input = JOptionPane.showOptionDialog(null, "No se Guardaron los datos correctamente!", "Lo siento :c", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null); 

                if(input == JOptionPane.OK_OPTION) 
                {    
                    
                    if(TipoPrecio){
                        cargarlistaproductosMenor("");
                    }else{
                        cargarlistaproductosMayor("");
                    }
                               
                }
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void Esperadetalleboleta(String numbol){ 
        for(int i=0;i<tbRegistro.getRowCount();i++)
        {
        String InsertarSQL="INSERT INTO EsperaDetalleCajaRegistradora(num_caj,cod_pro,cod_talla,precio,cantidad,pre_venta,tipoVenta,cod_color) VALUES (?,?,?,?,?,?,?,?)";
 
        String codpro=tbRegistro.getValueAt(i, 0).toString();
        String cantpro=tbRegistro.getValueAt(i, 5).toString();
        String preunit=tbRegistro.getValueAt(i, 4).toString();
        String importe=tbRegistro.getValueAt(i, 6).toString();
        String talla=tbRegistro.getValueAt(i, 2).toString();
        String color=tbRegistro.getValueAt(i, 3).toString();
        try {
            PreparedStatement pst = cn.prepareStatement(InsertarSQL);
            pst.setString(1,numbol);
            pst.setString(2,codpro);
            pst.setString(3,talla);
            pst.setString(4,preunit);
            pst.setString(5,cantpro);
            pst.setString(6,importe);
            pst.setString(7,Tventa[i]);
            pst.setString(8,color);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }}
public static conectar cc= new conectar();
public static Connection cn = CajaRegistradora.cc.conexion();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirCaja;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCerrarCaja;
    private javax.swing.JButton btnCobrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEspera;
    private javax.swing.JButton btnFEfectivo;
    private javax.swing.JButton btnFProducto;
    private javax.swing.JButton btnLVentas;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu jpEnviar;
    private javax.swing.JPopupMenu jpeliminar;
    private javax.swing.JMenuItem mnElegir;
    private javax.swing.JMenuItem mnEliminar;
    private javax.swing.JRadioButton rbtnPorMayor;
    private javax.swing.JRadioButton rbtnPorMenor;
    public static javax.swing.JTable tbProd;
    public static javax.swing.JTable tbRegistro;
    public static javax.swing.JTextField txtClient;
    public static javax.swing.JTextField txtDesc;
    public static javax.swing.JTextField txtEfect;
    private javax.swing.JTextField txtFecha;
    protected static javax.swing.JTextField txtProd;
    public static javax.swing.JTextField txtSubTotal;
    public static javax.swing.JTextField txtTicket;
    public static javax.swing.JTextField txtTotal;
    public static javax.swing.JTextField txtVen;
    public static javax.swing.JTextField txtVuelto;
    // End of variables declaration//GEN-END:variables
}
