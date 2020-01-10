package socket.controller.socketExchange;

import socket.controller.model.ServerMessage;

public class GetAllAuthorsRequest extends SocketExchange {

	public GetAllAuthorsRequest() {
		super(ServerMessage.GET_ALL_AUTHORS);
	}
}
