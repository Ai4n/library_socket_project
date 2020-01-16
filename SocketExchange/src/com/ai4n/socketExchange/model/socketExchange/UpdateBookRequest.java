package com.ai4n.socketExchange.model.socketExchange;

import com.ai4n.entities.book.Book;
import com.ai4n.socketExchange.model.ServerMessage;
import com.ai4n.socketExchange.model.SocketExchange;

public class UpdateBookRequest extends SocketExchange {

	private Book book;

	public UpdateBookRequest() {
		super(ServerMessage.UPDATE_BOOK);
	}

	public UpdateBookRequest(Book book) {
		super(ServerMessage.UPDATE_BOOK);
		this.book = book;
	}

	public Book getBook() {
		return book;
	}
}
