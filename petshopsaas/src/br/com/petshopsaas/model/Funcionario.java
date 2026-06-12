package br.com.petshopsaas.model;

import br.com.petshopsaas.enums.TipoUsuario;

public class Funcionario extends Usuario {
	private String cargo;
	private String especialidade;
	private int petShopId;

	public Funcionario() {
		setTipoUsuario(TipoUsuario.FUNCIONARIO);
	}

	public Funcionario(int id, String nome, String email, String senha, String telefone, String cargo,
			String especialidade, int petShopId) {
		super(id, nome, email, senha, telefone, TipoUsuario.FUNCIONARIO);
		this.cargo = cargo;
		this.especialidade = especialidade;
		this.petShopId = petShopId;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public int getPetShopId() {
		return petShopId;
	}

	public void setPetShopId(int petShopId) {
		this.petShopId = petShopId;
	}
}
