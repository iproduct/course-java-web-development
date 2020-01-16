package timeclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import timeserver.TimeServer;

public class TimeClient {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress addr = InetAddress.getByName("localhost");
		String[] cities = {
			"Sofia", "Rome", "Madrid", "Berlin", "Paris", "London", "Brussels", "Burgas"
		};
		for(String city : cities) {
			try(
				Socket s = new Socket(addr, TimeServer.PORT);
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream(), "utf-8"));
				PrintWriter out = new PrintWriter(
					new BufferedWriter(
						new OutputStreamWriter(s.getOutputStream(), "utf-8")), true) {
						}) {
				System.out.printf("Connected to server socket:%s\n", s);
				String zoneStr = "Europe/" + city;
				out.println(zoneStr);
				String time = in.readLine();
				System.out.printf("Time in %s is: %s\n", zoneStr, time);
				Thread.sleep(1000);
			} catch (IOException e) {
				System.err.printf("Can not connect to server: %s:%s\n", addr, TimeServer.PORT);
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
