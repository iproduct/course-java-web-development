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
	private Scanner sc = new Scanner(System.in);
	private volatile boolean finish = false;
	private BufferedReader in;
	private Thread thread;

	public ChatClient() {
		try {
			InetAddress addr = InetAddress.getByName(SERVER_URL);
			Socket socket = new Socket(addr, ChatServer.PORT);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			PrintWriter out = new PrintWriter(
					new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream(), "utf-8")),
					true);
			thread = new Thread(this);
			thread.start();
			String nickname = null;
			do {
				System.out.println("Your nickname:");
				nickname = sc.nextLine().trim();
			} while (nickname == null || nickname.length() > 0);
			out.println(nickname);
			String message;
			do {
				System.out.println("Your message:");
				message = sc.nextLine();
				out.println(message);
			} while (!"end".equalsIgnoreCase(message));
			System.out.printf("Bye, %s!\n", nickname);
			finish = true; // stop the receiver thread
		} catch (IOException e) {
			System.err.printf("Can not connect to server: %s:%s\n", SERVER_URL, TimeServer.PORT);
			e.printStackTrace();
		} finally {
			System.out.println("Closing client socket: " + socket);
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("Error closing socket: " + socket);
				e.printStackTrace();
			}
		}
	}

	@Override
	// Listens for incoming messages and prints them to console
	public void run() {
		String message = "";
		while(!finish) {
			try {
				message = in.readLine();
				System.out.println(message);
			} catch (IOException e) {
				System.err.println("Error receiving data from server.");
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) {
		new ChatClient();

	}

}
