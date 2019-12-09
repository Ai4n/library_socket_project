package socketExchange;

import java.util.ArrayList;

import com.google.gson.Gson;

import main.Book;
import main.ServerMessage;

public class UpdateBookRequest extends SocketExchange {

	private Book book;
	public UpdateBookRequest(Book book) {
		super(ServerMessage.UPDATE_BOOK);
		this.book = book;
	}

	public Book getBook() {
		return book;
	}

	public String json() {
		return new Gson().toJson(this);
	}
}
