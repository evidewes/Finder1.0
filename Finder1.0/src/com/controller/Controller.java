package com.controller;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.AbstractListModel;
import javax.swing.JList;

import com.model.Find;

public class Controller {
	
	private ExecutorService executor;
	private  JList<String> jList;
	private List<String> values;
	
	public ExecutorService getExecutor() {
		return executor;
	}
	
	public void search(File parent, String lookFor, JList<String> jList) {
		getExecutor().execute(new Find(parent, lookFor));
		
		this.jList = jList;
		values = new LinkedList<String>();
		this.jList.setModel(new AbstractListModel<String>() {

			private static final long serialVersionUID = 1L;
			public int getSize() {
				return values.size();
			}
			public String getElementAt(int index) {
				return values.get(index);
			}
		});
	}
	
	public void found(File founded) {
		//System.out.println(founded.getAbsolutePath());
		values.add(founded.getAbsolutePath());
		jList.setListData(values.toArray(new String[values.size()]));
	}
	
	private static Controller singleInstance;
	public static Controller getInstance() {
		if (singleInstance == null)
			singleInstance = new Controller();
		return singleInstance;
	}
	private Controller() {
		executor = Executors.newFixedThreadPool(50);
	}
	

}
