package socketExchange;

import com.google.gson.Gson;

import main.ServerMessage;

public class AddBookToUsersBookListRequest extends SocketExchange {

	private int bookId;
	private int userId;

	public AddBookToUsersBookListRequest(int bookId, int userId) {
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