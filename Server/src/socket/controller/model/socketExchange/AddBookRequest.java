package socket.controller.socketExchange;

import entities.book.Book;
import socket.controller.model.ServerMessage;

public class AddBookRequest extends SocketExchange{
	
	private Book book;
	
	public AddBookRequest(Book book) {
		super(ServerMessage.ADD_BOOK);
		this.book = book;
	}

	public Book getBook() {
		return book;
	}
}
