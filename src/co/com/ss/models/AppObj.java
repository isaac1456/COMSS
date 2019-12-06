package co.com.ss.models;

public class AppObj {
	int idApp = 0;
	String appName= "";
	
	public AppObj(int idApp, String appName) {
		super();
		this.idApp = idApp;
		this.appName = appName;
	}
	
	public int getIdApp() {
		return idApp;
	}
	public void setIdApp(int idApp) {
		this.idApp = idApp;
	}
	public String getAppName() {
		return appName;
	}
	
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
}
