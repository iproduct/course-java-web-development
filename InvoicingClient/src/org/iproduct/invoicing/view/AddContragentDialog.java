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

import org.iproduct.invoicing.IPTInvoicingClient;
import org.iproduct.invoicing.entity.Contragent;
import org.iproduct.invoicing.entity.ContragentType;
import org.iproduct.invoicing.entity.Item;
import org.iproduct.invoicing.jpa.exceptions.EntityAlreadyExistsException;

import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class AddContragentDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JPanel pnlForm = new JPanel();
	private JPanel pnlButtons = new JPanel();
	private JButton btnAdd = new JButton("Add Contragent"),
					btnCancel = new JButton("Cancel");
	
	
	private GridBagLayout gridbag = new GridBagLayout();
	private GridBagConstraints c = new GridBagConstraints();
	private Map<String, JTextField> fields = new HashMap<>();
	
	public AddContragentDialog(JFrame topFrame, IPTInvoicingClient parent) {
		super(topFrame, "Add New Contragent", false);
		
		setSize(600, 400);
		setLocationRelativeTo(null);
		
		// form panel
		pnlForm.setLayout(gridbag);
		
		c.fill = GridBagConstraints.NONE;
		c.weightx = 1.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.anchor = GridBagConstraints.CENTER;
		
		JLabel title = new JLabel("Add New Contragent");
		title.setFont(new Font("SansSerif", Font.PLAIN, 22));
		gridbag.setConstraints(title, c);
		pnlForm.add(title);
		
		//add text fields
		for(String lbl : Contragent.FIELD_NAMES) {
			makeField(lbl);
		}
		
		getContentPane().add(pnlForm);
		
		//add buttons
		pnlButtons.setLayout(new FlowLayout());
		pnlButtons.add(btnAdd);
		
		btnAdd.addActionListener(ev -> {
			String idNumberStr = fields.get(Contragent.FIELD_NAMES[0]).getText().trim();
			long idNumber = 0;
			try{
				idNumber = Long.parseLong(idNumberStr);
			} catch (NumberFormatException ex){
				JOptionPane.showMessageDialog(this, 
						"Invalid contragent ID number. Please enter valid ID (SSN or Bulstat).",
						"Contragent Error Dialog", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String name = fields.get(Contragent.FIELD_NAMES[1]).getText().trim();
			if(name.length() == 0) {
				JOptionPane.showMessageDialog(this, 
						"Invalid contragent name. Please enter nonempty contragent name",
						"Contragent Error Dialog", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String address = fields.get(Contragent.FIELD_NAMES[2]).getText().trim();
			if(address.length() == 0) {
				JOptionPane.showMessageDialog(this, 
						"Invalid contragent address. Please enter nonempty contragent address.",
						"Contragent Error Dialog", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String typeStr = fields.get(Contragent.FIELD_NAMES[3]).getText().trim();
			ContragentType type;
			try {
				type = Enum.valueOf(ContragentType.class, typeStr);
			} catch (IllegalArgumentException e){
				JOptionPane.showMessageDialog(this, 
						"Invalid contragent type. Please enter one of(PERSON, ORGANIZATION, VAT_ORGANIZATION).",
						"Contragent Error Dialog", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String accountablePerson = fields.get(Contragent.FIELD_NAMES[4]).getText().trim();
			String iban = fields.get(Contragent.FIELD_NAMES[5]).getText().trim();
			String bic = fields.get(Contragent.FIELD_NAMES[6]).getText().trim();
			Contragent contragent = new Contragent(idNumber, name, address, type, accountablePerson, iban, bic);	
			if(parent.getServer() != null) {
				try {
					contragent = parent.getServer().addContragent(contragent);
					JOptionPane.showMessageDialog(this, 
							"Contragent was successfully added: " + contragent.getName(),
							"Contragent Successfully Added", JOptionPane.INFORMATION_MESSAGE);
					clearFormFields();
					dispose();
				} catch (EntityAlreadyExistsException e) {
					JOptionPane.showMessageDialog(this, 
							"Contragent already exists. Please enter new contragent.",
							"Contragent Error Dialog", JOptionPane.ERROR_MESSAGE);
				} catch (RemoteException e) {
					JOptionPane.showMessageDialog(this, 
							"Error sending contragent to the server. Contragent NOT added.",
							"Server Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearFormFields();
				dispose();
			}
		});
		
		pnlButtons.add(btnCancel);
		getContentPane().add(BorderLayout.SOUTH, pnlButtons);
		
	}

	private void clearFormFields() {
		for(JTextField field : fields.values())
			field.setText("");
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
