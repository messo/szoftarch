package taskmeter.view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends ImagePanel {

	private final JButton loginButton = new JButton("Login");

	private JPasswordField passwordField;
	private JTextField textFieldUserName;

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		super(Toolkit.getDefaultToolkit().getImage(
				LoginPanel.class.getResource("/background/bg2.jpg")));
		System.out.println(getClass().getName());

		generateUI();
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	private void generateUI() {
		setSize(600, 600);
		setPreferredSize(getSize());

		// setBackground(new Color(0, 0, 0, 99));
		// setForeground(new Color(51, 102, 255));
		setBorder(null);
		setLayout(null);

		final JPanel logInPanel = new JPanel();
		add(logInPanel, BorderLayout.CENTER);
		logInPanel.setBackground(new Color(0, 0, 0, 60));
		logInPanel.setBounds(100, 160, 400, 280);

		final int width = 401, height = 281;

		logInPanel.setBounds(100, 160, 400, 280);
		logInPanel.setLayout(null);

		setMinimumSize(new Dimension(width, height));

		JLabel lblLoginTitle = new JLabel("Log In");
		lblLoginTitle.setForeground(new Color(51, 153, 255));
		lblLoginTitle.setBounds(12, 12, 70, 15);
		logInPanel.add(lblLoginTitle);

		JPanel decorationPanel = new JPanel();
		decorationPanel.setBackground(new Color(51, 153, 255));
		decorationPanel.setBounds(22, 39, 380, 2);
		logInPanel.add(decorationPanel);

		JPanel detailPanel = new JPanel();
		detailPanel.setBackground(new Color(0, 0, 0, 95));
		detailPanel.setBounds(50, 83, 300, 177);
		logInPanel.add(detailPanel);
		detailPanel.setLayout(null);

		JLabel lblUserName = new JLabel("User name");
		lblUserName.setBounds(12, 12, 117, 15);
		detailPanel.add(lblUserName);
		lblUserName.setForeground(Color.LIGHT_GRAY);

		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(12, 39, 276, 20);
		detailPanel.add(textFieldUserName);
		textFieldUserName.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(12, 71, 70, 15);
		detailPanel.add(lblPassword);
		lblPassword.setForeground(Color.LIGHT_GRAY);

		passwordField = new JPasswordField();
		passwordField.setBounds(12, 98, 276, 20);
		detailPanel.add(passwordField);

		loginButton.setBounds(168, 140, 120, 25);
		detailPanel.add(loginButton);
		loginButton.setFont(new Font("Dialog", Font.BOLD, 12));
		loginButton.setForeground(Color.LIGHT_GRAY);
		loginButton.setBackground(new Color(51, 153, 255));
	}

	public String getUserName() {
		return textFieldUserName.getText();
	}

	public char[] getPassword() {
		return passwordField.getPassword();
	}

	public void clearTextFields() {
		textFieldUserName.setText("");
		passwordField.setText("");
	}

	public void clearPasswordField() {
		passwordField.setText("");
	}

	public void setLoginButtonActionListener(final ActionListener al) {
		getLoginButton().addActionListener(al);
	}

	public void showErrorMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Aw, Snap!",
				JOptionPane.ERROR_MESSAGE);

	}
}
