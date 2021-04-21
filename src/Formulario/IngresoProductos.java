
/*
 * IngresoProductos.java
 *
 * @author elaprendiz http://www.youtube.com/user/JleoD7
 */
package Formulario;

import claseConectar.conectar;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.sql.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class IngresoProductos extends javax.swing.JInternalFrame {


    /** Creates new form IngresoProductos */
    public IngresoProductos() {
        initComponents();
         this.setLocation(150,30 );
         bloquear();
         cargar("");
         ComboboxCodPro();
    }
    private boolean cuadro;
    private int filaMod=-1;
     DefaultTableModel model;
     void bloquear(){
    txtcod.setEnabled(false);
    txtdes.setEnabled(false);
    txtpre.setEnabled(false);
    txtstock.setEnabled(false);
    txtpreM.setEnabled(false);
    JcomboTalla.setEnabled(false);
    JcomboColor.setEnabled(false);
    btnguardar.setEnabled(false);
    btnnewTalla.setEnabled(false);
    btnnuevo.setEnabled(true);
    btncancelar.setEnabled(false);
    btnactualizar.setEnabled(false);
    
    }
    void limpiar(){
    txtcod.setSelectedItem(txtcod.getItemCount());
    txtdes.setText("");
    txtpre.setText("");
    txtpreM.setText("");
    JcomboTalla.setSelectedItem(0);
    JcomboColor.setSelectedItem(0);
    txtstock.setText("");
    }
     void limpiartalla(){
    
    txtpre.setText("");
    txtpreM.setText("");
    JcomboTalla.setSelectedItem(0);
    JcomboColor.setSelectedItem(0);
    txtstock.setText("");
    }
    void desbloquear(){
    txtcod.setEnabled(true);
    txtdes.setEnabled(true);
    txtpre.setEnabled(true);
    txtpreM.setEnabled(true);
    txtstock.setEnabled(true);
    JcomboTalla.setEnabled(true);
     JcomboColor.setEnabled(true);
    btnguardar.setEnabled(true);
    btnnewTalla.setEnabled(true);
    btnnuevo.setEnabled(false);
    btncancelar.setEnabled(true);
    }
     void des_actualizar(){
    txtcod.setEnabled(true);
    txtcod.setEditable(false);
    txtdes.setEnabled(true);
    txtpre.setEnabled(false);
    txtpreM.setEnabled(false);
    JcomboTalla.setEnabled(false);
    JcomboColor.setEnabled(false);
    txtstock.setEnabled(false);
 

    
        btncancelar.setEnabled(true);
        btnactualizar.setEnabled(true);
        btnguardar.setEnabled(false);
        btnnuevo.setEnabled(false);
    }
    void cargar(String valor) {
        try{
            if(JcomboTalla.getItemCount()==0){
                Combobox();
            }else{
                JcomboTalla.setSelectedItem("Seleccione una opción");
            }    
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error carga de combobox!");
        }
        
        try{
            if(JcomboColor.getItemCount()==0){
                ComboboxColor();
            }else{
                JcomboColor.setSelectedItem("Seleccione una opción");
            }    
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error carga de combobox!");
        }
        
        try{
            String [] titulos={"Codigo","Descripcion","Stock Total"};
            String [] registros= new String[4];
            model=new DefaultTableModel(null,titulos)
            {
                @Override
                public boolean isCellEditable(int row, int col)
                {
                    return false;
                }
            };
            
            String cons="select * from producto WHERE CONCAT (descripcion,'',Stock) LIKE '%"+valor+"%'";
            Statement st= cn.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while(rs.next()){
                registros[0]=rs.getString(1);
                registros[1]=rs.getString(2);
                registros[2]=rs.getString(3);
                
                model.addRow(registros);      
                }
            tbproductos.setModel(model);
            tbproductos.getColumnModel().getColumn(0).setPreferredWidth(150);
            tbproductos.getColumnModel().getColumn(1).setPreferredWidth(200);
            tbproductos.getColumnModel().getColumn(2).setPreferredWidth(200);
            
            //spiner
           
            
            }catch(SQLException e){
                System.out.println(e.getMessage());
                 }
            cuadro=false;
          //  System.out.println("Formulario.IngresoProductos.cargar() "+cuadro);
     
    }
    
    
    void cargarTalla(String valor) {
        try{
            if(JcomboTalla.getItemCount()==0){
                Combobox();
            }else{
                JcomboTalla.setSelectedItem("Seleccione una opción");
            }    
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error carga de combobox!");
        }
        
        try{
            if(JcomboColor.getItemCount()==0){
                ComboboxColor();
            }else{
                JcomboColor.setSelectedItem("Seleccione una opción");
            }    
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error carga de combobox!");
        }
        
        try{
            String [] titulos={"Producto","Talla","Color","Stock","PrecioMenor","PrecioMayor"};
            String [] registros= new String[6];
            model=new DefaultTableModel(null,titulos)
            {
                @Override
                public boolean isCellEditable(int row, int col)
                {
                    return false;
                }
            };         
            String cons1= "select producto.descripcion,talla.des_talla,color.des_color, talla_producto.Stock,talla_producto.Precio_talla,talla_producto.Precio_talla_mayor";
            String cons2= "from talla,producto,talla_producto,color ";
            String cons3= "WHERE( talla_producto.cod_talla=talla.cod_talla) and (talla_producto.cod_pro=producto.cod_pro) and ( talla_producto.cod_color=color.cod_color)";
            String cons4= "and (CONCAT (producto.descripcion,' ',color.des_color,' ', talla.des_talla,talla_producto.Stock,talla_producto.Precio_talla,' ',talla_producto.Precio_talla_mayor)) LIKE"+"('%"+valor+"%')";
            String cons5=" order by producto.descripcion ASC,talla_producto.Precio_talla  ASC,color.des_color  ASC";
            String cons= cons1+" "+cons2+" "+cons3+" "+cons4+" "+cons5;

            Statement st= cn.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while(rs.next()){
                registros[0]=rs.getString(1);
                registros[1]=rs.getString(2);
                registros[2]=rs.getString(3);
                registros[3]=rs.getString(4);
                registros[4]=rs.getString(5);
                registros[5]=rs.getString(6);
                model.addRow(registros);      
                }
            tbproductos.setModel(model);
            tbproductos.getColumnModel().getColumn(0).setPreferredWidth(150);
            tbproductos.getColumnModel().getColumn(1).setPreferredWidth(50);
            tbproductos.getColumnModel().getColumn(2).setPreferredWidth(75);
            tbproductos.getColumnModel().getColumn(3).setPreferredWidth(100);
            
            //spiner
           
            
            }catch(SQLException e){
               System.out.println(e.getMessage());
            }    
        cuadro=true;
        System.out.println("Formulario.IngresoProductos.cargarTalla() "+cuadro);
    }
    void BuscarProductoEditar(String cod) {       
        try{       
            String codi="",desc="",prec="";
            String cons="select * from producto WHERE cod_pro='"+cod+"'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while(rs.next())
            {
                codi=rs.getString(1);
                desc=rs.getString(2);
           
            }
            txtcod.setSelectedItem(codi);
            txtdes.setText(desc);
            
            }catch(SQLException ex)
            {
            System.out.println(ex.getMessage());
            }
     
    }
    void BuscarProductoTallaEditar(String cod,String talla,String color) { 
    
    String sql="select cod_pro from producto WHERE descripcion='"+cod+"'";
    String cod_prod="";
    try{
        Statement st2= cn.createStatement();
            
        ResultSet rs = st2.executeQuery(sql);
        if(rs.next())
        {              
            cod_prod=rs.getString("cod_pro");                               
        }
               
    }catch(SQLException ex){
            
    }    
   
        String sql2="select cod_talla from talla WHERE des_talla='"+talla+"'";
        String codTalla="";
    try{
        Statement st2= cn.createStatement();
            
        ResultSet rs = st2.executeQuery(sql2);
        if(rs.next())
        {              
            codTalla=rs.getString("cod_talla");                               
        }
               
    }catch(SQLException ex){
            
    }
      String sql3="select cod_color from color WHERE des_color='"+color+"'";
        String codColor="";
    try{
        Statement st2= cn.createStatement();
            
        ResultSet rs = st2.executeQuery(sql3);
        if(rs.next())
        {              
            codColor=rs.getString("cod_color");                               
        }
               
    }catch(SQLException ex){
            
    }
        try{       
            String prec="",stock="",preM="";
            String cons="select * from talla_producto WHERE cod_pro='"+cod_prod+"' and cod_talla='"+codTalla+"' and cod_color = '"+codColor+"'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while(rs.next())
            {
                prec=rs.getString(4);
                stock=rs.getString(3);
                preM=rs.getString(5);
           
            }
            txtcod.setSelectedItem(cod_prod);
            txtdes.setText(cod);
            txtpre.setText(prec);
            txtpreM.setText(preM);
            txtstock.setText(stock);
            JcomboTalla.setSelectedItem(talla);
            JcomboColor.setSelectedItem(color);
            }catch(SQLException ex)
            {
            System.out.println(ex.getMessage());
            }
     
    }
        void codigos(){
           
        int j;
        int cont=1;
        String num="";
        String c="";
        String SQL="select max(cod_pro) from producto";
       // String SQL="select count(*) from factura";
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
                txtcod.addItem("CP0001");
            }
            else{
            char r1=c.charAt(2);
            char r2=c.charAt(3);
            char r3=c.charAt(4);
            char r4=c.charAt(5);
            String r;
            r=""+r1+r2+r3+r4;
                 j=Integer.parseInt(r);
                 GenerarCodigos gen= new GenerarCodigos();
                 gen.generar(j);
                 txtcod.addItem("CP"+gen.serie());   
                 txtcod.setSelectedItem("CP"+gen.serie()); 
            }                                                      
        } catch (SQLException ex) {
           Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
        }
