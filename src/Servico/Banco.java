package Servico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {
	
	 private Banco() { 
	        
	    }

	    public static Connection getConexao() throws SQLException, ClassNotFoundException {

	        Class.forName("com.mysql.cj.jdbc.Driver");

	        return DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda","root", "");
	        
	    }

}
