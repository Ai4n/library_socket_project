package SocketExchange;

import com.google.gson.Gson;
import Main.ServerMessage;
import Main.User;

public class UserCheckResponse extends SocketExchange {
	
	private User user;
	public UserCheckResponse (User user) {
		super(ServerMessage.USER_EXIST);
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	
	public static ServerMessage getMessage() {
		return ServerMessage.USER_EXIST;
	}
	
	public String json() {
		return new Gson().toJson(this);
	}
}
