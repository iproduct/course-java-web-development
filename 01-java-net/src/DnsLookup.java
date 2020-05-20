import java.net.*;

public class DnsLookup {
  public static void main(String[] args) throws Exception {

    InetAddress[] addr = InetAddress.getAllByName("yahoo.com");
    for(InetAddress a : addr) {
	    String domain  = a.getCanonicalHostName();
	    System.out.printf("%s --> %s [Accessible: %s]\n", a, domain, a.isReachable(2000));
    }
  }
}