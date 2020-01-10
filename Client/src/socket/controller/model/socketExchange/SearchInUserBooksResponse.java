package socket.controller.model.socketExchange;
import java.util.ArrayList;
import entities.book.Book;
import socket.controller.model.ServerMessage;
import socket.controller.model.SocketExchange;

public class SearchInUserBooksResponse extends SocketExchange {

	private ArrayList<Book> booksList;

	public SearchInUserBooksResponse(ArrayList<Book> booksList) {
		super(ServerMessage.SEARCH_BOOKS);
		this.booksList = booksList;
	}

	public ArrayList<Book> getBooksList() {
		return booksList;
	}
}
