package SocketRequests;

import Main.Author;
import Main.ServerMessage;

public abstract class SocketRequest {

	final public ServerMessage message;

	public SocketRequest(ServerMessage message) {
		this.message = message;
	}
	
}
