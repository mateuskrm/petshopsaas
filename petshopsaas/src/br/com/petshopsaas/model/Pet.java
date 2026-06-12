package br.com.petshopsaas.model;

import br.com.petshopsaas.enums.EspeciePet;

public class Pet {
	private int id;
	private String nome;
	private EspeciePet especie;
	private String raca;
	private int idade;
	private double peso;
	private String observacoes;
	private int clienteId;

	public Pet() {
	}

	public Pet(int id, String nome, EspeciePet especie, String raca, int idade, double peso, String observacoes,
			int clienteId) {
		this.id = id;
		this.nome = nome;
		this.especie = especie;
		this.raca = raca;
		this.idade = idade;
		this.peso = peso;
		this.observacoes = observacoes;
		this.clienteId = clienteId;
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

	public EspeciePet getEspecie() {
		return especie;
	}

	public void setEspecie(EspeciePet especie) {
		this.especie = especie;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	@Override
	public String toString() {
		return id + " - " + nome;
	}
}
