package chat.multicasting;

// ChatClient.java
// Chat client using TCP communication protocol
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

class ChatClientThread extends Thread implements ChatClientInterface {
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private String id ;
  private ChatGUI gui = new ChatGUI(this);
  public ChatClientThread(InetAddress addr) {
	//gui.setVisible(true);
    try {
      socket = 
        new Socket(addr, ChatServer.PORT);
    } catch(IOException e) {
      System.err.println("Socket failed");
    }
    try {    
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
              socket.getOutputStream())), true);
  	  gui.setVisible(true); //Visualize the ChatGUI 
      start();
    } catch(IOException e) {
    	System.err.println("Client could not be started");
    	e.printStackTrace();
    }
  }

  public String getUID() {
	  return id;
  } 
  public void setUID(String name) {
	  id = name;
	  out.println(id);  // send the id to the sever
	  gui.setTitle("Chat Client: " + id);
  } 
  public void newMessageEntered(String message) {
	  out.println(message);
  }
  public void run() {
	  String str="";
    try {
      while(!str.equals("END")){
        str = in.readLine();
        gui.addMessage(str);
      }
    } catch(IOException e) {
      System.err.println("IO Exception");
    } finally {
      try {
        socket.close();
      } catch(IOException e) {
        System.err.println("Socket can not be closed");
      }
    }
  }
}

public class ChatClient {
  static final String IP_ADDRESS = "192.168.1.16";
  public static void main(String[] args) 
      throws IOException, InterruptedException {
    InetAddress addr = 
      InetAddress.getByName(IP_ADDRESS);
    new ChatClientThread(addr);
  }
}