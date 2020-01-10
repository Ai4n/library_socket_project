package socket.controller.model.socketExchange;
import java.util.ArrayList;
import entities.book.Book;
import socket.controller.model.ServerMessage;
import socket.controller.model.SocketExchange;

public class GetAllBooksResponse extends SocketExchange {

	private ArrayList<Book> allBooksList;
	
	public GetAllBooksResponse (ArrayList<Book> allBooksList) {
		super(ServerMessage.GET_ALL_BOOKS);
		this.allBooksList = allBooksList;
	}
	
	public ArrayList<Book> getAllBooksList() {
		return allBooksList;
	}
}
