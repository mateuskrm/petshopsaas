package br.com.petshopsaas.model;

import br.com.petshopsaas.enums.TipoServico;

public class Servico {
	private int id;
	private String nome;
	private TipoServico tipoServico;
	private double valorAvulso;
	private int duracaoMinutos;

	public Servico() {
	}

	public Servico(int id, String nome, TipoServico tipoServico, double valorAvulso, int duracaoMinutos) {
		this.id = id;
		this.nome = nome;
		this.tipoServico = tipoServico;
		this.valorAvulso = valorAvulso;
		this.duracaoMinutos = duracaoMinutos;
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

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public double getValorAvulso() {
		return valorAvulso;
	}

	public void setValorAvulso(double valorAvulso) {
		this.valorAvulso = valorAvulso;
	}

	public int getDuracaoMinutos() {
		return duracaoMinutos;
	}

	public void setDuracaoMinutos(int duracaoMinutos) {
		this.duracaoMinutos = duracaoMinutos;
	}

	@Override
	public String toString() {
		return id + " - " + nome;
	}
}
