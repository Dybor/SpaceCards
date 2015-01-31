package com.geekteam.core.io;

public class Messenger {

	// Attributes
	private String name;
	private boolean active;
	
	// Builder
	public Messenger(String n) {
		name =n;
		active =true;
	}
	
	// Public methods
	public void setInactive() {
		active =false;
	}
	
	public void sendMessage(String msg) {
		if (active) System.out.println(name +" : "+msg);
	}
	
}
