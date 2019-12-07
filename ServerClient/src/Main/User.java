package Main;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;
	private int idUser;
	private String name;
	private String surName;
	private String login;
	private String password;
	private UserRole role;

	public User(int idUser, String login, String password, UserRole role) {
		this.idUser = idUser;
		this.login = login;
		this.password = password;
		this.role = role;
	}
	
	public User(String login, String password, UserRole role) {
		this.login = login;
		this.password = password;
		this.role = role;
	}
	
	public int getIdUser() {
		return idUser;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public String toString() {
		return login + " " + role;
	}
}
