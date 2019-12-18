package com.ss.shared;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.FieldType;
public class dataAppObjVersion extends DataSource {  
    // The DataSource would normally be defined external to any classes that use it.  
	//String localDir = System.getProperty("user.dir");
    private static dataAppObjVersion instance = null;  
      
    public static dataAppObjVersion getInstance() {  
        if (instance == null) {  
          instance = new dataAppObjVersion("VersionObj_JSON");  
        }  
        return instance;  
    }  

    public dataAppObjVersion(String id) {  
        setID(id);  
        setDataFormat(DSDataFormat.JSON);  
        
    //    DataSourceField idApp = new DataSourceField("idApp",FieldType.TEXT, "Id app");
       
        DataSourceField idVersion = new DataSourceField("idVersion", FieldType.TEXT,"Id Version");
        DataSourceField versionName = new DataSourceField("versionName",FieldType.TEXT, "Name Version");
		 
        setFields(idVersion, versionName);  
        setDataURL("/Resources/version.json");  
       // setClientOnly(true);
    }  

}  