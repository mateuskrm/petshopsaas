package br.com.petshopsaas.gui;

import br.com.petshopsaas.dao.AssinaturaDAO;
import br.com.petshopsaas.enums.StatusAssinatura;
import br.com.petshopsaas.model.Assinatura;
import javax.swing.*;
import java.time.LocalDate;
import java.util.*;

public class AssinaturaGUI extends SimpleCrudFrame<Assinatura> {
	private final AssinaturaDAO dao = new AssinaturaDAO();
	private final JTextField clienteId = new JTextField(15), planoId = new JTextField(15), inicio = new JTextField(15),
			fim = new JTextField(15), banhos = new JTextField(15), tosas = new JTextField(15);
	private final JComboBox<StatusAssinatura> status = new JComboBox<>(StatusAssinatura.values());

	public AssinaturaGUI() {
		super("Assinaturas",
				new String[] { "ID", "Cliente ID", "Plano ID", "Início", "Fim", "Status", "Banhos", "Tosas" });
		addField("ID", idField);
		addField("Cliente ID", clienteId);
		addField("Plano ID", planoId);
		addField("Data início yyyy-MM-dd", inicio);
		addField("Data fim yyyy-MM-dd", fim);
		addField("Status", status);
		addField("Banhos restantes", banhos);
		addField("Tosas restantes", tosas);
	}

	protected Assinatura criarObjetoDoFormulario() {
		return new Assinatura(parseInt(idField.getText()), parseInt(clienteId.getText()), parseInt(planoId.getText()),
				LocalDate.parse(inicio.getText()), LocalDate.parse(fim.getText()),
				(StatusAssinatura) status.getSelectedItem(), parseInt(banhos.getText()), parseInt(tosas.getText()));
	}

	protected Object[] toRow(Assinatura a) {
		return new Object[] { a.getId(), a.getClienteId(), a.getPlanoId(), a.getDataInicio(), a.getDataFim(),
				a.getStatus(), a.getBanhosRestantes(), a.getTosasRestantes() };
	}

	protected void preencherFormulario(Assinatura a) {
		idField.setText(String.valueOf(a.getId()));
		clienteId.setText(String.valueOf(a.getClienteId()));
		planoId.setText(String.valueOf(a.getPlanoId()));
		inicio.setText(String.valueOf(a.getDataInicio()));
		fim.setText(String.valueOf(a.getDataFim()));
		status.setSelectedItem(a.getStatus());
		banhos.setText(String.valueOf(a.getBanhosRestantes()));
		tosas.setText(String.valueOf(a.getTosasRestantes()));
	}

	protected void limparFormulario() {
		idField.setText("");
		clienteId.setText("");
		planoId.setText("");
		inicio.setText(LocalDate.now().toString());
		fim.setText(LocalDate.now().plusMonths(1).toString());
		status.setSelectedItem(StatusAssinatura.ATIVA);
		banhos.setText("");
		tosas.setText("");
		inicializarTela();
	}

	protected void inserir(Assinatura o) throws Exception {
		dao.salvar(o);
	}

	protected void atualizar(Assinatura o) throws Exception {
		dao.atualizar(o);
	}

	protected void excluir(int id) throws Exception {
		dao.deletar(id);
	}

	protected Assinatura buscar(int id) throws Exception {
		return dao.buscarPorId(id);
	}

	protected List<Assinatura> listar() throws Exception {
		return dao.listarTodos();
	}
}
