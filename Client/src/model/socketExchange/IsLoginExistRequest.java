package socket.model.socketExchange;

import socket.model.serverMessage.ServerMessage;

public class IsLoginExistRequest extends SocketExchange {

	private String newLogin;

	public IsLoginExistRequest(String newLogin) {
		super(ServerMessage.LOGIN_CHECK);
		this.newLogin = newLogin;
	}

	public String getNewLogin() {
		return newLogin;
	}
}