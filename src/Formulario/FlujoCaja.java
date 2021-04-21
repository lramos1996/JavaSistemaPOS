
/*
 * ConsultasBoletas.java
 *
 * Created @author elaprendiz http://www.youtube.com/user/JleoD7
 */
package Formulario;

import static Formulario.ConsultasVentas.tbboletas;
import static Formulario.Principal.jdpescritorio;
import claseConectar.conectar;
import java.net.URL;
//import java.awt.Image;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.*;
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Administrador
 */
public final class FlujoCaja extends javax.swing.JInternalFrame {

    /** Creates new form ConsultasBoletas */
    public FlujoCaja() {
        initComponents();
        fecha();
        //cargartodasBoletas();
      
        cargar();
        calcular();
        cargarEntradas();
        calcularEntradas();
        Total();
        //jDateChooser1.setEnabled(false);
        //cargarImagen();
    }
 
    private void Total(){
        String entrada=txtTotalEntradas.getText();
        String salidas=txtTotalSalidas.getText();
        double Entrada = Double.parseDouble(entrada);
        double Salida =  Double.parseDouble(salidas);
        double Final=0.0;
        try {
            Final=Entrada-Salida;
        } catch (NumberFormatException e) {
            Final=0.0;
        }  
        txtTotal.setText(""+Math.rint(Final*100)/100);
    }
            
