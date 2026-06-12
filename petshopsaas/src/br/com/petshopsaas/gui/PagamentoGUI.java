package br.com.petshopsaas.gui;

import br.com.petshopsaas.dao.PagamentoDAO;
import br.com.petshopsaas.enums.StatusPagamento;
import br.com.petshopsaas.model.Pagamento;
import javax.swing.*;
import java.time.LocalDate;
import java.util.*;

public class PagamentoGUI extends SimpleCrudFrame<Pagamento> {
	private final PagamentoDAO dao = new PagamentoDAO();
	private final JTextField assinaturaId = new JTextField(15), dataPagamento = new JTextField(15),
			dataVencimento = new JTextField(15), valor = new JTextField(15), forma = new JTextField(15);
	private final JComboBox<StatusPagamento> status = new JComboBox<>(StatusPagamento.values());

	public PagamentoGUI() {
		super("Pagamentos",
				new String[] { "ID", "Assinatura ID", "Data pagamento", "Vencimento", "Valor", "Status", "Forma" });
		addField("ID", idField);
		addField("Assinatura ID", assinaturaId);
		addField("Data pagamento yyyy-MM-dd", dataPagamento);
		addField("Vencimento yyyy-MM-dd", dataVencimento);
		addField("Valor", valor);
		addField("Status", status);
		addField("Forma pagamento", forma);
		inicializarTela();
	}

	protected Pagamento criarObjetoDoFormulario() {
		LocalDate pg = dataPagamento.getText().isBlank() ? null : LocalDate.parse(dataPagamento.getText());
		return new Pagamento(parseInt(idField.getText()), parseInt(assinaturaId.getText()), pg,
				LocalDate.parse(dataVencimento.getText()), parseDouble(valor.getText()),
				(StatusPagamento) status.getSelectedItem(), forma.getText());
	}

	protected Object[] toRow(Pagamento p) {
		return new Object[] { p.getId(), p.getAssinaturaId(), p.getDataPagamento(), p.getDataVencimento(), p.getValor(),
				p.getStatus(), p.getFormaPagamento() };
	}

	protected void preencherFormulario(Pagamento p) {
		idField.setText(String.valueOf(p.getId()));
		assinaturaId.setText(String.valueOf(p.getAssinaturaId()));
		dataPagamento.setText(p.getDataPagamento() == null ? "" : p.getDataPagamento().toString());
		dataVencimento.setText(String.valueOf(p.getDataVencimento()));
		valor.setText(String.valueOf(p.getValor()));
		status.setSelectedItem(p.getStatus());
		forma.setText(p.getFormaPagamento());
	}

	protected void limparFormulario() {
		idField.setText("");
		assinaturaId.setText("");
		dataPagamento.setText("");
		dataVencimento.setText(LocalDate.now().plusMonths(1).toString());
		valor.setText("");
		status.setSelectedItem(StatusPagamento.PENDENTE);
		forma.setText("");
	}

	protected void inserir(Pagamento o) throws Exception {
		dao.salvar(o);
	}

	protected void atualizar(Pagamento o) throws Exception {
		dao.atualizar(o);
	}

	protected void excluir(int id) throws Exception {
		dao.deletar(id);
	}

	protected Pagamento buscar(int id) throws Exception {
		return dao.buscarPorId(id);
	}

	protected List<Pagamento> listar() throws Exception {
		return dao.listarTodos();
	}
}
