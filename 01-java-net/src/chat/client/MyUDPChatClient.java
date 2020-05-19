package chat.client;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import chat.MessageListener;
import chat.NetClient;
import chat.model.ConnectionSettings;
import chat.utility.DatagramUtilities;

public class MyUDPChatClient extends Thread implements NetClient{
	private DatagramSocket socket;
	private byte[] buf = new byte[1000];
	private DatagramPacket dp = 
	    new DatagramPacket(buf, buf.length);
	private List<MessageListener> listeners = 
			Collections.synchronizedList(new ArrayList<MessageListener>());
	private volatile boolean stop = false;
	private InetAddress address;
	private ConnectionSettings settings;


	public MyUDPChatClient() {
	}
	
	public void run() {
		String message;
		while (!stop) {
			try {
				socket.receive(dp);
				message = DatagramUtilities.toString(dp);
				if(message!=null) {
					System.out.println(message);
					for(MessageListener listener : listeners)
						listener.onMessage(message);
				}
			} catch (SocketException e1) {
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Chat client closing ...");
	}

	public void requestStop() {
		stop = true;
	}

	@Override
	public void sendMessage(String message) {
		message = "[" + settings.getNickname() + "] " + new Date() +": "+message;
		send(message);
	}

	private void send(String message) {
		DatagramPacket p =
			DatagramUtilities.toDatagram(message, address, settings.getPort());
		System.out.println("Packet " + p.getAddress() + p.getPort());
		try {
			socket.send(p);
			System.out.println("Package sent" 
					+ DatagramUtilities.toString(p));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String login(ConnectionSettings settings) {
		System.out.println("Making client ");
		this.settings = settings;
		try {
			address = InetAddress.getByName(settings.getAddress());
			socket = new DatagramSocket();
			socket.connect(address, settings.getPort());
			socket.send(DatagramUtilities.toDatagram("", address, settings.getPort()));
			System.out.println("Socket created: "
					+socket.getLocalPort() 
					+ "-->" + socket.getPort());
			start();
		} catch (IOException e) {
			e.printStackTrace();
			socket.close();
		}
		return null;
	}

	@Override
	public void logout() {
		send("<END>");
		socket.close();
		requestStop();
		System.out.println("Client closed.");		
	}

	@Override
	public void addMessageListener(MessageListener ml) {
		listeners.add(ml);
	}

	@Override
	public void removeMessageListener(MessageListener ml) {
		listeners.remove(ml);
	}


}
