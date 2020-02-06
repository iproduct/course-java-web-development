/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


/*
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the "License").  You may not use this file except
 * in compliance with the License.
 *
 * You can obtain a copy of the license at
 * https://jwsdp.dev.java.net/CDDLv1.0.html
 * See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL
 * HEADER in each file and include the License file at
 * https://jwsdp.dev.java.net/CDDLv1.0.html  If applicable,
 * add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your
 * own identifying information: Portions Copyright [yyyy]
 * [name of copyright owner]
 */
package address;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlTransient;


@XmlType(propOrder =  {
    "name", "street", "city", "state", "zip"}
)
public class USAddress {
    @XmlTransient
    public int sessionID;
    private String city;
    private String name;
    private String state;
    private String street;
    private int zip;

    /**
     * The zero arg constructor is used by JAXB Unmarshaller to create
     * an instance of this type.
     */
    public USAddress() {
    }

    public USAddress(
        String name,
        String street,
        String city,
        String state,
        int zip) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
