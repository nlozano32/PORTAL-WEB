package com.proquinal.pep.managed_bean.seguridad;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
public class Prueba {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("---------------------- autenticarUsuario -----------------------------");
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	Statement st = null;
    	ResultSet rs = null;
    	String usuario = "810584";
    	String passwd = "13951979";
    	try{
	        String driverClass=null;
	        String connectionUrl=null; 
	        String userName=null;
	        String password=null;
	        
        	driverClass="oracle.jdbc.driver.OracleDriver";
	        connectionUrl="jdbc:oracle:thin:@172.16.1.8:1521/miami";
	        userName="PORTAL_PQN_PORTAL";	
	        password="portal10";	
          
          try{
        	  Class.forName(driverClass);
              conn = DriverManager.getConnection(connectionUrl,userName,password);
              System.out.println("---------------------- obtuvo conexion -----------------------------");
              
        	  String sql = "SELECT USUARIO, IDENTIFICACION FROM USUARIO WHERE USUARIO = ? AND CONTRASENA=?  ";
        	  System.out.println("-----Query -----------" + sql);
        	  //st = conn.createStatement();
        	  pstmt = conn.prepareStatement(sql);
        	  pstmt.setString(1,usuario);
        	  pstmt.setString(2,passwd);
        	  rs = pstmt.executeQuery();
        	  //rs = st.executeQuery(sql);
	          
	          ResultSetMetaData meta   = rs.getMetaData();
		        int               colmax = meta.getColumnCount();
		        System.out.println(colmax + "numeros");
		        int               i;
		        Object            o       = null;

		        for (;rs.next();) {
		            for (i = 0; i < colmax; ++i) {
		                o = rs.getObject(i + 1);    // Is SQL the first column is indexed
		                                            // with 1 not 0
		                System.out.print(o.toString() + " ");
		            }

		            System.out.println(" ");
		        }
		        
//	            int i = rs.getRow();
//	            System.out.println("Number of records: " + i);
//	            if ( i > 0 ){
//	            	System.out.println("Number of records: " + i);
//	            	pstmt.close();
//		            conn.close();
//	            }

            
          }
          catch (SQLException s){
            System.out.println("SQL statement is not executed!");
          }
        }
        catch (Exception e){
          e.printStackTrace();
        }

	}

}
