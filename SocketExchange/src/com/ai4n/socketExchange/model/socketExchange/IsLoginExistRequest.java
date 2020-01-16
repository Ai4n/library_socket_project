package com.ai4n.socketExchange.model.socketExchange;

import com.ai4n.socketExchange.model.ServerMessage;
import com.ai4n.socketExchange.model.SocketExchange;

public class IsLoginExistRequest extends SocketExchange {

	private String newLogin;

	public IsLoginExistRequest() {
		super(ServerMessage.LOGIN_CHECK);
	}

	public IsLoginExistRequest(String newLogin) {
		super(ServerMessage.LOGIN_CHECK);
		this.newLogin = newLogin;
	}

	public String getNewLogin() {
		return newLogin;
	}
}
