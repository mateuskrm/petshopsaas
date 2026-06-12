package br.com.petshopsaas.gui;

import br.com.petshopsaas.dao.AgendamentoDAO;
import br.com.petshopsaas.enums.StatusAgendamento;
import br.com.petshopsaas.model.Agendamento;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AgendamentoGUI extends SimpleCrudFrame<Agendamento> {
	private final AgendamentoDAO dao = new AgendamentoDAO();
	private final JTextField petId = new JTextField(15), funcionarioId = new JTextField(15),
			servicoId = new JTextField(15), assinaturaId = new JTextField(15), dataHora = new JTextField(15),
			observacoes = new JTextField(15);
	private final JComboBox<StatusAgendamento> status = new JComboBox<>(StatusAgendamento.values());
	private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	public AgendamentoGUI() {
		super("Agendamentos", new String[] { "ID", "Pet ID", "Funcionário ID", "Serviço ID", "Assinatura ID",
				"Data/Hora", "Status", "Obs." });
		addField("ID", idField);
		addField("Pet ID", petId);
		addField("Funcionário ID", funcionarioId);
		addField("Serviço ID", servicoId);
		addField("Assinatura ID opcional", assinaturaId);
		addField("Data/hora yyyy-MM-dd HH:mm", dataHora);
		addField("Status", status);
		addField("Obs.", observacoes);
		inicializarTela();
	}

	protected Agendamento criarObjetoDoFormulario() {
		Integer ass = assinaturaId.getText().isBlank() ? null : parseInt(assinaturaId.getText());
		return new Agendamento(parseInt(idField.getText()), parseInt(petId.getText()),
				parseInt(funcionarioId.getText()), parseInt(servicoId.getText()), ass,
				LocalDateTime.parse(dataHora.getText(), fmt), (StatusAgendamento) status.getSelectedItem(),
				observacoes.getText());
	}

	protected Object[] toRow(Agendamento a) {
		return new Object[] { a.getId(), a.getPetId(), a.getFuncionarioId(), a.getServicoId(), a.getAssinaturaId(),
				a.getDataHora() == null ? "" : a.getDataHora().format(fmt), a.getStatus(), a.getObservacoes() };
	}

	protected void preencherFormulario(Agendamento a) {
		idField.setText(String.valueOf(a.getId()));
		petId.setText(String.valueOf(a.getPetId()));
		funcionarioId.setText(String.valueOf(a.getFuncionarioId()));
		servicoId.setText(String.valueOf(a.getServicoId()));
		assinaturaId.setText(a.getAssinaturaId() == null ? "" : String.valueOf(a.getAssinaturaId()));
		dataHora.setText(a.getDataHora() == null ? "" : a.getDataHora().format(fmt));
		status.setSelectedItem(a.getStatus());
		observacoes.setText(a.getObservacoes());
	}

	protected void limparFormulario() {
		idField.setText("");
		petId.setText("");
		funcionarioId.setText("");
		servicoId.setText("");
		assinaturaId.setText("");
		dataHora.setText(LocalDateTime.now().plusDays(1).withSecond(0).withNano(0).format(fmt));
		status.setSelectedItem(StatusAgendamento.AGENDADO);
		observacoes.setText("");
	}

	protected void inserir(Agendamento o) throws Exception {
		dao.salvar(o);
	}

	protected void atualizar(Agendamento o) throws Exception {
		dao.atualizar(o);
	}

	protected void excluir(int id) throws Exception {
		dao.deletar(id);
	}

	protected Agendamento buscar(int id) throws Exception {
		return dao.buscarPorId(id);
	}

	protected List<Agendamento> listar() throws Exception {
		return dao.listarTodos();
	}
}
