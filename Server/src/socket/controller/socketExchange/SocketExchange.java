package socket.controller.controller.socketExchange;
import com.google.gson.Gson;

import model.ServerMessage;

public class SocketExchange {

	final public ServerMessage message;

	public SocketExchange(ServerMessage message) {
		this.message = message;
	}
	
	public String json() {
		return new Gson().toJson(this);
	}
}
