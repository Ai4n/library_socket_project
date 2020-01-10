package socket.controller.model.socketExchange;

import socket.controller.model.ServerMessage;
import entities.user.User;
import socket.controller.model.SocketExchange;

public class UserCheckResponse extends SocketExchange {
	
	private User user;
	
	public UserCheckResponse(boolean isCredentialsCorrect, User user) {
		super(isCredentialsCorrect ? ServerMessage.USER_EXIST : ServerMessage.USER_NOT_EXIST);
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
