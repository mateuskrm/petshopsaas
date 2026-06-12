package br.com.petshopsaas.model;

import br.com.petshopsaas.enums.StatusAgendamento;
import java.time.LocalDateTime;

public class Agendamento {
	private int id;
	private int petId;
	private int funcionarioId;
	private int servicoId;
	private Integer assinaturaId;
	private LocalDateTime dataHora;
	private StatusAgendamento status;
	private String observacoes;

	public Agendamento() {
	}

	public Agendamento(int id, int petId, int funcionarioId, int servicoId, Integer assinaturaId,
			LocalDateTime dataHora, StatusAgendamento status, String observacoes) {
		this.id = id;
		this.petId = petId;
		this.funcionarioId = funcionarioId;
		this.servicoId = servicoId;
		this.assinaturaId = assinaturaId;
		this.dataHora = dataHora;
		this.status = status;
		this.observacoes = observacoes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

	public int getFuncionarioId() {
		return funcionarioId;
	}

	public void setFuncionarioId(int funcionarioId) {
		this.funcionarioId = funcionarioId;
	}

	public int getServicoId() {
		return servicoId;
	}

	public void setServicoId(int servicoId) {
		this.servicoId = servicoId;
	}

	public Integer getAssinaturaId() {
		return assinaturaId;
	}

	public void setAssinaturaId(Integer assinaturaId) {
		this.assinaturaId = assinaturaId;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public StatusAgendamento getStatus() {
		return status;
	}

	public void setStatus(StatusAgendamento status) {
		this.status = status;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
}
