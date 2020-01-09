package socket.model.socketExchange;

import socket.model.serverMessage.ServerMessage;

public class GetAllUsersListRequest extends SocketExchange {
	
	public GetAllUsersListRequest() {
		super(ServerMessage.GET_ALL_USERS);
	}
}
