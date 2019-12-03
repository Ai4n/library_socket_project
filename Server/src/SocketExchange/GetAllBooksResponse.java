package SocketExchange;

import java.util.ArrayList;

import com.google.gson.Gson;

import Main.Book;
import Main.ServerMessage;

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
