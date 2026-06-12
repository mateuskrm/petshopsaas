package br.com.petshopsaas.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

	private final JPanel contentPanel = new JPanel(new BorderLayout());

	public MainFrame() {
		super("PetShop SaaS - Sistema Desktop");
		setSize(760, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setJMenuBar(criarMenuBar());
		add(contentPanel, BorderLayout.CENTER);
		exibirTelaInicial();
	}

	private JMenuBar criarMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menuCadastros = new JMenu("Cadastros");
		menuCadastros.add(itemMenu("PetShops", () -> abrirTela(new PetShopGUI())));
		menuCadastros.add(itemMenu("Usuários", () -> abrirTela(new UsuarioGUI())));
		menuCadastros.add(itemMenu("Clientes", () -> abrirTela(new ClienteGUI())));
		menuCadastros.add(itemMenu("Funcionários", () -> abrirTela(new FuncionarioGUI())));
		menuCadastros.add(itemMenu("Pets", () -> abrirTela(new PetGUI())));
		menuCadastros.addSeparator();
		menuCadastros.add(itemMenu("Planos", () -> abrirTela(new PlanoGUI())));
		menuCadastros.add(itemMenu("Serviços", () -> abrirTela(new ServicoGUI())));

		JMenu menuMovimentos = new JMenu("Movimentos");
		menuMovimentos.add(itemMenu("Assinaturas", () -> abrirTela(new AssinaturaGUI())));
		menuMovimentos.add(itemMenu("Agendamentos", () -> abrirTela(new AgendamentoGUI())));
		menuMovimentos.add(itemMenu("Pagamentos", () -> abrirTela(new PagamentoGUI())));

		JMenu menuSistema = new JMenu("Sistema");
		menuSistema.add(itemMenu("Tela inicial", this::exibirTelaInicial));
		menuSistema.addSeparator();
		menuSistema.add(itemMenu("Sair", this::dispose));

		menuBar.add(menuCadastros);
		menuBar.add(menuMovimentos);
		menuBar.add(menuSistema);
		return menuBar;
	}

	private JMenuItem itemMenu(String texto, Runnable acao) {
		JMenuItem item = new JMenuItem(texto);
		item.addActionListener(e -> acao.run());
		return item;
	}

	private void abrirTela(JFrame tela) {
		tela.setVisible(true);
	}

	private void exibirTelaInicial() {
		contentPanel.removeAll();

		JPanel painel = new JPanel(new BorderLayout(10, 10));
		painel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

		JLabel titulo = new JLabel("PetShop SaaS", SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 28));

		JLabel subtitulo = new JLabel(
				"Use o menu superior para navegar entre cadastros, assinaturas, agendamentos e pagamentos.",
				SwingConstants.CENTER);
		subtitulo.setFont(new Font("Arial", Font.PLAIN, 15));

		JPanel atalhos = new JPanel(new GridLayout(0, 3, 12, 12));
		atalhos.add(botaoAtalho("Clientes", () -> abrirTela(new ClienteGUI())));
		atalhos.add(botaoAtalho("Pets", () -> abrirTela(new PetGUI())));
		atalhos.add(botaoAtalho("Planos", () -> abrirTela(new PlanoGUI())));
		atalhos.add(botaoAtalho("Assinaturas", () -> abrirTela(new AssinaturaGUI())));
		atalhos.add(botaoAtalho("Agendamentos", () -> abrirTela(new AgendamentoGUI())));
		atalhos.add(botaoAtalho("Pagamentos", () -> abrirTela(new PagamentoGUI())));

		painel.add(titulo, BorderLayout.NORTH);
		painel.add(subtitulo, BorderLayout.CENTER);
		painel.add(atalhos, BorderLayout.SOUTH);

		contentPanel.add(painel, BorderLayout.CENTER);
		contentPanel.revalidate();
		contentPanel.repaint();
	}

	private JButton botaoAtalho(String texto, Runnable acao) {
		JButton botao = new JButton(texto);
		botao.setPreferredSize(new Dimension(160, 45));
		botao.addActionListener(e -> acao.run());
		return botao;
	}
}
