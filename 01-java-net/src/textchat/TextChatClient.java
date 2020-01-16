package textchat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class TextChatClient {
	public static String HOST = "localhost";	
	public static int PORT = 2019;	
	private static Scanner sc = new Scanner(System.in);
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private Updater updater;
	
	private class Updater extends Thread{
		private volatile boolean stop = false;
		public void run() {
			String message = "";
			while(!stop){
				try {
					message = in.readLine();
					System.out.println(message);
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("Error closing client socket");
				e.printStackTrace();
			}
		}
		public void finish() {
			stop = true;
		}
	}
	
	public TextChatClient(InetAddress addr, int port){
	  	try {
	      socket = 
	        new Socket(addr, port);
	    } catch(IOException e) {
	      System.err.println("Socket failed");
	    }
	    try {    
	      in = 
	        new BufferedReader(
	          new InputStreamReader(
	            socket.getInputStream()));
	      out = 
	        new PrintWriter(
	          new BufferedWriter(
	            new OutputStreamWriter(
	              socket.getOutputStream())), true);
	      updater = new Updater();
		  updater.start();
	    } catch(IOException e) {
	      try {
	        socket.close();
	      } catch(IOException e2) {
	        System.err.println("Socket not closed");
	      }
	    }    
	}
	public void processInput(){
		String message = "";
		System.out.print("Enter nickname:");
		message = sc.nextLine();
		out.println(message);
		
		while(!message.equalsIgnoreCase("end")){
			  message = sc.nextLine();
			  out.println(message);
		}
		updater.finish();
	}
	
	public static void main(String[] args) 
    throws IOException {
		  InetAddress addr = InetAddress.getByName(HOST);
		  TextChatClient client = new TextChatClient(addr, PORT);
		  client.processInput();
	}
}
