package chat.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import chat.MessageListener;
import chat.NetClient;
import chat.model.ConnectionSettings;
import chat.server.TCPChatServer;

public class TCPChatClient implements NetClient, Runnable {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private ConnectionSettings settings;
	private InetAddress address;
	private List<MessageListener> listeners = new CopyOnWriteArrayList<>();
	private volatile boolean stopped = false;

	public TCPChatClient() {
	}

	public Socket getSocket() {
		return socket;
	}

	public ConnectionSettings getSettings() {
		return settings;
	}

	@Override
	public String login(ConnectionSettings settings) {
		this.settings = settings;
		try {
			address = InetAddress.getByName(settings.getAddress());
			socket = new Socket(address, settings.getPort());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())),
						true);
			out.println(settings.getNickname());
			System.out.println("User " +settings.getNickname() 
				+ " logged to to server: " + socket);
			new Thread(this).start();
			return null;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return "Unknown host: " + settings.getAddress();
		} catch (IOException e) {
			e.printStackTrace();
			return "Error communicating with server.";
		}
	}

	@Override
	public void sendMessage(String message) {
		out.println(message);
	}

	@Override
	public void logout() {
		out.println("logout()");
		stopped = true;
	}

	@Override
	public void addMessageListener(MessageListener ml) {
		listeners.add(ml);
	}

	@Override
	public void removeMessageListener(MessageListener ml) {
		listeners.remove(ml);
	}

	@Override
	public void run() {
		while(!stopped) {
			String message;
			try {
				message = in.readLine();
				for(MessageListener listener : listeners)
					listener.onMessage(message);
//				listeners.stream().forEach(listener -> {
//					listener.onMessage(message);
//				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Chat client theread finished: " + settings);
	}
	
	public static void main(String[] args){
		ConnectionSettings settings = 
			new ConnectionSettings("localhost", TCPChatServer.PORT, "trayan");
		TCPChatClient client  = new TCPChatClient();
		client.addMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(String message) {
				System.out.println("!!!!! " + message);
				
			}
			
			@Override
			public void onError(String message) {
				// TODO Auto-generated method stub
				
			}
		});
		client.login(settings);
		client.sendMessage("aaaa");
		try {
			Thread.sleep(500000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
