package socket.controller.controller.socketExchange;
import java.util.ArrayList;
import entities.book.Book;
import model.ServerMessage;

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
