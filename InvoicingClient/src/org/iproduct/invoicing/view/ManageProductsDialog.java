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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.CellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.AbstractTableModel;

import org.iproduct.invoicing.IPTInvoicingClient;
import org.iproduct.invoicing.entity.Item;

public class ManageProductsDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	final private IPTInvoicingClient parent;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private ProductTableModel tableModel;
	JButton btnEdit = new JButton("Edit");
	JButton btnFinish = new JButton("OK");
	private boolean inEditMode;
	private final JButton btnDelete = new JButton("Delete");

	/**
	 * Create the dialog.
	 */
	public ManageProductsDialog(JFrame topFrame, IPTInvoicingClient parent) {
		super(topFrame, "Browse Product", true);

		this.parent = parent;
		setSize(600, 400);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		tableModel = new ProductTableModel();
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		contentPanel.add(scrollPane);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		btnEdit.setEnabled(false);
		btnDelete.setEnabled(false);
		
		table.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if(table.getSelectedRows().length == 1) {
				btnEdit.setEnabled(true);
			} else {
				btnEdit.setEnabled(false);
			}
			if(table.getSelectedRows().length >= 1) {
				btnDelete.setEnabled(true);
			} else {
				btnDelete.setEnabled(false);
			}
		});

		btnEdit.addActionListener(event -> {
			if (!inEditMode) {
				int[] selected = table.getSelectedRows();
				if (selected.length != 1) {
					JOptionPane.showMessageDialog(this, "Exactly one product should be selected for editing.",
							"Invalid Product Selection", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int modelRow = table.convertRowIndexToModel(selected[0]);
				tableModel.setEditable(modelRow);
				inEditMode = true;
				btnEdit.setText("Save");
				btnFinish.setText("Cancel");
			} else {
				if (table.isEditing()) { // finish editing and save changes in
											// currently edited field
					CellEditor editor = table.getColumnModel().getColumn(table.getEditingColumn()).getCellEditor();
					if (editor == null)
						editor = table.getDefaultEditor(
								table.getColumnModel().getColumn(table.getEditingColumn()).getClass());
					editor.stopCellEditing();
				}
				Item updatedItem = tableModel.confirmEdit();
				if(updatedItem != null)
					try {
						parent.getServer().updateItem(updatedItem);
						tableModel.refreshDataFromServer();
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(parent, 
							"Error updating item to server: " + updatedItem + " ! Item NOT updated.", 
							"Server Error", JOptionPane.ERROR_MESSAGE);
					}
				inEditMode = false;
				btnEdit.setText("Edit");
				btnFinish.setText("OK");
			}
		});

		btnEdit.setActionCommand("Edit");
		buttonPane.add(btnEdit);
		getRootPane().setDefaultButton(btnEdit);

		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inEditMode = false;
				btnEdit.setText("Edit");
				btnFinish.setText("OK");
				tableModel.cancelEdit();
				if (table.isEditing()) { // finish editing and cancel changes in
										 // currently edited field
					CellEditor editor = table.getColumnModel().
						getColumn(table.getEditingColumn()).getCellEditor();
					if (editor == null)
						editor = table.getDefaultEditor(
							table.getColumnModel().getColumn(table.getEditingColumn()).getClass());
					editor.cancelCellEditing();
				} else {
					dispose();
			    }
			}
		});
		btnDelete.addActionListener((ActionEvent event) -> {
			int[] selected = table.getSelectedRows();
			if (selected.length < 1) {
				JOptionPane.showMessageDialog(this, "At least one product should be selected for deleting.",
						"Invalid Product Selection", JOptionPane.ERROR_MESSAGE);
			} else {
				if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, 
					"You are going to permanently delete " + selected.length + " products. Are you sure?",
					"Product Delete Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)){
					for(int row : selected){
						try {
							parent.getServer().deleteItem(
								tableModel.getItem(table.convertRowIndexToModel(row)).getId());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					refresh();
				}
			}
		});
		
		buttonPane.add(btnDelete);
		buttonPane.add(btnFinish);

	}

	public void refresh() {
		tableModel.refreshDataFromServer();
		tableModel.fireTableDataChanged();
	}

	private class ProductTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;
		private List<Item> itemCache = new ArrayList<>();
		private int editableRow = -1;
		private Item oldRowItem;
		
		public void refreshDataFromServer() {
			itemCache.clear();
			try {
				itemCache.addAll(parent.getServer().getAllItems());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public Item getItem(int rowIndex) {
			return itemCache.get(rowIndex);
		}
		@Override
		public int getColumnCount() {
			return Item.FIELD_NAMES.length;
		}

		public void setEditable(int modelRow) {
			editableRow = modelRow;
			if (modelRow >= 0)
				oldRowItem = itemCache.get(modelRow);
		}

		public Item confirmEdit() {
			if (editableRow >= 0) {
				fireTableRowsUpdated(editableRow, editableRow);
				Item resultItem = itemCache.get(editableRow);
				editableRow = -1;
				return resultItem;
			}
			return null;
		}

		public void cancelEdit() {
			if (editableRow >= 0)
				itemCache.set(editableRow, oldRowItem);
			editableRow = -1;
		}

		@Override
		public int getRowCount() {
			return itemCache.size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			Item item;

			item = itemCache.get(row);

			String result = "Error";
			switch (col) {
			case 0:
				result = "" + item.getId();
				break;
			case 1:
				result = item.getName();
				break;
			case 2:
				result = item.getVendor();
				break;
			case 3:
				result = "" + item.getPrice();
				break;
			case 4:
				result = item.getCategory();
				break;
			}
			return result;
		}

		@Override
		public String getColumnName(int column) {
			return Item.FIELD_NAMES[column];
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return (Integer) rowIndex == editableRow && columnIndex > 0;
		}

		@Override
		public void setValueAt(Object val, int rowIndex, int columnIndex) {
			if (editableRow >= 0) {
				Item item;

				item = itemCache.get(editableRow);

				switch (columnIndex) {
				case 1:
					item.setName((String) val);
					break;
				case 2:
					item.setVendor((String) val);
					break;
				case 3:
					item.setPrice(Double.parseDouble((String) val));
					break;
				case 4:
					item.setCategory((String) val);
					break;
				}
			}
		}

	}

}