private void Combobox()
{
//Creamos la Consulta SQL
//Establecemos bloque try-catch-finally
try {     
    String cons="SELECT des_talla FROM talla ORDER BY des_talla ASC";
    Statement st2= cn.createStatement();
    ResultSet rs = st2.executeQuery(cons);
    //LLenamos nuestro ComboBox
    JcomboTalla.addItem("Seleccione una opción");
   
    while(rs.next()){
       JcomboTalla.addItem(rs.getString("des_talla"));  
    }
     
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);   
    }
}
private void ComboboxColor()
{
//Creamos la Consulta SQL
//Establecemos bloque try-catch-finally
try {     
    String cons="SELECT des_color FROM color ORDER BY des_color ASC";
    Statement st2= cn.createStatement();
    ResultSet rs = st2.executeQuery(cons);
   
    //LLenamos nuestro ComboBox
    JcomboColor.addItem("Seleccione una opción");
   
    while(rs.next()){
       JcomboColor.addItem(rs.getString("des_color"));
       // System.out.println("Formulario.IngresoProductos.ComboboxColor()"+rs.getString("des_color"));
    } 
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());   
    }
}

private void ComboboxCodPro()
{
//Creamos la Consulta SQL
//Establecemos bloque try-catch-finally
try {     
    String cons="SELECT cod_pro FROM producto ORDER BY cod_pro ASC";
    Statement st2= cn.createStatement();
    ResultSet rs = st2.executeQuery(cons);
   
    //LLenamos nuestro ComboBox
   
    while(rs.next()){
       txtcod.addItem(rs.getString("cod_pro"));
       // System.out.println("Formulario.IngresoProductos.ComboboxCodPro()"+rs.getString("cod_pro"));
    } 
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());   
    }
    
}
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        mnactualizar = new javax.swing.JMenuItem();
        mneliminar = new javax.swing.JMenuItem();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtdes = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtpre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtstock = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        JcomboTalla = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtpreM = new javax.swing.JTextField();
        colour = new javax.swing.JLabel();
        JcomboColor = new javax.swing.JComboBox<>();
        txtcod = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btnactualizar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        btnnewTalla = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbproductos = new javax.swing.JTable();
        txtbuscar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        Cambio = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();

        mnactualizar.setText("Actualizar");
        mnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnactualizarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnactualizar);

        mneliminar.setText("Eliminar");
        mneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mneliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mneliminar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("REGISTRO DE PRODUCTOS");
        setMaximumSize(new java.awt.Dimension(687, 628));
        setMinimumSize(new java.awt.Dimension(587, 568));
        setNormalBounds(new java.awt.Rectangle(0, 0, 551, 528));
        setPreferredSize(new java.awt.Dimension(587, 568));
        setVisible(false);

        jPanel3.setBackground(new java.awt.Color(238, 255, 255));
        jPanel3.setMinimumSize(new java.awt.Dimension(551, 528));

        jPanel1.setBackground(new java.awt.Color(30, 144, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle de Producto"));
        jPanel1.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel1.setText("Codigo:");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel2.setText("Descripcion:");

        txtdes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdesActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel3.setText("Precio x Menor:");

        txtpre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpreActionPerformed(evt);
            }
        });
        txtpre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpreKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel5.setText("Stock Talla:");

        txtstock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstockActionPerformed(evt);
            }
        });
        txtstock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtstockKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel6.setText("Talla");

        JcomboTalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcomboTallaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel7.setText("Precio x Mayor");

        txtpreM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpreMActionPerformed(evt);
            }
        });
        txtpreM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpreMKeyTyped(evt);
            }
        });

        colour.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        colour.setText("Color");

        JcomboColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcomboColorActionPerformed(evt);
            }
        });

        txtcod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcodActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtstock)
                            .addComponent(txtpreM)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel6)
                                    .addComponent(colour))
                                .addGap(27, 27, 27))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(6, 6, 6)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(JcomboColor, javax.swing.GroupLayout.Alignment.LEADING, 0, 177, Short.MAX_VALUE)
                            .addComponent(JcomboTalla, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtdes, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtpre, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcod, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtdes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JcomboTalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JcomboColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(colour))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtpre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtpreM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(30, 144, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setToolTipText("");

        btnnuevo.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnnuevo.setText("Nuevo");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnguardar.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnguardar.setText("Grabar");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnactualizar.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnactualizar.setText("Actualizar");
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });

        btncancelar.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btncancelar.setText("Cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        btnsalir.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        btnnewTalla.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnnewTalla.setText("NewTalla");
        btnnewTalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnewTallaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnnuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnewTalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnguardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnactualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(btncancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnnuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnnewTalla)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnguardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnactualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsalir)
                .addContainerGap())
        );

        tbproductos.setAutoCreateRowSorter(true);
        tbproductos.setBackground(new java.awt.Color(30, 144, 255));
        tbproductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbproductos.setToolTipText("");
        tbproductos.setComponentPopupMenu(jPopupMenu1);
        jScrollPane2.setViewportView(tbproductos);

        txtbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarActionPerformed(evt);
            }
        });
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Mostrar Todo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Cambio.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        Cambio.setText("Cambiar Cuadro");
        Cambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CambioActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel4.setText("Buscar:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(116, 116, 116))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addGap(33, 33, 33)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Cambio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtbuscar)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(Cambio))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
// TODO add your handling code here:
    this.dispose();
}//GEN-LAST:event_btnsalirActionPerformed

