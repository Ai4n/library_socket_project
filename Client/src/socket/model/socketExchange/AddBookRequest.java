package socket.model.socketExchange;
import entities.book.Book;
import socket.model.ServerMessage;
import socket.model.SocketExchange;

public class AddBookRequest extends SocketExchange {
	
	private Book book;
	
	public AddBookRequest(Book book) {
		super(ServerMessage.ADD_BOOK);
		this.book = book;
	}

	public Book getBook() {
		return book;
	}
}
