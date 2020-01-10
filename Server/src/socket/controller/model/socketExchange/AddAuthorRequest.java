package socket.controller.model.socketExchange;

import entities.book.Author;
import socket.controller.model.ServerMessage;
import socket.controller.model.SocketExchange;

public class AddAuthorRequest extends SocketExchange {

	private Author author;
	
	public AddAuthorRequest(Author author) {
		super(ServerMessage.ADD_AUTHOR);
		this.author = author;
	}

	public Author getAuthor() {
		return author;
	}
}
