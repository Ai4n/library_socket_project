package SocketExchange;

import com.google.gson.Gson;

import Main.ServerMessage;

public class GetAllUsersListRequest extends SocketExchange {
	
	public GetAllUsersListRequest() {
		super(ServerMessage.GET_ALL_USERS);
	}

	public String json() {
		return new Gson().toJson(this);
	}
}
