package chat.multicasting;

// ChatServer.java
// Chat server using TCP communication protocol. Works with multiple clients.
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

class ClientThread extends Thread {
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private String id = "";
  public ClientThread(Socket s) 
      throws IOException {
    socket = s;
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
        socket.getOutputStream())), true);
    id = in.readLine() + socket.getInetAddress();
    start();
  }
  public void notify(String message){
	  out.println(message);
	  System.out.println(id + " - " + message);
  }
  public String getID() { return id;}
  public void run() {
    try {
      while (true) {  
        String str = in.readLine();
        if (str.equals("END")) break;
        ChatServer.notifyClients(id, str);
      }
      System.out.println("closing...");
    } catch(IOException e) {
      System.err.println("IO Exception");
    } finally {
      try {
        socket.close();
      } catch(IOException e) {
        System.err.println("Socket not closed");
      }
    }
  }
}

public class ChatServer {  
  static final int PORT = 8080;
  static ArrayList clients = new ArrayList();
  public static void notifyClients(String sourceName, String message){
	  String newMessage = "< " + sourceName + " > : " + message;
	  Iterator it = clients.iterator();
	  ClientThread c;
	  while(it.hasNext()) {
		  c = ((ClientThread)it.next());
//		  if( !sourceName.equals(c.getID()))  //Uncomment if don't want the clients to receive their own messages
			  c.notify(newMessage);
	  }
  }
  public static void main(String[] args)
      throws IOException {
    ServerSocket servSocket = new ServerSocket(PORT);
    System.out.println("Server Started");
    try {
      while(true) {
        Socket socket = servSocket.accept();
        try {
          clients.add(new ClientThread(socket));
        } catch(IOException e) {
          socket.close();
        }
      }
    } finally {
      servSocket.close();
    }
  } 
}