package SocketExchange;

import com.google.gson.Gson;

import Main.ServerMessage;

public class AddUserRequest extends SocketExchange{

	private String newLogin;
	private String cryptPassword;
	
	public AddUserRequest(String newLogin, String cryptPassword) {
		super(ServerMessage.ADD_USER);
		this.newLogin = newLogin;
		this.cryptPassword = cryptPassword;
	}
	
	public String getNewLogin() {
		return newLogin;
	}
	
	public String getCryptPassword() {
		return cryptPassword;
	}
	
	public String json() {
		return new Gson().toJson(this);
	}
}
