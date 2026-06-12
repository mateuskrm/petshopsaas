package br.com.petshopsaas.gui;

import br.com.petshopsaas.dao.UsuarioDAO;
import br.com.petshopsaas.enums.TipoUsuario;
import br.com.petshopsaas.model.Usuario;
import javax.swing.*;
import java.util.*;

public class UsuarioGUI extends SimpleCrudFrame<Usuario> {
	private final UsuarioDAO dao = new UsuarioDAO();
	private final JTextField nome = new JTextField(15), email = new JTextField(15), senha = new JTextField(15),
			telefone = new JTextField(15);
	private final JComboBox<TipoUsuario> tipo = new JComboBox<>(TipoUsuario.values());

	public UsuarioGUI() {
		super("Usuários", new String[] { "ID", "Nome", "Email", "Telefone", "Tipo" });
		addField("ID", idField);
		addField("Nome", nome);
		addField("Email", email);
		addField("Senha", senha);
		addField("Telefone", telefone);
		addField("Tipo", tipo);
		inicializarTela();
	}

	protected Usuario criarObjetoDoFormulario() {
		return new Usuario(parseInt(idField.getText()), nome.getText(), email.getText(), senha.getText(),
				telefone.getText(), (TipoUsuario) tipo.getSelectedItem());
	}

	protected Object[] toRow(Usuario u) {
		return new Object[] { u.getId(), u.getNome(), u.getEmail(), u.getTelefone(), u.getTipoUsuario() };
	}

	protected void preencherFormulario(Usuario u) {
		idField.setText(String.valueOf(u.getId()));
		nome.setText(u.getNome());
		email.setText(u.getEmail());
		senha.setText(u.getSenha());
		telefone.setText(u.getTelefone());
		tipo.setSelectedItem(u.getTipoUsuario());
	}

	protected void limparFormulario() {
		idField.setText("");
		nome.setText("");
		email.setText("");
		senha.setText("");
		telefone.setText("");
		tipo.setSelectedIndex(0);
	}

	protected void inserir(Usuario o) throws Exception {
		dao.salvar(o);
	}

	protected void atualizar(Usuario o) throws Exception {
		dao.atualizar(o);
	}

	protected void excluir(int id) throws Exception {
		dao.deletar(id);
	}

	protected Usuario buscar(int id) throws Exception {
		return dao.buscarPorId(id);
	}

	protected List<Usuario> listar() throws Exception {
		return dao.listarTodos();
	}
}
