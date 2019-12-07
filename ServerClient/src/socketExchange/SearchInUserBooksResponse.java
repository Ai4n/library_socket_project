package socketExchange;

import java.util.ArrayList;

import com.google.gson.Gson;

import main.Book;
import main.ServerMessage;

public class SearchInUserBooksResponse extends SocketExchange {

	private ArrayList<Book> booksList;

	public SearchInUserBooksResponse(ArrayList<Book> booksList) {
		super(ServerMessage.SEARCH_BOOKS);
		this.booksList = booksList;
	}

	public ArrayList<Book> getBooksList() {
		return booksList;
	}
	
	public String json() {
		return new Gson().toJson(this);
	}
}
