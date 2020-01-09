package socket.controller.controller.socketExchange;
import java.util.ArrayList;
import entities.book.Book;
import model.ServerMessage;

public class GetAllBooksResponse extends SocketExchange {

	private ArrayList<Book> allBooksList;
	
	public ArrayList<Book> getAllBooksList() {
		return allBooksList;
	}

	public GetAllBooksResponse (ArrayList<Book> allBooksList) {
		super(ServerMessage.GET_ALL_BOOKS);
		this.allBooksList = allBooksList;
	}
}