    private void fecha(){
        try {
            txtnumero.setEnabled(false);
            rdbnnumero.setSelected(false);
            rdbbnfecha.setSelected(true);
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaDate = formato.parse(CajaRegistradora.fechaact());
            jDateChooser1.setDate(fechaDate);
       
        } catch (ParseException e) {
            System.err.println("Error! "+e.getMessage());
        }
    }
    private void cargartodasGastos()
    {
        DefaultTableModel tabla= new DefaultTableModel()
        {
             @Override
            public boolean isCellEditable(int row, int col)
            {
                return false;
            }
           /* public void valueChanged(ListSelectionEvent lse) {
                if (tbboletas.getSelectedRow() != -1) {
                    int fila = tbboletas.getSelectedRow();
                    System.out.println(".valueChanged() "+fila);
                    extraerDescripcion(tbboletas.getValueAt(fila, 0).toString());
                }
            }*/
        };
        String []titulos={"COD. GASTO","S/. GASTADO","FECHA","HORA"};
        tabla.setColumnIdentifiers(titulos);
        this.tbsalidas.setModel(tabla);
        String consulta= "select * from FlujoCaja where TipoOperacion = 'SALIDA'";
        String []Datos= new String [7];
        try {
            Statement st = cn.createStatement();
            ResultSet rs= st.executeQuery(consulta);
            while(rs.next())
            {
                Datos[0]=rs.getString("cod_caj");
                Datos[1]=rs.getString("Ingreso");
                Datos[2]=rs.getString("Fecha");
                Datos[3]=rs.getString("Hora");
                tabla.addRow(Datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlujoCaja.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        
    }
    private void cargartodaseEntradas()
    {
        DefaultTableModel tabla= new DefaultTableModel()
        {
             @Override
            public boolean isCellEditable(int row, int col)
            {
                return false;
            }
           /* public void valueChanged(ListSelectionEvent lse) {
                if (tbboletas.getSelectedRow() != -1) {
                    int fila = tbboletas.getSelectedRow();
                    System.out.println(".valueChanged() "+fila);
                    extraerDescripcion(tbboletas.getValueAt(fila, 0).toString());
                }
            }*/
        };
        String []titulos={"COD. GASTO","NUM. CAJ","S/. GASTADO","FECHA","HORA"};
        tabla.setColumnIdentifiers(titulos);
        this.tbentradas.setModel(tabla);
        String consulta= "select * from FlujoCaja where TipoOperacion = 'ENTRADA'";
        String []Datos= new String [7];
        try {
            Statement st = cn.createStatement();
            ResultSet rs= st.executeQuery(consulta);
            while(rs.next())
            {
                Datos[0]=rs.getString("cod_caj");
                Datos[1]=rs.getString("num_caj");
                Datos[2]=rs.getString("Ingreso");
                Datos[3]=rs.getString("Fecha");
                Datos[4]=rs.getString("Hora");
                tabla.addRow(Datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlujoCaja.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        
    }
    
    /*void cargarImagen(){
      //  "/Imagenes/negocios.jpg"
        ImageIcon fot = new ImageIcon("/Imagenes/negocios.jpg");
        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_DEFAULT));
        jLabel1.setIcon(icono);
        this.repaint();
    }*/
            

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        rdbnnumero = new javax.swing.JRadioButton();
        rdbbnfecha = new javax.swing.JRadioButton();
        rdbntodos = new javax.swing.JRadioButton();
        txtnumero = new javax.swing.JTextField();
        btnbuscador = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        btnImprimir = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanelEntradas = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtTotalEntradas = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbentradas = new javax.swing.JTable();
        jPanelSalidas = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbsalidas = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        txtTotalSalidas = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanelTotal = new javax.swing.JPanel();
        txtTotal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("GASTOS");
        setMaximumSize(new java.awt.Dimension(613, 321));
        setMinimumSize(new java.awt.Dimension(613, 321));

        jPanel2.setBackground(new java.awt.Color(227, 255, 255));
        jPanel2.setToolTipText("");

        jPanel1.setBackground(new java.awt.Color(227, 255, 255));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 153, 153), new java.awt.Color(0, 255, 255), new java.awt.Color(102, 0, 102), new java.awt.Color(153, 0, 153)));

        buttonGroup1.add(rdbnnumero);
        rdbnnumero.setText("Mostrar  por Nº:");
        rdbnnumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbnnumeroActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdbbnfecha);
        rdbbnfecha.setText("Mostrar por Fecha");
        rdbbnfecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbbnfechaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdbntodos);
        rdbntodos.setText("Mostrar todas:");
        rdbntodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbntodosActionPerformed(evt);
            }
        });

        btnbuscador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N
        btnbuscador.setBorder(null);
        btnbuscador.setBorderPainted(false);
        btnbuscador.setContentAreaFilled(false);
        btnbuscador.setHideActionText(true);
        btnbuscador.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnbuscador.setIconTextGap(2);
        btnbuscador.setInheritsPopupMenu(true);
        btnbuscador.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscarSelected.png"))); // NOI18N
        btnbuscador.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N
        btnbuscador.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnbuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscadorActionPerformed(evt);
            }
        });

        btnImprimir.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Imprimir.png"))); // NOI18N
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/contenido (1)++.png"))); // NOI18N
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/contenido.png"))); // NOI18N
        jButton1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/contenidoPresed.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                        .addComponent(rdbbnfecha)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdbnnumero)
                        .addGap(18, 18, 18)
                        .addComponent(txtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rdbntodos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnbuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 57, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdbnnumero))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(rdbbnfecha)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdbntodos)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(btnbuscador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanelEntradas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 153, 153), new java.awt.Color(51, 204, 255), new java.awt.Color(102, 0, 102), new java.awt.Color(204, 0, 204)), "ENTRADAS"));

        jPanel4.setBackground(new java.awt.Color(227, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 153, 153), new java.awt.Color(0, 204, 255), new java.awt.Color(102, 0, 102), new java.awt.Color(204, 0, 204)));

        txtTotalEntradas.setEditable(false);
        txtTotalEntradas.setBackground(new java.awt.Color(51, 255, 204));
        txtTotalEntradas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotalEntradas.setForeground(new java.awt.Color(51, 0, 51));

        jLabel5.setFont(new java.awt.Font("Thonburi", 1, 14)); // NOI18N
        jLabel5.setText("Total Entradas :    S/.");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTotalEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalEntradas)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        tbentradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbentradas);

        javax.swing.GroupLayout jPanelEntradasLayout = new javax.swing.GroupLayout(jPanelEntradas);
        jPanelEntradas.setLayout(jPanelEntradasLayout);
        jPanelEntradasLayout.setHorizontalGroup(
            jPanelEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEntradasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanelEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelEntradasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanelEntradasLayout.setVerticalGroup(
            jPanelEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEntradasLayout.createSequentialGroup()
                .addGap(0, 168, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelEntradasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(56, Short.MAX_VALUE)))
        );

        jPanelSalidas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 153, 153), new java.awt.Color(51, 204, 255), new java.awt.Color(102, 0, 102), new java.awt.Color(204, 0, 204)), "SALIDAS"));

        tbsalidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tbsalidas);

        jPanel5.setBackground(new java.awt.Color(227, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 153, 153), new java.awt.Color(0, 204, 255), new java.awt.Color(102, 0, 102), new java.awt.Color(204, 0, 204)));

        txtTotalSalidas.setEditable(false);
        txtTotalSalidas.setBackground(new java.awt.Color(51, 255, 204));
        txtTotalSalidas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotalSalidas.setForeground(new java.awt.Color(51, 0, 51));

        jLabel6.setFont(new java.awt.Font("Thonburi", 1, 14)); // NOI18N
        jLabel6.setText("Total Salidas :      S/.");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalSalidas)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelSalidasLayout = new javax.swing.GroupLayout(jPanelSalidas);
        jPanelSalidas.setLayout(jPanelSalidasLayout);
        jPanelSalidasLayout.setHorizontalGroup(
            jPanelSalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSalidasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelSalidasLayout.setVerticalGroup(
            jPanelSalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSalidasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelTotal.setBackground(new java.awt.Color(227, 255, 255));
        jPanelTotal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 153, 153), new java.awt.Color(0, 204, 255), new java.awt.Color(102, 0, 102), new java.awt.Color(204, 0, 204)));

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(51, 255, 204));
        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(51, 0, 51));

        jLabel7.setFont(new java.awt.Font("Thonburi", 1, 14)); // NOI18N
        jLabel7.setText("Total :      S/.");

        javax.swing.GroupLayout jPanelTotalLayout = new javax.swing.GroupLayout(jPanelTotal);
        jPanelTotal.setLayout(jPanelTotalLayout);
        jPanelTotalLayout.setHorizontalGroup(
            jPanelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTotalLayout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanelTotalLayout.setVerticalGroup(
            jPanelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTotalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal)
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanelSalidas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelEntradas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
private void rdbnnumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbnnumeroActionPerformed
// TODO add your handling code here:
    if(rdbnnumero.isSelected()==true)
    {
        txtnumero.setEnabled(true);
        txtnumero.requestFocus();
        jDateChooser1.setEnabled(false);
        jDateChooser1.setDate(null); 
    }
}//GEN-LAST:event_rdbnnumeroActionPerformed

