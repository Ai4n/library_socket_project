package socket.controller.socketExchange;

import socket.controller.model.ServerMessage;

public class GetAllBooksRequest extends SocketExchange {

	public GetAllBooksRequest() {
		super(ServerMessage.GET_ALL_BOOKS);
	}
}
