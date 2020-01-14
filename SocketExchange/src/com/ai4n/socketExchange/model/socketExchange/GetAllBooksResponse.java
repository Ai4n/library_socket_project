package com.ai4n.socketExchange.model.socketExchange;

import com.ai4n.entities.book.Book;
import com.ai4n.socketExchange.model.ServerMessage;
import com.ai4n.socketExchange.model.SocketExchange;

import java.util.ArrayList;

public class GetAllBooksResponse extends SocketExchange {

	private ArrayList<Book> allBooksList;
	
	public ArrayList<Book> getAllBooksList() {
		return allBooksList;
	}

	public GetAllBooksResponse (ArrayList<Book> allBooksList) {
		super(ServerMessage.GET_ALL_BOOKS);
		this.allBooksList = allBooksList;
	}
}
