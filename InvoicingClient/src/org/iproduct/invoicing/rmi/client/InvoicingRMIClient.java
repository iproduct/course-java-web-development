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

package org.iproduct.invoicing.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.iproduct.invoicing.rmi.Invoicing;

public class InvoicingRMIClient {
	public static final String INVOICING_SERVICE_NAME = "Invoicing";
	
	public static Invoicing connectToServer(String serviceUrl) 
			throws RemoteException, NotBoundException, MalformedURLException {
		//		System.setSecurityManager(new SecurityManager());
		//		Registry registry = LocateRegistry.getRegistry("192.168.1.100", 2016);
		//		Invoicing server = (Invoicing) registry.lookup("INVOICING_SERVICE_NAME");
		Invoicing server = (Invoicing) Naming.lookup(serviceUrl);
		return server;
		
	}
	
	public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException{
		Invoicing server = connectToServer("//localhost:2016/Invoicing");
		System.out.println(server.getAllItems());
	}
}
