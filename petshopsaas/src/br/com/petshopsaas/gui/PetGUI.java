package br.com.petshopsaas.gui;

import br.com.petshopsaas.dao.PetDAO;
import br.com.petshopsaas.enums.EspeciePet;
import br.com.petshopsaas.model.Pet;
import javax.swing.*;
import java.util.*;

public class PetGUI extends SimpleCrudFrame<Pet> {
	private final PetDAO dao = new PetDAO();
	private final JTextField nome = new JTextField(15), raca = new JTextField(15), idade = new JTextField(15),
			peso = new JTextField(15), observacoes = new JTextField(15), clienteId = new JTextField(15);
	private final JComboBox<EspeciePet> especie = new JComboBox<>(EspeciePet.values());

	public PetGUI() {
		super("Pets", new String[] { "ID", "Nome", "Espécie", "Raça", "Idade", "Peso", "Cliente ID" });
		addField("ID", idField);
		addField("Nome", nome);
		addField("Espécie", especie);
		addField("Raça", raca);
		addField("Idade", idade);
		addField("Peso", peso);
		addField("Obs.", observacoes);
		addField("Cliente ID", clienteId);
		inicializarTela();
	}

	protected Pet criarObjetoDoFormulario() {
		return new Pet(parseInt(idField.getText()), nome.getText(), (EspeciePet) especie.getSelectedItem(),
				raca.getText(), parseInt(idade.getText()), parseDouble(peso.getText()), observacoes.getText(),
				parseInt(clienteId.getText()));
	}

	protected Object[] toRow(Pet p) {
		return new Object[] { p.getId(), p.getNome(), p.getEspecie(), p.getRaca(), p.getIdade(), p.getPeso(),
				p.getClienteId() };
	}

	protected void preencherFormulario(Pet p) {
		idField.setText(String.valueOf(p.getId()));
		nome.setText(p.getNome());
		especie.setSelectedItem(p.getEspecie());
		raca.setText(p.getRaca());
		idade.setText(String.valueOf(p.getIdade()));
		peso.setText(String.valueOf(p.getPeso()));
		observacoes.setText(p.getObservacoes());
		clienteId.setText(String.valueOf(p.getClienteId()));
	}

	protected void limparFormulario() {
		idField.setText("");
		nome.setText("");
		raca.setText("");
		idade.setText("");
		peso.setText("");
		observacoes.setText("");
		clienteId.setText("");
		especie.setSelectedIndex(0);
	}

	protected void inserir(Pet o) throws Exception {
		dao.salvar(o);
	}

	protected void atualizar(Pet o) throws Exception {
		dao.atualizar(o);
	}

	protected void excluir(int id) throws Exception {
		dao.deletar(id);
	}

	protected Pet buscar(int id) throws Exception {
		return dao.buscarPorId(id);
	}

	protected List<Pet> listar() throws Exception {
		return dao.listarTodos();
	}
}
