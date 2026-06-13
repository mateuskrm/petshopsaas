package br.com.petshopsaas.gui;

import br.com.petshopsaas.dao.ClienteDAO;
import br.com.petshopsaas.model.Cliente;
import javax.swing.*;
import java.util.*;

public class ClienteGUI extends SimpleCrudFrame<Cliente> {
	private final ClienteDAO dao = new ClienteDAO();
	private final JTextField nome = new JTextField(15), email = new JTextField(15), senha = new JTextField(15),
			telefone = new JTextField(15), cpf = new JTextField(15), endereco = new JTextField(15),
			petShopId = new JTextField(15);

	public ClienteGUI() {
		super("Clientes", new String[] { "ID", "Nome", "Email", "Telefone", "CPF", "PetShop ID" });
		addField("ID", idField);
		addField("Nome", nome);
		addField("Email", email);
		addField("Senha", senha);
		addField("Telefone", telefone);
		addField("CPF", cpf);
		addField("Endereço", endereco);
		addField("PetShop ID", petShopId);
		inicializarTela();
	}

	protected Cliente criarObjetoDoFormulario() {
		return new Cliente(parseInt(idField.getText()), nome.getText(), email.getText(), senha.getText(),
				telefone.getText(), cpf.getText(), endereco.getText(), parseInt(petShopId.getText()));
	}

	protected Object[] toRow(Cliente c) {
		return new Object[] { c.getId(), c.getNome(), c.getEmail(), c.getTelefone(), c.getCpf(), c.getPetShopId() };
	}

	protected void preencherFormulario(Cliente c) {
		idField.setText(String.valueOf(c.getId()));
		nome.setText(c.getNome());
		email.setText(c.getEmail());
		senha.setText(c.getSenha());
		telefone.setText(c.getTelefone());
		cpf.setText(c.getCpf());
		endereco.setText(c.getEndereco());
		petShopId.setText(String.valueOf(c.getPetShopId()));
	}

	protected void limparFormulario() {
		idField.setText("");
		nome.setText("");
		email.setText("");
		senha.setText("");
		telefone.setText("");
		cpf.setText("");
		endereco.setText("");
		petShopId.setText("");
		inicializarTela();
	}

	protected void inserir(Cliente o) throws Exception {
		dao.salvar(o);
	}

	protected void atualizar(Cliente o) throws Exception {
		dao.atualizar(o);
	}

	protected void excluir(int id) throws Exception {
		dao.deletar(id);
	}

	protected Cliente buscar(int id) throws Exception {
		return dao.buscarPorId(id);
	}

	protected List<Cliente> listar() throws Exception {
		return dao.listarTodos();
	}
}
