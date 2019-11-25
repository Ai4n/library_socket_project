package Main;

import java.util.ArrayList;
import java.util.Scanner;

public class UserController {

	private Scanner scan = new Scanner(System.in);
	private User user;
	private SocketController socketController;

	public UserController(User user, SocketController socketController) {
		this.user = user;
		this.socketController = socketController;
	}

	public void userMenu() {
		loop: while (true) {
			System.out.println("\nEnter options:\n" + "1.Add book\n" + "2.Show all books\n" + "3.Search book\n"
					+ "4.Delete book\n" + "5.Quit\n");
			int number = scan.nextInt();
			switch (number) {
			case 1:
				addToUserBooks();
				break;
			case 2:
				showMyBooks();
				break;
			case 3:
				searchInUserBooks();
				break;
			case 4:
				deleteUserBook();
				break;
			case 5:
				return;
			default:
				System.out.println("enter 1-5");
				break loop;
			}
		}
	}

	private void showMyBooks() {
		ArrayList<Book> listBooks = getUserBooks();
		if (listBooks.equals(null)) {
			return;
		}
		printBooksList(listBooks);
	}

	private void addToUserBooks() {
		ArrayList<Book> listBooks = getAllBooks();
		if (listBooks.equals(null)) {
			return;
		} else if (!(user.equals(null))) {
			printBooksList(listBooks);
			System.out.println("Choose number of book: \n");
			int number = scan.nextInt();
			int bookId = listBooks.get(number - 1).getBookId();
			int userId = user.getIduser();
			socketController.writeInt(ServerMessage.ADD_USER_BOOK, bookId, userId);
		} else
			return;
	}

	private void printBooksList(ArrayList<Book> listBooks) {

		if (listBooks != null) {
			for (int i = 0; i < listBooks.size(); i++) {
				Book book = listBooks.get(i);
				if (book.equals(null)) {
					return;
				} else
					System.out.println((i + 1) + ". " + book);
			}
		}
	}

	private ArrayList<Book> getAllBooks() {
		socketController.writeMessage(ServerMessage.GET_ALL_BOOKS);
		ArrayList<Book> allBooks = socketController.read();
		
		return allBooks;
	}

	private ArrayList<Book> getUserBooks() {
		int userId = user.getIduser();
		String userStr = String.valueOf(userId);
		socketController.write(ServerMessage.SHOW_BOOKS, userStr);
		ArrayList<Book> listBooks = socketController.read();
		return listBooks;
	}

	private void searchInUserBooks() {
		int idUser = user.getIduser();
		String userId = String.valueOf(idUser);
		System.out.println("Please enter search text: \n");
		String text = scan.next();
		socketController.write(ServerMessage.SEARCH_BOOKS, userId, text);
		ArrayList<Book> foundBooks = socketController.read();
		if (foundBooks.equals(null)) {
			return;
		}
		int count = 1;
		for (Book book : foundBooks) {
			System.out.println(count + ". " + book);
			count++;
		}
	}

	private void deleteUserBook() {
		ArrayList<Book> listBooks = getUserBooks();
		if (listBooks.equals(null)) {
			return;
		}
		printBooksList(listBooks);
		System.out.println("Choose number of book: \n");
		int number = scan.nextInt();
		int bookId = listBooks.get(number - 1).getBookId();
		int userId = user.getIduser();
		socketController.writeInt(ServerMessage.DELETE_USER_BOOK, bookId, userId);
	}
}
