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

package org.iproduct.invoicing.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import org.iproduct.invoicing.entity.Contragent;
import org.iproduct.invoicing.entity.Invoice;
import org.iproduct.invoicing.entity.Item;
import org.iproduct.invoicing.entity.old.Position;
import org.iproduct.invoicing.jpa.exceptions.EntityAlreadyExistsException;
import org.iproduct.invoicing.jpa.exceptions.EntityDoesNotExistException;

public interface Invoicing extends Remote{
	//Item management
	Item addItem(Item item) throws RemoteException, EntityAlreadyExistsException;
	List<Item> getAllItems() throws RemoteException;
	List<Item> getItems(int from, int max) throws RemoteException;
	Item updateItem(Item item) throws RemoteException, EntityDoesNotExistException;
	Item deleteItem(long itemId) throws RemoteException, EntityDoesNotExistException;
	
	//Contragent managemeent
	Contragent addContragent(Contragent contragent) throws RemoteException, EntityAlreadyExistsException;
	List<Contragent> getAllContragents() throws RemoteException;
	List<Contragent> getContragents(int from, int max) throws RemoteException, EntityDoesNotExistException;
	Contragent updateContragent(Contragent Contragent) throws RemoteException;
	Contragent deleteContragent(long contragentId) 
			throws RemoteException, EntityDoesNotExistException;
	
	//Invoice management
	Invoice createInvoice(Date date, Contragent issuer, 
			Contragent receiver, List<Position> positions) throws RemoteException, EntityAlreadyExistsException;
	List<Invoice> getAllInvoices() throws RemoteException;
	List<Invoice> getInvoices(int from, int max) throws RemoteException;
	Invoice updateInvoice(Invoice invoice) throws RemoteException, EntityDoesNotExistException;
	Invoice deleteInvoice(long invoiceNumber) 
			throws RemoteException, EntityDoesNotExistException;
}
