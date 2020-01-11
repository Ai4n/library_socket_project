package socket.model.socketExchange;

import socket.model.ServerMessage;
import socket.model.SocketExchange;

public class GetAllUsersListRequest extends SocketExchange {
	
	public GetAllUsersListRequest() {
		super(ServerMessage.GET_ALL_USERS);
	}
}
