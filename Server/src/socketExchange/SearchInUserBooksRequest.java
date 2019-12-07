package socketExchange;

import com.google.gson.Gson;

import main.ServerMessage;

public class SearchInUserBooksRequest extends SocketExchange {

	private int userId;
	private String text;

	public SearchInUserBooksRequest(int userId, String text) {
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