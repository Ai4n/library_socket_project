package socket.controller.model.socketExchange;

import socket.controller.model.ServerMessage;
import socket.controller.model.SocketExchange;

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
