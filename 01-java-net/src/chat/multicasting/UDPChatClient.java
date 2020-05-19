package chat.multicasting;

// UDPChatClient.java
// Chat client using UDP communication protocol
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

interface ChatClientInterface {
	public void setUID(String name);
	public void newMessageEntered(String message);
	public String getUID();
}

class UDPChatClientThread extends Thread implements ChatClientInterface {
  private DatagramSocket socket;
  private byte[] buffer = new byte[2000];
  private InetAddress serverIPAddress;
  private DatagramPacket inPacket = 
    new DatagramPacket(buffer, buffer.length);
  private String id ;
  private ChatGUI gui = new ChatGUI(this);
  public UDPChatClientThread(InetAddress addr) {
    try {
        socket = new DatagramSocket();
        serverIPAddress = addr;
      } catch(SocketException e) {
        System.err.println("Cannot find host");
        System.exit(1);
      } 
  	  gui.setVisible(true); //Visualize the ChatGUI 
      start();
  }
  private void send(String message) {
	  try {
		String newMessage = "< " + id + " > : " + message;
		socket.send(DatagramUtility.getDatagramPacket(newMessage, 
				serverIPAddress, UDPChatServer.PORT));
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
    	socket.receive(inPacket);
    	str = DatagramUtility.getString(inPacket);
        gui.addMessage(str);
      }
    } catch(IOException e) {
      System.err.println("IO Exception");
    } finally {
      socket.close();
    }
  }
}


public class UDPChatClient {
  public static void main(String[] args) 
      throws IOException, InterruptedException {
    InetAddress addr = 
      InetAddress.getByName(UDPChatServer.IP_ADDRESS);
    new UDPChatClientThread(addr);
  }
}