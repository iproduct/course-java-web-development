package jdbcintro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter DB user (root for default):");
		String user  = sc.nextLine().trim();
		user  = user.length() > 0 ? user : "root";
		
		System.out.println("Enter DB password (root for default):");
		String password  = sc.nextLine().trim();
		password  = password.length() > 0 ? password : "root";
		
		Properties props = new Properties();
		props.setProperty("user", user);
		props.setProperty("password", password);
		
		// 1. Load DB driver (optional)
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Driver loaded successfully.");
		
		// 2. Connect to DB
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/organization?createDatabaseIfNotExist=true", props);
		System.out.println("Connected successfully.");

	}

}
