package br.com.petshopsaas.gui;

import br.com.petshopsaas.dao.FuncionarioDAO;
import br.com.petshopsaas.model.Funcionario;
import javax.swing.*;
import java.util.*;

public class FuncionarioGUI extends SimpleCrudFrame<Funcionario> {
	private final FuncionarioDAO dao = new FuncionarioDAO();
	private final JTextField nome = new JTextField(15), email = new JTextField(15), senha = new JTextField(15),
			telefone = new JTextField(15), cargo = new JTextField(15), especialidade = new JTextField(15),
			petShopId = new JTextField(15);

	public FuncionarioGUI() {
		super("Funcionários",
				new String[] { "ID", "Nome", "Email", "Telefone", "Cargo", "Especialidade", "PetShop ID" });
		addField("ID", idField);
		addField("Nome", nome);
		addField("Email", email);
		addField("Senha", senha);
		addField("Telefone", telefone);
		addField("Cargo", cargo);
		addField("Especialidade", especialidade);
		addField("PetShop ID", petShopId);
		inicializarTela();
	}

	protected Funcionario criarObjetoDoFormulario() {
		return new Funcionario(parseInt(idField.getText()), nome.getText(), email.getText(), senha.getText(),
				telefone.getText(), cargo.getText(), especialidade.getText(), parseInt(petShopId.getText()));
	}

	protected Object[] toRow(Funcionario f) {
		return new Object[] { f.getId(), f.getNome(), f.getEmail(), f.getTelefone(), f.getCargo(), f.getEspecialidade(),
				f.getPetShopId() };
	}

	protected void preencherFormulario(Funcionario f) {
		idField.setText(String.valueOf(f.getId()));
		nome.setText(f.getNome());
		email.setText(f.getEmail());
		senha.setText(f.getSenha());
		telefone.setText(f.getTelefone());
		cargo.setText(f.getCargo());
		especialidade.setText(f.getEspecialidade());
		petShopId.setText(String.valueOf(f.getPetShopId()));
	}

	protected void limparFormulario() {
		idField.setText("");
		nome.setText("");
		email.setText("");
		senha.setText("");
		telefone.setText("");
		cargo.setText("");
		especialidade.setText("");
		petShopId.setText("");
	}

	protected void inserir(Funcionario o) throws Exception {
		dao.salvar(o);
	}

	protected void atualizar(Funcionario o) throws Exception {
		dao.atualizar(o);
	}

	protected void excluir(int id) throws Exception {
		dao.deletar(id);
	}

	protected Funcionario buscar(int id) throws Exception {
		return dao.buscarPorId(id);
	}

	protected List<Funcionario> listar() throws Exception {
		return dao.listarTodos();
	}
}
