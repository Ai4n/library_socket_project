package SocketExchange;

import java.util.ArrayList;

import com.google.gson.Gson;
import Main.Book;
import Main.ServerMessage;

public class SearchBooksResponse extends SocketExchange {

	ArrayList<Book> foundedBooksList;

	public SearchBooksResponse(ArrayList<Book> foundedBooksList) {
		super(ServerMessage.SEARCH_BOOKS);
		this.foundedBooksList = foundedBooksList;
	}

	public String json() {
		return new Gson().toJson(this);
	}
}
