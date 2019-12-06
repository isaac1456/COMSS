package com.ss.connection;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class cargarConfiguracion {
	public String bd = "";
	public String User = "";
	public String password = "";
	public String ipbd = "";
	public String port = ""; 
	Properties p = new Properties();

	public void cargarDatos() {

		try {
			p.load(new FileReader("/libs/config.properties"));
	
			bd = p.getProperty("bd");
			User = p.getProperty("user");
			password = p.getProperty("password");
			ipbd = p.getProperty("ipbd");
			port = p.getProperty("port"); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
