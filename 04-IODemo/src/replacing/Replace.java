package replacing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Replace {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		if(args.length != 4) {
			System.out.println("Usage:\njava Replace <infilename> <outfilename> <word-to-be-replaced> <replacement>");
			return;
		}
		try( BufferedReader in = new BufferedReader(new FileReader(args[0])); 
		     PrintWriter out = new PrintWriter(
		    		 new BufferedWriter(new FileWriter(args[1]))) ){
			String s= new String();
		    while((s = in.readLine())!= null){
		    	s = s.replaceAll(args[2], args[3]);
		    	System.out.println(s);
		    	out.println(s);
		    }
		    System.out.println("Replacement is successful to file: " + args[1]);
		};
			    
			  
	}

}
