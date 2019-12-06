package com.ss.shared;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class Connect {
	// Connect to your database.
	// Replace server name, username, and password with your credentials
	static	cargarConfiguracion config = new cargarConfiguracion(); 

	public static Connection conectar()
	{
		Connection connection = null;
		//boolean connected = true; 
		   config.cargarDatos();
		String connectionUrl = "jdbc:sqlserver://"+config.ipbd+":"+config.port+";databaseName="+config.bd+";user="+config.User+";password="+config.password+"";

		try  {
			 connection = DriverManager.getConnection(connectionUrl);
			 
			// Code here.
		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}