private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
// TODO add your handling code here:
    NewTallaNormal();
    desbloquear();
    cargar("");
    limpiar();
    codigos();
    txtcod.setEditable(false);
    txtdes.requestFocus();
   
}//GEN-LAST:event_btnnuevoActionPerformed

private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
// TODO add your handling code here:
    bloquear();
}//GEN-LAST:event_btncancelarActionPerformed

private void txtdesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdesActionPerformed
// TODO add your handling code here:
    txtdes.transferFocus();
}//GEN-LAST:event_txtdesActionPerformed

private void txtpreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpreActionPerformed
// TODO add your handling code here:
    txtpre.transferFocus();
}//GEN-LAST:event_txtpreActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    if(cuadro){
        cargarTalla("");
    }else{
        cargar("");
    }
}//GEN-LAST:event_jButton1ActionPerformed

private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
// TODO add your handling code here:
    if(cuadro){
        cargarTalla(txtbuscar.getText());
    }else{
        cargar(txtbuscar.getText());
    }
}//GEN-LAST:event_txtbuscarKeyReleased

int SumStock(String codPro){
    int i=0,sum=0;
     String []sumaStock= new String[100];
       String sqlsuma="select * from talla_producto where cod_pro='"+codPro+"'";
    try{
        Statement st3= cn.createStatement();
        ResultSet rs3 = st3.executeQuery(sqlsuma);
        while(rs3.next())
        {              
            sumaStock[i]=rs3.getString("Stock");
            i=i+1;     
        }
    }catch(NumberFormatException | SQLException e){
    }  
    for(int j=0;j<i;j++){
        sum=sum+Integer.parseInt(sumaStock[j]);
    }

   
    return sum;
}

