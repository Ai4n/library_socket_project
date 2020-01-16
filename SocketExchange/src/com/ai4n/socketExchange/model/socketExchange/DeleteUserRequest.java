package com.ai4n.socketExchange.model.socketExchange;

import com.ai4n.socketExchange.model.ServerMessage;
import com.ai4n.socketExchange.model.SocketExchange;

public class DeleteUserRequest extends SocketExchange {

	private int userId;

	public DeleteUserRequest() {
		super(ServerMessage.DELETE_USER);
	}

	public DeleteUserRequest(int userId) {
		super(ServerMessage.DELETE_USER);
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}
}
