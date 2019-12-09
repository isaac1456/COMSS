package com.ss.shared;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.FieldType;
public class dataAppReg extends DataSource {  
    // The DataSource would normally be defined external to any classes that use it.  
	//String localDir = System.getProperty("user.dir");
    private static dataAppReg instance = null;  
      
    public static dataAppReg getInstance() {  
        if (instance == null) {  
          instance = new dataAppReg("appVersionReg_JSON");  
        }  
        return instance;  
    }  

    public dataAppReg(String id) {  
        setID(id);  
        setDataFormat(DSDataFormat.JSON);  
        
        DataSourceField idApp = new DataSourceField("idApp",FieldType.INTEGER, "Id app");
        DataSourceField appName = new DataSourceField("appName", FieldType.TEXT,"Name App");
     	DataSourceField idVersion = new DataSourceField("idVersion", FieldType.INTEGER,"Id Version");
        DataSourceField versionName = new DataSourceField("versionName",FieldType.TEXT, "Name Version");
		 
        setFields(idApp, appName, idVersion, versionName);  
        setDataURL("/Resources/appVersion.json");  
    }  

}  