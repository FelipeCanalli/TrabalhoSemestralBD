package cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import principal.GenericDao;

public class ClienteDAOImpl implements ClienteDAO {

	private Connection con;

	public ClienteDAOImpl() throws ClassNotFoundException, SQLException {
		GenericDao genericDao = new GenericDao();
		con = genericDao.getConnection();
	}

	@Override
	public Cliente adicionar(Cliente c) throws  SQLException {
			String sql = "INSERT INTO pessoa (nome, cpf, tipo, logradouro,numero, complemento,telefone,email) "
					+ "VALUES (?, ?, ?, ?,?,?,?.?)";

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
	public Cliente pesquisarporId(long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizar(long id, Cliente d) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Cliente> pesquisarPorNome(String nome) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
