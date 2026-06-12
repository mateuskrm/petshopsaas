package br.com.petshopsaas.model;

import br.com.petshopsaas.enums.TipoUsuario;

public class Cliente extends Usuario {
	private String cpf;
	private String endereco;
	private int petShopId;

	public Cliente() {
		setTipoUsuario(TipoUsuario.CLIENTE);
	}

	public Cliente(int id, String nome, String email, String senha, String telefone, String cpf, String endereco,
			int petShopId) {
		super(id, nome, email, senha, telefone, TipoUsuario.CLIENTE);
		this.cpf = cpf;
		this.endereco = endereco;
		this.petShopId = petShopId;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getPetShopId() {
		return petShopId;
	}

	public void setPetShopId(int petShopId) {
		this.petShopId = petShopId;
	}
}