private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
// TODO add your handling code here:
    String codPro,des,pre,stock;
    String sql,sql2,cod="",talla,sql3,codColor="";
    codPro=txtcod.getSelectedItem().toString();
    des=txtdes.getText();
    stock=txtstock.getText();
    pre=txtpre.getText();
    String preM=txtpreM.getText();
    talla=JcomboTalla.getSelectedItem().toString();
    String finalsuma;

    String sql4="select cod_pro from producto WHERE cod_pro='"+codPro+"'";
    String cod2="";
        try{
            Statement st5= cn.createStatement();
            ResultSet rs5 = st5.executeQuery(sql4);
            if(rs5.next())
            {              
                cod2=rs5.getString("cod_pro");        
            }        
        }catch(SQLException ex){          
        }
        if(cod2.equals("")){
            // System.out.println("vacio");
             GuardarInicial();
        }else{
           // System.out.println("existe "+cod2);
        }
 if(comprobar_llenos(des,talla,pre,preM,stock,JcomboColor.getSelectedItem().toString()))
 {
    sql="INSERT INTO talla_producto (cod_talla,cod_pro,Stock,Precio_talla,Precio_talla_mayor,cod_color) VALUES (?,?,?,?,?,?)";
    sql2="select cod_talla from talla WHERE des_talla='"+talla+"'";
    try{
        Statement st2= cn.createStatement();
            
        ResultSet rs = st2.executeQuery(sql2);
        if(rs.next())
        {              
            cod=rs.getString("cod_talla");                               
        }
               
    }catch(SQLException ex){
            
    }
    sql2="select cod_color from color WHERE des_color='"+JcomboColor.getSelectedItem().toString()+"'";
    try{
        Statement st2= cn.createStatement();
            
        ResultSet rs = st2.executeQuery(sql2);
        if(rs.next())
        {              
            codColor=rs.getString("cod_color");                               
        }
               
    }catch(SQLException ex){
            
    }
   
    if(NoSeRepite(codPro, cod,codColor))
    {
    try {
        PreparedStatement pst  = cn.prepareStatement(sql);
        pst.setString(1, cod);
        pst.setString(2, codPro);
        pst.setString(3, stock);
        pst.setString(4, pre);
        pst.setString(5, preM);
        pst.setString(6, codColor);
        int n=pst.executeUpdate();
        if(n>0){
            JOptionPane.showMessageDialog(null, "Registro Guardado con Exito");
        }
    } catch (SQLException ex) {
        Logger.getLogger(IngresoProductos.class.getName()).log(Level.SEVERE, null, ex);
    } 
    finalsuma=Integer.toString(SumStock(codPro));
    try {
                
        sql3="update producto set Stock='"+finalsuma+"' where cod_pro = '"+codPro+"'";
        PreparedStatement pst2 = cn.prepareStatement(sql3);
        pst2.executeUpdate();
        JOptionPane.showMessageDialog(null, "Actualizado");
        System.out.println("total= "+finalsuma);
        cargarTalla("");
        bloquear();
    } catch (HeadlessException | SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    }  
         HistorialProd(codPro, codColor, cod, "NUEVO");
         HistorialProd(codPro, codColor, cod, "STOCK_INICIAL");
    }else{
        JOptionPane.showMessageDialog(null,"Ya existe talla, elegir otro");
    }
    }else{
     JOptionPane.showMessageDialog(null,"Faltan Datos");
    }
}//GEN-LAST:event_btnguardarActionPerformed

