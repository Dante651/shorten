package com.dh.shorten.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ShortenUrl")
public class ShortenUrl extends BaseEntity {
	
	private String url;
	private String shorten;
	private int count;
	private Date date;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
