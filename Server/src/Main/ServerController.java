package Main;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.*;

import Repositories.BookRepo;
import Repositories.UserRepo;

public class ServerController {

	private SocketController socketController;

	public static Scanner scan = new Scanner(System.in);

	BookRepo bookRepo = new BookRepo();
	UserRepo userRepo = new UserRepo();

	public ServerController(Socket socket) throws IOException {
		socketController = new SocketController(socket);
		handleMessages();
	}

	public void handleMessages() {

		while (true) {

			String messageString = socketController.readUtf();
			ServerMessage message = ServerMessage.create(messageString);
			System.out.println("message: " + message);
			if (message == null)
				continue;
			
			switch (message) {
			case ADD_AUTHOR:
				addAuthor();
				break;
			case ADD_BOOK:
				addBook();
				break;
			case GET_ALL_BOOKS:
				showAllBooks();
				break;
			case GET_ALL_AUTHORS:
				getAllAuthorsList();
				break;
			case SEARCH_BOOK:
				searchBook();
				break;
			case USER_CHECK:
				checkUser();
				break;
			case LOGIN_CHECK:
				checkLogin();
				break;
			case ADD_USER:
				addUser();
				break;
			case ADD_USER_BOOK:
				addUserBook();
				break;
			case SHOW_BOOKS:
				showBooks();
				break;
			case SHOW_AUTHORS_BOOKS:
				showAllAuthorsBooks();
				break;
			case SEARCH_BOOKS:
				searchUsBooks();
				break;
			case DELETE_USER_BOOK:
				deleteUserBook();
				break;
			case DELETE_BOOK:
				deleteBook();
				break;
			case DELETE_AUTHOR:
			    deleteAuthor();
			    break;
			case UPDATE_BOOK:
				updateBook();
				break;
			default:
				return;
			}

		}
	}

	private void getAllAuthorsList() {
			ArrayList<Author> allAuthorsList = bookRepo.getAllAuthorsList();
			socketController.writeObject(allAuthorsList);	
	}

	private void updateBook() {
		Book book = socketController.read();
		if (book.equals(null))
			return;
		bookRepo.deleteBook(book.getBookId());
		bookRepo.addBook(book);

	}

	private void deleteBook() {
		String bookIdStr = socketController.readUtf();
		int bookId = Integer.valueOf(bookIdStr);
		bookRepo.deleteBook(bookId);
		bookRepo.deleteBooksData(bookId);

	}

	private void deleteUserBook() {
		String bookIdStr = socketController.readUtf();
		String userIdStr = socketController.readUtf();
		int bookIdInt = Integer.valueOf(bookIdStr);
		int userIdInt = Integer.valueOf(userIdStr);
		bookRepo.deleteUserBook(userIdInt, bookIdInt);
	}

	private void showBooks() {
		ArrayList<Book> foundBooks;
		String userId = socketController.readUtf();
		foundBooks = bookRepo.showBooks(userId);
		socketController.writeObject(foundBooks);
	}

	private void addUserBook() {
		String userId = socketController.readUtf();
		String bookId = socketController.readUtf();
		bookRepo.addUserBook(Integer.valueOf(userId), Integer.valueOf(bookId));
	}

	private void searchBook() {
		ArrayList<Book> foundBooks;
		String text;
		try {
			text = socketController.readUtf();
			foundBooks = bookRepo.searchBook(text);
			socketController.writeObject(foundBooks);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void searchUsBooks() {
		ArrayList<Book> foundBooks;
		String idUser;
		String text;
		try {
			idUser = socketController.readUtf();
			int idUserInt = Integer.valueOf(idUser);
			text = socketController.readUtf();
			foundBooks = bookRepo.searchUsBooks(idUserInt, text);
			socketController.writeObject(foundBooks);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addBook() {
		Book book;
		book = socketController.read();
		if (book.equals(null))
			return;
		bookRepo.addBook(book);
	}

	private void showAllBooks() {
		ArrayList<Book> allBooks;
		try {
			allBooks = bookRepo.showAllBooksUsingJoin();
			socketController.writeObject(allBooks);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void checkUser() {
		String login, password;
		login = socketController.readUtf();
		password = socketController.readUtf();
		User foundUser = userRepo.checkUser(login, password);
		if (foundUser.equals(null)) {
			socketController.writeMessage(ServerMessage.USER_NOT_EXIST);
		} else {
			socketController.writeObject(ServerMessage.USER_EXIST, foundUser);
		}

	}

	private void checkLogin() {
		String login = socketController.readUtf();
		boolean result = userRepo.checkLogin(login);
		socketController.write(result ? ServerMessage.USER_NOT_EXIST : ServerMessage.USER_EXIST);
	}

	private void addUser() {
		String login, password;
		login = socketController.readUtf();
		password = socketController.readUtf();
		User user = new User(login, password, UserRole.USER);
		userRepo.addUser(user);
	}
	
	private void addAuthor(){
		Author author = socketController.read();
		bookRepo.addAuthor(author);
	}
	
	private void deleteAuthor() {
		String authorIdStr = socketController.readUtf();
		int authorId = Integer.valueOf(authorIdStr);
		bookRepo.deleteAuthor(authorId);
	}
	
	private void showAllAuthorsBooks() {
		String authorIdStr = socketController.readUtf();
		if(authorIdStr.equals(null)) return;
		int authorId = Integer.valueOf(authorIdStr);
		ArrayList<Book> allAuthorBooks = bookRepo.showAllAuthorBooks(authorId);
		socketController.writeObject(allAuthorBooks);
	}
}
