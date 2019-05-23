/* COPYRIGHT & LICENSE HEADER
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
*
* IPTInvoicing project is a simple demo using Swing GUI and local file for DB
* 
* Copyright (c) 2012 - 2014 IPT - Intellectual Products & Technologies Ltd.
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

public class Contragent implements Serializable{
	private long idNumber;
	private String name;
	private String address;
	private String phone;
	private boolean organization;

	public Contragent() {
	}

	public Contragent(long idNumber, String name, String address) {
		this.idNumber = idNumber;
		this.name = name;
		this.address = address;
	}

	public Contragent(long idNumber, String name, String address,
			boolean organization) {
		this.idNumber = idNumber;
		this.name = name;
		this.address = address;
		this.organization = organization;
	}
	
	public Contragent(long idNumber, String name, String address,
			String phone, boolean organization) {
		this.idNumber = idNumber;
		this.name = name;
		this.address = address;
		this.organization = organization;
		this.phone = phone;
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

	public boolean isOrganization() {
		return organization;
	}

	public void setOrganization(boolean organization) {
		this.organization = organization;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String format() {
		StringBuilder sb = new StringBuilder("Identification Number: ");
		sb.append(idNumber).append("\nName: ").append(name).append("\nAddress: ")
			.append(address);
		return sb.toString();
	}

	@Override
	public String toString() {
		return "Person  [idNumber=" + idNumber + ", name=" + name + ", address=" + address + ", phone=" + phone
				+ ", organization=" + organization + "]";
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
		return (obj != null) && 
				(obj instanceof Contragent) && 
				(idNumber == ((Contragent)obj).idNumber);
	}

	public static void main(String[] args){
		Contragent c1 = new Contragent(1234567890, "Ivan Petrov", "Sofia 1000");
		Contragent c2 = new Contragent(1234567890, "Dimitar Dimitrov", "Sofia 1000");
//		System.out.println(c1.equals(c2));
		System.out.println(c1);
		System.out.println(c2);
	}
	
}
