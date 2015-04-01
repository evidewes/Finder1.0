package com.controller;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.model.Find;
import javax.swing.JTextArea;

public class Controller {
	
	private static Controller singleInstance;
	private ExecutorService executor;	
	private StringBuilder values = new StringBuilder();
	private JTextArea textArea;
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
	
	public void search(File parent, String lookFor, JTextArea textArea) {
		getExecutor().execute(new Find(parent, lookFor));
				
		this.textArea = textArea;
		this.values = new StringBuilder();
	}
	
	public void find(File found) {		
		values.append(found.getAbsolutePath());
		values.append("\n");
		textArea.setText(values.toString());
	}	
}