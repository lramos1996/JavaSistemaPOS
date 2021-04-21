package claseConectar;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.PrintStream;
/**
 *
 * @author elaprendiz http://www.youtube.com/user/JleoD7
 */

public class conectar {
Connection conect = null;
   public Connection conexion()
    {
          //String url = "jdbc:sqlserver://192.168.1.98:1433;databaseName=ventas";
          /*String url ="jdbc:jtds:sqlserver://192.168.1.7:1433/ventas";
          String user = "Usuario_2";
          String password = "comramos5c";*/
          String url ="jdbc:jtds:sqlserver://192.168.1.12:1433/ventas";
          String user = "Usuario_2";
          String password = "comramos5c";
      try {
        
           //Cargamos el Driver MySQL 
           Class.forName("net.sourceforge.jtds.jdbc.Driver");
           conect = DriverManager.getConnection(url, user, password);
            System.out.println("conectado");
           //JOptionPane.showMessageDialog(null, "conectado");
           //Cargamos el Driver Access
           //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           //Conectar en red base 
           //String strConect = "jdbc:odbc:Driver=Microsoft Access Driver (*.mdb);DBQ=//servidor/bd_cw/cw.mdb";
           //Conectar Localmente
           //String strConect = "jdbc:odbc:Driver=Microsoft Access Driver (*.mdb);DBQ=D:/cwnetbeans/cw.mdb";
          //conect = DriverManager.getConnection(strConect,"",""); 
        } catch ( ClassNotFoundException e ) {
            System.out.println("No conectado");
            JOptionPane.showMessageDialog(null,"Error ");
        } catch (SQLException e) {
            System.out.println("No conectado");
            JOptionPane.showMessageDialog(null,"Error ");
        }
        return conect;
     
}}
