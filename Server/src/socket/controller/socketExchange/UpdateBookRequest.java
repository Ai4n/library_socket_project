package socket.controller.controller.socketExchange;
import entities.book.Book;
import model.ServerMessage;

public class UpdateBookRequest extends SocketExchange {

	private Book book;

	public UpdateBookRequest(Book book) {
		super(ServerMessage.UPDATE_BOOK);
		this.book = book;
	}

	public Book getBook() {
		return book;
	}
}
