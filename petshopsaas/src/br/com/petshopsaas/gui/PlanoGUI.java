package br.com.petshopsaas.gui;

import br.com.petshopsaas.dao.PlanoDAO;
import br.com.petshopsaas.model.Plano;
import javax.swing.*;
import java.util.*;

public class PlanoGUI extends SimpleCrudFrame<Plano> {
	private final PlanoDAO dao = new PlanoDAO();
	private final JTextField nome = new JTextField(15), descricao = new JTextField(15), valor = new JTextField(15),
			banhos = new JTextField(15), tosas = new JTextField(15), petShopId = new JTextField(15);
	private final JCheckBox ativo = new JCheckBox();

	public PlanoGUI() {
		super("Planos", new String[] { "ID", "Nome", "Valor", "Banhos", "Tosas", "Ativo", "PetShop ID" });
		addField("ID", idField);
		addField("Nome", nome);
		addField("Descrição", descricao);
		addField("Valor mensal", valor);
		addField("Qtd. banhos", banhos);
		addField("Qtd. tosas", tosas);
		addField("Ativo", ativo);
		addField("PetShop ID", petShopId);
		inicializarTela();
	}

	protected Plano criarObjetoDoFormulario() {
		return new Plano(parseInt(idField.getText()), nome.getText(), descricao.getText(), parseDouble(valor.getText()),
				parseInt(banhos.getText()), parseInt(tosas.getText()), ativo.isSelected(),
				parseInt(petShopId.getText()));
	}

	protected Object[] toRow(Plano p) {
		return new Object[] { p.getId(), p.getNome(), p.getValorMensal(), p.getQuantidadeBanhos(),
				p.getQuantidadeTosas(), p.isAtivo(), p.getPetShopId() };
	}

	protected void preencherFormulario(Plano p) {
		idField.setText(String.valueOf(p.getId()));
		nome.setText(p.getNome());
		descricao.setText(p.getDescricao());
		valor.setText(String.valueOf(p.getValorMensal()));
		banhos.setText(String.valueOf(p.getQuantidadeBanhos()));
		tosas.setText(String.valueOf(p.getQuantidadeTosas()));
		ativo.setSelected(p.isAtivo());
		petShopId.setText(String.valueOf(p.getPetShopId()));
	}

	protected void limparFormulario() {
		idField.setText("");
		nome.setText("");
		descricao.setText("");
		valor.setText("");
		banhos.setText("");
		tosas.setText("");
		ativo.setSelected(true);
		petShopId.setText("");
	}

	protected void inserir(Plano o) throws Exception {
		dao.salvar(o);
	}

	protected void atualizar(Plano o) throws Exception {
		dao.atualizar(o);
	}

	protected void excluir(int id) throws Exception {
		dao.deletar(id);
	}

	protected Plano buscar(int id) throws Exception {
		return dao.buscarPorId(id);
	}

	protected List<Plano> listar() throws Exception {
		return dao.listarTodos();
	}
}
