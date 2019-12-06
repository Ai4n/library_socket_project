package SocketExchange;
import java.util.ArrayList;
import Main.Book;
import Main.ServerMessage;

public class GetAllUserBooksResponse extends SocketExchange {
	
		private ArrayList<Book> allBooksList;
		
		public GetAllUserBooksResponse (ArrayList<Book> allBooksList) {
			super(ServerMessage.SHOW_BOOKS);
			this.allBooksList = allBooksList;
		}
		
		public ArrayList<Book> getAllBooksList() {
			return allBooksList;
		}

		
}
