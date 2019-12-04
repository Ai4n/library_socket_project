package Main;

import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

import SocketExchange.DeleteBookFromUsersList;
import SocketExchange.DeleteBookRequest;
import SocketExchange.GetAllBooksRequest;
import SocketExchange.GetAllBooksResponse;
import SocketExchange.GetAllUsersBooksRequest;
import SocketExchange.GetAllUsersBooksResponse;

public class UserController {

	private Scanner scan = new Scanner(System.in);
	private User user;
	private SocketController socketController;
	Gson gson = new Gson();

	public UserController(User user, SocketController socketController) {
		this.user = user;
		this.socketController = socketController;
	}

	public void userMenu() {
		loop: while (true) {
			System.out.println("\nEnter options:\n" + "1.Add book to Library\n" + "2.Show all books in Library\n" + "3.Search book in Library\n"
					+ "4.Delete book from Library\n" + "5.Quit\n");
			int number = scan.nextInt();
			switch (number) {
			case 1:
				addBookToUsersList();
				break;
			case 2:
				showAllUsersBooks();
				break;
			case 3:
				searchBookInUsersList();
				break;
			case 4:
				deleteBookFromUsersList();
				break;
			case 5:
				return;
			default:
				System.out.println("enter 1-5");
				break loop;
			}
		}
	}

	private void showAllUsersBooks() {
		ArrayList<Book> listBooks = getAllUserBooks();
		if (listBooks.equals(null)) {
			return;
		}
		printList(listBooks);
	}
// to do
	private void addBookToUsersList() {
		ArrayList<Book> listBooks = getAllLibraryBooks();
		if (listBooks.equals(null)) {
			return;
		} else if (!(user.equals(null))) {
			printList(listBooks);
			System.out.println("Choose number of book: \n");
			int number = scan.nextInt();
			int bookId = listBooks.get(number - 1).getBookId();
			int userId = user.getIduser();
			socketController.writeInt(ServerMessage.ADD_USER_BOOK, bookId, userId);
		} else
			return;
	}

	private ArrayList<Book> getAllLibraryBooks() {
		GetAllBooksRequest getAllBooksRequest = new GetAllBooksRequest();
		socketController.write(getAllBooksRequest.json());
		String jsonMessage = socketController.read();
		GetAllBooksResponse getAllBooksResponse = gson.fromJson(jsonMessage, GetAllBooksResponse.class);
		return getAllBooksResponse.getAllBooksList();
	}

	private ArrayList<Book> getAllUserBooks() {
		int userId = user.getIduser();
		GetAllUsersBooksRequest getAllUsersBooksRequest = new GetAllUsersBooksRequest(userId);
		socketController.write(getAllUsersBooksRequest.json());
		String jsonMessage = socketController.read();
		GetAllUsersBooksResponse getAllUsersBooksResponse = gson.fromJson(jsonMessage, GetAllUsersBooksResponse.class);
		return getAllUsersBooksResponse.getAllBooksList();
	}
// to do
	private void searchBookInUsersList() {
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

	private void deleteBookFromUsersList() {
		ArrayList<Book> listBooks = getAllUserBooks();
		if (listBooks.equals(null)) {
			return;
		}
		printList(listBooks);
		System.out.println("Choose number of book: \n");
		int number = scan.nextInt();
		int bookId = listBooks.get(number - 1).getBookId();
		int userId = user.getIduser();
		DeleteBookFromUsersList deleteBookFromUsersList = new DeleteBookFromUsersList(bookId, userId);
		socketController.write(deleteBookFromUsersList.json());
	}
	

	private <T> void printList(ArrayList<T> anyList) {
		if (anyList != null) {
			for (int i = 0; i < anyList.size(); i++) {
				T element = (T) anyList.get(i);
				System.out.println((i + 1) + ". " + element);
			}
		}
	}

}
