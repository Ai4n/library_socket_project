package socketExchange;

import java.util.ArrayList;

import com.google.gson.Gson;

import main.Book;
import main.ServerMessage;

public class SearchBookResponse extends SocketExchange {

	private ArrayList<Book> foundedBooksList;

	public SearchBookResponse(ArrayList<Book> foundedBooksList) {
		super(ServerMessage.SEARCH_BOOK);
		this.foundedBooksList = foundedBooksList;
	}

	public String json() {
		return new Gson().toJson(this);
	}
}
