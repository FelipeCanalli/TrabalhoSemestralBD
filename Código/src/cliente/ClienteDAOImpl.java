package cliente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import principal.Conexao;

public class ClienteDAOImpl implements ClienteDAO {

	private Connection con;

	public ClienteDAOImpl() throws ClassNotFoundException, SQLException, IOException {
		Conexao genericDao = new Conexao();
		con = genericDao.getConnection();
	}

	@Override
	public Cliente adicionar(Cliente c) throws  SQLException {
			String sql = "INSERT INTO pessoa (nome, cpf, tipo, logradouro,numero, complemento,telefone,email) "
					+ "VALUES (?,?,?,?,?,?,?,?)";

			PreparedStatement st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			st.setString(1, c.getNome());
			st.setString(2, c.getCpf());
			st.setString(3, c.getTipoEnd());
			st.setString(4, c.getLograEnd());
			st.setString(5, c.getNumEnd());
			st.setString(6, c.getCompEnd());
			st.setString(7, c.getTelefone());
			st.setString(8, c.getEmail());
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				c.setId(rs.getInt(1));
			}
		return c;
	}

	@Override
	public void remover(long id) throws SQLException {
		String sql = "DELETE FROM pessoa WHERE id = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setLong(1, id);
		st.executeUpdate();
	}

	@Override
	public void atualizar(long id, Cliente c) 
			throws SQLException {
		String sql = "UPDATE pessoa SET nome=?, cpf=?, tipo=?, logradouro=?, numero=?, "
				+ "complemento=?, telefone=?, email=?"
				+ " WHERE id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, c.getNome());
		st.setString(2, c.getCpf());
		st.setString(3, c.getTipoEnd());
		st.setString(4, c.getLograEnd());
		st.setString(5, c.getNumEnd());
		st.setString(6, c.getCompEnd());
		st.setString(7, c.getTelefone());
		st.setString(8, c.getEmail());
		st.setDouble(9, id);
		st.executeUpdate();		
	}

	@Override
	public List<Cliente> pesquisarPorNome(String nome) throws SQLException {
		List<Cliente> lista = new ArrayList<>();
		String sql = " SELECT * FROM pessoa "
				+ "WHERE nome LIKE ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+ nome + "%");
		ResultSet rs = st.executeQuery();
		while (rs.next()) { 
			Cliente c = new Cliente();
			c.setId( rs.getLong("id") );
			c.setNome( rs.getString("nome") );
			c.setCpf(rs.getString("cpf"));
			c.setTipoEnd(rs.getString("tipo"));
			c.setLograEnd(rs.getString("logradouro"));
			c.setNumEnd(rs.getString("numero"));
			c.setCompEnd(rs.getString("complemento"));
			c.setTelefone(rs.getString("telefone"));
			c.setEmail(rs.getString("email"));
			lista.add(c);
		}
		return lista;
	}
}
