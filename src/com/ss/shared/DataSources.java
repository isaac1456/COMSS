package com.ss.shared;

import com.smartgwt.client.widgets.tree.TreeNode;

public class DataSources {

	public static final String HELPTEXT = "<br><b>Severity 1</b> - Critical problem<br>System is unavailable in production or "
			+ "is corrupting data, and the error severely impacts the user's operations."
			+ "<br><br><b>Severity 2</b> - Major problem<br>An important function of the system "
			+ "is not available in production, and the user's operations are restricted."
			+ "<br><br><b>Severity 3</b> - Minor problem<br>Inability to use a function of the "
			+ "system occurs, but it does not seriously affect the user's operations.";

	public static final TreeNode appRoot = new Functions.AppTreeNode("root",
			new Functions.AppTreeNode("Marketing", new Functions.AppTreeNode("Advertising"),
					new Functions.AppTreeNode("Community Relations")),
			new Functions.AppTreeNode("Sales", new Functions.AppTreeNode("Channel Sales"),
					new Functions.AppTreeNode("Direct Sales")),
			new Functions.AppTreeNode("Manufacturing", new Functions.AppTreeNode("Design"),
					new Functions.AppTreeNode("Development"), new Functions.AppTreeNode("QA")),
			new Functions.AppTreeNode("Services", new Functions.AppTreeNode("Support"),
					new Functions.AppTreeNode("Consulting")));

}
