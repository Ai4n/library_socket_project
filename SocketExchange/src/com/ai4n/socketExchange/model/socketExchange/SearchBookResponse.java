package com.ai4n.socketExchange.model.socketExchange;

import com.ai4n.entities.book.Book;
import com.ai4n.socketExchange.model.ServerMessage;
import com.ai4n.socketExchange.model.SocketExchange;

import java.util.ArrayList;

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
