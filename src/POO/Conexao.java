package POO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

public class Conexao {
	
	public Connection getConnection() {
		
		try {
			
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/db_pedido","admin","123");
			
		} catch (SQLException e) {
			
			throw new RuntimeErrorException(null,"ERRO: "+e);
			

			
		}
		
	}
	

}
