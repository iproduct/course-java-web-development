package timeserver;

import static java.util.logging.Level.*;

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
import java.time.ZonedDateTime;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TimeServer {
	public static final int PORT = 9000;
	public static Logger logger = Logger.getLogger(TimeServer.class.getSimpleName());

	public static void main(String[] args) {
		logger.setUseParentHandlers(false);
		Handler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(FINE);
		logger.addHandler(consoleHandler);
		Handler fileHandler;
		try {
			fileHandler = new FileHandler("timeservice-log.%u.%g.log", 1024 * 1024, 3);
			fileHandler.setLevel(INFO);
			fileHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(fileHandler);
		} catch (SecurityException |IOException e) {
			logger.log(WARNING, "Can not configure file logger.", e);
		} 
		try(ServerSocket ss = new ServerSocket(PORT)) {
			logger.info("TimeServer started on port: " + ss.getLocalPort());
			while(true) {
				try(
					Socket s = ss.accept(); 
					BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream(), "utf-8"));
					PrintWriter out = new PrintWriter(
						new BufferedWriter(
							new OutputStreamWriter(s.getOutputStream(), "utf-8")))) {
					logger.info("TimeServer connection accepted on port: " + ss.getLocalPort());
					String zoneStr = in.readLine();
					logger.info("Client request for time zone: " + zoneStr);
					ZoneId zoneId = ZoneId.of("Europe/London");
					try {
						zoneId = ZoneId.of(zoneStr);
					} catch(DateTimeException ex) {
						logger.log(WARNING, "Zone not recognized: " + zoneStr);
					}
					ZonedDateTime now = ZonedDateTime.now(zoneId);
					out.println(now.toString());
				}
			}
		} catch (IOException e) {
			logger.log(SEVERE, "Can not open Server Socket on port: " + PORT, e);
		}
		
	}

}
