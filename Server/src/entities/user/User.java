package main;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;
	private int idUser;
	private String name;
	private String surname;
	private String login;
	private String password;
	private UserRole role;

	public User(int idUser, String name, String surname, String login, String password, UserRole role) {
		this.idUser = idUser;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.role = role;
	}
	
	public User(String name, String surname, String login, String password, UserRole role) {
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.role = role;
	}
	public int getIdUser() {
		return idUser;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
	public UserRole getRole() {
		return role;
	}

	public String toString() {
		return name + " " + surname + " " + login + " " + role;
	}
}
