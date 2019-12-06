package com.ss.shared;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;



import co.com.ss.models.AppObj;

public class StatementSQL {
	Connect connect = new Connect();
	ArrayList<AppObj> ListappObjs = new ArrayList<AppObj>();
	AppObj objapp;


	public LinkedHashMap SelectApp() {
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		ResultSet resultSet = null;
		Connection connection = connect.conectar();

		Statement statement;
		try {
			statement = connection.createStatement();
		String selectSql = "select apps.appName, version.versionName from apps, version where apps.idApp = version.app_fk; ";
		resultSet = statement.executeQuery(selectSql);
			
			while (resultSet.next()) {
			//	objapp = new AppObj(resultSet.getInt(1), resultSet.getString(2)); 
			//s	ListappObjs.add(objapp); 
				
				hashMap.put(resultSet.getString(1), resultSet.getString(2));
				
				System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return hashMap;

	}

}
