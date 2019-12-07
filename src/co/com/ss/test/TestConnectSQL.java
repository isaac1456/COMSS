package co.com.ss.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.junit.Test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.ss.client.GreetingService;
import com.ss.client.GreetingServiceAsync;
import com.ss.server.Connect;






public class TestConnectSQL {

	@Test
	public void testConectar() {
		Connect connect = new Connect();
		
		Connection connection = connect.conectar(); 
		assertEquals(connection != null, true);	
	}

}
