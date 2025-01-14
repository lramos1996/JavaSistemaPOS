/*
 * Boleta.java
 *
 * @author elaprendiz http://www.youtube.com/user/JleoD7
 */
package Formulario;


import static Formulario.CajaRegistradora.txtVen;
import static Formulario.IngresoSistema.passw;
import static Formulario.IngresoSistema.usuario;
import static Formulario.ProductosB.TipoPrecio;
import claseConectar.conectar;

import java.awt.Color;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public final class Boleta extends javax.swing.JInternalFrame {

    /** Creates new form Boleta */
    public Boleta() {
        initComponents();
        this.setLocation(15,15 );
        txtfecha.setDisabledTextColor(Color.blue);
        txtfecha.setText(fechaact());
        txtnumbol.setDisabledTextColor(Color.red);
        txtcod.setDisabledTextColor(Color.blue);
        txtdire.setDisabledTextColor(Color.blue);
        txtdni.setDisabledTextColor(Color.blue);
        txtnomape.setDisabledTextColor(Color.blue);
        txttotal.setEditable(false);
        
        numeros();
    }
    ProductosB pro= new ProductosB();
    private void FlujoProducto(String codPro,String codTalla, String codColor,String Stock){
     
        String InsertarSQL="INSERT INTO FlujoProducto(cod_pro,cod_talla,cod_color,Stock,Fecha,Hora,TipoOperacion) VALUES (?,?,?,?,?,?,?)";
        String fecha = fechaact();
        String Hora = horaact();
        try {
            PreparedStatement pst = cn.prepareStatement(InsertarSQL);
            pst.setString(1,codPro);
            pst.setString(2,codTalla);
            pst.setString(3,codColor);
            pst.setString(4,Stock);
            pst.setString(5,fecha);
            pst.setString(6,Hora);
            pst.setString(7,"SALIDA");
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);        
        }
    }
    private void descontarstock(String codi,String talla,String color,String can)
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
        String consul="SELECT cod_color FROM color WHERE  des_color='"+color+"'";
        String col="";
        try {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(consul);
            while(rs.next())
            {
                col= rs.getString("cod_color");
            }
            
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        FlujoProducto(codi, cod, col, can);
        consul="SELECT * FROM talla_producto WHERE  cod_pro='"+codi+"' and cod_talla='"+cod+"' and cod_color = '"+col+"'";    
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
        String modi="UPDATE talla_producto SET Stock='"+desfinal+"' WHERE cod_pro='"+codi+"' and cod_talla='"+cod+"' and cod_color = '"+col+"'";
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
    void numeros()
    {
        
        
        String c="";
        String SQL="select max(num_bol) from boleta";
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
                txtnumbol.setText("00000001");
            }
            else{
            int j=Integer.parseInt(c);
            GenerarNumero gen= new GenerarNumero();
            gen.generar(j);
             txtnumbol.setText(gen.serie());
            }
            
           
      
         
                  
           
           
         
        } catch (SQLException ex) {
           Logger.getLogger(Boleta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void calcular()
    {
        String pre;
        String can;
        double igv=0;
        double total=0;
        double subtotal=0;
        double precio;
        int cantidad;
        double imp=0.0;
        
            /*can=Integer.parseInt(cant);
            imp=pre*can;
            dato[4]=Float.toString(imp);*/
        
        for(int i=0;i<tbdetbol.getRowCount();i++)
        {
            pre=tbdetbol.getValueAt(i, 4).toString();
            can=tbdetbol.getValueAt(i, 5).toString();
            precio=Double.parseDouble(pre);
            cantidad=Integer.parseInt(can);
            imp=precio*cantidad;
            subtotal=subtotal+imp;
            
            tbdetbol.setValueAt(Math.rint(imp*100)/100, i, 6);
            
        }
       
        txttotal.setText(""+Math.rint(subtotal*100)/100);
        
            
    }
    void boleta(){
       String InsertarSQL="INSERT INTO boleta(num_bol,cod_cli,pre_tot,fecha,cod_ven,hora_boleta,tipoVenta) VALUES (?,?,?,?,?,?,?)";
    String numbol=txtnumbol.getText();
    String codcli=txtcod.getText();
    String total=txttotal.getText();
    String fecha=txtfecha.getText();
    String conVend=codvendedor(),horaBoleta=horaact();
    String tipoVeta;
   
    if(TipoPrecio){
        tipoVeta="Minorista";
    }else{
        tipoVeta="Mayorista";
    }
    
    try {
            PreparedStatement pst = cn.prepareStatement(InsertarSQL);
            pst.setString(1,numbol);
            pst.setString(2,codcli);
            pst.setString(3,total);
            pst.setString(4,fecha);
            pst.setString(5,conVend);
            pst.setString(6,horaBoleta);
            pst.setString(7,tipoVeta);
           
            int n= pst.executeUpdate();
            if(n>0)
            {
                //JOptionPane.showMessageDialog(null,"Los datos se guardaron correctamente");
                int input = JOptionPane.showOptionDialog(null, "Los datos se guardaron correctamente", "The title", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null); 

                if(input == JOptionPane.OK_OPTION) 
                {    
                    ProductosB pro= new ProductosB();
                        if(TipoPrecio){
                           pro.cargarlistaproductosMenor("");
                        }else{
                            pro.cargarlistaproductosMayor("");
                        }
                               
                    ConsultasProductos prod=new ConsultasProductos ();         
                    prod.presionar();
                } 
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void detalleboleta(){
        for(int i=0;i<tbdetbol.getRowCount();i++)
        {
        String InsertarSQL="INSERT INTO detalleboleta(num_bol,cod_pro,des_pro,cant_pro,pre_unit,pre_venta,talla_prod,cod_color) VALUES (?,?,?,?,?,?,?,?)";
        String numbol=txtnumbol.getText();
        String codpro=tbdetbol.getValueAt(i, 0).toString();
        String despro=tbdetbol.getValueAt(i, 1).toString();
        String cantpro=tbdetbol.getValueAt(i, 5).toString();
        String preunit=tbdetbol.getValueAt(i, 4).toString();
        String importe=tbdetbol.getValueAt(i, 6).toString();
        String talla=tbdetbol.getValueAt(i, 2).toString();
        String color=tbdetbol.getValueAt(i, 3).toString();
        try {
            PreparedStatement pst = cn.prepareStatement(InsertarSQL);
            pst.setString(1,numbol);
            pst.setString(2,codpro);
            pst.setString(3,despro);
            pst.setString(4,cantpro);
            pst.setString(5,preunit);
            pst.setString(6,importe);
            pst.setString(7,talla);
            pst.setString(8,color);
            pst.executeUpdate();
          
           
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtnumbol = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtnomape = new javax.swing.JTextField();
        txtdni = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtcod = new javax.swing.JTextField();
        txtfecha = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtdire = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnclientes = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        btnproductos = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbdetbol = new javax.swing.JTable();
        btncalcular = new javax.swing.JButton();
        btnven1 = new javax.swing.JButton();
        btneli = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        txttotal = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("BOLETA");

        jLabel1.setFont(new java.awt.Font("Tempus Sans ITC", 0, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("TIENDA ");

        jLabel3.setText("DE:Lramos");

        jLabel4.setText("P.J. Miramar E-13 Parte Baja   ");

        jLabel5.setText("Telf: 481804");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/productos.jpg"))); // NOI18N
        jLabel7.setText("jLabel7");

        jLabel16.setText("LIMA - CALLAO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel16))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel1)))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addGap(14, 14, 14))
                    .addComponent(jLabel7)))
        );

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("RUC 10046495581");

        jLabel6.setBackground(new java.awt.Color(51, 51, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("       Boleta de Venta");
        jLabel6.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));

        jLabel8.setText("Nº");

        txtnumbol.setEnabled(false);
        txtnumbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnumbolActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel2)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(txtnumbol, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtnumbol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel9.setText("Señor(es):");

        txtnomape.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtnomape.setEnabled(false);

        txtdni.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtdni.setEnabled(false);
        txtdni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdniActionPerformed(evt);
            }
        });

        jLabel10.setText("DNI:");

        jLabel11.setText("Cod.Cliente");

        txtcod.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtcod.setEnabled(false);

        txtfecha.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtfecha.setEnabled(false);
        txtfecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfechaActionPerformed(evt);
            }
        });

        jLabel12.setText("Fecha:");

        txtdire.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtdire.setEnabled(false);
        txtdire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdireActionPerformed(evt);
            }
        });

        jLabel13.setText("Direccion:");

        btnclientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.jpg"))); // NOI18N
        btnclientes.setText("...");
        btnclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclientesActionPerformed(evt);
            }
        });

        jLabel14.setText("Productos:");

        btnproductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar1.JPG"))); // NOI18N
        btnproductos.setText("BUSCAR");
        btnproductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproductosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnomape, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnclientes, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtdire, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnproductos)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtnomape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnclientes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtdire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(btnproductos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbdetbol.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "TALLA", "COLOR", "PRECIO UNITARIO", "CANTIDAD", "PRECIO VENTA"
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
        jScrollPane2.setViewportView(tbdetbol);

        btncalcular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/calcular1.JPG"))); // NOI18N
        btncalcular.setText("REALIZAR CALCULO");
        btncalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncalcularActionPerformed(evt);
            }
        });

        btnven1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/16 (Save).jpg"))); // NOI18N
        btnven1.setText("REALIZAR VENTA");
        btnven1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnven1ActionPerformed(evt);
            }
        });

        btneli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Wzdelete.jpg"))); // NOI18N
        btneli.setText("ELIMINAR");
        btneli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliActionPerformed(evt);
            }
        });

        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/16 (Delete).jpg"))); // NOI18N
        btnsalir.setText("SALIR");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        jLabel20.setText("Total:");

        jLabel21.setText("S/.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnsalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btncalcular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btneli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnven1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(28, 28, 28))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btncalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnven1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btneli, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(42, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void txtfechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfechaActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtfechaActionPerformed

private void txtdireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdireActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtdireActionPerformed

private void txtdniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdniActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtdniActionPerformed

private void btncalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncalcularActionPerformed
// TODO add your handling code here:
    if(tbdetbol.getRowCount()<1)
    {
        JOptionPane.showMessageDialog(this, "ingrese algun producto");
    }
    else
    {
        calcular();
    }
    
}//GEN-LAST:event_btncalcularActionPerformed

