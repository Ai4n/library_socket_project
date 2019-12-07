package SocketExchange;

import com.google.gson.Gson;
import Main.ServerMessage;

public class AddBookToUserListRequest extends SocketExchange {

	private int bookId;
	private int userId;

	public AddBookToUserListRequest(int bookId, int userId) {
		super(ServerMessage.ADD_USER_BOOK);
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
