package com.dh.shortener.model;

import java.io.Serializable;

public class HelperId implements Serializable{
	

	private static final long serialVersionUID = 1L;

	
	private long fkUser;
	private long fkShorten;
	
	public long getFkUser() {
		return fkUser;
	}
	public void setFkUser(long fkUser) {
		this.fkUser = fkUser;
	}
	public long getFkShorten() {
		return fkShorten;
	}
	public void setFkShorten(long fkShorten) {
		this.fkShorten = fkShorten;
	}

}
