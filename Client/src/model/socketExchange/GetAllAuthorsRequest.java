package socket.model.socketExchange;

import socket.model.serverMessage.ServerMessage;

public class GetAllAuthorsRequest extends SocketExchange {

	public GetAllAuthorsRequest() {
		super(ServerMessage.GET_ALL_AUTHORS);
	}
}
