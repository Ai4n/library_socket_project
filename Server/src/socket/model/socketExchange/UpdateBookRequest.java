package socket.model.socketExchange;

import entities.book.Book;
import socket.model.ServerMessage;
import socket.model.SocketExchange;

public class UpdateBookRequest extends SocketExchange {

	private Book book;

	public UpdateBookRequest(Book book) {
		super(ServerMessage.UPDATE_BOOK);
		this.book = book;
	}

	public Book getBook() {
		return book;
	}
}
