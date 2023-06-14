package cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO {
	public Cliente adicionar(Cliente c) throws SQLException;
	public void remover(long id) throws SQLException;
	public void atualizar(long id, Cliente d) throws SQLException;
	public List<Cliente> pesquisarPorNome(String nome) throws SQLException;
}
