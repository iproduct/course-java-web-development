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

package org.iproduct.invoicing.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.iproduct.invoicing.entity.Position;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the invoice database table.
 * 
 */
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private long number;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date date;

	//bi-directional many-to-one association to Position
	@OneToMany(mappedBy="invoice")
	private List<Position> positions;

	//bi-directional many-to-one association to Contragent
	@ManyToOne
	@JoinColumn(name="receiver", nullable=false)
	private Contragent receiver;

	//bi-directional many-to-one association to Contragent
	@ManyToOne
	@JoinColumn(name="issuer", nullable=false)
	private Contragent issuer;

	public Invoice() {
	}

	public Invoice(long number, Contragent issuer, Contragent receiver) {
		this.number = number;
		this.issuer = issuer;
		this.receiver = receiver;
		this.positions = new ArrayList<>();
	}
	
	public Invoice(long number, Date date, Contragent issuer, Contragent receiver, List<Position> positions) {
		this.number = number;
		this.date = date;
		this.issuer = issuer;
		this.receiver = receiver;
		this.positions = positions;
	}
	
	public long getNumber() {
		return this.number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Position> getPositions() {
		return this.positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public Position addPosition(Position position) {
		getPositions().add(position);
		position.setInvoice(this);

		return position;
	}

	public Position removePosition(Position position) {
		getPositions().remove(position);
		position.setInvoice(null);

		return position;
	}

	public Contragent getReceiver() {
		return this.receiver;
	}

	public void setReceiver(Contragent receiver) {
		this.receiver = receiver;
	}

	public Contragent getIssuer() {
		return this.issuer;
	}

	public void setIssuer(Contragent issuer) {
		this.issuer = issuer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Invoice [number=").append(number).append(", date=").append(date).append(", positions=")
				.append(positions).append(", receiver=").append(receiver).append(", issuer=").append(issuer)
				.append("]");
		return builder.toString();
	}

}