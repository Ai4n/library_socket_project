package com.ai4n.socketExchange.model.socketExchange;

import com.ai4n.socketExchange.model.ServerMessage;
import com.ai4n.socketExchange.model.SocketExchange;

public class DeleteBookFromUsersBookList extends SocketExchange {

	private int userId;
	private int bookId;

	public DeleteBookFromUsersBookList(int bookId, int userId) {
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
}
