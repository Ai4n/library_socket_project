package socket.model.socketExchange;
import java.util.ArrayList;
import entities.book.Book;
import socket.model.serverMessage.ServerMessage;

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
