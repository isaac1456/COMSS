package com.ss.shared;

import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.events.FormItemClickHandler;
import com.smartgwt.client.widgets.form.fields.events.FormItemIconClickEvent;
import com.smartgwt.client.widgets.tree.TreeNode;

public class Functions {

	public static FormItemIcon iconTextHelp(String heltext) {
		final String aux = heltext; 
		// TODO Auto-generated method stub
		 FormItemIcon icon = new FormItemIcon();  
	        icon.setSrc("[SKIN]/actions/help.png");  
	        icon.addFormItemClickHandler(new FormItemClickHandler() {  
	            @Override  
	            public void onFormItemClick(FormItemIconClickEvent event) {  
	                SC.say(aux);  
	            }  
	        });  

	        return icon; 
	}
	
	 public static class AppTreeNode extends TreeNode {  
		  
	        public AppTreeNode(String name) {  
	            setName(name);  
	        }  
	  
	        public AppTreeNode(String name, AppTreeNode... children) {  
	            setName(name);  
	            setChildren(children);  
	        }  
	    }  
	 
	 public float calEfectividad(float A, float B) {
		return (A*100)/B;
	}
	 
	 public float calFuncion(float A, float B) {
			return (1 - A)/B;
		}

	 public float calBugs(float A, float B) {
			return A/B;
		}
	 
	 public float EED(float A, float B) {
			return A/(A+B);
		}
}


