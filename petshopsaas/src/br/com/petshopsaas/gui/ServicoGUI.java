package br.com.petshopsaas.gui;

import br.com.petshopsaas.dao.ServicoDAO;
import br.com.petshopsaas.enums.TipoServico;
import br.com.petshopsaas.model.Servico;
import javax.swing.*;
import java.util.*;

public class ServicoGUI extends SimpleCrudFrame<Servico> {
	private final ServicoDAO dao = new ServicoDAO();
	private final JTextField nome = new JTextField(15), valor = new JTextField(15), duracao = new JTextField(15);
	private final JComboBox<TipoServico> tipo = new JComboBox<>(TipoServico.values());

	public ServicoGUI() {
		super("Serviços", new String[] { "ID", "Nome", "Tipo", "Valor avulso", "Duração" });
		addField("ID", idField);
		addField("Nome", nome);
		addField("Tipo", tipo);
		addField("Valor avulso", valor);
		addField("Duração minutos", duracao);
		inicializarTela();
	}

	protected Servico criarObjetoDoFormulario() {
		return new Servico(parseInt(idField.getText()), nome.getText(), (TipoServico) tipo.getSelectedItem(),
				parseDouble(valor.getText()), parseInt(duracao.getText()));
	}

	protected Object[] toRow(Servico s) {
		return new Object[] { s.getId(), s.getNome(), s.getTipoServico(), s.getValorAvulso(), s.getDuracaoMinutos() };
	}

	protected void preencherFormulario(Servico s) {
		idField.setText(String.valueOf(s.getId()));
		nome.setText(s.getNome());
		tipo.setSelectedItem(s.getTipoServico());
		valor.setText(String.valueOf(s.getValorAvulso()));
		duracao.setText(String.valueOf(s.getDuracaoMinutos()));
	}

	protected void limparFormulario() {
		idField.setText("");
		nome.setText("");
		valor.setText("");
		duracao.setText("");
		tipo.setSelectedIndex(0);
	}

	protected void inserir(Servico o) throws Exception {
		dao.salvar(o);
	}

	protected void atualizar(Servico o) throws Exception {
		dao.atualizar(o);
	}

	protected void excluir(int id) throws Exception {
		dao.deletar(id);
	}

	protected Servico buscar(int id) throws Exception {
		return dao.buscarPorId(id);
	}

	protected List<Servico> listar() throws Exception {
		return dao.listarTodos();
	}
}
