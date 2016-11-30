package com.proquinal.pep.managed_bean.seguridad;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {
	
	    Connection conn;    
	                        

	    public JDBCTest(String driverClass, 
	                        String connectionUrl, 
	                        String userName, 
	                        String password) throws Exception    // note more general exception
	    {

	        // Load the Database Engine JDBC driver
	        Class.forName(driverClass);
	        // connect to the database.   This will load the db files and start the
	        // database if it is not alread running.
	        // db_file_name_prefix is used to open or create files that hold the state
	        // of the db.
	        // It can contain directory names relative to the
	        // current working directory
	        conn = DriverManager.getConnection(connectionUrl,userName,password);
	    }

	    public void shutdown() throws SQLException {

	        conn.close();   // if there are no other open connection
	                        // db writes out to files and shuts down
	                        // this happens anyway at garbage collection
	                        // when program ends
	    }

	//use for SQL commands CREATE and SELECT
	    public synchronized void query(String expression) throws SQLException {

	        Statement st = null;
	        ResultSet rs = null;

	        st = conn.createStatement();            // statement objects can be reused with
	                                                // repeated calls to execute but we
	                                                // choose to make a new one each time
	        rs = st.executeQuery(expression);       // run the query

	        // do something with the result set.
	        dump(rs);
	        st.close();     // NOTE!! if you close a statement the associated ResultSet is
	                        // closed too
	                        // so you should copy the contents to some other object.
	                        // the result set is invalidated also  if you recycle an Statement
	                        // and try to execute some other query before the result set has been
	                        // completely examined.
	    }

	//use for SQL commands DROP and INSERT and UPDATE
	    public synchronized void update(String expression) throws SQLException {

	        Statement st = null;

	        st = conn.createStatement();                // statements

	        int i = st.executeUpdate(expression);       // run the query

	        if (i == -1) {
	            System.out.println("db error : " + expression);
	        }

	        st.close();
	    }    // void update()

	    public static void dump(ResultSet rs) throws SQLException {

	        // the order of the rows in a cursor
	        // are implementation dependent unless you use the SQL ORDER statement
	        ResultSetMetaData meta   = rs.getMetaData();
	        int               colmax = meta.getColumnCount();
	        int               i;
	        Object            o       = null;

	        // the result set is a cursor into the data.  You can only
	        // point to one row at a time
	        // assume we are pointing to BEFORE the first row
	        // rs.next() points to next row and returns true
	        // or false if there is no next row, which breaks the loop 
	        for (;rs.next();) {
	            for (i = 0; i < colmax; ++i) {
	                o = rs.getObject(i + 1);    // Is SQL the first column is indexed
	                                            // with 1 not 0
	                System.out.print(o.toString() + " ");
	            }

	            System.out.println(" ");
	        }
	    }                                       //void dump( ResultSet rs )

	    public static void main(String[] args) {

	        String driverClass=null;
	        String connectionUrl=null; 
	        String userName=null;
	        String password=null;
	        
	        //oracleDriver=${lib}/ojdbc14.jar
//	        driverClass="oracle.jdbc.driver.OracleDriver";
//	        connectionUrl="jdbc:oracle:thin:@localhost:1521/XE";
//	        userName="WEBLOGIC_GROUPSPACE";	
//	        password="WEBLOGIC_GROUPSPACE";
	        
	        driverClass="oracle.jdbc.driver.OracleDriver";
	        connectionUrl="jdbc:oracle:thin:@172.16.1.8:1521/miami";
	        userName="PORTAL_PQN_PORTAL";	
	        password="portal10";
	                 

	        JDBCTest db = null;
	        try {
	            db = new JDBCTest(driverClass,connectionUrl,userName,password);
	        } catch (Exception ex1) {
	            ex1.printStackTrace();          // could not start db

	            return;                         // bye bye
	        }


	        try {

	            // do a query
	            //db.query("SELECT usuario FROM usuario");
	        	//db.query("SELECT NOMBRE1_EMPLEADO FROM VMEMPLEADO");
	        	db.query("SELECT USUARIO, IDENTIFICACION FROM USUARIO");
	            // at end of program
	            db.shutdown();
	        } catch (SQLException ex3) {
	            ex3.printStackTrace();
	        }
	    }    // main()
	}



