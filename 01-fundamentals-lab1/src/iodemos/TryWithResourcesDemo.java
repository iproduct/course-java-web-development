package iodemos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TryWithResourcesDemo {

	public static void main(String[] args) throws IOException {
		try(BufferedReader in = new BufferedReader(
				new FileReader("./src/iodemos/TryWithResourcesDemo.java"));
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("out-temp.txt")))
		){
			String line;
			int number = 0;
			while(( line = in.readLine()) != null) {
				System.out.println(line);
				out.printf("%3d: %s\n", ++number, line);
			}
		}
	}

}
