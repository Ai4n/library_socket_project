package com.ai4n.socketExchange.model.socketExchange;

import com.ai4n.entities.user.User;
import com.ai4n.socketExchange.model.ServerMessage;
import com.ai4n.socketExchange.model.SocketExchange;

public class AddUserRequest extends SocketExchange {

	private User user;

	public AddUserRequest(User user) {
		super(ServerMessage.ADD_USER);
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
