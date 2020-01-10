package socket.controller.model.socketExchange;

import socket.controller.model.ServerMessage;
import socket.controller.model.SocketExchange;

public class UserCheckRequest extends SocketExchange {

	private String login;
	private String password;
	
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