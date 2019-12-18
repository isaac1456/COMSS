package com.ss.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	String selectApp() ; 
	String insertMetrics(String nameMetrics, float valueMetric); 
	String validarVersion(String id);
	String selectAppaVersion();
	String saveAppVersion(String app, String version, boolean aux);
	String insertCiclos(String name, int idVersionfk);
	String[] reporteCiclos(int idApp, int idVersion);
	
}
