package SocketExchange;

import com.google.gson.Gson;

import Main.Book;
import Main.ServerMessage;

public class AddBookRequest extends SocketExchange{
	
	private Book book;
	
	public AddBookRequest(Book book) {
		super(ServerMessage.ADD_BOOK);
		this.book = book;
	}

	public Book getBook() {
		return book;
	}

	public String json() {
		return new Gson().toJson(this);
	}
}
