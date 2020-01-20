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


/**
 * The persistent class for the position database table.
 * 
 */
public class Position implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PositionPK id;

	@Column(nullable=false)
	private double price;

	@Column(nullable=false)
	private double quantity;

	//bi-directional many-to-one association to Invoice
	@ManyToOne
	@JoinColumn(name="invoice_number", nullable=false, insertable=false, updatable=false)
	private Invoice invoice;

	//bi-directional many-to-one association to Item
	@ManyToOne
	@JoinColumn(name="item_id", nullable=false)
	private Item item;

	public Position() {
	}

	public PositionPK getId() {
		return this.id;
	}

	public void setId(PositionPK id) {
		this.id = id;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Position [id=").append(id).append(", price=").append(price).append(", quantity=")
				.append(quantity).append(", invoice=").append(invoice).append(", item=").append(item).append("]");
		return builder.toString();
	}

}