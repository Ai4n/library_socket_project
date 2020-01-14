package com.ai4n.socketExchange.model.socketExchange;

import com.ai4n.entities.book.Book;
import com.ai4n.socketExchange.model.ServerMessage;
import com.ai4n.socketExchange.model.SocketExchange;

public class AddBookRequest extends SocketExchange {
	
	private Book book;
	
	public AddBookRequest(Book book) {
		super(ServerMessage.ADD_BOOK);
		this.book = book;
	}

	public Book getBook() {
		return book;
	}
}
