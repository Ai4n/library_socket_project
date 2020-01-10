package socket.controller.model.socketExchange;
import java.util.ArrayList;
import entities.book.Book;
import socket.controller.model.ServerMessage;
import socket.controller.model.SocketExchange;

public class GetAuthorBooksListResponse extends SocketExchange {

	private ArrayList<Book> authorsBooksList;

	public GetAuthorBooksListResponse(ArrayList<Book> authorsBooksList) {
		super(ServerMessage.SHOW_AUTHORS_BOOKS);
		this.authorsBooksList = authorsBooksList;
	}

	public ArrayList<Book> getAuthorsBooksList() {
		return authorsBooksList;
	}

}
