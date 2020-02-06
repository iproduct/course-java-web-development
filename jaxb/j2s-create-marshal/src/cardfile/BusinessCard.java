/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package cardfile;

import javax.xml.bind.annotation.*;


@XmlRootElement
public class BusinessCard {
    private Address address;
    private String cellPhone;
    private String company;
    private String email;
    private String fax;
    private String name;
    private String phone;
    private String title;

    public BusinessCard() {
    }

    public BusinessCard(
        String name,
        String title,
        String company,
        Address address,
        String phone,
        String cellPhone,
        String fax,
        String email) {
        this.name = name;
        this.title = title;
        this.company = company;
        this.address = address;
        this.phone = phone;
        this.cellPhone = cellPhone;
        this.fax = fax;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        if (name != null) {
            s.append(name)
             .append('\n');
        }

        if (title != null) {
            s.append(title)
             .append('\n');
        }

        if (company != null) {
            s.append(company)
             .append('\n');
        }

        if (address != null) {
            s.append(address.toString())
             .append('\n');
        }

        if (phone != null) {
            s.append("phone: ")
             .append(phone)
             .append('\n');
        }

        if (cellPhone != null) {
            s.append("cell:  ")
             .append(cellPhone)
             .append('\n');
        }

        if (fax != null) {
            s.append("fax:  ")
             .append(fax)
             .append('\n');
        }

        if (email != null) {
            s.append(email)
             .append('\n');
        }

        return s.toString();
    }
}
