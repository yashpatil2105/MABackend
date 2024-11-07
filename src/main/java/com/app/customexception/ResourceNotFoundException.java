package com.app.customexception;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends Exception{
	
	public ResourceNotFoundException(String mesg) {
		super(mesg);
	}

}
