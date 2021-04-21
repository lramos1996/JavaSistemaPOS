
/*
 * ProductosB.java
 *
 * @author elaprendiz http://www.youtube.com/user/JleoD7
 */
package Formulario;

import claseConectar.conectar;

import java.sql.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Administrador
 */
public class ProductosB extends javax.swing.JInternalFrame {
DefaultTableModel tabla;
public static boolean TipoPrecio=true;
       
    /** Creates new form ProductosB */
    public ProductosB() {
        initComponents();
          cargarlistaproductosMenor("");
          Pmenor.setSelected(true);
            
    }
String comparar(String cod,String talla, String color)
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
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cant;
        
    }
 
    public void cargarlistaproductosMenor(String dato){
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
    String mostrar2="WHERE( talla_producto.cod_talla=talla.cod_talla) and (talla_producto.cod_pro=producto.cod_pro) and (talla_producto.cod_color=color.cod_color) and"; 
    String mostrar3="(CONCAT (producto.cod_pro,' ',producto.descripcion,' ', talla.des_talla,color.des_color,talla_producto.Stock,talla_producto.Precio_talla) LIKE"+"'%"+dato+"%')";
    String mostrar4="order by producto.descripcion ASC,talla_producto.Precio_talla  asc";
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
                if(Registro[3].equals("0")){
                    JOptionPane.showMessageDialog(null, "No hay suficiente Stock de "+Registro[1]+"!", "ALERTA", 2);
                }
            }
            tbprod.setModel(tabla);
        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    
    }

public void cargarlistaproductosMayor(String dato){
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
    String mostrar2="WHERE( talla_producto.cod_talla=talla.cod_talla) and (talla_producto.cod_pro=producto.cod_pro) and (talla_producto.cod_color=color.cod_color) and "; 
    String mostrar3="(CONCAT (producto.cod_pro,' ',producto.descripcion,' ', talla.des_talla,color.des_color,talla_producto.Stock,talla_producto.Precio_talla_mayor) LIKE"+"'%"+dato+"%')";
    String mostrar4="order by producto.descripcion ASC,talla_producto.Precio_talla  asc";
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
                if(Registro[3].equals("0")){
                    JOptionPane.showMessageDialog(null, "No hay suficiente Stock de "+Registro[1]+"!", "ALERTA", 2);
                }
            }
            tbprod.setModel(tabla);
        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        mnenviar = new javax.swing.JMenuItem();
        txtprod = new javax.swing.JTextField();
        btnmostrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbprod = new javax.swing.JTable();
        Pmenor = new javax.swing.JRadioButton();
        Pmayor = new javax.swing.JRadioButton();

        mnenviar.setText("Enviar a Boleta");
        mnenviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnenviarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnenviar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("PRODUCTOS");

        txtprod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtprodKeyReleased(evt);
            }
        });

        btnmostrar.setText("Mostrar todo");
        btnmostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmostrarActionPerformed(evt);
            }
        });

        jLabel1.setText("Buscar Productos");

        tbprod.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbprod.setComponentPopupMenu(jPopupMenu1);
        jScrollPane2.setViewportView(tbprod);

        Pmenor.setText("Precio por menor");
        Pmenor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PmenorActionPerformed(evt);
            }
        });

        Pmayor.setText("Precio por mayor");
        Pmayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PmayorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Pmayor)
                    .addComponent(Pmenor)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtprod, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnmostrar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnmostrar)
                    .addComponent(txtprod, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Pmenor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Pmayor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void txtprodKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprodKeyReleased
// TODO add your handling code here:
    if(TipoPrecio){
        cargarlistaproductosMenor(txtprod.getText());
    }else{
        cargarlistaproductosMayor(txtprod.getText());
    }
}//GEN-LAST:event_txtprodKeyReleased

private void btnmostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmostrarActionPerformed
// TODO add your handling code here:
    if(TipoPrecio){
        cargarlistaproductosMenor("");
    }else{
        cargarlistaproductosMayor("");
    }
}//GEN-LAST:event_btnmostrarActionPerformed

private void mnenviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnenviarActionPerformed
// TODO add your handling code here:
     try {
         DefaultTableModel tabladet = (DefaultTableModel)     Boleta.tbdetbol.getModel();
    String[]  dato=new String[6];
   
    int  fila = tbprod.getSelectedRow();
    
    
    if(fila==-1)
    {
        JOptionPane.showMessageDialog(null, "No  ha seleccionado ningun registro");
    }
    else
    {
          String codins=tbprod.getValueAt(fila, 0).toString();
          String desins=tbprod.getValueAt(fila, 1).toString();
          String talla=tbprod.getValueAt(fila, 2).toString();
          String color=tbprod.getValueAt(fila, 3).toString();
          String preins=tbprod.getValueAt(fila, 5).toString();
        int c=0;
        int j=0;
         String  cant=JOptionPane.showInputDialog("ingrese cantidad");
        if((cant.equals("")) || (cant.equals("0")))
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
                String col="";
                try{
                    Statement st3= cn.createStatement();
                    ResultSet rs1 = st3.executeQuery(sql2);
                if(rs1.next())
                {              
                    col=rs1.getString("cod_color");
                }  
                }catch(SQLException ex){
            
                }
                int canting=Integer.parseInt(cant);
                int comp=Integer.parseInt(comparar(codins,cod,col));
                if(canting>comp)
                {
                    JOptionPane.showMessageDialog(this,"Ud. no cuenta con el stock apropiado");
                }
                else
                {
                    for(int i=0;i<Boleta.tbdetbol.getRowCount();i++)
                {
            Object com=Boleta.tbdetbol.getValueAt(i,0);
            Object tall=Boleta.tbdetbol.getValueAt(i,2);
            if(codins.equals(com)&&talla.equals(tall))
            {
                j=i;
                Boleta.tbdetbol.setValueAt(cant, i, 4);
                c=c+1;
       
            }
   
        }
        if(c==0)
        {
           
      
            dato[0]=codins;
            dato[1]=desins;
            dato[2]=talla;
            dato[3]=color;
            dato[4]=preins;
            dato[5]=cant;
            tabladet.addRow(dato);
        
            Boleta.tbdetbol.setModel(tabladet);
            
            
        }
             }
  
        }
        
    
        
        
        
    }
    } catch (Exception e) {
    }
}//GEN-LAST:event_mnenviarActionPerformed

    private void PmenorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PmenorActionPerformed
        // TODO add your handling code here:
        if(Pmenor.isSelected()){
            TipoPrecio=true;
            Pmayor.setSelected(false);
            cargarlistaproductosMenor("");
            System.out.print(TipoPrecio);
        }
    }//GEN-LAST:event_PmenorActionPerformed

    private void PmayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PmayorActionPerformed
        // TODO add your handling code here:
        if(Pmayor.isSelected()){
            TipoPrecio=false;
            Pmenor.setSelected(false);
            cargarlistaproductosMayor("");
            System.out.print(TipoPrecio);
        }
    }//GEN-LAST:event_PmayorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Pmayor;
    private javax.swing.JRadioButton Pmenor;
    private javax.swing.JButton btnmostrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem mnenviar;
    private javax.swing.JTable tbprod;
    private javax.swing.JTextField txtprod;
    // End of variables declaration//GEN-END:variables
conectar cc= new conectar();
Connection cn = cc.conexion();
}
