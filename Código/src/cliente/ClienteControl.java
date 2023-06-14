package cliente;

import java.io.IOException;
import java.sql.SQLException;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClienteControl {
	private LongProperty id = new SimpleLongProperty(0);
	private StringProperty nome = new SimpleStringProperty("Ricardo Satoshi");
	private StringProperty cpf = new SimpleStringProperty("44464857415");
	private StringProperty tipoEnd = new SimpleStringProperty("Rua");
	private StringProperty logrEnd = new SimpleStringProperty("dos Bitcoins");
	private StringProperty numEnd = new SimpleStringProperty("2009");
	private StringProperty compEnd = new SimpleStringProperty("AP 200");
	private StringProperty telefone = new SimpleStringProperty("11994871544");
	private StringProperty email = new SimpleStringProperty("satoshi@gmail.com");

	private ObservableList<Cliente> clientes = FXCollections.observableArrayList();
	
	private ClienteDAO clienteDAO; 


	public ClienteControl() 
			throws ClassNotFoundException, SQLException, IOException { 
		clienteDAO = new ClienteDAOImpl();
	}
	
	public void novo() {
		fromEntity(new Cliente());
	}

	public void salvar() throws SQLException {
			Cliente c = new Cliente();
			c.setNome(nome.get());
			c.setCpf(cpf.get());
			c.setTipoEnd(tipoEnd.get());
			c.setLograEnd(logrEnd.get());
			c.setNumEnd(numEnd.get());
			c.setCompEnd(compEnd.get());
			c.setTelefone(telefone.get());
			c.setEmail(email.get());
			if (id.get() == 0) {
			c = clienteDAO.adicionar( c );
			clientes.add(c);
		} else {
				clienteDAO.atualizar(id.get(), c);
		}
	}

	public void excluir(Cliente c) throws SQLException {
		clienteDAO.remover(c.getId());
	}

	public void fromEntity(Cliente c) {
		id.set(c.getId());
		nome.set(c.getNome());
		cpf.set(c.getCpf());
		tipoEnd.set(c.getTipoEnd());
		logrEnd.set(c.getLograEnd());
		numEnd.set(c.getNumEnd());
		compEnd.set(c.getCompEnd());
		telefone.set(c.getTelefone());
		email.set(c.getEmail());
	}

	public void pesquisar() throws SQLException { 
		clientes.clear();
		clientes.addAll(clienteDAO.pesquisarPorNome(nome.get()));
		// System.out.println("Nome: " +nome.get());
	}

	public LongProperty idProperty() {
		return id;
	}

	public StringProperty nomeProperty() {
		return nome;
	}

	public StringProperty cpfProperty() {
		return cpf;
	}

	public StringProperty tipoEndProperty() {
		return tipoEnd;
	}

	public StringProperty logrEndProperty() {
		return logrEnd;
	}

	public StringProperty numEndProperty() {
		return numEnd;
	}

	public StringProperty compEndProperty() {
		return compEnd;
	}

	public StringProperty telefoneProperty() {
		return telefone;
	}

	public StringProperty emailProperty() {
		return email;
	}

	public ObservableList<Cliente> getList() {
		return clientes;
	}
}