private void rdbbnfechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbbnfechaActionPerformed
// TODO add your handling code here:
    if(rdbbnfecha.isSelected()==true)
    {
        jDateChooser1.setEnabled(true);
        txtnumero.setEnabled(false);
        txtnumero.setText("");       
    }
}//GEN-LAST:event_rdbbnfechaActionPerformed

private void rdbntodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbntodosActionPerformed
// TODO add your handling code here:
    if(rdbntodos.isSelected()==true)
    {
        jDateChooser1.setEnabled(false);
        jDateChooser1.setDate(null);
        txtnumero.setText("");
        txtnumero.setEnabled(false);
        cargartodasGastos();
        calcular();
        cargartodaseEntradas();
        calcularEntradas();
        Total();
    }
}//GEN-LAST:event_rdbntodosActionPerformed

private void btnbuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscadorActionPerformed
// TODO add your handling code here:
    cargar();
    calcular();
    cargarEntradas();
    calcularEntradas();
    Total();
}//GEN-LAST:event_btnbuscadorActionPerformed
public static void cargar(){
    String num=txtnumero.getText();
    
    String consulta="";
    if(rdbnnumero.isSelected()==true)
    {
        consulta= "SELECT * FROM FlujoCaja WHERE cod_caj like '%"+num+"%' and TipoOperacion = 'SALIDA'";
    }
    if(rdbbnfecha.isSelected()==true)
    {
        Date fecha=jDateChooser1.getDate();
        SimpleDateFormat formatofecha= new SimpleDateFormat("dd/MM/YYYY");
        String fec=""+formatofecha.format(fecha);
        consulta= "SELECT * FROM FlujoCaja WHERE Fecha='"+fec+"' and TipoOperacion = 'SALIDA'";
    }
    if(rdbntodos.isSelected()==true)
    {
        consulta= "select * from FlujoCaja where TipoOperacion = 'SALIDA'";
    }
    
       
        DefaultTableModel tabla= new DefaultTableModel()
        {
             @Override
            public boolean isCellEditable(int row, int col)
            {
                return false;
            }
            /*public void valueChanged(ListSelectionEvent lse) {
                if (tbboletas.getSelectedRow() != -1) {
                    int fila = tbboletas.getSelectedRow();
                    extraerDescripcion(tbboletas.getValueAt(fila, 0).toString());
                    System.out.println(".valueChanged() "+fila);
                }
            }*/
        };
        String []titulos={"COD. GASTO","S/. GASTADO","FECHA","HORA"};
        tabla.setColumnIdentifiers(titulos);
        tbsalidas.setModel(tabla);
        
        String []Datos= new String [7];
        try {
            Statement st = cn.createStatement();
            ResultSet rs= st.executeQuery(consulta);
            while(rs.next())
            {
                Datos[0]=rs.getString("cod_caj");
                Datos[1]=rs.getString("Ingreso");
                Datos[2]=rs.getString("Fecha");
                Datos[3]=rs.getString("Hora");
                tabla.addRow(Datos);    
            }
            
            /*String []titulos={"COD. GASTO","NUM. CAJ","S/. GASTADO","FECHA","HORA"};
        tabla.setColumnIdentifiers(titulos);
        this.tbsalidas.setModel(tabla);
        String consulta= "select * from FlujoCaja where TipoOperacion = 'ENTRADA'";
        String []Datos= new String [7];
        try {
            Statement st = cn.createStatement();
            ResultSet rs= st.executeQuery(consulta);
            while(rs.next())
            {
                Datos[0]=rs.getString("cod_caj");
                Datos[1]=rs.getString("num_caj");
                Datos[2]=rs.getString("Ingreso");
                Datos[3]=rs.getString("Fecha");
                Datos[4]=rs.getString("Hora");
                tabla.addRow(Datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlujoCaja.class.getName()).log(Level.SEVERE, null, ex);
        }*/
            //calcular();
        } catch (SQLException ex) {
            Logger.getLogger(FlujoCaja.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
}
public static void cargarEntradas(){
    String num=txtnumero.getText();
    
    String consulta="";
    if(rdbnnumero.isSelected()==true)
    {
        consulta= "SELECT * FROM FlujoCaja WHERE cod_caj like '%"+num+"%' and TipoOperacion = 'ENTRADA'";
    }
    if(rdbbnfecha.isSelected()==true)
    {
        Date fecha=jDateChooser1.getDate();
    SimpleDateFormat formatofecha= new SimpleDateFormat("dd/MM/YYYY");
    String fec=""+formatofecha.format(fecha);
        consulta= "SELECT * FROM FlujoCaja WHERE Fecha='"+fec+"' and TipoOperacion = 'ENTRADA'";
    }
    if(rdbntodos.isSelected()==true)
    {
        consulta= "select * from FlujoCaja where TipoOperacion = 'ENTRADA'";
    }
    
       
        DefaultTableModel tabla= new DefaultTableModel()
        {
             @Override
            public boolean isCellEditable(int row, int col)
            {
                return false;
            }
            /*public void valueChanged(ListSelectionEvent lse) {
                if (tbboletas.getSelectedRow() != -1) {
                    int fila = tbboletas.getSelectedRow();
                    extraerDescripcion(tbboletas.getValueAt(fila, 0).toString());
                    System.out.println(".valueChanged() "+fila);
                }
            }*/
        };
        String []titulos={"COD. GASTO","NUM. CAJ","S/. INGRESO","FECHA","HORA"};
        tabla.setColumnIdentifiers(titulos);
        tbentradas.setModel(tabla);
        
        String []Datos= new String [7];
        try {
            Statement st = cn.createStatement();
            ResultSet rs= st.executeQuery(consulta);
            while(rs.next())
            {
                Datos[0]=rs.getString("cod_caj");
                Datos[1]=rs.getString("num_caj");
                Datos[2]=rs.getString("Ingreso");
                Datos[3]=rs.getString("Fecha");
                Datos[4]=rs.getString("Hora");
                tabla.addRow(Datos);   
            }
            /*String []titulos={"COD. GASTO","S/. GASTADO","FECHA","HORA"};
        tabla.setColumnIdentifiers(titulos);
        tbboletas.setModel(tabla);
       
        String []Datos= new String [7];
        try {
            Statement st = cn.createStatement();
            ResultSet rs= st.executeQuery(consulta);
            while(rs.next())
            {
                Datos[0]=rs.getString("num_caj");
                Datos[1]=rs.getString("Ingreso");
                Datos[2]=rs.getString("Fecha");
                Datos[3]=rs.getString("Hora");
                tabla.addRow(Datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasGastos.class.getName()).log(Level.SEVERE, null, ex);
        }*/
           // calcularEntradas();
        } catch (SQLException ex) {
            Logger.getLogger(FlujoCaja.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
}


/*private void CargarEntradas(String producto,String fecha,String TipoVenta,String comprobante,String vendedor,String consulta){
    
    String factura = "select detallefactura.des_pro,detallefactura.talla_prod,detallefactura.cod_color,detallefactura.cant_pro,\n" +
"	factura.total,vendedor.nom_ven,factura.fec_fac,factura.hora_factura,'FACTURA' as Comprobante, factura.tipoVenta\n" +
"	from detallefactura\n" +
"	inner join factura\n" +
"	on detallefactura.num_fac=factura.num_fac and concat(detallefactura.des_pro,' ',detallefactura.talla_prod) like '%"+producto+"%'\n" +
"	and factura.fec_fac like '%"+fecha+"%' and factura.tipoVenta like '%"+TipoVenta+"%' \n" +
"	inner join vendedor\n" +
"	on factura.cod_ven=vendedor.cod_ven	 and vendedor.nom_ven like '%"+vendedor+"%'	";
    String boleta = "select detalleboleta.des_pro,detalleboleta.talla_prod,detalleboleta.cod_color,detalleboleta.cant_pro,\n" +
"	boleta.pre_tot,vendedor.nom_ven,boleta.fecha,boleta.hora_boleta,'BOLETA' as Comprobante, boleta.tipoVenta\n" +
"	from  detalleboleta\n" +
"	inner join boleta\n" +
"	on boleta.num_bol=detalleboleta.num_bol  and concat(detalleboleta.des_pro,' ',detalleboleta.talla_prod) like '%"+producto+"%'\n" +
"	and boleta.fecha like '%"+fecha+"%' and boleta.tipoVenta like '%"+TipoVenta+"%' \n" +
"	inner join vendedor\n" +
"	on boleta.cod_ven=vendedor.cod_ven and vendedor.nom_ven like '%"+vendedor+"%'	";
    String NotaVenta = "	select producto.descripcion,DetalleCajaRegistradora.cod_talla,DetalleCajaRegistradora.cod_color,DetalleCajaRegistradora.cantidad,\n" +
"	DetalleCajaRegistradora.pre_venta,vendedor.nom_ven, CajaRegistradora.fecha,CajaRegistradora.hora_boleta,'NOTA VENTA' as Comprobante, DetalleCajaRegistradora.tipoVenta\n" +
"	from DetalleCajaRegistradora\n" +
"	inner join CajaRegistradora\n" +
"	on CajaRegistradora.num_caj=DetalleCajaRegistradora.num_caj \n" +
"	inner join producto\n" +
"	on producto.cod_pro=DetalleCajaRegistradora.cod_pro and concat(producto.descripcion,' ',DetalleCajaRegistradora.cod_talla) like '%"+producto+"%'\n" +
"	and CajaRegistradora.fecha like '%"+fecha+"%' and DetalleCajaRegistradora.tipoVenta like '%"+TipoVenta+"%' \n" +
"	join vendedor\n" +
"	on vendedor.cod_ven=CajaRegistradora.cod_ven and vendedor.nom_ven like '%"+vendedor+"%'	";
    if(rdbntodos.isSelected()==true)
    {
       consulta= factura+" UNION "+boleta+" UNION "+NotaVenta;
    }
    switch ( comprobante ) {
      case "Boleta":
           consulta = boleta;
           break;
      case "Factura":
           consulta = factura;
           break;
      case "Ticket":
           consulta = NotaVenta;
           break;

      default:
           consulta= factura+" UNION "+boleta+" UNION "+NotaVenta;
           break;  
    }

        DefaultTableModel tabla= new DefaultTableModel()
        {
             @Override
            public boolean isCellEditable(int row, int col)
            {
                return false;
            }
        };
        String []titulos={"PRODUCTO","TALLA","COLOR","CANTIDAD","MONTO","VENDEDOR","FECHA","HORA","COMPROBANTE","TIPO VENTA"};
        tabla.setColumnIdentifiers(titulos);
        TableRowSorter sorter = new TableRowSorter(tabla);
        tbentradas.setModel(tabla);
        tbentradas.setRowSorter(sorter);
        String []Datos= new String [11];
        try {
            Statement st = cn.createStatement();
            ResultSet rs= st.executeQuery(consulta);
            while(rs.next())
            {
                Datos[0]=rs.getString(1);
                Datos[1]=rs.getString(2);
                Datos[2]=rs.getString(3);
                Datos[3]=rs.getString(4);
                Datos[4]=rs.getString(5);
                Datos[5]=rs.getString(6);    
                Datos[6]=rs.getString(7);
                Datos[7]=rs.getString(8);
                Datos[8]=rs.getString(9);
                Datos[9]=rs.getString(10);
                tabla.addRow(Datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
}*/

        
public static void calcularEntradas()
    {
        String total;
        double Total;
        double SumTot=0.0;
        
            /*can=Integer.parseInt(cant);
            imp=pre*can;
            dato[4]=Float.toString(imp);*/    
        for(int i=0;i<tbentradas.getRowCount();i++)
        {           
            total=tbentradas.getValueAt(i, 2).toString();
            Total=Double.parseDouble(total);       
            SumTot=SumTot+Total;
            // txtcod.setText(""+Math.rint(c*100)/100)   
        }
       
        txtTotalEntradas.setText(""+Math.rint(SumTot*100)/100);
       /* txtsubtotal.setText(Double.toString(subtotal));
        txtigv.setText(""+Math.rint(igv*100)/100);
        txttotal.setText(""+Math.rint(total*100)/100);*/
        
            
    }
    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
   
        String numfact=txtnumero.getText();
        String fecha="";
        Date Fech=jDateChooser1.getDate();
        try {
            SimpleDateFormat formatofecha= new SimpleDateFormat("dd/MM/YYYY");
            fecha =""+formatofecha.format(Fech);
        } catch (Exception e) {
            fecha="";
        }

        String total=txtTotalEntradas.getText();
        conectar miconexion = new conectar();
        URL archivo = this.getClass().getResource("reportesBoletas.jasper");
        JasperReport jr = null;
        try {
            jr= (JasperReport) JRLoader.loadObject(archivo);
            Map parametro = new HashMap();
            parametro.put("numfact",numfact);
            parametro.put("fecha",fecha);
            parametro.put("total",total);
            JasperPrint jp =JasperFillManager.fillReport(jr,parametro, miconexion.conexion());
            JasperPrintManager.printReport(jp, true);
        } catch (JRException e) {
            System.out.printf("ERROR! "+e.getMessage());
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    
        String numfact=txtnumero.getText();
        String fecha="";
        Date Fech=jDateChooser1.getDate();
        try {
            SimpleDateFormat formatofecha= new SimpleDateFormat("dd/MM/YYYY");
            fecha =""+formatofecha.format(Fech);
        } catch (Exception e) {
            fecha="";
        }
        
        String total=txtTotalEntradas.getText();
        conectar miconexion = new conectar();
        URL archivo = this.getClass().getResource("reportesBoletas.jasper");
        JasperReport jr = null;
        try {
            jr= (JasperReport) JRLoader.loadObject(archivo);
            Map parametro = new HashMap();
            parametro.put("numfact",numfact);
            parametro.put("fecha",fecha);
            parametro.put("total",total);
            JasperPrint jp =JasperFillManager.fillReport(jr,parametro, miconexion.conexion());
            JasperViewer jv = new JasperViewer(jp,false);
            jv.setVisible(true);
            jv.setTitle("Reporte Boletas");
        } catch (JRException e) {
            System.out.printf("ERROR! "+e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    public static void calcular()
    {
      
        
        String total;
        double Total;
        double SumTot=0.0;
        
            /*can=Integer.parseInt(cant);
            imp=pre*can;
            dato[4]=Float.toString(imp);*/    
        for(int i=0;i<tbsalidas.getRowCount();i++)
        {           
            total=tbsalidas.getValueAt(i, 1).toString();
            Total=Double.parseDouble(total);       
            SumTot=SumTot+Total;
            // txtcod.setText(""+Math.rint(c*100)/100)   
        }
        txtTotalSalidas.setText(""+Math.rint(SumTot*100)/100);
       /* txtsubtotal.setText(Double.toString(subtotal));
        txtigv.setText(""+Math.rint(igv*100)/100);
        txttotal.setText(""+Math.rint(total*100)/100);*/            
    }  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnbuscador;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    public static com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelEntradas;
    private javax.swing.JPanel jPanelSalidas;
    private javax.swing.JPanel jPanelTotal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JRadioButton rdbbnfecha;
    public static javax.swing.JRadioButton rdbnnumero;
    public static javax.swing.JRadioButton rdbntodos;
    public static javax.swing.JTable tbentradas;
    public static javax.swing.JTable tbsalidas;
    public static javax.swing.JTextField txtTotal;
    public static javax.swing.JTextField txtTotalEntradas;
    public static javax.swing.JTextField txtTotalSalidas;
    public static javax.swing.JTextField txtnumero;
    // End of variables declaration//GEN-END:variables
public static conectar cc= new conectar();
public static Connection cn = cc.conexion();
}
