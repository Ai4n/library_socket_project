package com.ai4n.socketExchange.model;

import com.google.gson.Gson;

public class SocketExchange {

	final public ServerMessage message;
	public String json;
	public SocketExchange() {
		this.message = ServerMessage.EMPTY;
	}
	public SocketExchange(ServerMessage message) {
		this.message = message;
	}
	
	public String json() {
		return new Gson().toJson(this);
	}
}
