package com.ai4n.socketExchange.model.socketExchange;

import com.ai4n.entities.book.Author;
import com.ai4n.socketExchange.model.ServerMessage;
import com.ai4n.socketExchange.model.SocketExchange;

public class AddAuthorRequest extends SocketExchange {

	private Author author;

	public AddAuthorRequest() {
		super(ServerMessage.ADD_AUTHOR);
	}

	public AddAuthorRequest(Author author) {
		super(ServerMessage.ADD_AUTHOR);
		this.author = author;
	}

	public Author getAuthor() {
		return author;
	}
}
