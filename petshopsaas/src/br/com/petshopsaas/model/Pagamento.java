package br.com.petshopsaas.model;

import br.com.petshopsaas.enums.StatusPagamento;
import java.time.LocalDate;

public class Pagamento {
	private int id;
	private int assinaturaId;
	private LocalDate dataPagamento;
	private LocalDate dataVencimento;
	private double valor;
	private StatusPagamento status;
	private String formaPagamento;

	public Pagamento() {
	}

	public Pagamento(int id, int assinaturaId, LocalDate dataPagamento, LocalDate dataVencimento, double valor,
			StatusPagamento status, String formaPagamento) {
		this.id = id;
		this.assinaturaId = assinaturaId;
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
		this.valor = valor;
		this.status = status;
		this.formaPagamento = formaPagamento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAssinaturaId() {
		return assinaturaId;
	}

	public void setAssinaturaId(int assinaturaId) {
		this.assinaturaId = assinaturaId;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public StatusPagamento getStatus() {
		return status;
	}

	public void setStatus(StatusPagamento status) {
		this.status = status;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
}
