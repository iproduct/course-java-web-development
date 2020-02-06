/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package cardfile;

import javax.xml.bind.annotation.*;


@XmlType
public class Address {
    private String city;
    private String name;
    private String state;
    private String street;
    private short zip;

    public Address() {
    }

    public Address(
        String name,
        String street,
        String city,
        String state,
        short zip) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public short getZip() {
        return zip;
    }

    public void setZip(short zip) {
        this.zip = zip;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        if (name != null) {
            s.append(name)
             .append('\n');
        }

        s.append(street)
         .append('\n')
         .append(city)
         .append(", ")
         .append(state)
         .append(" ")
         .append(zip);

        return s.toString();
    }
}
