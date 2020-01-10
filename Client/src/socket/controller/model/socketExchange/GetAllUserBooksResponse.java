package socket.controller.model.socketExchange;

import java.util.ArrayList;
import entities.book.Book;
import socket.controller.model.ServerMessage;
import socket.controller.model.SocketExchange;

public class GetAllUserBooksResponse extends SocketExchange {

	private ArrayList<Book> allBooksList;

	public GetAllUserBooksResponse(ArrayList<Book> allBooksList) {
		super(ServerMessage.SHOW_BOOKS);
		this.allBooksList = allBooksList;
	}

	public ArrayList<Book> getAllBooksList() {
		return allBooksList;
	}
}
