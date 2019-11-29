package SocketRequests;

import Main.Book;
import Main.ServerMessage;

public class AddBookRequest extends SocketRequest{
	
	private Book book;
	
	public AddBookRequest(Book book) {
		super(ServerMessage.ADD_BOOK);
		this.book = book;
	}

	public Book getBook() {
		return book;
	}

}