private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
// TODO add your handling code here:
    if(JcomboTalla.getSelectedItem().toString().equals("Seleccione una opción")&&!txtpre.isEnabled()){
        if(txtdes.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Falta datos!");
        }else{
                String sql="UPDATE producto SET descripcion ='"+txtdes.getText()+"' WHERE cod_pro = '"+txtcod.getSelectedItem().toString()+"'"; 
                try {
                    PreparedStatement pst = cn.prepareStatement(sql);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Actualizado");
                    cargar("");
                    bloquear();
                    limpiar();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
        }
     
    }else{
        txtdes.setEditable(false);
        if(comprobar_llenos(txtdes.getText(), JcomboTalla.getSelectedItem().toString(), txtpre.getText(),txtpreM.getText(), txtstock.getText(),JcomboColor.getSelectedItem().toString())){
            String sql2="select cod_talla from talla WHERE des_talla='"+JcomboTalla.getSelectedItem().toString()+"'";
            String codTalla="";
            try{
                Statement st2= cn.createStatement();    
                ResultSet rs = st2.executeQuery(sql2);
                if(rs.next())
                {              
                    codTalla=rs.getString("cod_talla");                               
                }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
            String codColor="";
            sql2="select cod_color from talla WHERE des_color='"+JcomboColor.getSelectedItem().toString()+"'";
            try{
                Statement st2= cn.createStatement();    
                ResultSet rs = st2.executeQuery(sql2);
                if(rs.next())
                {              
                    codColor=rs.getString("cod_color");                               
                }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
            String talla=(String)tbproductos.getValueAt(filaMod,1);
            String color=(String)tbproductos.getValueAt(filaMod,2);
            if(NoSeRepite(txtcod.getSelectedItem().toString(), codTalla,codColor)){
               
                String sql="UPDATE talla_producto SET cod_talla ='"+codTalla+"',Stock='"+txtstock.getText()+"', Precio_talla='"+txtpre.getText()+"',Precio_talla_mayor='"+txtpreM.getText()+"', cod_color = '"+codColor+"' where cod_pro = '"+txtcod.getSelectedItem().toString()+"' and cod_talla='"+codTalla+"' and cod_color='"+codColor+"'"; 
                try {
                    PreparedStatement pst = cn.prepareStatement(sql);
                    pst.executeUpdate();                                   
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                } 
                
                String finalsuma=Integer.toString(SumStock(txtcod.getSelectedItem().toString()));
                try {
                
                    String sql3="update producto set Stock='"+finalsuma+"' where cod_pro = '"+txtcod.getSelectedItem().toString()+"'";
                    PreparedStatement pst2 = cn.prepareStatement(sql3);
                    pst2.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Actualizado");
                    System.out.println("total= "+finalsuma);
                    cargarTalla("");
                    bloquear();
                    limpiar();
                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                } 
                HistorialProd(txtcod.getSelectedItem().toString(), codColor, codTalla, "NUEVO");
                HistorialProd(txtcod.getSelectedItem().toString(), codColor, codTalla, "STOCK_INICIAL");
            }else{
                if(talla.equals(JcomboTalla.getSelectedItem().toString())){
                    String sql="UPDATE talla_producto SET cod_talla ='"+codTalla+"',Stock='"+txtstock.getText()+"', Precio_talla='"+txtpre.getText()+"',Precio_talla_mayor='"+txtpreM.getText()+"', cod_color = '"+codColor+"' where cod_pro = '"+txtcod.getSelectedItem().toString()+"' and cod_talla='"+codTalla+"' and cod_color='"+codColor+"'"; 
                    try {
                        PreparedStatement pst = cn.prepareStatement(sql);
                        pst.executeUpdate();                                   
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }           
                    String finalsuma=Integer.toString(SumStock(txtcod.getSelectedItem().toString()));
                    try {               
                        String sql3="update producto set Stock='"+finalsuma+"' where cod_pro = '"+txtcod.getSelectedItem().toString()+"'";
                        PreparedStatement pst2 = cn.prepareStatement(sql3);
                        pst2.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Actualizado");
                        System.out.println("total= "+finalsuma);
                        cargarTalla("");
                        bloquear();
                        limpiar();
                    } catch (HeadlessException | SQLException e) {
                        JOptionPane.showMessageDialog(null, e);
                    } 
                    HistorialProd(txtcod.getSelectedItem().toString(), codColor, codTalla, "NUEVO");
                    HistorialProd(txtcod.getSelectedItem().toString(), codColor, codTalla, "STOCK_INICIAL");
                }else{
                    JOptionPane.showMessageDialog(null, "Talla o color ya existe..!");
                }              
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Falta datos..!");
        }
      
       
    }
    
}//GEN-LAST:event_btnactualizarActionPerformed

private void txtstockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstockActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtstockActionPerformed
private void NewTalla(){
    txtcod.setEditable(false);
    txtdes.setEditable(false);
    txtpre.setEditable(true);
    txtpreM.setEditable(true);
    JcomboTalla.setSelectedItem(0);
    JcomboColor.setSelectedItem(0);
    txtstock.setEditable(true);
    txtpre.requestFocus();
    
}
private void NewTallaNormal(){
    txtdes.setEditable(true);
}

private void GuardarInicial(){
    String cod,des,pre,stock;
            String sql;
            cod=txtcod.getSelectedItem().toString();
            des=txtdes.getText();
            stock="0";
            sql="INSERT INTO producto (cod_pro,descripcion,Stock) VALUES (?,?,?)";
        try {
            PreparedStatement pst  = cn.prepareStatement(sql);
            pst.setString(1, cod);
            pst.setString(2, des);
            pst.setString(3, stock);
            int n=pst.executeUpdate();
            if(n>0){
                System.out.println("Registro 1 Guardado con Exito");
        }
        } catch (SQLException ex) {
            Logger.getLogger(IngresoProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    boolean comprobar_llenos(String des,String talla,String precio,String precioM,String Stock,String color){
        boolean comprobar;
        comprobar = !(des.equals("")||talla.equals("Seleccione una opción")||precio.equals("")||precioM.equals("")||Stock.equals("")||color.equals("Seleccione una opción"));
        return comprobar;
    }

    private void btnnewTallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnewTallaActionPerformed
        // TODO add your handling code here:
           // prod();
            String cod="",cod2="",des,pre,stock,sql2,talla,codPro,prod,sql3,color;
            String sql;
            String CodColor="";
            pre=txtpre.getText();
            codPro=txtcod.getSelectedItem().toString();
            talla=JcomboTalla.getSelectedItem().toString();
            color=JcomboColor.getSelectedItem().toString();
            stock=txtstock.getText();
            des=txtdes.getText();
            
            sql="INSERT INTO talla_producto (cod_talla,cod_pro,Stock,Precio_talla,Precio_talla_mayor,cod_color) VALUES (?,?,?,?,?,?)";
            sql2="select cod_talla from talla WHERE des_talla='"+talla+"'";
            String sql4="select cod_color from color WHERE des_color='"+color+"'";
    if(comprobar_llenos(des,talla,pre,txtpreM.getText(),stock,color))
    {
        try{
            Statement st2= cn.createStatement();
            ResultSet rs = st2.executeQuery(sql2);
            if(rs.next())
            {              
                cod=rs.getString("cod_talla");
            }  
        }catch(SQLException ex){ 
        }
        try{
            
            Statement st2= cn.createStatement();
            ResultSet rs = st2.executeQuery(sql4);
            if(rs.next())
            {              
                CodColor=rs.getString("cod_color");
            }  
        }catch(SQLException ex){ 
        }
        if(NoSeRepite(codPro, cod,CodColor))
        {
        sql3="select cod_pro from producto WHERE cod_pro='"+codPro+"'";
        try{
            Statement st2= cn.createStatement();
            ResultSet rs = st2.executeQuery(sql3);
            if(rs.next())
            {              
                cod2=rs.getString("cod_pro");        
            }        
        }catch(SQLException ex){          
        }
        if(cod2.equals("")){
            // System.out.println("vacio");
             GuardarInicial();
        }else{
           // System.out.println("existe "+cod2);
        }
        try {
            PreparedStatement pst  = cn.prepareStatement(sql);
            pst.setString(1, cod);
            pst.setString(2, codPro);
            pst.setString(3, stock);
            pst.setString(4, pre);
             pst.setString(5, txtpreM.getText());
             pst.setString(6, CodColor);
            int n=pst.executeUpdate();
            if(n>0){
            JOptionPane.showMessageDialog(null, "Registro Guardado con Exito");
            }
            HistorialProd(codPro, CodColor, cod, "NUEVO");
            HistorialProd(codPro, CodColor, cod, "STOCK_INICIAL");
            cargarTalla("");
            String finalsuma=Integer.toString(SumStock(codPro));
            try {           
                sql3="update producto set Stock='"+finalsuma+"' where cod_pro = '"+codPro+"'";
                PreparedStatement pst2 = cn.prepareStatement(sql3);
                pst2.executeUpdate();
                System.out.println("total= "+finalsuma);            
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }  
        } catch (SQLException ex) {
            Logger.getLogger(IngresoProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
            limpiartalla();
            NewTalla();
        }else{
            JOptionPane.showMessageDialog(null, "Talla o Color existe, Elige otra");
        }
    }
    else
    {
        JOptionPane.showMessageDialog(null, "Falta agregar datos! ");
    }
    
    }//GEN-LAST:event_btnnewTallaActionPerformed
 
    private boolean NoSeRepite(String cod_pro,String cod_talla,String cod_color){
        String sql3="select * from talla_producto WHERE cod_pro='"+cod_pro+"' and cod_talla = '"+cod_talla+"' and cod_color = '"+cod_color+"'";
        String cod2="";
        int contador=0;
        boolean comp=true;
        try{
            Statement st2= cn.createStatement();
            ResultSet rs = st2.executeQuery(sql3);
            while(rs.next())
            {              
                cod2=rs.getString(1); 
                contador=contador+1;
             
            }        
        }catch(SQLException ex){          
        }  
        if(contador==1) comp=false;
        return comp;
    }
            
    private void JcomboTallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcomboTallaActionPerformed
        // TODO add your handling code here:
            /*if(presion){
                GuardarInicial();
                System.out.println("verdad");
                presion=false;
            }else{
                System.out.println("falso");
             }*/

    }//GEN-LAST:event_JcomboTallaActionPerformed

    private void CambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CambioActionPerformed
        // TODO add your handling code here:
       if(!Cambio.isSelected()){
           cargar("");
       }else{
           cargarTalla("");
       }
          
    }//GEN-LAST:event_CambioActionPerformed
    private void bloquearActualizar(){
        btnactualizar.setEnabled(true);
        btnnewTalla.setEnabled(false);
        btnguardar.setEnabled(false);   
    }
    private void mnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnactualizarActionPerformed
        // TODO add your handling code here: 
    try {
        filaMod=tbproductos.getSelectedRow();
        if(filaMod==-1)
        {
        JOptionPane.showMessageDialog(null, "Seleccione alguna fila");
        }
        else
        {
        
        String cod=(String)tbproductos.getValueAt(filaMod, 0);
        String talla=(String)tbproductos.getValueAt(filaMod,1);
        String color=(String)tbproductos.getValueAt(filaMod,2);
        desbloquear();
        bloquearActualizar();
        if(!cuadro){
            BuscarProductoEditar(cod);
            txtpre.setEnabled(false);
            txtpreM.setEnabled(false);
            txtstock.setEnabled(false);
            JcomboTalla.setEnabled(false);
            JcomboColor.setEnabled(false);
            txtcod.setEditable(false);
        } else{
            BuscarProductoTallaEditar(cod, talla,color);
            txtcod.setEditable(false);
            txtdes.setEditable(false);
        }
        
        }
        
    } catch (HeadlessException e) {
    }
    }//GEN-LAST:event_mnactualizarActionPerformed

    
    private void mneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mneliminarActionPerformed
        // TODO add your handling code here:
    if(cuadro){
        int filasel= tbproductos.getSelectedRow();
        try {
            if(filasel==-1)
            {
                JOptionPane.showMessageDialog(null, "Seleccione algun dato");
            }
            else
            {
                String  cod=(String)tbproductos.getValueAt(filasel, 0);
                String  codtalla=(String)tbproductos.getValueAt(filasel, 1);
                String  color=(String)tbproductos.getValueAt(filasel, 2);
                String  CantRestar=(String)tbproductos.getValueAt(filasel, 3);
                String sql2="select cod_talla from talla WHERE des_talla='"+codtalla+"'";  
                String codigo="",cod2="",cant="0";
                try{
                    Statement st2= cn.createStatement();
                    ResultSet rs = st2.executeQuery(sql2);
                    if(rs.next())
                    {              
                        codigo=rs.getString("cod_talla");
                    }  
                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                }
                String codColor = "";
                
                 try{
                     String sql3="select cod_color from talla WHERE des_color='"+color+"'";
                    Statement st2= cn.createStatement();
                    ResultSet rs = st2.executeQuery(sql2);
                    if(rs.next())
                    {              
                        codColor=rs.getString("cod_color");
                    }  
                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                }
                String sql3="select * from producto WHERE descripcion='"+cod+"'";
                try{
                    Statement st2= cn.createStatement();
                    ResultSet rs = st2.executeQuery(sql3);
                    if(rs.next())
                    {              
                        cod2=rs.getString(1);
                        cant=rs.getString(3);
                    }        
                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                }
                String eliminarSQL="DELETE FROM talla_producto WHERE cod_pro = '"+cod2+"' and cod_talla='"+codigo+"' and cod_color = '"+codColor+"'";
                try {
                    PreparedStatement pst  = cn.prepareStatement(eliminarSQL);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Borrado");
                    HistorialProd(cod2, codColor, codigo, "ELIMINAR");
                    cargarTalla("");
                } 
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                int finalsuma=Integer.parseInt(cant)-Integer.parseInt(CantRestar);
                if(finalsuma>0){
                    try {            
                        String sql4="update producto set Stock='"+Integer.toString(finalsuma)+"' where cod_pro = '"+cod2+"'";
                        PreparedStatement pst2 = cn.prepareStatement(sql4);
                        pst2.executeUpdate();
                    } catch (HeadlessException | SQLException e) {
                        JOptionPane.showMessageDialog(null, e);
                    } 
                    
                }else{
                    try {            
                        String sql4="update producto set Stock='0' where cod_pro = '"+cod2+"'";
                        PreparedStatement pst2 = cn.prepareStatement(sql4);
                        pst2.executeUpdate();
                    } catch (HeadlessException | SQLException e) {
                        JOptionPane.showMessageDialog(null, e);
                    } 
                     String eliminar="DELETE FROM producto WHERE cod_pro = '"+cod2+"'";
                    try {
                        PreparedStatement pst  = cn.prepareStatement(eliminar);
                        pst.executeUpdate();
                        HistorialProd(cod2, codColor, codigo, "ELIMINAR");
                        cargar("");
                    } 
                    catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }   
   
                /*if(NoSeRepite(cod2, codigo)){         
                    String eliminar="DELETE FROM producto WHERE cod_pro = '"+cod2+"'";
                    try {
                        PreparedStatement pst  = cn.prepareStatement(eliminar);
                        pst.executeUpdate();
                        cargar("");
                    } 
                    catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }*/
           }
            
        } 
        catch (HeadlessException e)
        {
            System.out.println(e.getMessage());
        }       
    }else{
        int filasel= tbproductos.getSelectedRow();
        try {
            if(filasel==-1)
            {
                JOptionPane.showMessageDialog(null, "Seleccione algun dato");
            }
            else
            {
                String  cod=(String)tbproductos.getValueAt(filasel, 0);
                String eliminarSQL="DELETE FROM producto WHERE cod_pro = '"+cod+"'";
                try {
                    PreparedStatement pst  = cn.prepareStatement(eliminarSQL);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Borrado");
                    cargar("");
                } 
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
           }
        } 
        catch (HeadlessException e)
        {
            System.out.println(e.getMessage());
        }
    }
        
    }//GEN-LAST:event_mneliminarActionPerformed

    private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarActionPerformed

    private void txtpreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpreKeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();
        if(txtpre.getText().length()>=9) evt.consume();
        if(car!='.'){
            if((car<'0' || car>'9')){ evt.consume();}    
        }
        
    }//GEN-LAST:event_txtpreKeyTyped

    private void txtstockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstockKeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();
        if(txtstock.getText().length()>=9) evt.consume();
        if((car < '0' || car > '9')) evt.consume();
    }//GEN-LAST:event_txtstockKeyTyped

    private void txtpreMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpreMActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtpreMActionPerformed

    private void txtpreMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpreMKeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();
        if(txtpreM.getText().length()>=9) evt.consume();
        if(car!='.'){
            if((car<'0' || car>'9')){ evt.consume();}    
        }
    }//GEN-LAST:event_txtpreMKeyTyped

    private void JcomboColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcomboColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JcomboColorActionPerformed

    private void txtcodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodActionPerformed
        // TODO add your handling code here:
        prod();
        
    }//GEN-LAST:event_txtcodActionPerformed
    private void prod(){
        String sql2="select descripcion from producto where cod_pro = '"+txtcod.getSelectedItem().toString()+"'";
        int contador=0;
        try{
            Statement st2= cn.createStatement();
            ResultSet rs = st2.executeQuery(sql2);
            if(rs.next())
            {              
                 txtdes.setText(rs.getString("descripcion")); 
                contador=contador+1;
                System.out.println("Formulario.IngresoProductos.prod()"+rs.getString("descripcion")+" "+txtdes.getText());     
            }  
            if(contador==0){
                txtdes.setText("");
                txtdes.requestFocus();
            }
        }catch(SQLException ex){ 
        }
    }   
