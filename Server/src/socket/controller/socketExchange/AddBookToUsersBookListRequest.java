package socket.controller.socketExchange;

import model.ServerMessage;

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
}
