package DAO;

import java.sql.*;

public class DatabaseConnection {
	private static DatabaseConnection dbConnection;
	private Connection connection;
	
	private DatabaseConnection() {
		try {
			String dbURL = "jdbc:sqlserver://DESKTOP-EHGHC3A;user=sa;password=sysadm123;databaseName=LibraryDB";
			connection = DriverManager.getConnection(dbURL);
		} catch (SQLException e) {
			System.out.println("Failed to connect to database.");
		}
	}
	
	public static DatabaseConnection getInstance() {
		if(dbConnection == null)
		{
			dbConnection = new DatabaseConnection();
		}
		else
		{
			try 
			{
				if (dbConnection.getConnection().isClosed())
					{
						dbConnection = new DatabaseConnection();
					}
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		return dbConnection;
	}
	
	public Connection getConnection() {
		return connection;
	}
}
