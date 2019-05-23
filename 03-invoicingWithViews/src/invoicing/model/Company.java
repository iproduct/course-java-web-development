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
import java.util.Scanner;

public class Company extends Contragent implements Serializable {
	private boolean vatRegistered;
	private String accountablePerson;
	private String bic;
	private String iban;
	
	public Company() {
	}

	public Company(long idNumber, String name, String address) {
		super(idNumber, name, address);
	}

	public Company(long idNumber, String name, String address, boolean organization) {
		super(idNumber, name, address, organization);
	}

	public Company(long idNumber, String name, String address, String phone, boolean organization) {
		super(idNumber, name, address, phone, organization);
	}

	public Company(long idNumber, String name, String address, String phone, boolean organization, 
			boolean vatRegistered, String accountablePerson, String bic, String iban) {
		super(idNumber, name, address, phone, organization);
		this.vatRegistered = vatRegistered;
		this.accountablePerson = accountablePerson;
		this.bic = bic;
		this.iban = iban;
	}

	public boolean isVatRegistered() {
		return vatRegistered;
	}

	public void setVatRegistered(boolean vatRegistered) {
		this.vatRegistered = vatRegistered;
	}

	public String getAccountablePerson() {
		return accountablePerson;
	}

	public void setAccountablePerson(String accountablePerson) {
		this.accountablePerson = accountablePerson;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	@Override
	public String toString() {
		return "Company [idNumber=" + getIdNumber() + ", name=" + getName() + ", address="
				+ getAddress() + ", organization=" + isOrganization() + ", phone=" + getPhone() +", VAT_Registered=" + vatRegistered 
				+ ", accountablePerson=" + accountablePerson + ", BIC=" + bic
				+ ", IBAN=" + iban + "]";
	}

	public String format() {
		StringBuilder builder = new StringBuilder(super.format());
		if(isVatRegistered())
			builder.append("\nVAT Number: ").append("BG").append(getIdNumber());
		builder.append("\nAccountable Person: ").append(accountablePerson)
				.append("\nIBAN: ").append(iban)
				.append("\nBIC: ").append(bic);
		return builder.toString();
	}

}
