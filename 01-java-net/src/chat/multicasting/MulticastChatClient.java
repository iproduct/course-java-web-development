package chat.multicasting;

// ChatClient.java
// Chat program using multicasting. No server needed.
// (c) Copyright IPT - Intellectual Products & Technologies Ltd., 2004-2006.
// All rights reserved. This software program can be compiled and modified only as a part of the 
// "Programming in Java" course provided by IPT - Intellectual Products & Technologies Ltd.,
// for educational purposes only, and provided that this copyright notice is kept unchanged 
// with the program. The program is provided "as is", without express or implied warranty of any 
// kind, including any implied warranty of merchantability, fitness for a particular purpose or 
// non-infringement. Should the Source Code or any resulting software prove defective, the user
// assumes the cost of all necessary servicing, repair, or correction. In no event shall 
// IPT - Intellectual Products & Technologies Ltd. be liable to any party under any legal theory 
// for direct, indirect, special, incidental, or consequential damages, including lost profits, 
// business interruption, loss of business information, or any other pecuniary loss, or for
// personal injuries, arising out of the use of this source code and its documentation, or arising 
// out of the inability to use any resulting program, even if IPT - Intellectual Products & 
// Technologies Ltd. has been advised of the possibility of such damage. 
// Contact information: www.iproduct.org, e-mail: office@iproduct.org 

import java.net.*;
import java.io.*;

class MulticastChatClientThread extends Thread 
	implements ChatClientInterface {
  private MulticastSocket s;
  private byte[] buf = new byte[1000];
  private InetAddress multicastIPAddress;
  private int port;
  private DatagramPacket inPacket = 
    new DatagramPacket(buf, buf.length);
  private String id ;
  private ChatGUI gui = new ChatGUI(this);
  public MulticastChatClientThread(InetAddress addr, int port) {
	this.port = port;
    multicastIPAddress = addr;
    try {
        s = new MulticastSocket(port);
        System.out.println("Loopback mode:" + s.getLoopbackMode());
        System.out.println(multicastIPAddress+ ", " + port);
        s.joinGroup(multicastIPAddress);
        System.out.println("Multicast Socket: " + s);
     } catch(IOException e) {
        System.err.println("Cannot find host: " );
        e.printStackTrace(System.err);
        System.exit(1);
      } 
  	  gui.setVisible(true); //Visualize the ChatGUI 
      start();
  }
  public void send(String message) {
	  try {
		String newMessage = "< " + id + " > : " + message;
		s.send(DatagramUtility.getDatagramPacket(newMessage, 
				multicastIPAddress, port));
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
  public String getUID() {
	  return id;
  } 
  public void setUID(String name) {
	  id = name;
	  gui.setTitle("Chat Client: " + id);
  } 
  public void newMessageEntered(String message) {
	  send(message);
  }
  public void run() {
	  String str="";
    try {
      while(!str.equals("END")){
    	s.receive(inPacket);
    	str = DatagramUtility.getString(inPacket);
        gui.addMessage(str);
      }
    } catch(IOException e) {
      System.err.println("IO Exception");
    } finally {
      try {
		s.leaveGroup(multicastIPAddress);
      } catch (IOException e) {
			e.printStackTrace();
      }
	  s.close();
    }
  }
}

public class MulticastChatClient {
  static final int PORT = 6801;
  static final String IP_ADDRESS = "224.1.1.1";
  public static void main(String[] args) 
      throws IOException, InterruptedException {
    InetAddress addr = 
    	InetAddress.getByName(IP_ADDRESS);
    new MulticastChatClientThread(addr, PORT);
  }
}