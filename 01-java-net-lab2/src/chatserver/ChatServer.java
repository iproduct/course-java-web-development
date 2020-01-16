package chatserver;

import static java.util.logging.Level.SEVERE;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

public class ChatServer implements Runnable {
	public static final int PORT = 9000;
	public static Logger logger = Logger.getLogger(ChatServer.class.getSimpleName());
	private ExecutorService pool;
	private List<ChatHandler> handlers = new CopyOnWriteArrayList<>();

	public void run() {
		try (ServerSocket ss = new ServerSocket(PORT)) {
			logger.info("ChatServer started on port: " + ss.getLocalPort());
			pool = Executors.newCachedThreadPool();
			while (true) {
				try (Socket s = ss.accept()) {
					ChatHandler handler = new ChatHandler(this, s);
					handlers.add(handler);
					pool.execute(handler);
				} catch(IOException ex) {
					logger.log(SEVERE, "Error accepting client connection.", ex);
				}
			}
		} catch (IOException e) {
			logger.log(SEVERE, "Can not open Server Socket on port: " + PORT, e);
		} finally {
			pool.shutdown();
		}
	}

	public static void main(String[] args) {
		new ChatServer().run();
	}

	public void sendToAll(String message) {
		for(ChatHandler h: handlers) {
			h.sendMessage(message);
		}
	}
	
	public void removeClient(ChatHandler handler) {
		handlers.remove(handler);
	}
	

}
