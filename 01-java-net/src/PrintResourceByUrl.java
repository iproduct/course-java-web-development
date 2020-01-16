import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class PrintResourceByUrl {
  public static void main(String[] args) throws IOException {
	  // https://tools.ietf.org/html/rfc1118
	  URL u = new URL("https://tools.ietf.org/rfc/rfc1118.txt");
	  InputStream stream = u.openConnection().getInputStream();
	  BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
	  String line;
	  while((line = reader.readLine()) != null) {
		  System.out.println(line);
	  }
	  
  }
}
