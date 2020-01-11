package socket.model.socketExchange;

import socket.model.ServerMessage;
import socket.model.SocketExchange;

public class GetAllBooksRequest extends SocketExchange {

	public GetAllBooksRequest() {
		super(ServerMessage.GET_ALL_BOOKS);
	}
}
