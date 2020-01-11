package socket.model.socketExchange;

import socket.model.ServerMessage;
import socket.model.SocketExchange;

public class GetAllAuthorsRequest extends SocketExchange {

	public GetAllAuthorsRequest() {
		super(ServerMessage.GET_ALL_AUTHORS);
	}
}
