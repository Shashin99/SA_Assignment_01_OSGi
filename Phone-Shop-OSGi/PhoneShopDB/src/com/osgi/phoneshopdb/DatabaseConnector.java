package com.osgi.phoneshopdb;

import java.sql.Connection;

public interface DatabaseConnector {
	public Connection getDatabaseConnection();

}
