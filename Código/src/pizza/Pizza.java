package pizza;

public class Pizza {
private long id = 0;
private String nome = "";
private Double preco = 0.00;
private String ingredientes = "";
private String tamanho = "Grande";


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


public Double getPreco() {
	return preco;
}


public void setPreco(Double preco) {
	this.preco = preco;
}


public String getIngredientes() {
	return ingredientes;
}


public void setIngredientes(String ingredientes) {
	this.ingredientes = ingredientes;
}


public String getTamanho() {
	return tamanho;
}


public void setTamanho(String tamanho) {
	this.tamanho = tamanho;
}


@Override
public String toString() {
	return "Pizza [id=" + id + ", nome=" + nome + ", preco=" + preco + ", ingredientes=" + ingredientes + ", tamanho="
			+ tamanho + "]";
}
}
