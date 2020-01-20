/* COPYRIGHT & LICENSE HEADER
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
*
* IPTInvoiceClient is part of multi-tier client-server invoicing application 
* using JPA, Swing and RMI technologies.
* 
* Copyright (c) 2012 - 2016 IPT - Intellectual Products & Technologies Ltd.
* All rights reserved.
*
* E-mail: office@iproduct.org
* Web: http://iproduct.org/
*
* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License (the "License")
* as published by the Free Software Foundation version 2 of the License.
* You may not use this file except in compliance with the License. You can
* obtain a copy of the License at root directory of this project in file
* LICENSE.txt.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License along
* with this program; if not, write to the Free Software Foundation, Inc.,
* 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*
* When distributing the software, include this COPYRIGHT & LICENSE HEADER
* in each file, and include the License file LICENSE.txt in the root directory
* of your distributable.
*
* GPL Classpath Exception:
* IPT - Intellectual Products & Technologies (IPT) designates this particular
* file as subject to the "Classpath" exception as provided by IPT in
* the GPL Version 2 License file that accompanies this code.
*
* In case you modify this file,
* please add the appropriate notice below the existing Copyright notices,
* with the fields enclosed in brackets {} replaced by your own identification:
* "Portions Copyright (c) {year} {name of copyright owner}"
*/

package org.iproduct.invoicing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.iproduct.invoicing.rmi.Invoicing;
import org.iproduct.invoicing.rmi.client.InvoicingRMIClient;
import org.iproduct.invoicing.view.AddContragentDialog;
import org.iproduct.invoicing.view.AddProductDialog;
import org.iproduct.invoicing.view.ManageContragentsDialog;
import org.iproduct.invoicing.view.ManageProductsDialog;

import java.awt.event.ActionListener;

public class IPTInvoicingClient extends JApplet {
	private static final long serialVersionUID = 1L;
	private Invoicing server;
	private String serviceUrl;

	private JFrame mainFrame;
	private AddProductDialog dlgAddProduct;
	private ManageProductsDialog dlgBrowseProducts;
	private AddContragentDialog dlgAddContragent;
	private ManageContragentsDialog dlgManageContragents;
	private final Action opendb = new SwingAction();

	public IPTInvoicingClient() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IPTInvoicingClient(JFrame mainFrame) {
		this();
		this.mainFrame = mainFrame;
	}

	public Invoicing getServer() {
		if(server == null) connectToServer();
		return server;
	}

	public void init() {
		JLabel label = new JLabel("");
		getContentPane().add(label, BorderLayout.NORTH);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.setAction(opendb);
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnFile.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnFile.add(mntmSave);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnFile.add(mntmExit);

		JMenu mnInvoices = new JMenu("Invoices");
		menuBar.add(mnInvoices);

		JMenu mnContragents = new JMenu("Contragents");
		menuBar.add(mnContragents);

		JMenuItem mntmAddContragent = new JMenuItem("Add Contragent");
		mntmAddContragent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));
		mntmAddContragent.addActionListener((ActionEvent arg0) -> {
			if (dlgAddContragent == null) {
				dlgAddContragent = new AddContragentDialog(mainFrame, IPTInvoicingClient.this);
			}
			dlgAddContragent.setVisible(true);			
		});
		mnContragents.add(mntmAddContragent);
		
		JMenuItem mntmManageContragents = new JMenuItem("Manage Contragents");
		mntmManageContragents.addActionListener((ActionEvent e) -> {
			if (dlgManageContragents == null) {
				dlgManageContragents = new ManageContragentsDialog(mainFrame, IPTInvoicingClient.this);
			}
			dlgManageContragents.refresh();
			dlgManageContragents.setVisible(true);
		});
		mntmManageContragents.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		mnContragents.add(mntmManageContragents);

		JMenu mnProducts = new JMenu("Products");
		menuBar.add(mnProducts);

		JMenuItem mntmAddProduct = new JMenuItem("Add Product");
		mntmAddProduct
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));
		mnProducts.add(mntmAddProduct);

		JMenuItem mntmManageProducts = new JMenuItem("Manage Products");
		mntmManageProducts.addActionListener(ev -> {
			if (dlgBrowseProducts == null) {
				dlgBrowseProducts = new ManageProductsDialog(mainFrame, IPTInvoicingClient.this);
			}
			dlgBrowseProducts.refresh();
			dlgBrowseProducts.setVisible(true);
		});
		mntmManageProducts.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK));
		mnProducts.add(mntmManageProducts);

		mntmAddProduct.addActionListener(ev -> {
			if (dlgAddProduct == null) {
				dlgAddProduct = new AddProductDialog(mainFrame, IPTInvoicingClient.this);
			}
			dlgAddProduct.setVisible(true);
		});

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnHelp.add(mntmHelp);

		JMenuItem mntmAboutProduct = new JMenuItem("About");
		mnHelp.add(mntmAboutProduct);

	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame mainFrame = new JFrame("Product Demo");
				JApplet applet = new IPTInvoicingClient(mainFrame);
				mainFrame.getContentPane().add(applet);
				applet.init();
				mainFrame.setSize(800, 600);
				mainFrame.setLocationRelativeTo(null);
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainFrame.setVisible(true);
			}
		});
	}

	private class SwingAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "Connect to Server");
			putValue(SHORT_DESCRIPTION, "Connect to server");
		}

		public void actionPerformed(ActionEvent e) {
			if (connectToServer())
				JOptionPane.showMessageDialog(mainFrame, "Successfully connected to: " + serviceUrl,
						"Server Connection Successfull", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public boolean connectToServer() {
			serviceUrl = JOptionPane.showInputDialog(
				"Please, enter Invocing server host and port (ex. //localhost:2016/Invoicing):",
				"//localhost:2016/Invoicing");
			if(serviceUrl == null) return false; //cancel connection
			Pattern p = Pattern.compile("((https?|ftp|gopher|telnet|file):)?((//)|(\\\\))+[\\d\\w_-]+(\\.[\\d\\w_-]+)*(:\\d+)?[\\w\\d:#@%;$()~/_?\\+-=\\\\\\.&]*");
			Matcher m = p.matcher(serviceUrl); 
			if (m.matches()) {
				try {
					server = InvoicingRMIClient.connectToServer(serviceUrl);
					return true;
				} catch (RemoteException | MalformedURLException | NotBoundException e1) {
					JOptionPane.showMessageDialog(mainFrame, e1, "Server Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(mainFrame, 
						"Invalid Server URL Error: " + serviceUrl, 
						"Invalid Server URL Error", JOptionPane.ERROR_MESSAGE);
			}
			return false;
	}
}
