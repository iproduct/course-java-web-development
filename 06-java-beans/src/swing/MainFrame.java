package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bulb.BulbBean;
import facebean.FaceBean;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import simple.HelloBean;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import bulb.SwitchBean;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=61,239
	 */
	private final HelloBean helloBean = new HelloBean();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		helloBean.setCongratMessage("Hi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 928, 781);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		FaceBean faceBean = new FaceBean();
		faceBean.setEnabled(false);
		faceBean.setMouthWidth(160);
		faceBean.setBackground(Color.YELLOW);
		faceBean.setForeground(Color.MAGENTA);

		FaceBean faceBean_1 = new FaceBean();
		faceBean_1.setBackground(Color.ORANGE);

		BulbBean bulbBean = new BulbBean();

		JButton btnToggle = new JButton("TOGGLE");
		btnToggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bulbBean.setOn(!bulbBean.isOn());
				System.out.println("BulbBean is " + (bulbBean.isOn() ? "ON" : "OFF"));
				bulbBean.repaint();
			}
		});
		btnToggle.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		BulbBean bulbBean3 = new BulbBean();
		
		SwitchBean switchBean = new SwitchBean();
		switchBean.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent pcev) {
				System.out.println("Property changed: " + pcev.toString());
				if(pcev.getPropertyName().equals("closed")) {
					bulbBean3.toggle();
				}
			}
		});

		BulbBean bulbBean2 = new BulbBean();
		switchBean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equalsIgnoreCase("ON"))
					bulbBean2.switchOn();
				else
					bulbBean2.switchOff();
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(faceBean, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addComponent(faceBean_1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(bulbBean, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnToggle))
					.addGap(54)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(bulbBean3, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
						.addComponent(switchBean, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
						.addComponent(bulbBean2, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(145, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(bulbBean2, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(switchBean, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(faceBean_1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
								.addComponent(faceBean, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
								.addComponent(bulbBean, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnToggle)))
					.addGap(115)
					.addComponent(bulbBean3, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(206, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
