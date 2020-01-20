/* COPYRIGHT & LICENSE HEADER
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
*
* IPTInvoiceServer is part of multi-tier client-server invoicing application 
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

package org.iproduct.invoicing.view;

import java.awt.BorderLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.swing.JApplet;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.filechooser.FileFilter;

import org.iproduct.invoicing.controller.ItemController;
import org.iproduct.invoicing.entity.old.Item;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;

public class MainWindow extends JApplet {
	private ItemController<Item> productController = new ItemController<>();
	
	private JFrame mainFrame;
	private AddProductDialog dlgAddProduct;
	private BrowseProductsDialog dlgBrowseProducts;
	private final Action opendb = new SwingAction();
	
	public MainWindow() {
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
	
	public MainWindow(JFrame mainFrame) {
		this();
		this.mainFrame = mainFrame;
	}
	
	public ItemController<Item> getProductController() {
		return productController;
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
		
		JMenuItem mntmAdd = new JMenuItem("Add Contragent");
		mnContragents.add(mntmAdd);
		
		JMenu mnProducts = new JMenu("Products");
		menuBar.add(mnProducts);
		
		JMenuItem mntmAddProduct = new JMenuItem("Add Product");
		mntmAddProduct.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));
		mnProducts.add(mntmAddProduct);
		
		JMenuItem mntmBrowseProducts = new JMenuItem("Browse Products");
		mntmBrowseProducts.addActionListener(ev -> {
			if(dlgBrowseProducts == null) {
				dlgBrowseProducts = 
						new BrowseProductsDialog(mainFrame, MainWindow.this);
			}
			dlgBrowseProducts.refresh();
			dlgBrowseProducts.setVisible(true);
		});
		mntmBrowseProducts.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK));
		mnProducts.add(mntmBrowseProducts);
		
		JSeparator separator_1 = new JSeparator();
		mnProducts.add(separator_1);
		
		JMenuItem mntmEditProduct = new JMenuItem("Edit Product");
		mnProducts.add(mntmEditProduct);
		
		JMenuItem mntmDeleteProduct = new JMenuItem("Delete Product");
		mnProducts.add(mntmDeleteProduct);
		
		mntmAddProduct.addActionListener( ev -> {
			if(dlgAddProduct == null) {
				dlgAddProduct = new AddProductDialog(mainFrame, MainWindow.this);
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
				JApplet applet = new MainWindow(mainFrame);
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
		public SwingAction() {
			putValue(NAME, "Open");
			putValue(SHORT_DESCRIPTION, "Load Database from file");
		}
		public void actionPerformed(ActionEvent e) {
		      JFileChooser c = new JFileChooser(".");
		      c.addChoosableFileFilter(new FileFilter() {
		  		@Override
		  		public String getDescription() {
		  			return "Invoicing Database File (*.db)";
		  		}
		  		@Override
		  		public boolean accept(File f) {
		  			return Pattern.matches(".*\\.db", f.getName()) || f.isDirectory();
		  		}
		        });
		      // Demonstrate "Open" dialog:
		      int rVal = c.showOpenDialog(MainWindow.this);
		      if(rVal == JFileChooser.APPROVE_OPTION) {
		    	File file = c.getSelectedFile();
		    	productController.setProductsDBFile(file);
		    	productController.readProductsFromFile();
		      }
		}
	}
}
