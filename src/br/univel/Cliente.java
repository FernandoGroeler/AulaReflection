package br.univel;

@Tabela
public class Cliente {
	@Coluna(pk=true)	
	private int id;

	@Coluna
	private String nome;
	
	@Coluna
	private String endereco;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	

}
