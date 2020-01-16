package netdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class PrintResourceByUrl {

	public static void main(String[] args) {
		String urlStr = "https://tools.ietf.org/rfc/rfc1118.txt";
		try {
			URL url = new URL(urlStr);
			InputStream stream = url.openConnection().getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			String line;
			while((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch(MalformedURLException ex) {
			System.err.printf("Url %s is malformed", urlStr);
		} catch (IOException e) {
			System.err.printf("Can not open Url: %s", urlStr);
		}
	}
}