private void btnclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclientesActionPerformed
// TODO add your handling code here:
     ClientesB cli = new ClientesB();
    Principal.jdpescritorio.add(cli);
    cli.toFront();
    cli.setVisible(true);
}//GEN-LAST:event_btnclientesActionPerformed

private void btnproductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnproductosActionPerformed
// TODO add your handling code here:
    try {
       
        Principal.jdpescritorio.add(pro);
        pro.toFront();
        pro.setVisible(true);
       
    } catch (Exception e) {
    }
}//GEN-LAST:event_btnproductosActionPerformed

private void btnven1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnven1ActionPerformed
// TODO add your handling code here:
    if((txtcod.getText().equals(""))||(txttotal.getText().equals(""))){
        JOptionPane.showMessageDialog(this, "Ingrese cliente, producto o realice operacion");
    }
    else{
         String capcod,capcan,captalla,capcol;
    for(int i=0;i<Boleta.tbdetbol.getRowCount();i++)
    {
        capcod=Boleta.tbdetbol.getValueAt(i, 0).toString();
        capcan=Boleta.tbdetbol.getValueAt(i, 5).toString();
        captalla=Boleta.tbdetbol.getValueAt(i, 2).toString();
        capcol=Boleta.tbdetbol.getValueAt(i, 3).toString();
        descontarstock(capcod, captalla,capcol,capcan);
        
    }
    boleta();
    IngresoCaja();
    detalleboleta();
    txtcod.setText("");
        txtnomape.setText("");
        txtdni.setText("");
        txtdire.setText("");
        txttotal.setText("");
       
        DefaultTableModel modelo = (DefaultTableModel) tbdetbol.getModel();
        int a =tbdetbol.getRowCount()-1;
        int i;
        for(i=a;i>=0;i--)
        {
            modelo.removeRow(i);
        }
        numeros();
    }
    if(TipoPrecio){
        pro.cargarlistaproductosMenor("");
    }else{
        pro.cargarlistaproductosMayor("");
    }
   /* ProductosB pro= new ProductosB();
    pro.cargarlistaproductos("");
    ConsultasProductos prod=new ConsultasProductos ();
    prod.mostrartodosproductos();*/
}//GEN-LAST:event_btnven1ActionPerformed

