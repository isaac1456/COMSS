package co.com.ss.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.ss.shared.Connect;
import com.ss.shared.StatementSQL;





public class TestConnectSQL {

	@Test
	public void testConectar() {
		Connect connect = new Connect();
		
		Connection connection = connect.conectar(); 
		assertEquals(connection != null, true);	
		new StatementSQL().SelectApp();
			
	}

}
