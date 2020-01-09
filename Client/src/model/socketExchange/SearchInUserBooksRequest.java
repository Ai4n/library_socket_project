package socket.model.socketExchange;

import socket.model.serverMessage.ServerMessage;

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
}
