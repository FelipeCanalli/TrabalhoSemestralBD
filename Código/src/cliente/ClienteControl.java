package cliente;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClienteControl {
	private LongProperty id = new SimpleLongProperty(0);
	private StringProperty nome = new SimpleStringProperty("Felipe");
	private StringProperty cpf = new SimpleStringProperty("44464857415");
	private StringProperty tipoEnd = new SimpleStringProperty("Rua");
	private StringProperty LogrEnd = new SimpleStringProperty("Honorio");
	private StringProperty numEnd = new SimpleStringProperty("42");
	private StringProperty compEnd = new SimpleStringProperty("Casa 2");
	private StringProperty telefone = new SimpleStringProperty("11994871544");
	private StringProperty email = new SimpleStringProperty("@gmail.com");

	private ObservableList<Cliente> clientes = FXCollections.observableArrayList();

	private long idCounter = 1l;

	public void novo() {
		fromEntity(new Cliente());
	}

	public void salvar() {
		if (id.get() == 0) {
			Cliente c = new Cliente();
			c.setNome(nome.get());
			c.setCpf(cpf.get());
			c.setTipoEnd(tipoEnd.get());
			c.setLograEnd(LogrEnd.get());
			c.setNumEnd(numEnd.get());
			c.setCompEnd(compEnd.get());
			c.setTelefone(telefone.get());
			c.setEmail(email.get());

			c.setId(idCounter++);
			clientes.add(c);
		} else {
			for (int i = 0; i < clientes.size(); i++) {
				Cliente c = clientes.get(i);
				if (c.getId() == id.get()) {
					Cliente cNova = new Cliente();
					cNova.setId(c.getId());
					cNova.setNome(nome.get());
					cNova.setCpf(cpf.get());
					cNova.setTipoEnd(tipoEnd.get());
					cNova.setNumEnd(numEnd.get());
					cNova.setLograEnd(LogrEnd.get());
					cNova.setCompEnd(compEnd.get());
					cNova.setTelefone(telefone.get());
					cNova.setEmail(email.get());

					clientes.set(i, cNova);
					break;
				}
			}
		}
	}

	public void excluir(Cliente c) {
		clientes.remove(c);
	}

	public void fromEntity(Cliente c) {
		id.set(c.getId());
		nome.set(c.getNome());
		cpf.set(c.getCpf());
		tipoEnd.set(c.getTipoEnd());
		LogrEnd.set(c.getLograEnd());
		numEnd.set(c.getNumEnd());
		compEnd.set(c.getCompEnd());
		telefone.set(c.getTelefone());
		email.set(c.getEmail());

	}

	public void pesquisar() {
		for (Cliente c : clientes) {
			if (c.getNome().toLowerCase().contains(nome.get().toLowerCase())) {
				nome.set(c.getNome());
				cpf.set(c.getCpf());
				tipoEnd.set(c.getTipoEnd());
				LogrEnd.set(c.getLograEnd());
				numEnd.set(c.getNumEnd());
				compEnd.set(c.getCompEnd());
				telefone.set(c.getTelefone());
				email.set(c.getEmail());

				break;
			}
		}
		System.out.println("Nome: " + nome.get());
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

	public StringProperty LogrEndProperty() {
		return LogrEnd;
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
