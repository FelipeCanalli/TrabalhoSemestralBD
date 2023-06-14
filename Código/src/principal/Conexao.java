package principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import cliente.Cliente;

public class Conexao {

	private Connection con;

	public Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		File f = new File("./config.properties");
		System.out.println(f.getAbsolutePath());
		InputStream input = new FileInputStream(f);
		Properties prop = new Properties();
		// load a properties file
		prop.load(input);
		// get the property value and print it out
		Class.forName(prop.getProperty("db.driver"));
		System.out.println("Driver Mariadb carregado");
		con = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"),
				prop.getProperty("db.pass"));
		System.out.println("Conectado no banco de dados");
		return con;
	}
	
	public static void main(String[] args) {		
		Conexao c = new Conexao();
		Connection con;
		try {
			con = c.getConnection();
			// inserir(new Cliente(),con );
			System.out.println(con);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void inserir (Cliente c,Connection con) throws SQLException {
		String sql = "INSERT INTO pessoa (nome, cpf, tipo, logradouro,numero, complemento,telefone,email) "
				+ "VALUES (?,?,?,?,?,?,?,?)";

		PreparedStatement st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		st.setString(1, "Leandro Colevati");
		st.setString(2, "44474847788");
		st.setString(3, "Rua");
		st.setString(4, "Fatec");
		st.setString(5, "452");
		st.setString(6, "Casa");
		st.setString(7, "11554786488");
		st.setString(8, "Lecolevati@gmail.com");
		st.executeUpdate();
		ResultSet rs = st.getGeneratedKeys();
		if (rs.next()) {
			c.setId(rs.getInt(1));
			System.out.println(rs.getInt(1));
		}
	}
}
