package SocketExchange;

import java.util.ArrayList;

import com.google.gson.Gson;
import Main.Book;
import Main.ServerMessage;

public class GetBooksResponse extends SocketExchange {

	ArrayList<Book> foundedBooksList;

	public GetBooksResponse(ArrayList<Book> foundedBooksList) {
		super(ServerMessage.SEARCH_BOOKS);
		this.foundedBooksList = foundedBooksList;
	}

	public ArrayList<Book> getFoundedBooksList() {
		return foundedBooksList;
	}

	public String json() {
		return new Gson().toJson(this);
	}
}
