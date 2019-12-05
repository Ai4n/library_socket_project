package SocketExchange;

import com.google.gson.Gson;

import Main.ServerMessage;

public class LoginCheckRequest extends SocketExchange {

	private String newLogin;

	public LoginCheckRequest(String newLogin) {
		super(ServerMessage.LOGIN_CHECK);
		this.newLogin = newLogin;
	}

	public String getNewLogin() {
		return newLogin;
	}

	public String json() {
		return new Gson().toJson(this);
	}

}
