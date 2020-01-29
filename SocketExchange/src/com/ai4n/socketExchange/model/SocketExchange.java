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

	public static SocketExchange create(String jsonString) {
		try {
			SocketExchange request = new Gson().fromJson(jsonString, SocketExchange.class);
			request.json = jsonString;
			return request;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
