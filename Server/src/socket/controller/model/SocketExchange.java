package socket.controller.model;

import com.google.gson.Gson;
import socket.controller.model.ServerMessage;

public class SocketExchange {

	final public ServerMessage message;

	public SocketExchange(ServerMessage message) {
		this.message = message;
	}
	
	public String json() {
		return new Gson().toJson(this);
	}
}
