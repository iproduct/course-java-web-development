package chat.multicasting;

// UDPChatServer.java
// Chat server using UDP communication protocol. Works with multiple clients.
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

import java.io.*;
import java.net.*;
import java.util.*;

class AddressInfo {
	public InetAddress address;
	public int port;
	public AddressInfo(InetAddress a, int p) {
		address = a;
		port = p;
	}
}

public class UDPChatServer {  
  static final int PORT = 8080;
  static final String IP_ADDRESS = "192.168.1.16";
  private ArrayList <AddressInfo> clients = new ArrayList<AddressInfo>();
  private byte[] buffer = new byte[2000];
  private DatagramPacket inPacket = 
    new DatagramPacket(buffer, buffer.length);
  private static DatagramSocket socket;
  
  public UDPChatServer() {
	  try {
	      socket = new DatagramSocket(PORT);
	      System.out.println("Server started");
	      while(true) {
	        socket.receive(inPacket);
	        AddressInfo ai = new AddressInfo(inPacket.getAddress(), inPacket.getPort());
	        if (isNewAddress(ai)) {
	        	clients.add(ai);
	        }
	        notifyClients(DatagramUtility.getString(inPacket));
	      }
	    } catch(SocketException e) {
	      System.err.println("Can't open socket");
	      System.exit(1);
	    } catch(IOException e) {
	      System.err.println("Communication error");
	      e.printStackTrace();
	    }
  }
  public boolean isNewAddress (AddressInfo ai){
	  int i = 0;
	  while( (i<clients.size()) && !(clients.get(i).address.toString().equals(ai.address.toString()) 
	    && clients.get(i).port == ai.port)) 
		 i++; 
	  return i== clients.size();
  }
  public void notifyClients(String message){
	  Iterator <AddressInfo> it = clients.iterator();
	  while(it.hasNext()) {
		  AddressInfo ai = it.next();
	      DatagramPacket outPacket = 
		          DatagramUtility.getDatagramPacket(message, ai.address, ai.port);
      
		  try {
			socket.send(outPacket);
		  } catch (IOException e) {
			e.printStackTrace();
		  }
	  }
  }
  public static void main(String[] args) {
	  new UDPChatServer();
  } 
}