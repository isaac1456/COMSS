package com.ss.server;

import com.ss.client.GreetingService;
import com.ss.shared.FieldVerifier;

import co.com.ss.models.AppObj;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
	LinkedHashMap<String, String> arrayList = null;

	public void conectar() {

		// boolean connected = true;
		// config.cargarDatos();
		try {
			String connectionUrl = "jdbc:sqlserver://" + ipbd + ":" + port + ";databaseName=" + bd + ";user=" + user
					+ ";password=" + password + "";
			Class.forName(driver).newInstance();

			connection = DriverManager.getConnection(connectionUrl);
			System.out.println(
					"*********************************Conecto *************************************************");

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println(
					"*********************************Conecto *************************************************");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public String selectApp() {
		conectar();
		ResultSet resultSet = null;
		// arrayList = new LinkedHashMap<String, String>();
		String localDir = System.getProperty("user.dir");
		JSONArray appList = new JSONArray();

		System.out.println("*********************************entro*************************************************");
		Statement statement;
		try {

			String selectSql = "select apps.idApp, apps.appName from apps";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectSql);
			// hashMap.put("app name", "Version ");

			while (resultSet.next()) {

				JSONObject appdetails = new JSONObject();
				appdetails.put("idApp", resultSet.getInt(1));
				appdetails.put("appName", resultSet.getString(2));

				appList.add(appdetails);

				System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
			}
			wirteJson(appList, "apps");
			//connection.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return localDir + "\\Resources\\apps.json";

	}

	public String insertMetrics(String nameMetrics, float valueMetric) {
		conectar();
		ResultSet resultSet = null;
		Statement statement;
		String result = "no";
		System.out.println("----------------------------");
		// JSONArray appList = new JSONArray();
		try {

			int auxMax = 0;

			statement = connection.createStatement();

		
			
			
				String selectTop = "SELECT TOP 1 * FROM ciclos ORDER BY idCiclos DESC;";

				resultSet = statement.executeQuery(selectTop);
				if (resultSet.next()) {
					auxMax = resultSet.getInt(1);
				}
				String insertSql = "insert into metrics (metricName, metricValue, ciclos_fk) values('" + nameMetrics + "', "+valueMetric+", "+auxMax+");";
				
				statement.executeUpdate(insertSql);
				result = "Se agrego la Metrica";
			

			// wirteJson(appList, "version");
			// connection.close();

		} catch (SQLException e) {

			e.printStackTrace();
			result = "No se Registro, Verifique la informacion";
		}

		return result;


	}

	public String validarVersion(String id) {
		conectar();
		ResultSet resultSet = null;
		Statement statement;
		String result = "no";
		System.out.println("----------------------------");
		JSONArray appList = new JSONArray();
		try {

			String selectSql = "select * from version where app_fk =" + id + ";";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectSql);

			while (resultSet.next()) {

				JSONObject appdetails = new JSONObject();

				appdetails.put("idVersion", resultSet.getInt(1));
				appdetails.put("versionName", resultSet.getString(2));

				appList.add(appdetails);

			}
			wirteJson(appList, "version");
			//connection.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;

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

	private void wirteJson(JSONArray array, String name) {
		String localDir = System.getProperty("user.dir");

		try (FileWriter file = new FileWriter(localDir + "\\Resources\\" + name + ".json")) {

			file.write(array.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String selectAppaVersion() {
		conectar();
		ResultSet resultSet = null;
		// arrayList = new LinkedHashMap<String, String>();
		String localDir = System.getProperty("user.dir");
		JSONArray appList = new JSONArray();

		System.out.println("*********************************entro*************************************************");
		Statement statement;
		try {

			String selectSql = "select apps.idApp, apps.appName, version.idVersion,version.versionName from apps, version where apps.idApp = version.app_fk; ";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectSql);
			// hashMap.put("app name", "Version ");
			System.out.println(
					"*********************************Select app Version *************************************************");
			while (resultSet.next()) {
				System.out.println("*********************************: " + resultSet.getString(2));

				JSONObject appdetails = new JSONObject();
				appdetails.put("idApp", resultSet.getInt(1));
				appdetails.put("appName", resultSet.getString(2));
				appdetails.put("idVersion", resultSet.getInt(3));
				appdetails.put("versionName", resultSet.getString(4));

				appList.add(appdetails);

				System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
			}
			wirteJson(appList, "appVersion");

		} catch (SQLException e) {

			e.printStackTrace();
		}
		// System.out.println("*********************************After while " +
		// arrayList.size());
		return localDir + "\\Resources\\appVersion.json";

	}

	@Override
	public String saveAppVersion(String app, String version, boolean aux) {
		conectar();
		ResultSet resultSet = null;
		Statement statement;
		String result = "no";
		System.out.println("----------------------------");
		// JSONArray appList = new JSONArray();
		try {

			int auxMax = 0;

			statement = connection.createStatement();

			if (aux) {
				String insertverAppsql = "insert into version (versionName, app_fk) values('" + version + "'," + app
						+ " );";
				statement.executeUpdate(insertverAppsql);
				result = "Se agrego la Version";

			} else {
				String selectTop = "SELECT TOP 1 * FROM apps ORDER BY idApp DESC";

				resultSet = statement.executeQuery(selectTop);
				if (resultSet.next()) {
					auxMax = resultSet.getInt(1) + 1;
				}
				String insertAppSql = "insert into apps (appName) values('" + app + "');";
				String insertverSql = "insert into version (versionName, app_fk) values('" + version + "'," + auxMax
						+ " );";
				statement.executeUpdate(insertAppSql);
				statement.executeUpdate(insertverSql);
				result = "Se agrego la App";
			}

			// wirteJson(appList, "version");
			// connection.close();

		} catch (SQLException e) {

			e.printStackTrace();
			result = "No se Registro, Verifique la informacion";
		}

		return result;

	}

	@Override
	public String insertCiclos(String name, int idVersionfk) {
		conectar();
		ResultSet resultSet = null;
		Statement statement;
		String result = "no";
		System.out.println("----------------------------"+name+idVersionfk);
		// JSONArray appList = new JSONArray();
		try {

			int auxMax = 0;

			statement = connection.createStatement();


			String insertverCiclosql = "insert into ciclos (ciclosName, version_fk) values('" + name+ "'," + idVersionfk
					+ " );";
				statement.executeUpdate(insertverCiclosql);
				result = "Se agrego el Ciclo";

			
//				String selectTop = "SELECT TOP 1 * FROM apps ORDER BY idApp DESC";
//
//				resultSet = statement.executeQuery(selectTop);
//				if (resultSet.next()) {
//					auxMax = resultSet.getInt(1) + 1;
//				}
//				String insertAppSql = "insert into apps (appName) values('" + name + "');";
//				String insertverSql = "insert into version (versionName, app_fk) values('" + name + "'," + auxMax
//						+ " );";
//				statement.executeUpdate(insertAppSql);
//				statement.executeUpdate(insertverSql);
//				result = "Se agrego la App";
			

			// wirteJson(appList, "version");
			// connection.close();

		} catch (SQLException e) {

			e.printStackTrace();
			result = "No se Registro, Verifique la informacion";
		}

		return result;
	}

}
