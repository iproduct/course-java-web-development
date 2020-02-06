/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package inlinecustomize.primer;

import java.math.BigInteger;
import javax.xml.bind.DatatypeConverter;


public class MyDatatypeConverter {
    public static short parseIntegerToShort(String value) {
        BigInteger result = DatatypeConverter.parseInteger(value);

        return (short) (result.intValue());
    }

    public static String printShortToInteger(short value) {
        BigInteger result = BigInteger.valueOf(value);

        return DatatypeConverter.printInteger(result);
    }

    public static int parseIntegerToInt(String value) {
        BigInteger result = DatatypeConverter.parseInteger(value);

        return result.intValue();
    }

    public static String printIntToInteger(int value) {
        BigInteger result = BigInteger.valueOf(value);

        return DatatypeConverter.printInteger(result);
    }
}
;
