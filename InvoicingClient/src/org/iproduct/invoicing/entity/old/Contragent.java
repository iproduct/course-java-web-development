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

package org.iproduct.invoicing.entity.old;

import java.io.Serializable;

public class Contragent implements Serializable {
	private static final long serialVersionUID = 1L;
	private long idNumber;
	private String name;
	private String address;
	private CotragentType type;
	private String accountablePerson;
	private String iban;
	private String bic;
	
	public Contragent() {
	}

	public Contragent(long idNumber, String name, String address) {
		this.idNumber = idNumber;
		this.name = name;
		this.address = address;
	}

	public Contragent(long idNumber, String name, String address, CotragentType type, String accountablePerson) {
		this.idNumber = idNumber;
		this.name = name;
		this.address = address;
		this.type = type;
		this.accountablePerson = accountablePerson;
	}

	public Contragent(long idNumber, String name, String address, CotragentType type, String accountablePerson,
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
		return idNumber;
	}

	public void setIdNumber(long idNumber) {
		this.idNumber = idNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public CotragentType getType() {
		return type;
	}

	public void setType(CotragentType type) {
		this.type = type;
	}

	public String getAccountablePerson() {
		return accountablePerson;
	}

	public void setAccountablePerson(String accountablePerson) {
		this.accountablePerson = accountablePerson;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idNumber ^ (idNumber >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Contragent))
			return false;
		Contragent other = (Contragent) obj;
		if (idNumber != other.idNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contragent [idNumber=").append(idNumber).append(", name=").append(name).append(", address=")
				.append(address).append(", type=").append(type).append(", accountablePerson=").append(accountablePerson)
				.append(", iban=").append(iban).append(", bic=").append(bic).append("]");
		return builder.toString();
	}
	
	
}
