package Main;

import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.Gson;
import SocketExchange.*;

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
		while (true) {
			System.out.println("\nEnter options:\n" + "1.Add book\n" + "2.Show all books\n" + "3.Search book\n"
					+ "4.Delete book\n" + "5.Quit\n");
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
				break;
			}
		}
	}

	private void showAllUsersBooks() {
		printList(getAllUsersBooks());
	}

	private void addBookToUsersList() {
		ArrayList<Book> listBooks = getAllLibraryBooks();
		if (listBooks.equals(null)) {
			return;
		} else if (!(user.equals(null))) {
			printList(listBooks);
			System.out.println("Choose number of book: \n");
			int number = scan.nextInt();
			if (0 == number || number > listBooks.size()) {
				return;
			}
			int bookId = listBooks.get(number - 1).getBookId();
			int userId = user.getIdUser();
			AddBookToUsersListRequest addBookToUsersListRequest = new AddBookToUsersListRequest(bookId, userId);
			socketController.write(addBookToUsersListRequest.json());
		} else
			return;
	}

	private ArrayList<Book> getAllLibraryBooks() {
		GetAllBooksRequest getAllBooksRequest = new GetAllBooksRequest();
		socketController.write(getAllBooksRequest.json());
		String jsonMessage = socketController.readUtf();
		GetAllBooksResponse getAllBooksResponse = gson.fromJson(jsonMessage, GetAllBooksResponse.class);
		return getAllBooksResponse.getAllBooksList();
	}

	private ArrayList<Book> getAllUsersBooks() {
		int userId = user.getIdUser();
		GetAllUserBooksRequest getAllUserBooksRequest = new GetAllUserBooksRequest(userId);
		socketController.write(getAllUserBooksRequest.json());
		String jsonMessage = socketController.readUtf();
		GetAllUserBooksResponse getAllUserBooksResponse = gson.fromJson(jsonMessage, GetAllUserBooksResponse.class);
		return getAllUserBooksResponse.getAllBooksList();
	}

	private void searchBookInUsersList() {
		int userId = user.getIdUser();
		System.out.println("Please enter search text: \n");
		String text = scan.next();
		SearchInUserBooksRequest searchInUserBooksRequest = new SearchInUserBooksRequest(userId, text);
		socketController.write(searchInUserBooksRequest.json());
		String jsonMessage = socketController.readUtf();
		SearchInUserBooksResponse searchInUserBooksResponse = gson.fromJson(jsonMessage, SearchInUserBooksResponse.class);
		printList(searchInUserBooksResponse.getBooksList());
	}

	private void deleteBookFromUsersList() {
		ArrayList<Book> listBooks = getAllUsersBooks();
		printList(listBooks);
		System.out.println("Choose number of book: \n");
		int number = scan.nextInt();
		if (number == 0 || number > listBooks.size()) {
			return;
		}
		int bookId = listBooks.get(number - 1).getBookId();
		int userId = user.getIdUser();
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
