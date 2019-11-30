package SocketExchange;

import java.util.ArrayList;
import Main.Book;
import Main.ServerMessage;

public class SearchBookRequest extends SocketExchange{

	int bookId;
	
		public SearchBookRequest(int bookId){
		super(ServerMessage.SEARCH_BOOK);
		this.bookId = bookId;
	}
}
