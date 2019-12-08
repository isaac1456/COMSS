package com.ss.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.IPickTreeItem;
import com.smartgwt.client.widgets.form.fields.MultiComboBoxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tree.Tree;
import com.ss.shared.DataSources;
import com.ss.shared.Functions;
import com.ss.shared.dataAppObj;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Comss implements EntryPoint {

	public void onModuleLoad() {

		final GreetingServiceAsync gsa = GWT.create(GreetingService.class);

		// LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		// hashMap.put("wrq", "weqrwe");
		final TabSet tabSet = new TabSet();
		tabSet.setTabBarPosition(Side.TOP);
		tabSet.setWidth("90%");
		tabSet.setHeight("80%");

		tabSet.setStyleName("containertabs");
		Tab tTabApp = new Tab("Aplicaciones", "");
		Tab tTabResult = new Tab("Resultados", "");

		tabSet.addTab(tTabApp);
		tabSet.addTab(tTabResult);
	//	ListGridField lgfidApp = new ListGridField("idApp");
		ListGridField lgfNameApp = new ListGridField("appName");
		//ListGridField lgfidVersion = new ListGridField("idVersion");
		ListGridField lgfVersion = new ListGridField("versionName");

		final DynamicForm formApp = new DynamicForm();
		formApp.setWidth(500);

		final SelectItem cbxapps = new SelectItem("apps");
		gsa.selectApp(new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				  DataSource dataApp = dataAppObj.getInstance();  
				cbxapps.setOptionDataSource(dataApp);
				

			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});

		cbxapps.setWidth(240);
		cbxapps.setTitle("Apps");
		cbxapps.setValueField("idVersion");
		cbxapps.setDisplayField("versionName");

		cbxapps.setPickListFields( lgfNameApp, lgfVersion);
		cbxapps.setPickListWidth(240);
		cbxapps.setIcons(Functions.iconTextHelp(DataSources.HELPTEXT));
		cbxapps.setShowIcons(true);

		tTabApp.setPane(formApp);

		final TextItem txtnameapp = new TextItem();
		txtnameapp.setTitle("Nombre del ciclo");
		txtnameapp.setWidth(240);
		txtnameapp.setRequired(true);
		txtnameapp.setIcons(Functions.iconTextHelp(DataSources.HELPTEXT));
		txtnameapp.setShowIcons(true);

		final MultiComboBoxItem mcbxmetrics = new MultiComboBoxItem();
		// mcbxmetrics.setWidth(240);
		mcbxmetrics.setUseInsertionOrder(false);
		mcbxmetrics.setName("Metricas");
		mcbxmetrics.setShowPending(true);

		// mcbxmetrics.setIcons(Functions.iconTextHelp(DataSources.HELPTEXT));
		mcbxmetrics.setShowIcons(true);

		final Label label = new Label();
		label.setHeight(30);
		label.setPadding(10);
		label.setAlign(Alignment.CENTER);
		label.setValign(VerticalAlignment.CENTER);
		label.setWrap(false);

		formApp.setFields(new FormItem[] { cbxapps, txtnameapp, txtversionapp, mcbxmetrics });

		final DynamicForm formResult = new DynamicForm();
		formResult.setWidth(500);

		Tree tree = new Tree();
		tree.setRoot(DataSources.appRoot);

		IPickTreeItem iptiApp = new IPickTreeItem();
		iptiApp.setTitle("Apps");
		iptiApp.setValueField("name");
		iptiApp.setValueTree(tree);
		iptiApp.setWidth(240);
		
		final ListGrid grid = new ListGrid();  
   
        grid.setWidth100();  
        grid.setHeight(100);  
        grid.setAutoFetchData(true);  

		formResult.setItems(new FormItem[] { iptiApp });

		tTabResult.setPane(formResult);

		RootPanel.get("containerMain").add(tabSet);
		RootPanel.get("containerMain").add(grid);
		// tabSet.draw();
		System.out.println("Result on post+result+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

	
	}
}
