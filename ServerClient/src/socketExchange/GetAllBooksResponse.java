package socketExchange;

import java.util.ArrayList;

import main.Book;
import main.ServerMessage;

public class GetAllBooksResponse extends SocketExchange {

	private ArrayList<Book> allBooksList;
	
	public GetAllBooksResponse (ArrayList<Book> allBooksList) {
		super(ServerMessage.GET_ALL_BOOKS);
		this.allBooksList = allBooksList;
	}
	
	public ArrayList<Book> getAllBooksList() {
		return allBooksList;
	}

	
}
