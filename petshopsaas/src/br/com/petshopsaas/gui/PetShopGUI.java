package br.com.petshopsaas.gui;

import br.com.petshopsaas.dao.PetShopDAO;
import br.com.petshopsaas.model.PetShop;
import javax.swing.*;
import java.util.*;

public class PetShopGUI extends SimpleCrudFrame<PetShop> {
	private final PetShopDAO dao = new PetShopDAO();
	private final JTextField nome = new JTextField(15), cnpj = new JTextField(15), endereco = new JTextField(15),
			telefone = new JTextField(15);

	public PetShopGUI() {
		super("PetShops", new String[] { "ID", "Nome", "CNPJ", "Endereço", "Telefone" });
		addField("ID", idField);
		addField("Nome", nome);
		addField("CNPJ", cnpj);
		addField("Endereço", endereco);
		addField("Telefone", telefone);
		inicializarTela();
	}

	protected PetShop criarObjetoDoFormulario() {
		return new PetShop(parseInt(idField.getText()), nome.getText(), cnpj.getText(), endereco.getText(),
				telefone.getText());
	}

	protected Object[] toRow(PetShop p) {
		return new Object[] { p.getId(), p.getNome(), p.getCnpj(), p.getEndereco(), p.getTelefone() };
	}

	protected void preencherFormulario(PetShop p) {
		idField.setText(String.valueOf(p.getId()));
		nome.setText(p.getNome());
		cnpj.setText(p.getCnpj());
		endereco.setText(p.getEndereco());
		telefone.setText(p.getTelefone());
	}

	protected void limparFormulario() {
		idField.setText("");
		nome.setText("");
		cnpj.setText("");
		endereco.setText("");
		telefone.setText("");
	}

	protected void inserir(PetShop o) throws Exception {
		dao.salvar(o);
	}

	protected void atualizar(PetShop o) throws Exception {
		dao.atualizar(o);
	}

	protected void excluir(int id) throws Exception {
		dao.deletar(id);
	}

	protected PetShop buscar(int id) throws Exception {
		return dao.buscarPorId(id);
	}

	protected List<PetShop> listar() throws Exception {
		return dao.listarTodos();
	}
}
