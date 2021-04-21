
package Formulario;

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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author Luis Ramos
 */
public class ActualizarProductos extends javax.swing.JInternalFrame {
    String opera="Ingreso";
    public ActualizarProductos() {
       // preInit();
        initComponents();
        //paintComponents();
        mostrar("","","",opera);
    }
    DefaultTableModel tabla;
    private  String Ingreso="";
    int rowant=0;
    String codP="",codT="",codC="";
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        ScrolTabla = new javax.swing.JScrollPane();
        tbReg = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtprod = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txttalla = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtcol = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jrbDevolver = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();

        jToggleButton1.setText("jToggleButton1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setNormalBounds(new java.awt.Rectangle(0, 0, 672, 418));

        jPanel1.setBackground(new java.awt.Color(238, 255, 255));

        ScrolTabla.setBackground(new java.awt.Color(238, 255, 255));
        ScrolTabla.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 0, 153), new java.awt.Color(0, 51, 153), new java.awt.Color(153, 153, 153), new java.awt.Color(204, 204, 204)));

        tbReg.setAutoCreateRowSorter(true);
        tbReg.setBackground(new java.awt.Color(238, 255, 255));
        tbReg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbReg.setGridColor(new java.awt.Color(204, 0, 0));
        tbReg.setOpaque(false);
        tbReg.setSelectionBackground(new java.awt.Color(153, 0, 153));
        tbReg.setSelectionForeground(new java.awt.Color(255, 255, 0));
        tbReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbRegKeyReleased(evt);
            }
        });
        ScrolTabla.setViewportView(tbReg);

        jPanel2.setBackground(null);
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 0, 153), new java.awt.Color(0, 0, 204), java.awt.Color.lightGray, new java.awt.Color(204, 204, 204)));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Actualización Productos");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel3.setBackground(null);
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 0, 153), new java.awt.Color(0, 51, 153), new java.awt.Color(102, 102, 102), new java.awt.Color(204, 204, 204)));

        txtprod.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtprod.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102)));
        txtprod.setOpaque(false);
        txtprod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtprodKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel2.setText("Producto");

        txttalla.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txttalla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102)));
        txttalla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttallaKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel3.setText("Talla");

        txtcol.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtcol.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102)));
        txtcol.setOpaque(false);
        txtcol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcolKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel4.setText("Color");

        btnGuardar.setBackground(null);
        btnGuardar.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardar.png"))); // NOI18N
        btnGuardar.setBorder(null);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setInheritsPopupMenu(true);
        btnGuardar.setLabel("Guardar");
        btnGuardar.setOpaque(false);
        btnGuardar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardar Boton.png"))); // NOI18N
        btnGuardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardarSelection.png"))); // NOI18N
        btnGuardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jrbDevolver.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jrbDevolver.setText("Activar Devolución");
        jrbDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbDevolverActionPerformed(evt);
            }
        });

        jButton2.setBackground(null);
        jButton2.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/contenido.png"))); // NOI18N
        jButton2.setText("Informe");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 204), new java.awt.Color(153, 0, 153), null, null));
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusable(false);
        jButton2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/contenidoPresed.png"))); // NOI18N
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/contenidoSelection.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtprod, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txttalla, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jrbDevolver))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtcol, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcol, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txttalla, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtprod, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jrbDevolver)))
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ScrolTabla)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrolTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbRegKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRegKeyReleased
        // TODO add your handling code here:
        String StockInicial;
        int row=tbReg.getSelectedRow();
        int col=tbReg.getSelectedColumn();
        
        if(col==4){
            char car = evt.getKeyChar();
            try {
                if(rowant==row){
                    if(car!=KeyEvent.VK_BACKSPACE){
                        if((car < '0' || car > '9')){
                            evt.consume();
                        }else{
                            if(row == -1){
                                JOptionPane.showMessageDialog(null, "elegir!");
                            }else{
                                //Ingreso=tbReg.getValueAt(row, col).toString();                           
                                Ingreso=Ingreso+car;

                                StockInicial=tbReg.getValueAt(row, 3).toString();
                               // tbReg.setValueAt(Ingreso, row, 5);
                               if(jrbDevolver.isSelected()){
                                   calcularResta(row,StockInicial,Ingreso);
                               }else{
                                   calcular(row,StockInicial,Ingreso);
                               }
                            }
                        }
                    }else{
                        System.out.println("Formulario.ActualizarProductos.tbRegKeyReleased() "+Ingreso);
                        //Ingreso=tbReg.getValueAt(row, col).toString();   
                        Ingreso=Ingreso.substring(0, Ingreso.length() - 1);
                        System.out.println("Formulario.ActualizarProductos.tbRegKeyReleased() "+Ingreso);
                        if(Ingreso.equals("")){
                            StockInicial=tbReg.getValueAt(row, 3).toString();
                            if(jrbDevolver.isSelected()){
                                calcularResta(row,StockInicial,"0");
                            }else{
                                calcular(row,StockInicial,"0");
                            }  
                        }else{
                            StockInicial=tbReg.getValueAt(row, 3).toString();
                               // tbReg.setValueAt(Ingreso, row, 5);
                                if(jrbDevolver.isSelected()){
                                   calcularResta(row,StockInicial,Ingreso);
                                }else{
                                   calcular(row,StockInicial,Ingreso);
                                }
                        }    
                        // System.out.println("Formulario.ActualizarProductos.tbRegKeyReleased() "+"KeyEvent.VK_BACKSPACE");
                    }
                }else{
                    //Ingreso="";
                    Ingreso=tbReg.getValueAt(row, col).toString();   
                    if(car!=KeyEvent.VK_BACKSPACE){
                        if((car < '0' || car > '9')){
                            evt.consume();
                        }else{
                            if(row == -1){
                                JOptionPane.showMessageDialog(null, "elegir!");
                            }else{
                                Ingreso=Ingreso+car;
                               // System.out.println("Formulario.ActualizarProductos.tbRegKeyReleased() "+Ingreso);
                                StockInicial=tbReg.getValueAt(row, 3).toString();
                               // tbReg.setValueAt(Ingreso, row, 5);
                                if(jrbDevolver.isSelected()){
                                   calcularResta(row,StockInicial,Ingreso);
                                }else{
                                   calcular(row,StockInicial,Ingreso);
                                }
                            }
                        }
                    }else{
                        System.out.println("Formulario.ActualizarProductos.tbRegKeyReleased() "+Ingreso);
                       // Ingreso=tbReg.getValueAt(row, col).toString();   
                        Ingreso=Ingreso.substring(0, Ingreso.length() - 1);
                        System.out.println("Formulario.ActualizarProductos.tbRegKeyReleased() "+Ingreso);
                        if(Ingreso.equals("")){
                           StockInicial=tbReg.getValueAt(row, 3).toString();
                            if(jrbDevolver.isSelected()){
                                calcularResta(row,StockInicial,"0");
                            }else{
                                calcular(row,StockInicial,"0");
                            }                           
                
                        }else{
                            StockInicial=tbReg.getValueAt(row, 3).toString();
                               // tbReg.setValueAt(Ingreso, row, 5);
                            if(jrbDevolver.isSelected()){
                                calcularResta(row,StockInicial,Ingreso);
                            }else{
                                calcular(row,StockInicial,Ingreso);
                            }                            
                        }    
                        // System.out.println("Formulario.ActualizarProductos.tbRegKeyReleased() "+"KeyEvent.VK_BACKSPACE");
                    }
                }
                rowant=row;
            } catch (HeadlessException e) {
                System.out.println("Formulario.ActualizarProductos.tbRegKeyReleased() "+e.getMessage());
            }
        }   
    }//GEN-LAST:event_tbRegKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
            String cappro,captalla,codcolor,Ing,StockFinal,fecha,hora;
            int cont=0;
            //Boolean con;
            //tbReg.clearSelection();
            for(int i=0;i<tbReg.getRowCount();i++)
            {
                cappro=tbReg.getValueAt(i, 0).toString();
                captalla=tbReg.getValueAt(i, 1).toString();
                codcolor=tbReg.getValueAt(i, 2).toString();
                Ing=tbReg.getValueAt(i, 4).toString();
                StockFinal=tbReg.getValueAt(i, 5).toString();
                fecha=fechaact();
                hora=horaact();
                //con=Integer.parseInt(StockFinal)-Integer.parseInt(Ing)==Integer.parseInt(tbReg.getValueAt(i, 3).toString());
                if(!((Integer.parseInt(StockFinal)-Integer.parseInt(tbReg.getValueAt(i, 3).toString())==0)&&(Ing.equals("")||Ing.equals("0")))){
                    if(Ing.equals("")||Ing.equals("0")){
                        Ing=Integer.toString(Integer.parseInt(StockFinal)-Integer.parseInt(tbReg.getValueAt(i, 3).toString()));
                        if(Integer.parseInt(Ing) < 0){Ing=Integer.toString(Integer.parseInt(Ing)*(-1));}
                    }
                    if(!jrbDevolver.isSelected()){
                        guardar(cod_prod(cappro), cod_talla(captalla), cod_color(codcolor), Ing, StockFinal, fecha, hora, "INGRESO");
                    }else{
                        guardar(cod_prod(cappro), cod_talla(captalla), cod_color(codcolor), Ing, StockFinal, fecha, hora, "DEVOLUCION");    
                    }
                    //guardar(cod_prod(cappro), cod_talla(captalla), cod_color(codcolor), Ing, StockFinal, fecha, hora, "INGRESO");
                    System.out.println(cod_prod(cappro)+" "+cod_talla(captalla)+" "+cod_color(codcolor)+" "+Ing+" "+StockFinal); 
                    System.out.println("Registro "+i);
                    cont=cont+1;
                }
                   
                //descontarstock(capcod, captalla,tbReg.getValueAt(i, 3).toString(), capcan);
            }
            JOptionPane.showMessageDialog(null,cont+" Registros guardado con exito");
            mostrar("","","",opera);         
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtprodKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprodKeyReleased
        // TODO add your handling code here:
        codP=txtprod.getText();
        mostrar(codP,codT,codC,opera);
       //codP=cod_prod(txtprod.getText());  
        System.out.println("Formulario.ActualizarProductos.txtprodKeyReleased() = "+codP);
    }//GEN-LAST:event_txtprodKeyReleased

    private void txttallaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttallaKeyReleased
        // TODO add your handling code here:
        codT=txttalla.getText();
        mostrar(codP,codT,codC,opera);
        System.out.println("Formulario.ActualizarProductos.txttallaKeyReleased() "+codT);
    }//GEN-LAST:event_txttallaKeyReleased

    private void txtcolKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcolKeyReleased
        // TODO add your handling code here:
        codC=txtcol.getText();
        mostrar(codP,codT,codC,opera);
        System.out.println("Formulario.ActualizarProductos.txtcolKeyReleased() "+codC);
    }//GEN-LAST:event_txtcolKeyReleased

    private void jrbDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbDevolverActionPerformed
        // TODO add your handling code here:
        if(jrbDevolver.isSelected()){
            opera="Devolucion";
            Ingreso="";
        }else{
            opera="Ingreso";
            Ingreso="";
        }
        mostrar(codP,codT,codC,opera);
    }//GEN-LAST:event_jrbDevolverActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        conectar miconexion = new conectar();
        URL archivo = this.getClass().getResource("ReportActualizacionProd.jasper");
        JasperReport jr = null;          
        try {
            Map parametro = new HashMap();
            parametro.put("Fecha",fechaact());
            jr= (JasperReport) JRLoader.loadObject(archivo);
            JasperPrint jp =JasperFillManager.fillReport(jr,parametro, miconexion.conexion());
            JasperViewer jv = new JasperViewer(jp,false);
            jv.setVisible(true);
            jv.setTitle("Visor");
          //  jv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } catch (JRException e) {
           System.out.printf(e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    
    
    void guardar(String cappro,String captalla,String codcolor,String Ing,String StockFinal,String fecha,String hora,String tipoOper){
        String sql="INSERT INTO FlujoProducto (cod_pro,cod_talla,cod_color,Stock,Fecha,Hora,TipoOperacion) VALUES (?,?,?,?,?,?,?)";
         try {
            PreparedStatement pst  = cn.prepareStatement(sql);
            pst.setString(1, cappro);
            pst.setString(2, captalla);
            pst.setString(3, codcolor);
            pst.setString(4, Ing);
            pst.setString(5, fecha);
            pst.setString(6, hora);
            pst.setString(7, tipoOper);
            int n=pst.executeUpdate();
            if(n>0){
                System.out.println("Registro Guardado con Exito");
            }else{
                JOptionPane.showMessageDialog(null, "Registro no guardado!!");
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(IngresoProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {           
            sql="update talla_producto set Stock='"+StockFinal+"' where cod_pro = '"+cappro+"' \n" +       
            "and cod_talla = '"+captalla+"' and cod_color = '"+codcolor+"'";
            PreparedStatement pst2 = cn.prepareStatement(sql);
            pst2.executeUpdate();    
            System.out.println("Actualizacion Guardado con Exito");
        } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
       } 
    }
   /* //private void preInit(){
        ImagenFondo = new ImageIcon("/Imagenes/fondo-celeste-oscuro.jpg").getImage();
    }
    /*public void paint(Graphics g){
        /*ImageIcon fot = new ImageIcon("Imagenes/fondo-celeste-oscuro.jpg");
        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_DEFAULT));
        jLabel1.setIcon(icono);
        this.repaint();*/
       /* int largo=jPanel1.getBounds().height;
        int ancho=jPanel1.getBounds().width;
        System.out.println("largo = "+largo+" Ancho = "+ancho);
        ImageIcon imagen = new ImageIcon( getClass().getResource("/Imagenes/fondo-celeste-oscuro.jpg"));
        //System.out.println("Formulario.ActualizarProductos.cargarImagen()"+jLabel2.getWidth()+" "+jLabel2.getHeight());
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(jLabel2.getWidth(),jLabel2.getHeight(),Image.SCALE_DEFAULT));
        
        jLabel2.setIcon(imagen);
       // repaint();*/
    /*
        Dimension tam = getSize();
        //ImageIcon ImagenFondo = new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/fondo-celeste-oscuro.jpg")).getImage());
        g.drawImage(ImagenFondo, 0, 0, getWidth(),getHeight(),this) ;  
    }*/
private void mostrar(String codPro,String codTall,String codCol,String TipoOpera){
    String [] Titulo = {"Producto","Talla","Color","Stock Inicial",TipoOpera,"Stock Final","Fecha Ult.Act"};
    tabla= new DefaultTableModel(null,Titulo)
        {
              @Override
                public boolean isCellEditable(int row, int col)
                {
                    return col == 4;
                }
                
        };
    String sql = "select producto.descripcion, talla.des_talla, color.des_color,talla_producto.Stock,\n" +
"    max(CONCAT(FlujoProducto.Fecha,FlujoProducto.Hora)) as fecha,FlujoProducto.TipoOperacion\n" +
"    from FlujoProducto,talla_producto,producto,talla,color\n" +
"    where talla_producto.cod_pro=FlujoProducto.cod_pro and talla_producto.cod_talla=FlujoProducto.cod_talla \n" +
"    and talla_producto.cod_color=FlujoProducto.cod_color\n" +
"    and talla_producto.cod_pro=producto.cod_pro and talla_producto.cod_talla=talla.cod_talla and talla_producto.cod_color=color.cod_color\n" +
"    and TipoOperacion='STOCK_INICIAL'  and producto.descripcion like '%"+codPro+"%'and talla.des_talla like '%"+codTall+"%'and color.des_color like '%"+codCol+"%'\n" +
"    group by producto.descripcion, talla.des_talla, color.des_color,talla_producto.Stock,FlujoProducto.TipoOperacion\n" +
"	order by producto.descripcion , talla.des_talla asc, color.des_color";
    String Registro[]=new String [8];

    Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs =st.executeQuery(sql);
            while(rs.next())
            {
                Registro[0]=rs.getString(1);
                Registro[1]=rs.getString(2);
                Registro[2]=rs.getString(3);
                Registro[3]=rs.getString(4);
                Registro[4]="";
                Registro[5]=Registro[3];
                Registro[6]=rs.getString(5);
                Registro[6]=Registro[6].substring(0, Registro[6].length() - 5);
                tabla.addRow(Registro);
            }
        tbReg.setModel(tabla);
        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
} 
 void calcular(int fila,String StockInicial,String Ingreso)
    {
     
        int precio;
        int cantidad;
        int imp=0;       

            precio=Integer.parseInt(StockInicial);
            cantidad=Integer.parseInt(Ingreso);
            imp=precio+cantidad;
            
            // txtcod.setText(""+Math.rint(c*100)/100)
            tbReg.setValueAt(Integer.toString(imp), fila, 5);
                   
    }
  void calcularResta(int fila,String StockInicial,String Ingreso)
    {
     
        int precio;
        int cantidad;
        int imp=0;       

            precio=Integer.parseInt(StockInicial);
            cantidad=Integer.parseInt(Ingreso);
            imp=precio-cantidad;
            
            // txtcod.setText(""+Math.rint(c*100)/100)
            if(imp>=0) tbReg.setValueAt(Integer.toString(imp), fila, 5);
            else tbReg.setValueAt("0", fila, 5);
                   
    }

private String prod(String cod){
        String sql2="select descripcion from producto where cod_pro = '"+cod+"'";
        String prod = "";
        try{
            Statement st2= cn.createStatement();
            ResultSet rs = st2.executeQuery(sql2);
            if(rs.next())
            {              
                 prod=rs.getString("descripcion"); 
                    
            }  
           
        }catch(SQLException ex){ 
        }
        
        return prod;
}   
private String cod_prod(String cod){
        String sql2="select cod_pro from producto where descripcion like '%"+cod+"%'";
        String prod = "";
        try{
            Statement st2= cn.createStatement();
            ResultSet rs = st2.executeQuery(sql2);
            if(rs.next())
            {              
                 prod=rs.getString("cod_pro"); 
                    
            }  
           
        }catch(SQLException ex){ 
        }
        
        return prod;
}  
private String color(String codcolor){
        String sql2="select des_color from color where cod_color = '"+codcolor+"'";
        String prod = "";
        try{
            Statement st2= cn.createStatement();
            ResultSet rs = st2.executeQuery(sql2);
            if(rs.next())
            {              
                 prod=rs.getString("des_color"); 
                    
            }  
           
        }catch(SQLException ex){ 
        }
        
        return prod;
} 
private String cod_color(String codcolor){
        String sql2="select cod_color from color where des_color like '%"+codcolor+"%'";
        String prod = "";
        try{
            Statement st2= cn.createStatement();
            ResultSet rs = st2.executeQuery(sql2);
            if(rs.next())
            {              
                 prod=rs.getString("cod_color"); 
                    
            }  
           
        }catch(SQLException ex){ 
        }
        
        return prod;
} 
private String talla(String codtalla){
        String sql2="select des_talla from talla where cod_talla ='"+codtalla+"'";
        String prod = "";
        try{
            Statement st2= cn.createStatement();
            ResultSet rs = st2.executeQuery(sql2);
            if(rs.next())
            {              
                 prod=rs.getString("des_talla"); 
                    
            }  
           
        }catch(SQLException ex){ 
        }
        
        return prod;
} 
private String cod_talla(String codtalla){
        String sql2="select cod_talla from talla where des_talla like '%"+codtalla+"%'";
        String prod = "";
        try{
            Statement st2= cn.createStatement();
            ResultSet rs = st2.executeQuery(sql2);
            if(rs.next())
            {              
                 prod=rs.getString("cod_talla"); 
                    
            }  
           
        }catch(SQLException ex){ 
        }
        
        return prod;
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
conectar cc= new conectar();
Connection cn = cc.conexion();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrolTabla;
    private javax.swing.JButton btnGuardar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JRadioButton jrbDevolver;
    private javax.swing.JTable tbReg;
    private javax.swing.JTextField txtcol;
    private javax.swing.JTextField txtprod;
    private javax.swing.JTextField txttalla;
    // End of variables declaration//GEN-END:variables
}
