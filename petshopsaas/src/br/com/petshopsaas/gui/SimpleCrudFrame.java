package br.com.petshopsaas.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public abstract class SimpleCrudFrame<T> extends JFrame {
	protected final JTable tabela = new JTable();
	protected final DefaultTableModel tableModel;
	protected final JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 8));
	protected final JTextField idField = new JTextField();

	public SimpleCrudFrame(String titulo, String[] colunas) {
		super(titulo);
		setSize(900, 520);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		tableModel = new DefaultTableModel(colunas, 0) {
			@Override
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};
		tabela.setModel(tableModel);
		idField.setEditable(false);

		JButton novo = new JButton("Novo");
		JButton salvar = new JButton("Salvar");
		JButton excluir = new JButton("Excluir");
		JButton atualizar = new JButton("Atualizar lista");
		JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		botoes.add(novo);
		botoes.add(salvar);
		botoes.add(excluir);
		botoes.add(atualizar);

		add(new JScrollPane(tabela), BorderLayout.CENTER);
		add(formPanel, BorderLayout.EAST);
		add(botoes, BorderLayout.SOUTH);

		novo.addActionListener(e -> limparFormulario());
		salvar.addActionListener(e -> salvarAcao());
		excluir.addActionListener(e -> excluirAcao());
		atualizar.addActionListener(e -> carregarTabela());
		tabela.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting())
				preencherFormularioSelecionado();
		});
	}
	
    protected void inicializarTela() {
        carregarTabela();
    }

	protected void addField(String label, JComponent field) {
		formPanel.add(new JLabel(label));
		formPanel.add(field);
	}

	protected int getSelectedId() {
		int row = tabela.getSelectedRow();
		return row < 0 ? 0 : Integer.parseInt(tableModel.getValueAt(row, 0).toString());
	}

	protected int parseInt(String v) {
		return v == null || v.isBlank() ? 0 : Integer.parseInt(v.trim());
	}

	protected double parseDouble(String v) {
		return v == null || v.isBlank() ? 0 : Double.parseDouble(v.trim().replace(",", "."));
	}

	private void salvarAcao() {
		try {
			T obj = criarObjetoDoFormulario();
			if (idField.getText().isBlank())
				inserir(obj);
			else
				atualizar(obj);
			limparFormulario();
			carregarTabela();
			JOptionPane.showMessageDialog(this, "Registro salvo com sucesso.");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
		}
	}

	private void excluirAcao() {
		try {
			int id = getSelectedId();
			if (id == 0)
				return;
			excluir(id);
			limparFormulario();
			carregarTabela();
			JOptionPane.showMessageDialog(this, "Registro excluído.");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
		}
	}

	protected void carregarTabela() {
		try {
			tableModel.setRowCount(0);
			for (T obj : listar())
				tableModel.addRow(toRow(obj));
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Erro ao listar: " + ex.getMessage());
		}
	}

	protected void preencherFormularioSelecionado() {
		try {
			int id = getSelectedId();
			if (id != 0)
				preencherFormulario(buscar(id));
		} catch (Exception ignored) {
		}
	}

	protected abstract T criarObjetoDoFormulario() throws Exception;

	protected abstract Object[] toRow(T obj);

	protected abstract void preencherFormulario(T obj);

	protected abstract void limparFormulario();

	protected abstract void inserir(T obj) throws Exception;

	protected abstract void atualizar(T obj) throws Exception;

	protected abstract void excluir(int id) throws Exception;

	protected abstract T buscar(int id) throws Exception;

	protected abstract List<T> listar() throws Exception;
}
