package controllers;

import java.util.ArrayList;
import java.util.Scanner;
import com.ai4n.entities.book.Book;
import com.ai4n.socketExchange.controller.SocketController;
import com.ai4n.entities.user.User;
import com.ai4n.socketExchange.model.SocketExchange;
import com.ai4n.socketExchange.model.socketExchange.*;

public class UserController {

	private Scanner scan = new Scanner(System.in);
	private User user;
	private SocketController socketController;
	SocketExchange request = socketController.readMessage();

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
				quitAndCloseSession();
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
		if (listBooks == null) {
			return;
		} else if (!(user == null)) {
			printList(listBooks);
			System.out.println("Choose number of book: \n");
			int number = scan.nextInt();
			if (0 == number || number > listBooks.size()) {
				return;
			}
			int bookId = listBooks.get(number - 1).getBookId();
			int userId = user.getIdUser();
			AddBookToUsersBookListRequest addBookToUsersBookListRequest = new AddBookToUsersBookListRequest(bookId, userId);
			socketController.write(addBookToUsersBookListRequest);
		} else
			return;
	}

	private ArrayList<Book> getAllLibraryBooks() {
		GetAllBooksRequest getAllBooksRequest = new GetAllBooksRequest();
		socketController.write(getAllBooksRequest);
		GetAllBooksResponse getAllBooksResponse = socketController.convertMessage(request.json, new GetAllBooksResponse());
		return getAllBooksResponse.getAllBooksList();
	}

	private ArrayList<Book> getAllUsersBooks() {
		int userId = user.getIdUser();
		GetAllUserBooksRequest getAllUserBooksRequest = new GetAllUserBooksRequest(userId);
		socketController.write(getAllUserBooksRequest);
		GetAllUserBooksResponse getAllUserBooksResponse = socketController.convertMessage(request.json, new GetAllUserBooksResponse());
		return getAllUserBooksResponse.getAllBooksList();
	}

	private void searchBookInUsersList() {
		int userId = user.getIdUser();
		System.out.println("Please enter search text: \n");
		String text = scan.next();
		SearchInUserBooksRequest searchInUserBooksRequest = new SearchInUserBooksRequest(userId, text);
		socketController.write(searchInUserBooksRequest);
		SearchInUserBooksResponse searchInUserBooksResponse = socketController.convertMessage(request.json, new SearchInUserBooksResponse());
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
		DeleteBookFromUsersBookList deleteBookFromUsersBookList = new DeleteBookFromUsersBookList(bookId, userId);
		socketController.write(deleteBookFromUsersBookList);
	}

	private <T> void printList(ArrayList<T> anyList) {
		if (anyList != null) {
			for (int i = 0; i < anyList.size(); i++) {
				T element = anyList.get(i);
				System.out.println((i + 1) + ". " + element);
			}
		}
	}

	private void quitAndCloseSession() {
		QuitAndCloseSessionRequest quitAndCloseSessionRequest = new QuitAndCloseSessionRequest();
		socketController.write(quitAndCloseSessionRequest);
	}
}
