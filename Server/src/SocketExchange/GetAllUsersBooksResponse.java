package SocketExchange;
import java.util.ArrayList;
import Main.Book;
import Main.ServerMessage;

public class GetAllUsersBooksResponse extends SocketExchange {
	
		private ArrayList<Book> allBooksList;
		
		public GetAllUsersBooksResponse (ArrayList<Book> allBooksList) {
			super(ServerMessage.SHOW_BOOKS);
			this.allBooksList = allBooksList;
		}
		
		public ArrayList<Book> getAllBooksList() {
			return allBooksList;
		}

		
}
