package SocketExchange;

import com.google.gson.Gson;

import Main.ServerMessage;

public class DeleteBookFromUsersList extends SocketExchange {

	private int userId;
	private int bookId;

	public DeleteBookFromUsersList(int bookId, int userId) {
		super(ServerMessage.DELETE_USER_BOOK);
		this.bookId = bookId;
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public int getUserId() {
		return userId;
	}

	public String json() {
		return new Gson().toJson(this);
	}
}
