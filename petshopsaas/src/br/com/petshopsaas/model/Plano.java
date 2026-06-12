package br.com.petshopsaas.model;

public class Plano {
	private int id;
	private String nome;
	private String descricao;
	private double valorMensal;
	private int quantidadeBanhos;
	private int quantidadeTosas;
	private boolean ativo;
	private int petShopId;

	public Plano() {
	}

	public Plano(int id, String nome, String descricao, double valorMensal, int quantidadeBanhos, int quantidadeTosas,
			boolean ativo, int petShopId) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.valorMensal = valorMensal;
		this.quantidadeBanhos = quantidadeBanhos;
		this.quantidadeTosas = quantidadeTosas;
		this.ativo = ativo;
		this.petShopId = petShopId;
	}

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValorMensal() {
		return valorMensal;
	}

	public void setValorMensal(double valorMensal) {
		this.valorMensal = valorMensal;
	}

	public int getQuantidadeBanhos() {
		return quantidadeBanhos;
	}

	public void setQuantidadeBanhos(int quantidadeBanhos) {
		this.quantidadeBanhos = quantidadeBanhos;
	}

	public int getQuantidadeTosas() {
		return quantidadeTosas;
	}

	public void setQuantidadeTosas(int quantidadeTosas) {
		this.quantidadeTosas = quantidadeTosas;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public int getPetShopId() {
		return petShopId;
	}

	public void setPetShopId(int petShopId) {
		this.petShopId = petShopId;
	}

	@Override
	public String toString() {
		return id + " - " + nome;
	}
}
