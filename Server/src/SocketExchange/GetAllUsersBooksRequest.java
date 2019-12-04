package SocketExchange;

import com.google.gson.Gson;
import Main.ServerMessage;

public class GetAllUsersBooksRequest extends SocketExchange {

	private int userId;
	
	public GetAllUsersBooksRequest(int userId) {
		super(ServerMessage.SHOW_BOOKS);
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}
	
	public String json() {
		return new Gson().toJson(this);
	}
}
