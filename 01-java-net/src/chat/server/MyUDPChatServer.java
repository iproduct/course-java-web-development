package chat.server;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import chat.utility.DatagramUtilities;

class ConnectionData {
	InetAddress ip;
	int port;
	public ConnectionData(InetAddress ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	public InetAddress getIp() {
		return ip;
	}
	public void setIp(InetAddress ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	@Override
	public boolean equals(Object obj){
		if(obj instanceof ConnectionData) {
			ConnectionData cd = (ConnectionData)obj;
			return ip.equals(cd.ip)
				&& port == cd.port; 
		} else return false;
	}
}
public class MyUDPChatServer extends Thread {
	private byte[] buf = new byte[1000];
	private DatagramPacket dp = 
	    new DatagramPacket(buf, buf.length);
	private DatagramSocket socket;
	private List <ConnectionData> clients = 
		new ArrayList<ConnectionData>();
	
	public MyUDPChatServer(int port) throws IOException {
		socket = new DatagramSocket(port);
	    System.out.println("Server Started: " + socket 
	    		+ " on port " + socket.getLocalPort());
	    start();
	}
	public void run() {
		while(true) {
			try {
				socket.receive(dp);
				String message = DatagramUtilities.toString(dp);
				System.out.println("Message received: " + message);
				ConnectionData  cd = new ConnectionData(
						dp.getAddress(), dp.getPort());
				if(! clients.contains(cd) ) {
					clients.add(cd);
				} else {
					if(message.equals("<END>")){
						clients.remove(cd);
						message = cd.getIp()+":"+cd.getPort()
						+" is out of chat";
						System.out.println(message);

					}
				}
				for(ConnectionData c : clients) {
					DatagramPacket packet = 
						DatagramUtilities.toDatagram(message, 
						c.getIp(), c.getPort());
					socket.send(packet);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args)  throws IOException {
		MyUDPChatServer s1 = new MyUDPChatServer(
				TCPChatServer.PORT);
	}

}
