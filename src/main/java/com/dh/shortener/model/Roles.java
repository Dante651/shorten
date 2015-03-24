package com.dh.shortener.model;

public final class Roles {
	
	public final static String ROLE_USER = "ROLE_USER";
	public final static String ROLE_ADMIN = "ROLE_ADMIN";
	
	public static String[] getRoles(){
		return new String[] {ROLE_USER, ROLE_ADMIN};
	}

}
