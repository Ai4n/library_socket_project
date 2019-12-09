package socketExchange;

import java.util.ArrayList;

import com.google.gson.Gson;

import main.Book;
import main.ServerMessage;

public class GetAuthorBooksListResponse extends SocketExchange {

	private ArrayList<Book> authorsBooksList;
	public GetAuthorBooksListResponse(ArrayList<Book> authorsBooksList) {
		super(ServerMessage.SHOW_AUTHORS_BOOKS);
		this.authorsBooksList = authorsBooksList;
	}

	public ArrayList<Book> getAuthorsBooksList() {
		return authorsBooksList;
	}

	public String json() {
		return new Gson().toJson(this);
	}

}
