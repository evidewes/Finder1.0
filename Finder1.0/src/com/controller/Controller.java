package com.controller;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.DefaultListModel;
import com.model.Find;

public class Controller {
	
	private ExecutorService executor;	
	private DefaultListModel<String> values = new DefaultListModel<String>();
	
	public ExecutorService getExecutor() {
		return executor;
	}
	
	public void search(File parent, String lookFor, DefaultListModel<String> values) {
		getExecutor().execute(new Find(parent, lookFor));
		
		this.values = values;
		this.values.clear();
	}
	
	public void find(File found) {
		values.addElement(found.getAbsolutePath());		
	}
	
	private static Controller singleInstance;
	
	public static Controller getInstance() {
		if (singleInstance == null) {
			singleInstance = new Controller();
		}
		return singleInstance;
	}
	
	private Controller() {
		executor = Executors.newFixedThreadPool(50);
	}
}