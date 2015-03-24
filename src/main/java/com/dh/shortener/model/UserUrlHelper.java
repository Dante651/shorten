package com.dh.shortener.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(HelperId.class)
@Table(name="Url_Users")
public class UserUrlHelper implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	public UserUrlHelper() {

	}

	public UserUrlHelper(long fkUser, long fkShorten) {
		this.fkUser = fkUser;
		this.fkShorten = fkShorten;
	}

	@Id
	@Column(name="fk_user")
	private long fkUser;
	
	@Id
	@Column(name="fk_shorten")
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
