package com.ai4n.socketExchange.model.socketExchange;

import com.ai4n.entities.user.User;
import com.ai4n.socketExchange.model.ServerMessage;
import com.ai4n.socketExchange.model.SocketExchange;

public class UserCheckResponse extends SocketExchange {
	
	private User user;

	public UserCheckResponse() {
		super(ServerMessage.EMPTY);
	}

	public UserCheckResponse(boolean isCredentialsCorrect, User user) {
		super(isCredentialsCorrect ? ServerMessage.USER_EXIST : ServerMessage.USER_NOT_EXIST);
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
