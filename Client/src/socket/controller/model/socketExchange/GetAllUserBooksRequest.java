package socket.controller.model.socketExchange;

import socket.controller.model.ServerMessage;
import socket.controller.model.SocketExchange;

public class GetAllUserBooksRequest extends SocketExchange {

	private int userId;
	
	public GetAllUserBooksRequest(int userId) {
		super(ServerMessage.SHOW_BOOKS);
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}
}
