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
package shoppingCart;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Iterator;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlType(name = "KitchenWorldBasketType")
public class KitchenWorldBasket {
    @XmlJavaTypeAdapter(AdapterPurchaseListToHashMap.class)
    HashMap<Integer, String> basket = new HashMap<Integer, String>();

    public KitchenWorldBasket() {
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("KitchenWorldBasket:\n");

        // For QA consistency order the output. 
        TreeMap<Integer, String> tMap = new TreeMap<Integer, String>(basket);

        for (Integer key : tMap.keySet()) {
            buf.append(
                    "key: " + key.toString() + "\tvalue: " + tMap.get(key)
                    + "\n");
        }

        return buf.toString();
    }
}
