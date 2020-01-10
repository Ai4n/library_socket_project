package socket.controller.model.socketExchange;

import socket.controller.model.ServerMessage;
import socket.controller.model.SocketExchange;

public class GetAllBooksRequest extends SocketExchange {

	public GetAllBooksRequest() {
		super(ServerMessage.GET_ALL_BOOKS);
	}
}
