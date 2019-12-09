package socketExchange;

import java.util.ArrayList;

import com.google.gson.Gson;

import main.Book;
import main.ServerMessage;

public class GetAllBooksResponse extends SocketExchange {

	private ArrayList<Book> allBooksList;
	
	public ArrayList<Book> getAllBooksList() {
		return allBooksList;
	}

	public GetAllBooksResponse (ArrayList<Book> allBooksList) {
		super(ServerMessage.GET_ALL_BOOKS);
		this.allBooksList = allBooksList;
	}
	
	public String json() {
		return new Gson().toJson(this);
	}
	
}
