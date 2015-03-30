package com.controller;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.DefaultListModel;

import com.model.Find;

public class Controller {
	
	private static Controller singleInstance;
	private ExecutorService executor;	
	private DefaultListModel<String> values = new DefaultListModel<String>();
	
	private Controller() {
		executor = Executors.newFixedThreadPool(100);
	}
	
	public static Controller getInstance() {
		if (singleInstance == null) {
			singleInstance = new Controller();
		}
		return singleInstance;
	}
	
	public ExecutorService getExecutor() {
		return executor;
	}
	
	public void search(File parent, String lookFor, DefaultListModel<String> values) {
		getExecutor().execute(new Find(parent, lookFor));
		
		this.values = values;
		this.values.clear();
	}
	
	public void find(File found) {		
		//System.out.println(found.getAbsolutePath());	
		values.addElement(found.getAbsolutePath());		
	}	
}