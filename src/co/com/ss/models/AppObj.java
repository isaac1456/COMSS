package co.com.ss.models;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AppObj implements IsSerializable {
	int idApp = 0;
	String appName = "";
	int idVersion = 0;
	String versionName = "";
	ArrayList<AppObj> arrayListAppObjt = null; 


	public AppObj(int idApp, String appName, int idVersion, String versionName) {
		super();
		this.idApp = idApp;
		this.appName = appName;
		this.idVersion = idVersion;
		this.versionName = versionName;
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

	public int getIdVersion() {
		return idVersion;
	}

	public void setIdVersion(int idVersion) {
		this.idVersion = idVersion;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public ArrayList<AppObj> arrayAppdata(AppObj appObj) {
		ArrayList<AppObj> appObjs = new ArrayList<AppObj>();
		appObjs.add(appObj);
		return appObjs;
		// TODO Auto-generated method stub

	}

	public ArrayList<AppObj> getArrayListAppObjt() {
		return arrayListAppObjt;
	}

	public void setArrayListAppObjt(ArrayList<AppObj> arrayListAppObjt) {
		this.arrayListAppObjt = arrayListAppObjt;
	}

}
