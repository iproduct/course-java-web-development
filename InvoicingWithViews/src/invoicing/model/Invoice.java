/* COPYRIGHT & LICENSE HEADER
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
*
* InvoiceRegister project is a simple invoicing demo.
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

package invoicing.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Invoice implements Comparable<Invoice>, Serializable{
	private static long invoiceCount = 0;
	private long number = ++ invoiceCount;
	private Contragent issuer; 
	private Contragent receiver;
	private Date date = new Date();
	private List<Position> positions = new ArrayList<>();
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Invoice [number=").append(number).append(", issuer=")
				.append(issuer).append(", receiver=").append(receiver)
				.append(", date=").append(date).append(", positions=")
				.append(positions).append(", getTotal()=")
				.append(getTotal()).append(", getVAT()=").append(getVAT())
				.append(", getTotalPlusVAT()=").append(getTotalPlusVAT())
				.append("]");
		return builder.toString();
	}

	public Invoice() {
	}

	public Invoice(Contragent issuer, Contragent receiver, List<Position> positions) {
		this.issuer = issuer;
		this.receiver = receiver;
		this.positions = positions;
	}

	public Invoice(long number, Contragent issuer, Contragent receiver, Date date,
			List<Position> positions) {
		this.number = number;
		this.issuer = issuer;
		this.receiver = receiver;
		this.date = date;
		this.positions = positions;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public Contragent getIssuer() {
		return issuer;
	}

	public void setIssuer(Contragent issuer) {
		this.issuer = issuer;
	}

	public Contragent getReceiver() {
		return receiver;
	}

	public void setReceiver(Contragent receiver) {
		this.receiver = receiver;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (number ^ (number >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoice other = (Invoice) obj;
		if (number != other.number)
			return false;
		return true;
	}
	
	public double getTotal() {
		double sum = 0;
		for(Position p: positions){
			sum += p.getTotalPrice();
		}
		return sum;
	}
	
	public double getVAT() {
		return getTotal() * Position.VAT_RATE;
	}
	
	public double getTotalPlusVAT() {
		return getTotal() +  getVAT();
	}

	public void addPosition(Product product, double quantity){
		positions.add(new Position(product, quantity));
	}

	@Override
	public int compareTo(Invoice other) {
		return Long.compare(this.getNumber(), other.getNumber());
	}
	
}
