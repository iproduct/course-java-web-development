package netdemo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DnsLookup {

	public static void main(String[] args) throws IOException {
		InetAddress[] addr = InetAddress.getAllByName("yahoo.com");
		for(InetAddress a: addr) {
			String domain = a.getCanonicalHostName();
			System.out.printf("%s --> %s [Accessible: %s]\n", a, domain, a.isReachable(2000));
		}
	}
}
