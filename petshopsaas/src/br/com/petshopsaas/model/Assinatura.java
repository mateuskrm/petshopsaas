package br.com.petshopsaas.model;

import br.com.petshopsaas.enums.StatusAssinatura;
import java.time.LocalDate;

public class Assinatura {
	private int id;
	private int clienteId;
	private int planoId;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private StatusAssinatura status;
	private int banhosRestantes;
	private int tosasRestantes;

	public Assinatura() {
	}

	public Assinatura(int id, int clienteId, int planoId, LocalDate dataInicio, LocalDate dataFim,
			StatusAssinatura status, int banhosRestantes, int tosasRestantes) {
		this.id = id;
		this.clienteId = clienteId;
		this.planoId = planoId;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.status = status;
		this.banhosRestantes = banhosRestantes;
		this.tosasRestantes = tosasRestantes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public int getPlanoId() {
		return planoId;
	}

	public void setPlanoId(int planoId) {
		this.planoId = planoId;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public StatusAssinatura getStatus() {
		return status;
	}

	public void setStatus(StatusAssinatura status) {
		this.status = status;
	}

	public int getBanhosRestantes() {
		return banhosRestantes;
	}

	public void setBanhosRestantes(int banhosRestantes) {
		this.banhosRestantes = banhosRestantes;
	}

	public int getTosasRestantes() {
		return tosasRestantes;
	}

	public void setTosasRestantes(int tosasRestantes) {
		this.tosasRestantes = tosasRestantes;
	}

	@Override
	public String toString() {
		return id + " - Cliente " + clienteId + " / Plano " + planoId;
	}
}
