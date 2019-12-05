package SocketExchange;

import java.util.ArrayList;

import com.google.gson.Gson;

import Main.Book;
import Main.ServerMessage;

public class GetUsersBookResponse extends SocketExchange {

	private ArrayList<Book> booksList;

	public GetUsersBookResponse(ArrayList<Book> booksList) {
		super(ServerMessage.SHOW_BOOKS);
		this.booksList = booksList;
	}

	public ArrayList<Book> getBooksList() {
		return booksList;
	}
	
	public String json() {
		return new Gson().toJson(this);
	}
}
