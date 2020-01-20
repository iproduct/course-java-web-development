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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.iproduct.invoicing.entity.old.Item;

public class AddProductDialog extends JDialog {
	public static final String[] FIELD_NAMES = {
		"name", "price",  "vendor", "group"
	};
	
	private final MainWindow parent;
	private JPanel pnlForm = new JPanel();
	private JPanel pnlButtons = new JPanel();
	private JButton btnAdd = new JButton("Add Product"),
					btnCancel = new JButton("Cancel");
	
	
	private GridBagLayout gridbag = new GridBagLayout();
	private GridBagConstraints c = new GridBagConstraints();
	private Map<String, JTextField> fields = new HashMap<>();
	
	public AddProductDialog(JFrame topFrame, MainWindow parent) {
		super(topFrame, "Add New Product", false);
		
		this.parent = parent;
		setSize(600, 400);
		setLocationRelativeTo(null);
		
		// form panel
		pnlForm.setLayout(gridbag);
		
		c.fill = GridBagConstraints.NONE;
		c.weightx = 1.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.anchor = GridBagConstraints.CENTER;
		
		JLabel title = new JLabel("Add New Product");
		title.setFont(new Font("SansSerif", Font.PLAIN, 22));
		gridbag.setConstraints(title, c);
		pnlForm.add(title);
		
		//add text fields
		for(String lbl : FIELD_NAMES) {
			makeField(lbl);
		}
		
		add(pnlForm);
		
		//add buttons
		pnlButtons.setLayout(new FlowLayout());
		pnlButtons.add(btnAdd);
		
		btnAdd.addActionListener(ev -> {
			String name = fields.get(FIELD_NAMES[0]).getText().trim();
			if(name.length() == 0) {
				JOptionPane.showMessageDialog(this, 
						"Invalid product name. Please enter nonempty product name",
						"Product Error Dialog", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String priceStr = fields.get(FIELD_NAMES[1]).getText().trim();
			double price = 0;
			try{
				price = Double.parseDouble(priceStr);
			} catch (NumberFormatException ex){
				JOptionPane.showMessageDialog(this, 
						"Invalid product price. Please enter valid price.",
						"Product Error Dialog", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String vendor = fields.get(FIELD_NAMES[2]).getText().trim();
			String group = fields.get(FIELD_NAMES[3]).getText().trim();
			if(group.length() == 0)
					group = "Default";
			
			Item item = new Item(0L, name, vendor, price, group);	
			if(parent.getProductController().addItem(item))
				dispose();
			else {
				JOptionPane.showMessageDialog(this, 
						"Product already exists. Please enter new product.",
						"Product Error Dialog", JOptionPane.ERROR_MESSAGE);
			};
			
		});
		
		pnlButtons.add(btnCancel);
		add(BorderLayout.SOUTH, pnlButtons);
		
	}

	private void makeField(String label){
		StringBuilder sb = new StringBuilder(label.substring(0,1).toUpperCase());
		sb.append(label.substring(1,label.length()));
		JLabel lbl = new JLabel(sb.toString());
		c.weightx = 0;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(5, 30, 5, 10);
		gridbag.setConstraints(lbl, c);
		pnlForm.add(lbl);
		
		//add JTextFiled
		JTextField jtf = new JTextField();
		fields.put(label, jtf);
		c.weightx = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		gridbag.setConstraints(jtf, c);
		pnlForm.add(jtf);
	}
}