private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
// TODO add your handling code here:
    this.dispose();
}//GEN-LAST:event_btnsalirActionPerformed

private void btneliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliActionPerformed
// TODO add your handling code here:
    DefaultTableModel model = (DefaultTableModel) tbdetbol.getModel();
    int fila = tbdetbol.getSelectedRow();
    if(fila>=0)
    {
        model.removeRow(fila);
    }
    else
    {
        JOptionPane.showMessageDialog(null, "Tabla vacia o no seleccione ninguna fila");
    }
    
}//GEN-LAST:event_btneliActionPerformed

    private void txtnumbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnumbolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnumbolActionPerformed
   
    private void IngresoCaja(){
        String InsertarSQL="INSERT INTO FlujoCaja(num_caj,Ingreso,Comentario,Fecha,Hora,TipoOperacion) VALUES (?,?,?,?,?,?)";
        String caj=txtnumbol.getText();
        String codven=codvendedor();
        String total=txttotal.getText();    
        String fecha=txtfecha.getText();
        String horaBoleta=horaact();
        String TipoOperacion="ENTRADA";    

        try {
                PreparedStatement pst = cn.prepareStatement(InsertarSQL);
                pst.setString(1,caj);
                pst.setString(2,total);
                pst.setString(3,"Venta Boleta Realizada Por "+usuario+": "+codven);
                pst.setString(4,fecha);
                pst.setString(5,horaBoleta);
                pst.setString(6,TipoOperacion);
                int n= pst.executeUpdate();
                if(n>0)
                {
                }else{
                   JOptionPane.showMessageDialog(null, "No se registro datos de Boleta!");
                }

            } catch (SQLException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            }  
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncalcular;
    private javax.swing.JButton btnclientes;
    private javax.swing.JButton btneli;
    private javax.swing.JButton btnproductos;
    private javax.swing.JButton btnsalir;
    public javax.swing.JButton btnven1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    public static javax.swing.JTable tbdetbol;
    public static javax.swing.JTextField txtcod;
    public static javax.swing.JTextField txtdire;
    public static javax.swing.JTextField txtdni;
    public static javax.swing.JTextField txtfecha;
    public static javax.swing.JTextField txtnomape;
    private javax.swing.JTextField txtnumbol;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
conectar cc= new conectar();
Connection cn = cc.conexion();
}
