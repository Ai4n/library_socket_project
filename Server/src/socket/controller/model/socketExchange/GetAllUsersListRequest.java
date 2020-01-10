package socket.controller.socketExchange;

import socket.controller.model.ServerMessage;

public class GetAllUsersListRequest extends SocketExchange {
	
	public GetAllUsersListRequest() {
		super(ServerMessage.GET_ALL_USERS);
	}
}
