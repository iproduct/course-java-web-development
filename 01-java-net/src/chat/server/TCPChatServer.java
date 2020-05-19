package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPChatServer implements Runnable{
	public static final int PORT = 8000;
	private final Logger logger = Logger.getLogger(TCPChatServer.class.getName());
	private final List<ChatService> services = new CopyOnWriteArrayList<>();
	private int serverPort;
	private final ExecutorService exec = Executors.newCachedThreadPool();
	

	public TCPChatServer(int serverPort) {
		this.serverPort = serverPort;
		logger.setLevel(Level.ALL);
	}
	
	public TCPChatServer() {
		this(PORT);
	}


	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	
	public void sendAll(String messageToSend) {
		services.stream().forEach(service -> {
			try {
				service.sendMessage(messageToSend);
			} catch (Exception e) {
				logger.log(Level.SEVERE, 
					"Error sending message to client: " + service + ": ", e);
			}
		});
	}
	
	public void removeService(ChatService service){
		services.remove(service);
	}

	@Override
	public void run() {
		try (ServerSocket ss = new ServerSocket(PORT)) {
			logger.log(Level.INFO, "TimeServer stated on port: " + ss.getLocalPort());

			while (true) {
				try {
					Socket s = ss.accept();
					logger.log(Level.INFO, "TimeServer connection accepted: " + s);
					ChatService service = new ChatService(this, s);
					services.add(service);
					exec.execute(service);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException ex) {
			logger.log(Level.SEVERE, 
				"Problem running Chat Server on port " + PORT + ": ", ex);
		}
	}
	
	public static void main(String[] args) throws IOException {
		TCPChatServer server = new TCPChatServer();
		server.run();
	}

}
