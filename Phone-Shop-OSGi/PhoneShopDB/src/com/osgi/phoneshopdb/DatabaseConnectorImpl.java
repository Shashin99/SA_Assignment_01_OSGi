package com.osgi.phoneshopdb;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnectorImpl implements DatabaseConnector {

	private Connection dbConnection;
	private final String driverName;
	private String ConnectionString;
	private String dbUser;
	private String dbPassword;
	
	public DatabaseConnectorImpl() {
		this.driverName = "com.mysql.jdbc.Driver";
		this.ConnectionString = "jdbc:mysql://localhost:3307/phonedb";
		this.dbUser = "root";
		this.dbPassword = "";
	}
	
	@Override
	public Connection getDatabaseConnection() {
		try {
			Class.forName(driverName);
			dbConnection = (Connection)DriverManager.getConnection(ConnectionString,dbUser,dbPassword);
			
			System.out.println("Database Connection Eshtablished Successfully");
			
		}catch(Exception ex) {
			System.out.println("dbConnectionError: " + ex.getMessage());
		}
		return dbConnection;
	}
}
