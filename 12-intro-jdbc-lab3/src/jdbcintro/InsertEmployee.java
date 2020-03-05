package jdbcintro;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import jdbcintro.model.Employee;

public class InsertEmployee {
	public static final String INSERT_EMPLOYEE_SQL =
			"INSERT INTO employees ("
			+ " `first_name`, `last_name`, `middle_name`, `job_title`, `department_id`,  `manager_id`, "
			+ " `hire_date`, `salary`, `address_id`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
		Properties props = new Properties();
		String dbConfigPath = InsertEmployee.class.getClassLoader().getResource("database.properties").getPath();
		props.load(new FileInputStream(dbConfigPath));

		// 1. Load DB driver (optional)
		try {
			Class.forName(props.getProperty("driver", "com.mysql.cj.jdbc.Driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Driver loaded successfully.");
		
		// 2. Connect to DB
		Connection con = DriverManager.getConnection(props.getProperty("url", 
				"jdbc:mysql://localhost:3306/organization?createDatabaseIfNotExist=true"), props);
		System.out.println("Connected successfully.");
		
		// 3. Execute statement
		Employee e = new Employee("Ivan", "Petrov", "Hristov", "Developer", 11, 42, new Date(), 45000, 1);
		PreparedStatement s = con.prepareStatement(INSERT_EMPLOYEE_SQL);
		s.setString(1, e.getFirstName());
		s.setString(2, e.getLastName());
		s.setString(3, e.getMiddleName());
		s.setString(4, e.getJobTitle());
		s.setLong(5, e.getDepartmentId());
		s.setLong(6, e.getManagerId());
		s.setDate(7, new java.sql.Date(e.getHireDate().getTime()));
		s.setDouble(8, e.getSalary());
		s.setLong(9, e.getAddressId());
		
		int records = s.executeUpdate();
		System.out.printf("Updated records: %d%n", records);
		
		// 4. Process results
		PreparedStatement s2 = con.prepareStatement("SELECT * FROM employees");
		ResultSet rs = s2.executeQuery();
		int n = 0;
		while(rs.next()) {
			System.out.printf("| %4d | %-15.15s | %-15.15s | %10.2f |%n",
					++n, rs.getString(2), rs.getString("last_name"), rs.getDouble("salary"));					
		}
		
		// 5. Close connection
		con.close();

	}

}
