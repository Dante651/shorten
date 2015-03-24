package com.dh.shortener.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity()
@Table(name="ShortenUrl")
public class ShortenUrl extends BaseEntity {
	
	@Column(length=500)
	private String url;
	
	private String shorten;

	private int count = 0;
	
	private String date;
	
	@ManyToOne
	private User user = null;
	
	public ShortenUrl() {
		
	}

	public ShortenUrl(String url, String shorten) {
		this.url = url;
		this.shorten = shorten;
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getShorten() {
		return shorten;
	}
	
	public void setShorten(String shorten) {
		this.shorten = shorten;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

}
