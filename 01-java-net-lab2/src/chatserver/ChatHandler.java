package chatserver;

import static java.util.logging.Level.FINE;
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
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
		logger.setUseParentHandlers(false);
		Handler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(FINE);
		consoleHandler.setFormatter(new SimpleFormatter());
		logger.addHandler(consoleHandler);
		logger.setLevel(FINE);
		
		this.server = server;
		this.socket = socket;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream(), SOCKET_ENCODING));
			out = new PrintWriter(
					new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream(), SOCKET_ENCODING)), true);
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
			logger.info("User '" + nickname + "' logged in.");
			while(!"end".equalsIgnoreCase(message)) {
				message = in.readLine();
				logger.fine("Message received: " + nickname + ": " + message);
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
