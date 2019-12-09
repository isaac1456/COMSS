package com.ss.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gargoylesoftware.htmlunit.javascript.host.Window;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.FormItemCriteriaFunction;
import com.smartgwt.client.widgets.form.fields.FormItemFunctionContext;
import com.smartgwt.client.widgets.form.fields.IPickTreeItem;
import com.smartgwt.client.widgets.form.fields.MultiComboBoxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;
import com.smartgwt.client.widgets.tree.Tree;
import com.ss.shared.DataSources;
import com.ss.shared.Functions;
import com.ss.shared.dataAppObj;
import com.ss.shared.dataAppObjVersion;
import com.ss.shared.dataAppReg;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Comss implements EntryPoint {

	public void onModuleLoad() {

		final GreetingServiceAsync gsa = GWT.create(GreetingService.class);

		// LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		// hashMap.put("wrq", "weqrwe");
		// Map<String, String[]> map = new HashMap<String, String[]>();
		final TabSet tabSet = new TabSet();
		tabSet.setTabBarPosition(Side.TOP);
		tabSet.setWidth("90%");
		tabSet.setHeight("80%");

		tabSet.setStyleName("containertabs");
		Tab tTabApp = new Tab("Aplicaciones", "");
		Tab tTabResult = new Tab("Resultados", "");
		Tab tTabRegisteer = new Tab("Registro", "");

		tabSet.addTab(tTabApp);
		tabSet.addTab(tTabResult);
		tabSet.addTab(tTabRegisteer);
		CheckboxItem chkaddVerApp = new CheckboxItem();
		// ListGridField lgfidApp = new ListGridField("idApp");
		ListGridField lgfNameApp = new ListGridField("appName");
		// ListGridField lgfidVersion = new ListGridField("idVersion");
		ListGridField lgfVersion = new ListGridField("versionName");

		final DynamicForm formApp = new DynamicForm();
		formApp.setWidth(500);
		final TextItem txtNameCiclo = new TextItem();
		final SelectItem cbxappsReg = new SelectItem("apps");
		final SelectItem cbxapps = new SelectItem("apps");
		final SelectItem cbxVersion = new SelectItem();
		gsa.selectApp(new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				DataSource dataApp = dataAppObj.getInstance();
				// DataSource dataVersion = dataAppObjVersion.getInstance();

				cbxapps.setOptionDataSource(dataApp);
				cbxappsReg.setOptionDataSource(dataApp);

				// cbxVersion.setOptionDataSource(dataVersion);

			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});

		cbxapps.setWidth(240);
		cbxapps.setTitle("Apps");
		cbxapps.setValueField("idApp");
		cbxapps.setDisplayField("appName");
		cbxapps.setName("cbxAppName");

		cbxapps.setPickListFields(lgfNameApp);
		cbxapps.setPickListWidth(240);

		cbxapps.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {

				gsa.validarVersion("" + event.getValue(), new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) { // TODO Auto-generated

					}

					@Override
					public void onSuccess(String result) {

						formApp.clearValue("cbxVersionName");
						DataSource dataVersion = dataAppObjVersion.getInstance();
						cbxVersion.setOptionDataSource(dataVersion);
					}
				});
			}
		});

		cbxVersion.setWidth(240);
		cbxVersion.setName("cbxVersionName");
		cbxVersion.setTitle("Version");
		cbxVersion.setValueField("versionName");
		cbxVersion.setDisplayField("versionName");
		cbxVersion.setPickListFields(lgfVersion);
		cbxVersion.setPickListWidth(240);

		/*
		 * cbxVersion.setPickListFilterCriteriaFunction(new FormItemCriteriaFunction() {
		 * 
		 * @Override public Criteria getCriteria(FormItemFunctionContext itemContext) {
		 * String version = (String) cbxapps.getValue(); Criteria criteria = new
		 * Criteria("idApp", version); return criteria; } });
		 */

		tTabApp.setPane(formApp);

		txtNameCiclo.setTitle("Nombre del ciclo");
		txtNameCiclo.setWidth(240);
		txtNameCiclo.setRequired(true);
		txtNameCiclo.setIcons(Functions.iconTextHelp(DataSources.HELPTEXT));
		txtNameCiclo.setShowIcons(true);
		txtNameCiclo.setDisabled(true);

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

		formApp.setFields(new FormItem[] { cbxapps, cbxVersion, txtNameCiclo, mcbxmetrics });

		final DynamicForm formResult = new DynamicForm();
		formResult.setWidth(500);

		Tree tree = new Tree();
		tree.setRoot(DataSources.appRoot);

		IPickTreeItem iptiApp = new IPickTreeItem();
		iptiApp.setTitle("Apps");
		iptiApp.setValueField("name");
		iptiApp.setValueTree(tree);
		iptiApp.setWidth(240);

		formResult.setItems(new FormItem[] { iptiApp });

		tTabResult.setPane(formResult);
		VLayout layoutRegister = new VLayout(15);
		final ListGrid listGrid = new ListGrid();
		final DynamicForm formRegisterApp = new DynamicForm();
		formRegisterApp.setWidth(500);

		final TextItem nameApp = new TextItem();
		nameApp.setTitle("Nombre de la App");
		nameApp.setWidth(240);
		nameApp.setRequired(true);

		final TextItem version = new TextItem();
		version.setTitle("Version");
		version.setWidth(240);
		version.setRequired(true);

		ButtonItem btnsaveApp = new ButtonItem();
		btnsaveApp.setName("btnsaveApp");
		btnsaveApp.setTitle("Registrar");

		btnsaveApp.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (formRegisterApp.validate()) {

					boolean aux = true;
					String name = nameApp.getDisplayValue();
					String vers = version.getDisplayValue();
					if (name.equalsIgnoreCase("")) {
						
						name = cbxappsReg.getValueField();
		
						

					} else {
						
						aux = false;
					}

					SC.say(name + "-" + aux);
					/*
					 * gsa.saveAppVersion(name, vers,aux, new AsyncCallback<String>() {
					 * 
					 * @Override public void onSuccess(String result) {
					 * 
					 * // TODO Auto-generated method stub formRegisterApp.resetValues();
					 * gsa.selectAppaVersion(new AsyncCallback<String>() {
					 * 
					 * @Override public void onFailure(Throwable caught) { // TODO Auto-generated
					 * method stub
					 * 
					 * }
					 * 
					 * @Override public void onSuccess(String result) { // DataSource dataSAppReg =
					 * dataAppReg.getInstance(); //
					 * listGrid.setDataSource(dataAppReg.getInstance());
					 * 
					 * listGrid.refreshData(); } });
					 * 
					 * }
					 * 
					 * @Override public void onFailure(Throwable caught) { // TODO Auto-generated
					 * method stub
					 * 
					 * } });
					 */
				}
			}
		});

		cbxappsReg.setWidth(240);
		cbxappsReg.setTitle("Apps");
		cbxappsReg.setValueField("idApp");
		cbxappsReg.setDisplayField("appName");
		cbxappsReg.setName("cbxAppName");
		cbxappsReg.setDisabled(true);
		

		cbxappsReg.setPickListFields(lgfNameApp);
		cbxappsReg.setPickListWidth(240);

		cbxappsReg.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {

				/*
				 * gsa.validarVersion("" + event.getValue(), new AsyncCallback<String>() {
				 * 
				 * @Override public void onFailure(Throwable caught) { // TODO Auto-generated
				 * 
				 * }
				 * 
				 * @Override public void onSuccess(String result) {
				 * 
				 * formApp.clearValue("cbxVersionName"); DataSource dataVersion =
				 * dataAppObjVersion.getInstance(); cbxVersion.setOptionDataSource(dataVersion);
				 * } });
				 */
			}
		});

		chkaddVerApp.setName("addVersion");
		chkaddVerApp.setTitle("Agregar version a una App");
		chkaddVerApp.setValue(false);
		chkaddVerApp.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				cbxappsReg.setDisabled(!((Boolean) event.getValue()));
				cbxappsReg.setRequired(true);
				nameApp.setDisabled(((Boolean) event.getValue()));
				nameApp.setRequired(false);
			}
		});

		formRegisterApp.setFields(new FormItem[] { cbxappsReg, nameApp, version, chkaddVerApp, btnsaveApp });

		layoutRegister.addMember(formRegisterApp);

		// layoutRegister.addMember(save);

		listGrid.setWidth(500);
		listGrid.setHeight(500);
		listGrid.setDataSource(dataAppReg.getInstance());
		listGrid.setAutoFetchData(true);
		listGrid.setShowAllRecords(true);

		tTabRegisteer.addTabSelectedHandler(new TabSelectedHandler() {

			@Override
			public void onTabSelected(TabSelectedEvent event) {
				// SC.say("entro en tab");

				gsa.selectAppaVersion(new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(String result) {
						// DataSource dataSAppReg = dataAppReg.getInstance();
						// listGrid.setDataSource(dataAppReg.getInstance());

						listGrid.refreshData();
					}
				});

				gsa.selectApp(new AsyncCallback<String>() {

					@Override
					public void onSuccess(String result) {
						cbxappsReg.clearValue();
						DataSource dataApp = dataAppObj.getInstance();
						// DataSource dataVersion = dataAppObjVersion.getInstance();

						cbxappsReg.setOptionDataSource(dataApp);
						// cbxVersion.setOptionDataSource(dataVersion);

					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}
				});

			}
		});

		tTabApp.addTabSelectedHandler(new TabSelectedHandler() {

			@Override
			public void onTabSelected(TabSelectedEvent event) {
				gsa.selectApp(new AsyncCallback<String>() {

					@Override
					public void onSuccess(String result) {
						cbxapps.clearValue();
						DataSource dataApp = dataAppObj.getInstance();
						// DataSource dataVersion = dataAppObjVersion.getInstance();

						cbxapps.setOptionDataSource(dataApp);
						// cbxVersion.setOptionDataSource(dataVersion);

					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}
				});

			}
		});
		layoutRegister.addMember(listGrid);

		tTabRegisteer.setPane(layoutRegister);

		RootPanel.get("containerMain").add(tabSet);

		System.out.println("Result on post+result+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

	}
}
