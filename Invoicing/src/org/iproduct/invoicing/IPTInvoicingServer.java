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

package org.iproduct.invoicing;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.iproduct.invoicing.jpa.JPAMainController;
import org.iproduct.invoicing.rmi.Invoicing;
import org.iproduct.invoicing.rmi.impl.InvoicingImpl;

public class IPTInvoicingServer {
	public static final int RMI_REGISTRY_PORT = 2016;

	public static void main(String[] args) {
		try {
			System.setSecurityManager(new SecurityManager());
			InvoicingImpl invoicingImpl = new InvoicingImpl();
			Registry registry = LocateRegistry.createRegistry(RMI_REGISTRY_PORT);
			System.out.println("RMI registry started on port: " + RMI_REGISTRY_PORT);
//			UnicastRemoteObject.unexportObject(invoicingImpl, true);
			Invoicing stub = 
		          (Invoicing) UnicastRemoteObject.exportObject(invoicingImpl, 0);
		    registry.rebind("Invoicing", stub);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}


}
