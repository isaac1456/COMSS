package com.ss.client;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

import co.com.ss.models.AppObj;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
	void selectApp(AsyncCallback<String> asyncCallback); 
}
