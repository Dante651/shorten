package com.dh.shortener.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User extends BaseEntity {

	public User() {

	}

	public User(RegistrationHelper helper){
		this.userName = helper.getUserName();
		this.password = helper.getPassword();
		this.email = helper.getEmail();
	}

	private String userName;

	private String password;

	private String email;

	private String role = Roles.ROLE_USER;

	private boolean enabled = true;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "Url_Users", joinColumns = @JoinColumn(name = "fk_user"), inverseJoinColumns = @JoinColumn(name = "fk_shorten"))
	private Set<ShortenUrl> shortenUrl;

	public Set<ShortenUrl> getShortenUrl() {
		return shortenUrl;
	}

	public void setShortenUrl(Set<ShortenUrl> shortenUrl) {
		this.shortenUrl = shortenUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return userName;
	}

}
