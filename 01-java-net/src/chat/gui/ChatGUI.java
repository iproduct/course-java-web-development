package chat.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import chat.MessageListener;
import chat.NetClient;
import chat.client.MyUDPChatClient;
import chat.client.TCPChatClient;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ChatGUI {

	private JFrame frame;
	private SignInDialog signDialog;
	private NetClient netClient;
	private JTextField jtfNewMessage;
	private JTextArea jtaMessages;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatGUI window = new ChatGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChatGUI() {
		netClient = new MyUDPChatClient(); //Choose protocol
		initialize();
	}

	public NetClient getNetClient() {
		return netClient;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//initialize Sign In dialog
		signDialog = new SignInDialog(this);
		
		//add incoming message listener
		netClient.addMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(String message) {
				System.out.println("!!!!! " + message);
				jtaMessages.append(message + "\n");		
			}
			
			@Override
			public void onError(String message) {
				System.out.println("ERROR: " + message);
				JOptionPane.showMessageDialog(frame, 
						message, "Chat Error", 
						JOptionPane.ERROR_MESSAGE);
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnChat = new JMenu("Chat");
		menuBar.add(mnChat);
		
		JMenuItem mntmSignin = new JMenuItem("Sign In");
		mntmSignin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signDialog.setVisible(true);
			}
		});
		mntmSignin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		mnChat.add(mntmSignin);
		
		JMenuItem mntmSignOut = new JMenuItem("Sign Out");
		mntmSignOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnChat.add(mntmSignOut);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		jtaMessages = new JTextArea();
		jtaMessages.setEditable(false);
		JScrollPane textArea = new JScrollPane(jtaMessages);
		
		JLabel lblMessages = new JLabel("Messages:");
		
		jtfNewMessage = new JTextField();
		jtfNewMessage.setColumns(10);
		jtfNewMessage.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar()==KeyEvent.VK_ENTER){
					sendMessage();
		        }	
			}
		});
		
		JLabel lblNewMessage = new JLabel("New message:");
		
		JButton btnNewButton = new JButton("Send Message");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
								.addComponent(lblMessages)
								.addComponent(lblNewMessage)
								.addComponent(jtfNewMessage, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(331)
							.addComponent(btnNewButton)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblMessages)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(lblNewMessage)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jtfNewMessage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(52)
					.addComponent(btnNewButton)
					.addContainerGap(75, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}

	private void sendMessage() {
		String message = jtfNewMessage.getText();
		if(message.length() > 0) {
			netClient.sendMessage(jtfNewMessage.getText());
			jtfNewMessage.setText("");
		}
	}
}
