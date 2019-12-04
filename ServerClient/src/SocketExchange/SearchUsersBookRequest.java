package SocketExchange;

import com.google.gson.Gson;
import Main.ServerMessage;

public class SearchUsersBookRequest extends SocketExchange {

	private int userId;
	private String text;

	public SearchUsersBookRequest(int userId, String text) {
		super(ServerMessage.SEARCH_BOOKS);
		this.userId = userId;
		this.text = text;
	}

	public int getUserId() {
		return userId;
	}

	public String getText() {
		return text;
	}

	public String json() {
		return new Gson().toJson(this);
	}
}
