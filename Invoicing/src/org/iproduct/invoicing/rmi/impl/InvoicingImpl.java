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

package org.iproduct.invoicing.rmi.impl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.iproduct.invoicing.entity.Contragent;
import org.iproduct.invoicing.entity.Invoice;
import org.iproduct.invoicing.entity.Item;
import org.iproduct.invoicing.entity.Position;
import org.iproduct.invoicing.jpa.ContragentJPAController;
import org.iproduct.invoicing.jpa.ItemJPAController;
import org.iproduct.invoicing.jpa.JPAMainController;
import org.iproduct.invoicing.jpa.PositionJPAController;
import org.iproduct.invoicing.jpa.exceptions.EntityAlreadyExistsException;
import org.iproduct.invoicing.jpa.exceptions.EntityDoesNotExistException;
import org.iproduct.invoicing.rmi.Invoicing;

public class InvoicingImpl extends UnicastRemoteObject implements Invoicing {
	private ItemJPAController itemController = JPAMainController.getItemController();
	private ContragentJPAController contragentController = JPAMainController.getContragentController();
	private PositionJPAController positionController = JPAMainController.getPositionController();
//	private InvoiceJPAController invoiceController;

	private static final long serialVersionUID = 1L;

	public InvoicingImpl() throws RemoteException {
	}

	public InvoicingImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
		super(port, csf, ssf);
	}

	public InvoicingImpl(int port) throws RemoteException {
		super(port);
	}

	@Override
	public Item addItem(Item item) throws RemoteException, EntityAlreadyExistsException {
		return itemController.create(item);
	}

	@Override
	public List<Item> getAllItems() throws RemoteException {
//		 Item[] hardwareItems = new Item[] {
//				new Item(1, "Whiteboard Marker", "", .89, "Hardware"),
//				new Item(2, "USB Flash 16GB", "AData", 8.5, "Hardware"),
//				new Item(3, "Computer Mouse", "Logitech", 12.33, "Hardware") 
//		};
//		return Arrays.asList(hardwareItems);
		return itemController.findAll();
	}

	@Override
	public List<Item> getItems(int from, int max) throws RemoteException {
		return itemController.find(from, max);
	}

	@Override
	public Item updateItem(Item item) throws RemoteException, EntityDoesNotExistException {
		return itemController.update(item);
	}

	@Override
	public Item deleteItem(long itemId) throws RemoteException, EntityDoesNotExistException {
		return itemController.delete(itemId);
	}

	@Override
	public Contragent addContragent(Contragent contragent) throws RemoteException, EntityAlreadyExistsException {
		return contragentController.create(contragent);
	}

	@Override
	public List<Contragent> getAllContragents() throws RemoteException {
		return contragentController.findAll();
	}

	@Override
	public List<Contragent> getContragents(int from, int max) throws RemoteException {
		return contragentController.find(from, max);
	}

	@Override
	public Contragent updateContragent(Contragent contragent) throws RemoteException, EntityDoesNotExistException {
		return contragentController.update(contragent);
	}

	@Override
	public Contragent deleteContragent(long contragentId) throws RemoteException, EntityDoesNotExistException {
		return contragentController.delete(contragentId);
	}

	@Override
	public Invoice createInvoice(Date date, Contragent issuer, Contragent receiver, List<Position> positions)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Invoice> getAllInvoices() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Invoice> getInvoices(int from, int max) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invoice updateInvoice(Invoice invoice) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invoice deleteInvoice(long invoiceNumber) throws RemoteException, EntityDoesNotExistException {
		// TODO Auto-generated method stub
		return null;
	}


}
