package socket.controller.model.socketExchange;

import java.util.ArrayList;
import entities.book.Book;
import socket.controller.model.ServerMessage;
import socket.controller.model.SocketExchange;

public class SearchBookResponse extends SocketExchange {

	private ArrayList<Book> foundedBooksList;

	public SearchBookResponse(ArrayList<Book> foundedBooksList) {
		super(ServerMessage.SEARCH_BOOK);
		this.foundedBooksList = foundedBooksList;
	}
	
	public ArrayList<Book> getFoundedBooksList() {
		return foundedBooksList;
	}
}
