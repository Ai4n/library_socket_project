package socket.controller.socketExchange;

import entities.book.Book;
import socket.controller.model.ServerMessage;

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
