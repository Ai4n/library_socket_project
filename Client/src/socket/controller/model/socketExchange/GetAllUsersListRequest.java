package socket.controller.model.socketExchange;

import socket.controller.model.ServerMessage;
import socket.controller.model.SocketExchange;

public class GetAllUsersListRequest extends SocketExchange {
	
	public GetAllUsersListRequest() {
		super(ServerMessage.GET_ALL_USERS);
	}
}
