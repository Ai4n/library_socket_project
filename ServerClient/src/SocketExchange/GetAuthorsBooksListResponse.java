package SocketExchange;

import java.util.ArrayList;

import Main.Book;
import Main.ServerMessage;

public class GetAuthorsBooksListResponse extends SocketExchange {

	private ArrayList<Book> authorsBooksList;

	public GetAuthorsBooksListResponse(ArrayList<Book> authorsBooksList) {
		super(ServerMessage.SHOW_AUTHORS_BOOKS);
		this.authorsBooksList = authorsBooksList;
	}

	public ArrayList<Book> getAuthorsBooksList() {
		return authorsBooksList;
	}

}
