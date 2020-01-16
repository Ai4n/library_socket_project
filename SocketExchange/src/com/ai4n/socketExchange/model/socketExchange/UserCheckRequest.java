package com.ai4n.socketExchange.model.socketExchange;

import com.ai4n.socketExchange.model.ServerMessage;
import com.ai4n.socketExchange.model.SocketExchange;

public class UserCheckRequest extends SocketExchange {

	private String login;
	private String password;

	public UserCheckRequest() {
		super(ServerMessage.USER_CHECK);
	}

	public UserCheckRequest(String login, String password) {
		super(ServerMessage.USER_CHECK);
		this.login = login;
		this.password = password;
	}
	
	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
}