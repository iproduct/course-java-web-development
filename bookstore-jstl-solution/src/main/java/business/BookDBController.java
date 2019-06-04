package business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import shopping.Book;

public class BookDBController {
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL =
	    "jdbc:mysql://localhost:3306/javaweb";
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "root";

	private List<Book> availableBooks = new ArrayList<Book>();
	private Connection connection;
	
	public void reload() {
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e1) {
			System.err.println("MySQL DB Driver not found");
			e1.printStackTrace();
		}
		try {
			if(connection == null || connection.isClosed()){
				connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			}
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM books ORDER BY name");
			while(rs.next()){
				availableBooks.add(new Book(
						rs.getLong(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getDouble(7),
						rs.getInt(8),
						rs.getBoolean(9)
						));
			}
			System.out.println(availableBooks);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Book> getAllBooks(){
		return availableBooks;
	}
	
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Book getBookById(long id){
		try {
			if(connection == null || connection.isClosed()){
				connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			}
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(
					"SELECT * FROM books WHERE id=" + id );
			if(rs.next()) {
				return new Book(
						rs.getLong(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getDouble(7),
						rs.getInt(8),
						rs.getBoolean(9)
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void main(String[] args){
		BookDBController bdc = new BookDBController();
		bdc.reload();
	}
}

