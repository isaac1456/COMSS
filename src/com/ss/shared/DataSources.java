package com.ss.shared;

import com.smartgwt.client.widgets.tree.TreeNode;

public class DataSources {

	public static final String HELPTEXT = "<br><b>Severity 1</b> - Critical problem<br>System is unavailable in production or "
			+ "is corrupting data, and the error severely impacts the user's operations."
			+ "<br><br><b>Severity 2</b> - Major problem<br>An important function of the system "
			+ "is not available in production, and the user's operations are restricted."
			+ "<br><br><b>Severity 3</b> - Minor problem<br>Inability to use a function of the "
			+ "system occurs, but it does not seriously affect the user's operations.";

	public static final TreeNode appRoot = new Funciones.AppTreeNode("root",
			new Funciones.AppTreeNode("Marketing", new Funciones.AppTreeNode("Advertising"),
					new Funciones.AppTreeNode("Community Relations")),
			new Funciones.AppTreeNode("Sales", new Funciones.AppTreeNode("Channel Sales"),
					new Funciones.AppTreeNode("Direct Sales")),
			new Funciones.AppTreeNode("Manufacturing", new Funciones.AppTreeNode("Design"),
					new Funciones.AppTreeNode("Development"), new Funciones.AppTreeNode("QA")),
			new Funciones.AppTreeNode("Services", new Funciones.AppTreeNode("Support"),
					new Funciones.AppTreeNode("Consulting")));

}
