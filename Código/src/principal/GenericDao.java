package principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDao {
	
private Connection c;

	public Connection getConnection() throws ClassNotFoundException, SQLException{
		String hostName = "localhost";
		String dbName = "pizzaria";
		String user = "felipe";
		String senha = "123456";
		
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		
		c = DriverManager.getConnection(String.format("jdbc:jtds:sqlserver://%s:65368;databaseName=%s;user=%s;password=%s;", hostName, dbName, user,senha ));
		System.out.println(c);
		return c;
	}
}
