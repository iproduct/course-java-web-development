package app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import beandemo.BulbBean;
import beandemo.SwitchBean;
import javax.swing.JCheckBox;

public class MainApp extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp frame = new MainApp();
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
	public MainApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		BulbBean bulbBean = new BulbBean();

		JButton btnToggle = new JButton("TOGGLE");
		btnToggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				bulbBean.toggle();
			}
		});
		btnToggle.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btnToggle.setForeground(Color.BLUE);

		BulbBean bulbBean2 = new BulbBean();
		bulbBean2.setColor(Color.MAGENTA);

		SwitchBean switchBean = new SwitchBean();
		switchBean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bulbBean2.toggle();
			}
		});

		BulbBean bulbBean3 = new BulbBean();
		bulbBean3.setColor(Color.green);
		
		JCheckBox chckbxVetoChage = new JCheckBox("Veto chage");

		SwitchBean switchBean3 = new SwitchBean();
		switchBean3.addVetoableChangeListener(new VetoableChangeListener() {
			public void vetoableChange(PropertyChangeEvent ev) throws PropertyVetoException  {
				System.out.println("Vetoable property change: " + ev);
				if (chckbxVetoChage.isSelected()) {
					throw new PropertyVetoException("NO!", ev);
				}
			}
		});
		switchBean3.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent ev) {
				System.out.println("Property changed: " + ev);
				if (ev.getPropertyName().equalsIgnoreCase("closed") && !ev.getOldValue().equals(ev.getNewValue())) {
					if ((Boolean) ev.getNewValue())
						bulbBean3.switchOn();
					else
						bulbBean3.switchOff();
				}
			}
		});


		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnToggle)
						.addComponent(bulbBean, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
					.addGap(99)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(bulbBean2, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
							.addComponent(bulbBean3, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addGap(95))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(switchBean, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
							.addComponent(switchBean3, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addGap(84))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(chckbxVetoChage, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
							.addGap(41))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(bulbBean3, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(switchBean3, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(bulbBean, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addGap(49)
							.addComponent(btnToggle))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(bulbBean2, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(switchBean, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))
					.addGap(78)
					.addComponent(chckbxVetoChage)
					.addContainerGap(169, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
