package timeserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TimeServer {
	public static final int PORT = 8080;
	public static Logger logger = Logger.getLogger(TimeServer.class.getName());

	public static void main(String[] args) throws IOException {
		System.out.println(logger.getParent());
		logger.setUseParentHandlers(false);
		Handler consoleHandler = new ConsoleHandler();
		Handler fileHandler = new FileHandler("timeservice-log.%u.%g.txt", 1024 * 1024, 3);
		consoleHandler.setLevel(Level.FINE);
		fileHandler.setLevel(Level.INFO);
		fileHandler.setFormatter(new SimpleFormatter());
		logger.addHandler(consoleHandler);
		logger.addHandler(fileHandler);
		logger.setLevel(Level.ALL);
		logger.getParent().setLevel(Level.ALL);
		System.out.println(logger.getUseParentHandlers());
		try (ServerSocket ss = new ServerSocket(PORT)) {
			logger.log(Level.INFO, "TimeServer stated on port: " + ss.getLocalPort());

			while (true) {
				try (Socket s = ss.accept()) {
					logger.log(Level.INFO, "TimeServer connection accepted: " + s);
					BufferedReader in = new BufferedReader(
							new InputStreamReader(s.getInputStream()));
					PrintWriter out = new PrintWriter(
							new BufferedWriter(
									new OutputStreamWriter(s.getOutputStream())),
							true);

					String zoneStr = in.readLine();
					logger.log(Level.FINE, "Client time request for zone: " + zoneStr);
					ZoneId zoneId;
					try {
						zoneId = ZoneId.of(zoneStr);
					} catch (DateTimeException ex) {
						logger.log(Level.SEVERE, 
								"Invalid zone Id: " + zoneStr + ". Taking Greenwich zone instead",
								ex);
						zoneId = ZoneId.of("Europe/London");
					}
					LocalDateTime now = LocalDateTime.now(zoneId);
					out.println(now.toString());

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
