package chat.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import chat.model.ConnectionSettings;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class SignInDialog extends JDialog {
	public static String IPADDRESS_PATTERN = 
	        "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
	public static String DEFAULT_HOST = "localhost";
	public static int DEFAULT_PORT = 8000;

	private final JPanel contentPanel = new JPanel();
	private JTextField jtfNickname;
	private JTextField jtfUrl;
	private JTextField jtfPort;
	private ChatGUI parent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SignInDialog dialog = new SignInDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SignInDialog(ChatGUI parent) {
		this.parent = parent;
		setTitle("Sign In Settimgs");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblNickname = new JLabel("Nickname");
		
		jtfNickname = new JTextField();
		jtfNickname.setColumns(10);
		
		JLabel lblChatServerUrl = new JLabel("Chat Server URL");
		
		jtfUrl = new JTextField();
		jtfUrl.setColumns(10);
		
		JLabel lblChatServerPort = new JLabel("Chat Server Port");
		
		jtfPort = new JTextField();
		jtfPort.setColumns(10);
		
		//set default values
		jtfUrl.setText(DEFAULT_HOST);
		jtfPort.setText("" + DEFAULT_PORT);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblChatServerUrl)
								.addComponent(lblNickname))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(jtfNickname, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
								.addComponent(jtfUrl, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblChatServerPort)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(jtfPort, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNickname)
						.addComponent(jtfNickname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblChatServerUrl)
						.addComponent(jtfUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblChatServerPort)
						.addComponent(jtfPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(103, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							ConnectionSettings settings = new ConnectionSettings(
								jtfUrl.getText(), 
								Integer.parseInt(jtfPort.getText()), 
								jtfNickname.getText());
							if(! Pattern.matches("[A-Za-z0-9_]+|" + IPADDRESS_PATTERN, 
									settings.getAddress())) {
								JOptionPane.showMessageDialog(SignInDialog.this, 
										"Invalid Char Server URL.", "Invalid Data", 
										JOptionPane.ERROR_MESSAGE);
							} else if (settings.getNickname().length() < 2) {
								JOptionPane.showMessageDialog(SignInDialog.this, 
										"Nickname should be at least 2 characters long.", "Invalid Data", 
										JOptionPane.ERROR_MESSAGE);
							} else {
								parent.getNetClient().login(settings);
								SignInDialog.this.dispose();
							}
						} catch (NumberFormatException ex){
							JOptionPane.showMessageDialog(SignInDialog.this, 
									"Invalid PORT number.", "Invalid Data", 
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SignInDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
