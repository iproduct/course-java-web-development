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
import java.util.List;


/**
 * The persistent class for the contragent database table.
 * 
 */

public class Contragent implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String[] FIELD_NAMES = {
			"ID Number", "Name",  "Address", "Type", "Accountable Person", "IBAN", "BIC"
	};
	private long idNumber;
	private String accountablePerson;
	private String address;
	private String bic;
	private String iban;
	private String name;
	private ContragentType type;
	private List<Invoice> issuedBy;
	private List<Invoice> receivedBy;

	public Contragent() {
	}

	public Contragent(long idNumber, String name, String address) {
		this.idNumber = idNumber;
		this.name = name;
		this.address = address;
	}

	public Contragent(long idNumber, String name, String address, ContragentType type, String accountablePerson) {
		this.idNumber = idNumber;
		this.name = name;
		this.address = address;
		this.type = type;
		this.accountablePerson = accountablePerson;
	}

	public Contragent(long idNumber, String name, String address, ContragentType type, String accountablePerson,
			String iban, String bic) {
		this.idNumber = idNumber;
		this.name = name;
		this.address = address;
		this.type = type;
		this.accountablePerson = accountablePerson;
		this.iban = iban;
		this.bic = bic;
	}


	public long getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(long idNumber) {
		this.idNumber = idNumber;
	}

	public String getAccountablePerson() {
		return this.accountablePerson;
	}

	public void setAccountablePerson(String accountablePerson) {
		this.accountablePerson = accountablePerson;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBic() {
		return this.bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getIban() {
		return this.iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ContragentType getType() {
		return this.type;
	}

	public void setType(ContragentType type) {
		this.type = type;
	}

	public List<Invoice> getInvoices1() {
		return this.issuedBy;
	}

	public void setInvoices1(List<Invoice> invoices1) {
		this.issuedBy = invoices1;
	}

	public Invoice addInvoices1(Invoice invoices1) {
		getInvoices1().add(invoices1);
		invoices1.setIssuer(this);

		return invoices1;
	}

	public Invoice removeInvoices1(Invoice invoices1) {
		getInvoices1().remove(invoices1);
		invoices1.setIssuer(null);

		return invoices1;
	}

	public List<Invoice> getInvoices2() {
		return this.receivedBy;
	}

	public void setInvoices2(List<Invoice> invoices2) {
		this.receivedBy = invoices2;
	}

	public Invoice addInvoices2(Invoice invoices2) {
		getInvoices2().add(invoices2);
		invoices2.setReceiver(this);

		return invoices2;
	}

	public Invoice removeInvoices2(Invoice invoices2) {
		getInvoices2().remove(invoices2);
		invoices2.setReceiver(null);

		return invoices2;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contragent [idNumber=").append(idNumber).append(", accountablePerson=")
				.append(accountablePerson).append(", address=").append(address).append(", bic=").append(bic)
				.append(", iban=").append(iban).append(", name=").append(name).append(", type=").append(type)
				.append(", issuedBy=").append(issuedBy).append(", receivedBy=").append(receivedBy).append("]");
		return builder.toString();
	}

}