private void HistorialProd(String CodPro,String CodColor,String CodTalla, String Operacion){
    String Stock= txtstock.getText();
    String fech = CajaRegistradora.fechaact();
    String hora = CajaRegistradora.horaact();
    String sql="INSERT INTO FlujoProducto (cod_pro,cod_talla,cod_color,Stock,Fecha,Hora,TipoOperacion) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst  = cn.prepareStatement(sql);
            pst.setString(1, CodPro);
            pst.setString(2, CodTalla);
            pst.setString(3, CodColor);
            pst.setString(4, Stock);
            pst.setString(5, fech);
            pst.setString(6, hora);
            pst.setString(7, Operacion);
            int n=pst.executeUpdate();
            if(n>0){
                System.out.println("Registro 1 Guardado con Exito");
        }
        } catch (SQLException ex) {
            Logger.getLogger(IngresoProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Cambio;
    private javax.swing.JComboBox<String> JcomboColor;
    private javax.swing.JComboBox<String> JcomboTalla;
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnewTalla;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel colour;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem mnactualizar;
    private javax.swing.JMenuItem mneliminar;
    private javax.swing.JTable tbproductos;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JComboBox<String> txtcod;
    private javax.swing.JTextField txtdes;
    private javax.swing.JTextField txtpre;
    private javax.swing.JTextField txtpreM;
    private javax.swing.JTextField txtstock;
    // End of variables declaration//GEN-END:variables
   conectar cc= new conectar();
   Connection cn=cc.conexion();
}
