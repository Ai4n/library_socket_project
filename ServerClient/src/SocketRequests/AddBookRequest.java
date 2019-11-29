package SocketRequests;

import java.awt.print.Book;

import com.google.gson.Gson;

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

	public String json() {
		return new Gson().toJson(this);
	}
}
