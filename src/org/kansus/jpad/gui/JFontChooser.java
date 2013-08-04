package org.kansus.jpad.gui;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class JFontChooser extends javax.swing.JDialog {

	private static final long serialVersionUID = 6075807849089266848L;
	/** A return status code - returned if Cancel button has been pressed */
	public static final int RET_CANCEL = 0;
	/** A return status code - returned if OK button has been pressed */
	public static final int RET_OK = 1;

	private int returnStatus = RET_CANCEL;
	private Font font;

	private javax.swing.JPanel fontPanel;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JList<String> lstSize;
	private javax.swing.JButton okButton;
	private javax.swing.JList<String> lstFont;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JList<String> lstStyle;
	private javax.swing.JPanel mainPanel;
	private javax.swing.JButton cancelButton;
	private javax.swing.JPanel previewPanel;
	private javax.swing.JLabel lblPreview;
	private javax.swing.JPanel buttonPanel;
	private javax.swing.JScrollPane jScrollPane3;

	public JFontChooser() {
		super((javax.swing.JFrame) null);
		this.font = new Font("Dialog", Font.PLAIN, 12);
		initComponents();
		lblPreview.setFont(font);
	}

	public JFontChooser(Font font) {
		super((javax.swing.JFrame) null);
		this.font = font;
		initComponents();
		lblPreview.setFont(font);
		lstFont.setSelectedValue(font.getFontName(), true);
		lstSize.setSelectedValue(String.valueOf(font.getSize()), true);
		lstStyle.setSelectedIndex(font.getStyle());
	}

	public JFontChooser(java.awt.Frame parent) {
		super(parent);
		this.font = new Font("Dialog", Font.PLAIN, 12);
		initComponents();
		lblPreview.setFont(font);
	}

	/** Creates new form JFontChooser */
	public JFontChooser(java.awt.Frame parent, Font font) {
		super(parent);
		this.font = font;
		initComponents();
		lblPreview.setFont(font);
	}

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
		doClose(RET_CANCEL);
	}

	/** Closes the dialog */
	private void closeDialog(java.awt.event.WindowEvent evt) {
		doClose(RET_CANCEL);
	}

	private void doClose(int retStatus) {
		returnStatus = retStatus;
		setVisible(false);
	}

	/** @return the font chosen by the user */
	public Font getFont() {
		return font;
	}

	/** @return the return status of this dialog - one of RET_OK or RET_CANCEL */
	public int getReturnStatus() {
		return returnStatus;
	}

	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		mainPanel = new javax.swing.JPanel();
		fontPanel = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		lstFont = new javax.swing.JList<String>(GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getAvailableFontFamilyNames());
		jScrollPane2 = new javax.swing.JScrollPane();
		lstStyle = new javax.swing.JList<String>();
		jScrollPane3 = new javax.swing.JScrollPane();
		lstSize = new javax.swing.JList<String>();
		previewPanel = new javax.swing.JPanel();
		lblPreview = new javax.swing.JLabel();
		buttonPanel = new javax.swing.JPanel();
		okButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();

		setTitle("Fonte");
		setModal(true);
		setResizable(false);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				closeDialog(evt);
			}
		});

		mainPanel.setLayout(new java.awt.GridLayout(2, 1));

		fontPanel.setLayout(new java.awt.GridBagLayout());

		jLabel1.setText("Font");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		gridBagConstraints.weightx = 2.0;
		fontPanel.add(jLabel1, gridBagConstraints);

		jLabel2.setText("Style");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		fontPanel.add(jLabel2, gridBagConstraints);

		jLabel3.setText("Size");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		gridBagConstraints.weightx = 0.2;
		fontPanel.add(jLabel3, gridBagConstraints);

		lstFont.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		lstFont.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
			public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
				lstFontValueChanged(evt);
			}
		});

		jScrollPane1.setViewportView(lstFont);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.ipadx = 1;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		gridBagConstraints.weightx = 2.0;
		fontPanel.add(jScrollPane1, gridBagConstraints);

		lstStyle.setModel(new javax.swing.AbstractListModel<String>() {

			private static final long serialVersionUID = 1L;
			private String[] strings = { "Plain", "Bold", "Italic", "Bold Italic" };

			public String getElementAt(int i) {
				return strings[i];
			}

			public int getSize() {
				return strings.length;
			}
		});
		lstStyle.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		lstStyle.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
			public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
				lstStyleValueChanged(evt);
			}
		});

		jScrollPane2.setViewportView(lstStyle);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.ipadx = 1;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		fontPanel.add(jScrollPane2, gridBagConstraints);

		lstSize.setModel(new javax.swing.AbstractListModel<String>() {

			private static final long serialVersionUID = 1L;
			private String[] strings = { "8", "10", "11", "12", "14", "16", "20", "24", "28", "36", "48", "72", "96" };

			public String getElementAt(int i) {
				return strings[i];
			}

			public int getSize() {
				return strings.length;
			}
		});
		lstSize.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		lstSize.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
			public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
				lstSizeValueChanged(evt);
			}
		});

		jScrollPane3.setViewportView(lstSize);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.ipadx = 1;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		gridBagConstraints.weightx = 0.2;
		fontPanel.add(jScrollPane3, gridBagConstraints);

		mainPanel.add(fontPanel);

		previewPanel.setLayout(new java.awt.BorderLayout());

		previewPanel.setBorder(new javax.swing.border.TitledBorder(null, "Preview",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12)));
		lblPreview.setFont(new java.awt.Font("Dialog", 0, 12));
		lblPreview.setText("ABCDEFG abcdefg");
		previewPanel.add(lblPreview, java.awt.BorderLayout.CENTER);

		mainPanel.add(previewPanel);

		getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

		buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

		okButton.setText("OK");
		okButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okButtonActionPerformed(evt);
			}
		});

		buttonPanel.add(okButton);

		cancelButton.setText("Cancel");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});

		buttonPanel.add(cancelButton);

		getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

		pack();
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new java.awt.Dimension(443, 429));
		setLocation((screenSize.width - 443) / 2, (screenSize.height - 429) / 2);
	}

	private void lstFontValueChanged(javax.swing.event.ListSelectionEvent evt) {
		font = new Font((String) lstFont.getSelectedValue(), font.getStyle(), font.getSize());
		lblPreview.setFont(font);
	}

	private void lstSizeValueChanged(javax.swing.event.ListSelectionEvent evt) {
		int size = Integer.parseInt((String) lstSize.getSelectedValue());
		font = new Font(font.getFamily(), font.getStyle(), size);
		lblPreview.setFont(font);
	}

	private void lstStyleValueChanged(javax.swing.event.ListSelectionEvent evt) {
		int style = -1;
		String selStyle = (String) lstStyle.getSelectedValue();
		if (selStyle == "Plain")
			style = Font.PLAIN;
		if (selStyle == "Bold")
			style = Font.BOLD;
		if (selStyle == "Italic")
			style = Font.ITALIC;
		if (selStyle == "Bold Italic")
			style = Font.BOLD + Font.ITALIC;

		font = new Font(font.getFamily(), style, font.getSize());
		lblPreview.setFont(font);
	}

	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
		doClose(RET_OK);
	}
}
