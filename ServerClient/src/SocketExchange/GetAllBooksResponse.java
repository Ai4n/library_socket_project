package SocketExchange;

import java.util.ArrayList;

import Main.Book;
import Main.ServerMessage;

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
