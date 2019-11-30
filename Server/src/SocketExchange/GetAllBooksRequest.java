package SocketExchange;

import java.util.ArrayList;
import Main.Book;
import Main.ServerMessage;

public class GetAllBooksRequest extends SocketExchange {

	private ArrayList<Book> allBooks;

	public GetAllBooksRequest(ArrayList<Book> allBooks) {
		super(ServerMessage.GET_ALL_BOOKS);
		this.allBooks = allBooks;
	}

	public ArrayList<Book> getAllBooks() {
		return allBooks;
	}
}
