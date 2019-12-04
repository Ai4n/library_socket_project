package SocketExchange;

import java.util.ArrayList;

import com.google.gson.Gson;

import Main.Book;
import Main.ServerMessage;

public class UpdateBookRequest extends SocketExchange {

	private Book book;
	public UpdateBookRequest(Book book) {
		super(ServerMessage.UPDATE_BOOK);
		this.book = book;
	}

	public Book getBook() {
		return book;
	}

	public String json() {
		return new Gson().toJson(this);
	}
}
