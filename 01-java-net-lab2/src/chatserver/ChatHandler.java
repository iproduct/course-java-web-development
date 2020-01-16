package chatserver;

import static java.util.logging.Level.SEVERE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

import timeserver.TimeServer;

public class ChatHandler implements Runnable {
	public static final String SOCKET_ENCODING = "utf-8";
	public static Logger logger = Logger.getLogger(ChatHandler.class.getSimpleName());

	private ChatServer server;
	private final Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private String nickname;

	public ChatHandler(ChatServer server, Socket socket) {
		this.server = server;
		this.socket = socket;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), SOCKET_ENCODING));
			PrintWriter out = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), SOCKET_ENCODING)));
		} catch (UnsupportedEncodingException e) {
			logger.log(SEVERE, "Can not recognize encoding: " + SOCKET_ENCODING, e);
		} catch (IOException e) {
			logger.log(SEVERE, "Can not open input/output stream for socket: " + socket, e);
		}

	}

	@Override
	public void run() {
		String message = "";
		try {
			nickname = in.readLine();
			while(!"end".equalsIgnoreCase(message)) {
				message = in.readLine();
				server.sendToAll(nickname + ": " + message);
			}
			logger.info("User '" + nickname + "' logged out.");
		} catch(IOException ex) {
			logger.log(SEVERE, "Error receiving data from client: " + socket, ex);
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				logger.log(SEVERE, "Error closing socket: " + socket, e);
			}
			server.removeClient(this);
		}
	}
	
	public void sendMessage(String message) {
		out.println(message);
	}

}
