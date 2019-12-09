package socketExchange;

import main.Book;
import main.ServerMessage;

public class AddBookRequest extends SocketExchange{
	
	private Book book;
	
	public AddBookRequest(Book book) {
		super(ServerMessage.ADD_BOOK);
		this.book = book;
	}

	public Book getBook() {
		return book;
	}
}
