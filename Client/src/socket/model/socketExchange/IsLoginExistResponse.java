package socket.model.socketExchange;

import socket.model.ServerMessage;
import socket.model.SocketExchange;

public class IsLoginExistResponse extends SocketExchange {

	public IsLoginExistResponse(Boolean isLoginExist) {
		super(isLoginExist ? ServerMessage.USER_EXIST : ServerMessage.USER_NOT_EXIST);
	}
}
