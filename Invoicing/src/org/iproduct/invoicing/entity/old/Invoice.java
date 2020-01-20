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

package org.iproduct.invoicing.entity.old;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invoice <T extends Item> {
	private long number;
	private Date date = new Date();
	private String issuer, receiver;
	private List<Position<T>> positions;
	
	public Invoice() {
	}

	public Invoice(long number, String issuer, String receiver) {
		this.number = number;
		this.issuer = issuer;
		this.receiver = receiver;
		this.positions = new ArrayList<>();
	}
	
	public Invoice(long number, Date date, String issuer, String receiver, List<Position<T>> positions) {
		this.number = number;
		this.date = date;
		this.issuer = issuer;
		this.receiver = receiver;
		this.positions = positions;
	}
	
	
	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public List<Position<T>> getPositions() {
		return positions;
	}

	public void setPositions(List<Position<T>> positions) {
		this.positions = positions;
	}

	public void addPosition(T item, double quantity){
		addPosition(item, quantity, item.getPrice());
	}
	
	public void addPosition(T item, double quantity, double price){
		Position p = new Position<Item>(positions.size() + 1 , item, quantity);
		positions.add(p);
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nIssuer: ").append(issuer)
			.append("\nReceiver=").append(receiver);
		for(Position<T> p : getPositions())
			builder.append(String.format("\n| %4d | %30s | %7.2f | %7.2f | %9.2f |", 
					p.getNumber(), p.getItem().getName(), p.getPrice(), 
					p.getQuantity(), p.getPrice() * p.getQuantity()));
		
		builder.append("\nPrice: ").append(getPrice())
			   .append("\nVAT:   ").append(getVAT())
			   .append("\nTotal: ").append(getTotal());
		return builder.toString();
	}

	private double getTotal() {
		return getPrice() + getVAT();
	}

	private double getVAT() {
		return 0.2 * getPrice();
	}

	private double getPrice() {
		double sum = 0;
		for(Position<T> item: positions){
			sum += item.getPrice();
		}
		return sum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
