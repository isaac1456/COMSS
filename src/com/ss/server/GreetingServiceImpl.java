package com.ss.server;

import com.ss.client.GreetingService;
import com.ss.shared.FieldVerifier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	static String bd = "comss";

	static String ipbd = "127.0.0.1";

	static String password = "root";

	static String port = "1433";

	static String user = "root";
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	static Connection connection = null;


	public  void conectar() {

		// boolean connected = true;
		// config.cargarDatos();
		try {
			String connectionUrl = "jdbc:sqlserver://" + ipbd + ":" + port + ";databaseName=" + bd + ";user=" + user
					+ ";password=" + password + "";
			Class.forName(driver).newInstance();
			
			connection = DriverManager.getConnection(connectionUrl);
			System.out.println("*********************************Conecto *************************************************");


		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("*********************************Conecto *************************************************");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("*********************************Conecto *************************************************");

		} 

	}

	public String selectApp() {
		conectar();
		ResultSet resultSet = null;
	
		String request = "";
		System.out.println("*********************************entro*************************************************");
		Statement statement;
		try {
			
			String selectSql = "select apps.appName, version.versionName from apps, version where apps.idApp = version.app_fk; ";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectSql);
			// hashMap.put("app name", "Version ");
			System.out.println("*********************************before while*************************************************");
			while (resultSet.next()) {
				System.out.println("*********************************while *************************************************");
				// objapp = new AppObj(resultSet.getInt(1), resultSet.getString(2));
				// s ListappObjs.add(objapp);

				// hashMap.put(resultSet.getString(1), resultSet.getString(2));
				request = resultSet.getString(1) + " " + resultSet.getString(2);
				System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
			}
			System.out.println("*********************************After while *************************************************");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return request;

	}

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid.
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException("Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>"
				+ userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}
