package chatclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import chatserver.ChatServer;
import timeserver.TimeServer;

public class ChatClient implements Runnable {
	public static final String SERVER_URL = "localhost";
	public static final String SOCKET_ENCODING = "utf-8";
	private Scanner sc = new Scanner(System.in);
	private volatile boolean finish = false;
	Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private Thread thread;

	public ChatClient() {
		try {
			InetAddress addr = InetAddress.getByName(SERVER_URL);
			socket = new Socket(addr, ChatServer.PORT);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream(), SOCKET_ENCODING));
			System.out.println("Successfully connected to server: " + socket);
			out = new PrintWriter(
				new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), SOCKET_ENCODING)), true);
			
			thread = new Thread(this);
			thread.start();
		} catch (IOException e) {
			System.err.printf("Can not connect to server: %s:%s\n", SERVER_URL, TimeServer.PORT);
			e.printStackTrace(System.err);
		}
	}
	
	@Override
	// listens for incoming messages and prints them to console in separate thread
	public void run() {
		try {
			while(!finish) {
				String message = in.readLine();		
				System.out.println(message);
			}
		} catch (IOException e) {
			System.err.println("Error receiving data from server.");
			e.printStackTrace(System.err);
		}
		System.out.println("Closing client socket: " + socket);
		try {
			if(socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			System.err.println("Error closing socket: " + socket);
			e.printStackTrace(System.err);
		}
		stopThreads();
	}
	
	// reads messages from console and sends them to socket out in main thread
	public void readMessages() {
		if(socket == null || socket.isClosed()) return;
		String nickname = null;
		do {
			System.out.println("Your nickname:");
			nickname = sc.nextLine().trim();
		} while (nickname == null || nickname.length() == 0);
		out.println(nickname);
		String message;
		do {
			System.out.println("Your message:");
			message = sc.nextLine();
			out.println(message);
		} while (!finish && !"end".equalsIgnoreCase(message));
		System.out.printf("Bye, %s!\n", nickname);
		stopThreads();
	}

	public void stopThreads() {
		finish = true; 
		try {
			Thread.sleep(2000); //wait threads to finish
		} catch (InterruptedException e) {}
		System.out.println("Stopping the client.");
		System.exit(0);
	}
	
	public static void main(String[] args) {
		ChatClient client = new ChatClient();
		client.readMessages();
	}

}
