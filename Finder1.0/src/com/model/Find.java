package com.model;

import java.io.File;
import java.util.concurrent.ThreadPoolExecutor;

import com.controller.Controller;


public class Find implements Runnable {
	
	private File parent;
	private String lookingFor;
	private Controller controller = Controller.getInstance();
	
	public Find(File parent, String lookingFor) {
		this.parent = parent;
		this.lookingFor = lookingFor;
	}

	@Override
	public void run() {
		for (File f : parent.listFiles()) {
				
			if(f.isDirectory() && f.getName().charAt(0) != '.' /* ignorando diretórios ocultos */) {
				Find find = new Find(f, lookingFor);
				controller.getExecutor().execute(find);
			} else {
				if (f.getName().contains(lookingFor)) {
					// System.out.println(f.getAbsolutePath());
					controller.found(f);
				}

			}
		}

		if (((ThreadPoolExecutor) controller.getExecutor()).getActiveCount() <= 1) {
			System.out.println("done!");
			controller.getExecutor().shutdown();
		}
	}
}
