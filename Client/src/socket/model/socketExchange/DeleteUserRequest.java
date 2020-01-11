package socket.model.socketExchange;

import socket.model.ServerMessage;
import socket.model.SocketExchange;

public class DeleteUserRequest extends SocketExchange {

	private int userId;

	public DeleteUserRequest(int userId) {
		super(ServerMessage.DELETE_USER);
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}
}
