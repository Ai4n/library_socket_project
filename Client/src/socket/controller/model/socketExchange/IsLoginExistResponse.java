package socket.controller.model.socketExchange;

import socket.controller.model.ServerMessage;
import socket.controller.model.SocketExchange;

public class IsLoginExistResponse extends SocketExchange {

	public IsLoginExistResponse(Boolean isLoginExist) {
		super(isLoginExist ? ServerMessage.USER_EXIST : ServerMessage.USER_NOT_EXIST);
	}
}
