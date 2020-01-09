package socket.model.socketExchange;

import socket.model.serverMessage.ServerMessage;

public class GetAllBooksRequest extends SocketExchange {

	public GetAllBooksRequest() {
		super(ServerMessage.GET_ALL_BOOKS);
	}
}
