package org.kansus.jpad.gui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

public class About extends JDialog {

	private static final long serialVersionUID = -716622569011031030L;

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			About dialog = new About();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public About() {
		initialize();
	}

	private void initialize() {
		setResizable(false);
		setTitle("Sobre o JPad");
		setIconImage(Toolkit.getDefaultToolkit().getImage("/org/kansus/jpad/res/icon.png"));
		setBounds(100, 100, 450, 267);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(117, 10, 200, 50);
		lblNewLabel.setIcon(new ImageIcon(About.class.getResource("/org/kansus/jpad/res/kansus.png")));
		contentPanel.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(36, 71, 362, 2);
		contentPanel.add(separator);

		JLabel lblNewLabel_1 = new JLabel("JPad \u00E9 um produto Kansus Corporation");
		lblNewLabel_1.setBounds(120, 84, 193, 21);
		contentPanel.add(lblNewLabel_1);

		JLabel lblTodosOsDireitos = new JLabel("Todos os direitos reservados");
		lblTodosOsDireitos.setBounds(147, 107, 139, 14);
		contentPanel.add(lblTodosOsDireitos);

		JLabel lblVerso = new JLabel("Vers\u00E3o 1.0");
		lblVerso.setBounds(193, 127, 57, 14);
		contentPanel.add(lblVerso);

		JLabel lblProgramaDesenvolvidoPor = new JLabel("Programa desenvolvido por Ed Charles Nascimento Ferreira");
		lblProgramaDesenvolvidoPor.setBounds(75, 144, 284, 14);
		contentPanel.add(lblProgramaDesenvolvidoPor);

		JLabel lblDuranteADisciplina = new JLabel("Durante a disciplina Estruturas de dados 2");
		lblDuranteADisciplina.setBounds(120, 164, 205, 14);
		contentPanel.add(lblDuranteADisciplina);

		JButton okButton = new JButton("OK");
		okButton.setBounds(361, 195, 63, 23);
		contentPanel.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		okButton.setActionCommand("OK");
		getRootPane().setDefaultButton(okButton);
	}
}
