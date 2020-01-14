package com.ai4n.socketExchange.model.socketExchange;

import com.ai4n.socketExchange.model.ServerMessage;
import com.ai4n.socketExchange.model.SocketExchange;

public class DeleteBookRequest extends SocketExchange {

	private int bookId;

	public DeleteBookRequest(int bookId) {
		super(ServerMessage.DELETE_BOOK);
		this.bookId = bookId;
	}

	public int getBookId() {
		return bookId;
	}
}
