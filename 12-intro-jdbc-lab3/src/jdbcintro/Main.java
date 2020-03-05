package jdbcintro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		
		// 3. Execute statement
		System.out.println("Enter minimal salary (20000 for default):");
		String salaryStr  = sc.nextLine().trim();
		salaryStr  = salaryStr.length() > 0 ? salaryStr : "20000";
		
		double salary = Double.parseDouble(salaryStr);
		
		PreparedStatement s = con.prepareStatement("SELECT * FROM employees WHERE salary > ?");
		s.setDouble(1, salary);
		
		ResultSet rs = s.executeQuery();
		
		// 4. Process results
		int n = 0;
		while(rs.next()) {
			
			System.out.printf("| %4d | %-15.15s | %-15.15s | %10.2f |%n",
					++n, rs.getString(2), rs.getString("last_name"), rs.getDouble("salary"));					
		}
		
		// 5. Close connection
		con.close();

	}

}
