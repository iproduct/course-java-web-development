package chat.utility;
import java.io.UnsupportedEncodingException;
import java.net.*;

public class DatagramUtilities {
  public static DatagramPacket toDatagram(
    String s, InetAddress destIA, int destPort) {
    byte[] buff = new byte[0];
	try {
		buff = s.getBytes("UTF-8");
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
    return new DatagramPacket(buff, buff.length, 
      destIA, destPort);
  }
  public static String toString(DatagramPacket p){
	String result = "";
    try {
		result = 
		  new String(p.getData(), 0, p.getLength(), "UTF-8");
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	return result;
  }
} ///:~