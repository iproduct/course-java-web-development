/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package unmarshalvalidate;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;
import org.w3c.dom.Node;

import primer.po2.*;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Schema;


/*
 * $Id: Main.java,v 1.3 2007/02/01 16:03:36 jendrock Exp $
 *
 * Copyright 2003 Sun Microsystems, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Sun Microsystems, Inc.
 * Use is subject to license terms.
 *
 */
public class Main {
    // This sample application demonstrates how to enable validation during
    // the unmarshal operations. 
    public static void main(String[] args) {
        try {
            // create a JAXBContext capable of handling classes generated into
            // the primer.po package
            JAXBContext jc = JAXBContext.newInstance("primer.po");

            // create an Unmarshaller
            Unmarshaller u = jc.createUnmarshaller();
            // u.setValidating(true); //deprecated

            SchemaFactory sf = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);

            try {
                Schema schema = sf.newSchema(new File("po.xsd"));
                u.setSchema(schema);
                u.setEventHandler(
                        new ValidationEventHandler() {
                            // allow unmarshalling to continue even if there are errors
                            public boolean handleEvent(ValidationEvent ve) {
                                // ignore warnings
                                if (ve.getSeverity() != ValidationEvent.WARNING) {
                                    ValidationEventLocator vel = ve.getLocator();
                                    System.out.println(
                                            "Line:Col[" + vel.getLineNumber()
                                            + ":" + vel.getColumnNumber()
                                            + "]:" + ve.getMessage());
                                }

                                return true;
                            }
                        });
            } catch (org.xml.sax.SAXException se) {
                System.out.println(
                        "Unable to validate due to following error.");
                se.printStackTrace();
            }

            // unmarshal an invalid po instance document into a tree of Java
            // content objects composed of classes from the primer.po package.
            System.out.println(
                    "NOTE: This sample is working correctly if you see validation errors!!");

            Object poe = u.unmarshal(new File("po.xml"));

            // even though document was determined to be invalid unmarshalling,
            // marshal out result.
            System.out.println("");
            System.out.println("Still able to marshal invalid document");

            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(poe, System.out);
        } catch (UnmarshalException ue) {
            // The JAXB specification does not mandate how the JAXB provider
            // must behave when attempting to unmarshal invalid XML data.  In
            // those cases, the JAXB provider is allowed to terminate the 
            // call to unmarshal with an UnmarshalException.
            System.out.println("Caught UnmarshalException");
        } catch (JAXBException je) {
            je.printStackTrace();
        }
    }
}
