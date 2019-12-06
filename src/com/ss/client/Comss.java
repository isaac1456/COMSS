package com.ss.client;

import java.util.LinkedHashMap;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.IPickTreeItem;
import com.smartgwt.client.widgets.form.fields.MultiComboBoxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.FormItemClickHandler;
import com.smartgwt.client.widgets.form.fields.events.FormItemIconClickEvent;
import com.smartgwt.client.widgets.form.validator.IntegerRangeValidator;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tree.Tree;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Comss implements EntryPoint {

	public void onModuleLoad() {

		final TabSet tabSet = new TabSet();
		tabSet.setTabBarPosition(Side.TOP);
		tabSet.setWidth("90%");
		tabSet.setHeight("80%");

		tabSet.setStyleName("containertabs");
		Tab tTabApp = new Tab("Aplicaciones", "");
		Tab tTabResult = new Tab("Resultados", "");

		tabSet.addTab(tTabApp);
		tabSet.addTab(tTabResult);

		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		hashMap.put("1", "uno");
		hashMap.put("2", "dos");
		hashMap.put("3", "tres");

		ListGridField lgfName = new ListGridField("Name");
		ListGridField lgfVersion = new ListGridField("Version");

		final DynamicForm formApp = new DynamicForm();
		formApp.setWidth(500);

		SelectItem cbxapps = new SelectItem("apps");
		cbxapps.setWidth(240);
		cbxapps.setTitle("Apps");
		cbxapps.setValueField("Version");
		cbxapps.setDisplayField("Name");
		cbxapps.setValueMap(hashMap);
		cbxapps.setPickListFields(lgfName, lgfVersion);
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

		final TextItem txtversionapp = new TextItem();
		txtversionapp.setTitle("Version");
		txtversionapp.setWidth(240);
		txtversionapp.setRequired(true);
		txtversionapp.setKeyPressFilter("[0-9.]");
		txtversionapp.setIcons(Functions.iconTextHelp(DataSources.HELPTEXT));
		txtversionapp.setShowIcons(true);
		// populationField.setType(ListGridFieldType.INTEGER);

		// DataSourceField dataSourceField = new DataSourceField();

		// populationField.setValidators(integerRangeValidator);

		MultiComboBoxItem mcbxmetrics = new MultiComboBoxItem();
		// mcbxmetrics.setWidth(240);
		mcbxmetrics.setUseInsertionOrder(false);
		mcbxmetrics.setName("Metricas");
		mcbxmetrics.setShowPending(true);
		mcbxmetrics.setValueMap(hashMap);
	//	mcbxmetrics.setIcons(Functions.iconTextHelp(DataSources.HELPTEXT));
		mcbxmetrics.setShowIcons(true);

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
		
		formResult.setItems(new FormItem[] {iptiApp});
		
		tTabResult.setPane(formResult);

		RootPanel.get("containerMain").add(tabSet);
		// tabSet.draw();

	}

}
