package cliente;

public class Cliente {
	private long id = 0;
	private String nome = "";
	private String cpf = "0";
	private String tipoEnd = "";
	private String lograEnd = "";
	private String numEnd = "0";
	private String compEnd = "";
	private String telefone = "0";
	private String email = "";

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTipoEnd() {
		return tipoEnd;
	}

	public void setTipoEnd(String tipoEnd) {
		this.tipoEnd = tipoEnd;
	}

	public String getLograEnd() {
		return lograEnd;
	}

	public void setLograEnd(String lograEnd) {
		this.lograEnd = lograEnd;
	}

	public String getNumEnd() {
		return numEnd;
	}

	public void setNumEnd(String numEnd) {
		this.numEnd = numEnd;
	}

	public String getCompEnd() {
		return compEnd;
	}

	public void setCompEnd(String compEnd) {
		this.compEnd = compEnd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() { 
		return "Nome: " + nome + "\nCPF: " + 
						cpf + "\nTelefone: " + telefone;
	}
	
}
