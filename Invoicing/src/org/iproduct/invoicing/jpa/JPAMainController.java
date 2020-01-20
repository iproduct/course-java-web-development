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

package org.iproduct.invoicing.jpa;

import static org.iproduct.invoicing.entity.ContragentType.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.iproduct.invoicing.entity.Contragent;
import org.iproduct.invoicing.entity.Item;
import org.iproduct.invoicing.jpa.exceptions.EntityAlreadyExistsException;
import org.iproduct.invoicing.jpa.exceptions.EntityDoesNotExistException;

public class JPAMainController{
	private static EntityManagerFactory emf;
	private static ContragentJPAController contragentController = new ContragentJPAController();
	private static ItemJPAController itemController = new ItemJPAController();
	private static PositionJPAController positionController = new PositionJPAController();
//	private static InvoiceJPAController invoiceController;


	public static EntityManager getEntityManager() {
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory("Invoicing");
		}
		return emf.createEntityManager();
	}
	
	public static ContragentJPAController getContragentController() {
		return contragentController;
	}

	public static ItemJPAController getItemController() {
		return itemController;
	}

	public static PositionJPAController getPositionController() {
		return positionController;
	}

//	public static InvoiceJPAController getInvoiceController() {
//		return invoiceController;
//	}

	public static void main(String[] args) {
		EntityManager em = JPAMainController.getEntityManager();

		List<Item> items = em.createNamedQuery("Item.findAll", Item.class).getResultList();
//		for(Item item : items)
//			System.out.println(item);
		
		Contragent c1 = new Contragent(456644555, "WebWidgets Ltd.", "Plovdiv 4000", 
				ORGANIZATION, "Stoyan Petrov");
//		try {
//			getContragentController().create(c1);
//			Contragent c2 = em.find(Contragent.class, c1.getIdNumber());
//			c2.setAddress("Plovdiv 4000");
//			getContragentController().update(c2);
//			getContragentController().delete(c2.getIdNumber());
//		} catch (EntityAlreadyExistsException e) {
//			e.printStackTrace();
//		} catch (EntityDoesNotExistException e) {
//			e.printStackTrace();
//		}
		
		
		List<Contragent> contragents = new ContragentJPAController().findAll();
//		List<Contragent> contragents = em.createNamedQuery("Contragent.findAll", 
//				Contragent.class)
//			.getResultList();
		for(Contragent contrgent : contragents)
			System.out.println(contrgent);
		
		//Print count
		System.out.println("Contragents count: " + new ContragentJPAController().getCount());

	}

}
