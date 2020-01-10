package socket.controller.model.socketExchange;

import socket.controller.model.ServerMessage;
import socket.controller.model.SocketExchange;

public class GetAllAuthorsRequest extends SocketExchange {

	public GetAllAuthorsRequest() {
		super(ServerMessage.GET_ALL_AUTHORS);
	}
}
