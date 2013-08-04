package org.kansus.jpad.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JPopupMenu.Separator;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

import org.kansus.jpad.util.IOStream;
import org.kansus.jpad.util.TextTransfer;

public class JavaPad extends JFrame {

	private static final long serialVersionUID = -8232116481515580406L;

	private JPanel contentPane;
	public JTextArea textArea = new JTextArea();
	private TextTransfer transfer = new TextTransfer();
	private StatusBar statusBar = new StatusBar();
	private IOStream io = new IOStream();
	static JavaPad frame;
	private UndoManager undoManager = new UndoManager();

	/**
	 * Launch the application.
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
				frame = new JavaPad();
				frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JavaPad() {
		initialize();
	}

	public void insertAtCursor(String myValue) {
		int startPos = textArea.getSelectionStart();
		int endPos = textArea.getSelectionEnd();
		textArea.setText(textArea.getText().substring(0, startPos) + myValue
				+ textArea.getText().substring(endPos, textArea.getText().length()));
		textArea.setSelectionStart(startPos + myValue.length());
	}

	private void initialize() {
		textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
			public void undoableEditHappened(UndoableEditEvent e) {
				undoManager.addEdit(e.getEdit());
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				// Se o texto atual for diferente do último texto salvo
				if (!textArea.getText().equals(io.contents.toString())) {
					int result = JOptionPane.showConfirmDialog(frame, "Deseja salvar as alterações neste documento?",
							"JPad", JOptionPane.YES_NO_CANCEL_OPTION);
					if (result == JOptionPane.YES_OPTION || result == JOptionPane.NO_OPTION) {
						if (result == JOptionPane.YES_OPTION) {
							// Se ainda não há um caminho especificado para o
							// documento atual
							if (io.file == null) {
								JFileChooser sfd = new JFileChooser();
								FileNameExtensionFilter filter = new FileNameExtensionFilter(
										"Documentos de texto (*.txt)", "txt");
								sfd.addChoosableFileFilter(filter);
								sfd.setFileFilter(filter);
								int response = sfd.showSaveDialog(getParent());
								if (response == JFileChooser.APPROVE_OPTION) {
									try {
										io.SaveFile(sfd.getSelectedFile().getAbsolutePath(), textArea.getText());
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							} else {
								try {
									io.SaveFile(io.file.getAbsolutePath(), textArea.getText());
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}
				} else {
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			}
		});
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(JavaPad.class.getResource("/org/kansus/jpad/res/icon.png")));
		setTitle("Sem t\u00EDtulo - JPad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("Arquivo");
		menuBar.add(mnFile);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		JMenuItem mntmNovo = new JMenuItem("Novo");
		mntmNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Se algo foi mudado em comparação com o último arquivo salvo
				if (!textArea.getText().equals(io.contents.toString())) {
					int result = JOptionPane.showConfirmDialog(frame, "Deseja salvar as alterações neste documento?",
							"JPad", JOptionPane.YES_NO_CANCEL_OPTION);
					if (result == JOptionPane.YES_OPTION || result == JOptionPane.NO_OPTION) {
						if (result == JOptionPane.YES_OPTION) {
							// Se ainda não há um caminho especificado para o
							// documento atual
							if (io.file == null) {
								JFileChooser sfd = new JFileChooser();
								FileNameExtensionFilter filter = new FileNameExtensionFilter(
										"Documentos de texto (*.txt)", "txt");
								sfd.addChoosableFileFilter(filter);
								sfd.setFileFilter(filter);
								int response = sfd.showSaveDialog(getParent());
								if (response == JFileChooser.APPROVE_OPTION) {
									try {
										io.SaveFile(sfd.getSelectedFile().getAbsolutePath(), textArea.getText());
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							} else {
								try {
									io.SaveFile(io.file.getAbsolutePath(), textArea.getText());
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
						textArea.setText("");
						undoManager.discardAllEdits();
						frame.setTitle(" Sem Título - JPad");
						io.contents = new StringBuilder();
						io.file = null;
					}
				} else {
					textArea.setText("");
					undoManager.discardAllEdits();
					frame.setTitle(" Sem Título - JPad");
					io.contents = new StringBuilder();
					io.file = null;
				}
			}
		});
		mntmNovo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnFile.add(mntmNovo);

		JMenuItem mntmAbrir = new JMenuItem("Abrir...");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Se algo foi mudado em comparação com o último arquivo salvo
				if (!textArea.getText().equals(io.contents.toString())) {
					int result = JOptionPane.showConfirmDialog(frame, "Deseja salvar as alterações neste documento?",
							"JPad", JOptionPane.YES_NO_CANCEL_OPTION);
					if (result == JOptionPane.YES_OPTION || result == JOptionPane.NO_OPTION) {
						if (result == JOptionPane.YES_OPTION) {
							// Se ainda não há um caminho especificado para o
							// documento atual
							if (io.file == null) {
								JFileChooser sfd = new JFileChooser();
								FileNameExtensionFilter filter = new FileNameExtensionFilter(
										"Documentos de texto (*.txt)", "txt");
								sfd.addChoosableFileFilter(filter);
								sfd.setFileFilter(filter);
								int response = sfd.showSaveDialog(getParent());
								if (response == JFileChooser.APPROVE_OPTION) {
									try {
										io.SaveFile(sfd.getSelectedFile().getAbsolutePath(), textArea.getText());
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							} else {
								try {
									io.SaveFile(io.file.getAbsolutePath(), textArea.getText());
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
						JFileChooser ofd = new JFileChooser();
						FileNameExtensionFilter filter = new FileNameExtensionFilter("Documentos de texto (*.txt)",
								"txt");
						ofd.addChoosableFileFilter(filter);
						ofd.setFileFilter(filter);
						int response = ofd.showOpenDialog(getParent());
						if (response == JFileChooser.APPROVE_OPTION) {
							try {
								textArea.setText("");
								textArea.setText(io.OpenFile(ofd.getSelectedFile().getAbsolutePath()));
							} catch (IOException e) {
								e.printStackTrace();
							}
							frame.setTitle(ofd.getSelectedFile().getName() + " - JPad");
						}
					}
				} else {
					JFileChooser ofd = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Documentos de texto (*.txt)", "txt");
					ofd.addChoosableFileFilter(filter);
					ofd.setFileFilter(filter);
					int response = ofd.showOpenDialog(getParent());
					if (response == JFileChooser.APPROVE_OPTION) {
						try {
							textArea.setText("");
							textArea.setText(io.OpenFile(ofd.getSelectedFile().getAbsolutePath()));
						} catch (IOException e) {
							e.printStackTrace();
						}
						frame.setTitle(ofd.getSelectedFile().getName() + " - JPad");
					}
				}
			}
		});
		mntmAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnFile.add(mntmAbrir);

		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mntmSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Se ainda não há um caminho especificado para o documento
				// atual
				if (io.file == null) {
					JFileChooser sfd = new JFileChooser();
					File f = new File("*.txt");
					sfd.setSelectedFile(f);
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Documentos de texto (*.txt)", "txt");
					sfd.addChoosableFileFilter(filter);
					sfd.setFileFilter(filter);
					int response = sfd.showSaveDialog(getParent());
					if (response == JFileChooser.APPROVE_OPTION) {
						try {
							io.SaveFile(sfd.getSelectedFile().getAbsolutePath(), textArea.getText());
						} catch (IOException e) {
							e.printStackTrace();
						}
						frame.setTitle(sfd.getSelectedFile().getName() + " - JPad");
					}
				} else {
					try {
						io.SaveFile(io.file.getAbsolutePath(), textArea.getText());
					} catch (IOException e) {
						e.printStackTrace();
					}
					frame.setTitle(io.file.getName() + " - JPad");
				}
			}
		});
		mntmSalvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnFile.add(mntmSalvar);

		JMenuItem mntmSalvarComo = new JMenuItem("Salvar como...");
		mntmSalvarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser sfd = new JFileChooser();
				sfd.setDialogTitle("Salvar como...");
				File f = new File("*.txt");
				sfd.setSelectedFile(f);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Documentos de texto (*.txt)", "txt");
				sfd.addChoosableFileFilter(filter);
				sfd.setFileFilter(filter);
				int response = sfd.showSaveDialog(getParent());
				if (response == JFileChooser.APPROVE_OPTION) {
					try {
						io.SaveFile(sfd.getSelectedFile().getAbsolutePath(), textArea.getText());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		mnFile.add(mntmSalvarComo);

		Separator separator_3 = new Separator();
		mnFile.add(separator_3);

		JMenuItem mntmImprimir = new JMenuItem("Imprimir");
		mntmImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					textArea.print();
				} catch (PrinterException e) {
					e.printStackTrace();
				}
			}
		});
		mntmImprimir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		mnFile.add(mntmImprimir);

		Separator separator_2 = new Separator();
		mnFile.add(separator_2);
		mnFile.add(mntmSair);

		JMenu mnEditar = new JMenu("Editar");
		menuBar.add(mnEditar);

		JMenuItem mntmNewMenuItem = new JMenuItem("Desfazer");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (undoManager.canUndo())
						undoManager.undo();
				} catch (CannotRedoException cre) {
					cre.printStackTrace();
				}
			}

		});
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		mnEditar.add(mntmNewMenuItem);

		JMenuItem mntmRefazer = new JMenuItem("Refazer");
		mntmRefazer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (undoManager.canRedo())
						undoManager.redo();
				} catch (CannotRedoException cre) {
					cre.printStackTrace();
				}
			}
		});
		mntmRefazer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
		mnEditar.add(mntmRefazer);

		Separator mntmRecortar = new JPopupMenu.Separator();
		mnEditar.add(mntmRecortar);

		JMenuItem mntmRecortar_1 = new JMenuItem("Recortar");
		mntmRecortar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				transfer.setClipboardContents(textArea.getSelectedText());
				textArea.replaceRange("", textArea.getSelectionStart(), textArea.getSelectionEnd());
			}
		});
		mntmRecortar_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		mnEditar.add(mntmRecortar_1);

		JMenuItem mntmCopiar = new JMenuItem("Copiar");
		mntmCopiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				transfer.setClipboardContents(textArea.getSelectedText());
			}
		});
		mntmCopiar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mnEditar.add(mntmCopiar);

		JMenuItem mntmColar = new JMenuItem("Colar");
		mntmColar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insertAtCursor(transfer.getClipboardContents());
			}
		});
		mntmColar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		mnEditar.add(mntmColar);

		JMenuItem mntmExcluir = new JMenuItem("Excluir");
		mntmExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.replaceRange("", textArea.getSelectionStart(), textArea.getSelectionEnd());
			}
		});
		mntmExcluir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		mnEditar.add(mntmExcluir);

		Separator separator = new Separator();
		mnEditar.add(separator);

		JMenu mnFormatar = new JMenu("Formatar");
		menuBar.add(mnFormatar);

		final JCheckBoxMenuItem chckbxmntmQuebraAutomticaDe = new JCheckBoxMenuItem("Quebra autom\u00E1tica de linha");
		chckbxmntmQuebraAutomticaDe.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (chckbxmntmQuebraAutomticaDe.isSelected()) {
					textArea.setLineWrap(true);
				} else {
					textArea.setLineWrap(false);
				}
			}
		});
		mnFormatar.add(chckbxmntmQuebraAutomticaDe);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Fonte...");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				JFontChooser font = new JFontChooser(textArea.getFont());
				font.show();
				if (font.getReturnStatus() == JFontChooser.RET_OK)
					textArea.setFont(font.getFont());
			}
		});
		mnFormatar.add(mntmNewMenuItem_2);

		JMenu mnExibir = new JMenu("Exibir");
		menuBar.add(mnExibir);

		final JCheckBoxMenuItem mntmNewMenuItem_1 = new JCheckBoxMenuItem("Barra de status");
		mntmNewMenuItem_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (mntmNewMenuItem_1.isSelected()) {
					statusBar.setVisible(true);
				} else {
					statusBar.setVisible(false);
				}
			}
		});
		mnExibir.add(mntmNewMenuItem_1);

		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		JMenuItem mntmAjuda = new JMenuItem("Exibir Ajuda");
		mntmAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Não é possível que você seja tão burro ao ponto de precisar de"
						+ " ajuda para utilizar um programa tão simples!", "Ajuda", JOptionPane.OK_OPTION);
			}
		});
		mnAjuda.add(mntmAjuda);

		JSeparator separator_1 = new JSeparator();
		mnAjuda.add(separator_1);

		JMenuItem mntmSobreOJpad = new JMenuItem("Sobre o JPad");
		mntmSobreOJpad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				About about = new About();
				about.setLocationRelativeTo(null);
				about.setVisible(true);
			}
		});
		mnAjuda.add(mntmSobreOJpad);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		textArea.setFont(new Font("Arial", Font.PLAIN, 14));
		JScrollPane scroll = new JScrollPane(textArea);
		contentPane.add(scroll, BorderLayout.CENTER);

		JMenuItem mntmSelecionarTudo = new JMenuItem("Selecionar tudo");
		mntmSelecionarTudo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.selectAll();
			}
		});
		mntmSelecionarTudo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mnEditar.add(mntmSelecionarTudo);

		statusBar.setVisible(false);
		contentPane.add(statusBar, BorderLayout.SOUTH);
	}

	public class StatusBar extends JLabel {

		private static final long serialVersionUID = -465669134493206484L;

		/** Creates a new instance of StatusBar */
		public StatusBar() {
			super();
			super.setPreferredSize(new Dimension(100, 16));
			setMessage("Ready");
		}

		public void setMessage(String message) {
			setText(" " + message);
		}
	}
}
