package socketExchange;
import java.util.ArrayList;
import main.Book;
import main.ServerMessage;

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
