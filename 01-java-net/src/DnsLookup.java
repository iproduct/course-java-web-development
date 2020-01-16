//: c15:WhoAmI.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Finds out your network address when
// you're connected to the Internet.
import java.net.*;

public class DnsLookup {
  public static void main(String[] args) throws Exception {

    InetAddress[] addr = InetAddress.getAllByName("yahoo.com");
    for(InetAddress a : addr) {
	    String domain  = a.getCanonicalHostName();
	    System.out.printf("%s --> %s [Accessible: %s]\n", a, domain, a.isReachable(2000));
    }
  }
} ///:~