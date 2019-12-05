package Main;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.*;

import Repositories.BookRepo;
import Repositories.UserRepo;
import SocketExchange.*;

public class ServerController {

	private SocketController socketController;

	public static Scanner scan = new Scanner(System.in);

	BookRepo bookRepo = new BookRepo();
	UserRepo userRepo = new UserRepo();
	Gson gson = new Gson();

	public ServerController(Socket socket) throws IOException {
		socketController = new SocketController(socket);
		handleMessages();
	}

	public void handleMessages() {

		while (true) {
			String jsonMessage = socketController.readUtf();
			SocketExchange request = gson.fromJson(jsonMessage, SocketExchange.class);
			System.out.println("message: " + request.message);
			if (request == null)
				continue;
			switch (request.message) {
			case ADD_AUTHOR:
				AddAuthorRequest addAuthorRequest = gson.fromJson(jsonMessage, AddAuthorRequest.class);
				addAuthor(addAuthorRequest.getAuthor());
				break;
			case ADD_BOOK:
				AddBookRequest addBookRequest = gson.fromJson(jsonMessage, AddBookRequest.class);
				addBook(addBookRequest.getBook());
				break;
			case GET_ALL_BOOKS:
				getAllBooksList();
				break;
			case GET_ALL_AUTHORS:
				getAllAuthorsList();
				break;
			case SEARCH_BOOK:
				SearchBooksRequest searchBookRequest = gson.fromJson(jsonMessage, SearchBooksRequest.class);
				searchBookInLibrary(searchBookRequest.getTextForSearch());
				break;
			case USER_CHECK:
				UserCheckRequest userCheckRequest = gson.fromJson(jsonMessage, UserCheckRequest.class);
				checkUser(userCheckRequest.getLogin(), userCheckRequest.getPassword());
				break;
			case LOGIN_CHECK:
				LoginCheckRequest loginCheckRequest = gson.fromJson(jsonMessage, LoginCheckRequest.class);
				checkLogin(loginCheckRequest.getNewLogin());
				break;
			case ADD_USER:
				addUser();
				break;
			case ADD_USER_BOOK:
				AddBookToUsersListRequest addBookToUsersListRequest = gson.fromJson(jsonMessage,
						AddBookToUsersListRequest.class);
				addBookToUsersList(addBookToUsersListRequest.getBookId(), addBookToUsersListRequest.getUserId());
				break;
			case SHOW_BOOKS:
				GetAllUsersBooksRequest getAllUsersBooksRequest = gson.fromJson(jsonMessage, GetAllUsersBooksRequest.class);
				showAllUsersBooks(getAllUsersBooksRequest.getUserId());
				break;
			case SHOW_AUTHORS_BOOKS:
				GetAuthorsBooksListRequest getAuthorsBooksListRequest = gson.fromJson(jsonMessage,
						GetAuthorsBooksListRequest.class);
				showAllAuthorsBooks(getAuthorsBooksListRequest.getAuthorId());
				break;
			case SEARCH_BOOKS:
				SearchUsersBookRequest searchUsersBookRequest = gson.fromJson(jsonMessage,
						SearchUsersBookRequest.class);
				searchInUsersBookList(searchUsersBookRequest.getUserId(), searchUsersBookRequest.getText());
				break;
			case DELETE_USER_BOOK:
				DeleteBookFromUsersList deleteBookFromUsersList = gson.fromJson(jsonMessage,
						DeleteBookFromUsersList.class);
				deleteUsersBookInList(deleteBookFromUsersList.getBookId(), deleteBookFromUsersList.getUserId());
				break;
			case DELETE_BOOK:
				DeleteBookRequest deleteBookRequest = gson.fromJson(jsonMessage, DeleteBookRequest.class);
				deleteBook(deleteBookRequest.getBookId());
				break;
			case DELETE_AUTHOR:
				DeleteAuthorRequest deleteAuthorRequest = gson.fromJson(jsonMessage, DeleteAuthorRequest.class);
				deleteAuthor(deleteAuthorRequest.getAuthorId());
				break;
			case UPDATE_BOOK:
				UpdateBookRequest updateBookRequest = gson.fromJson(jsonMessage, UpdateBookRequest.class);
				updateBook(updateBookRequest.getBook());
				break;
			default:
				return;
			}

		}
	}

	private void addAuthor(Author author) {
		bookRepo.addAuthor(author);
	}

	private void addBook(Book book) {
		bookRepo.addBook(book);
	}

	private void getAllBooksList() {
		GetAllBooksResponse getAllAuthorsResponse = new GetAllBooksResponse(bookRepo.getAllBooksListUsingJoin());
		socketController.write(getAllAuthorsResponse.json());
		;
	}

	private void getAllAuthorsList() {
		GetAllAuthorsResponse getAllAuthorsResponse = new GetAllAuthorsResponse(bookRepo.getAllAuthorsList());
		socketController.write(getAllAuthorsResponse.json());
	}

	private void searchBookInLibrary(String string) {
		ArrayList<Book> foundedBooksList = bookRepo.searchBook(string);
		SearchBooksResponse searchBooksResponse = new SearchBooksResponse(foundedBooksList);
		socketController.write(searchBooksResponse.json());
	}

	private void updateBook(Book book) {
		bookRepo.updateBook(book);
	}

	private void deleteBook(int bookId) {
		bookRepo.deleteBook(bookId);
		bookRepo.deleteBooksData(bookId);

	}

	private void deleteUsersBookInList(int bookId, int userId) {
		bookRepo.deleteUsersBook(userId, bookId);
	}

	private void showAllUsersBooks(int userId) {
		ArrayList<Book> foundBooksList = bookRepo.showAllUsersBooks(userId);
		GetAllUsersBooksResponse getAllUsersBooksResponse = new GetAllUsersBooksResponse(foundBooksList);
		socketController.write(getAllUsersBooksResponse.json());
	}

	private void addBookToUsersList(int bookId, int userId) {
		bookRepo.addBookToUsersList(bookId, userId);
	}

	private void searchInUsersBookList(int userId, String text) {
		ArrayList<Book> booksList = bookRepo.searchBookInUsersList(userId, text);
		SearchUsersBookResponse searchUsersBookResponse = new SearchUsersBookResponse(booksList);
		socketController.write(searchUsersBookResponse.json());
	}

	private void checkUser(String login, String password) {
		User user = userRepo.checkUser(login, password);
			if(user.equals(null)) {
				UserNotExist userNotExist = new UserNotExist(user);
				socketController.write(userNotExist.json());
				}
			UserCheckResponse userCheckResponse = new UserCheckResponse(user);
			socketController.write(userCheckResponse.json());		
	}

	private void checkLogin(String newLogin) {
		Boolean result = userRepo.checkLogin(newLogin);
		socketController.write(result ? ServerMessage.USER_NOT_EXIST : ServerMessage.USER_EXIST);
	}

	private void addUser() {
		String login, password;
		login = socketController.readUtf();
		password = socketController.readUtf();
		User user = new User(login, password, UserRole.USER);
		userRepo.addUser(user);
	}

	private void deleteAuthor(int authorId) {
		bookRepo.deleteAuthor(authorId);
	}

	private void showAllAuthorsBooks(int authorId) {
		ArrayList<Book> authorsBooksList;
		authorsBooksList = bookRepo.showAllAuthorBooks(authorId);
		GetAuthorsBooksListResponse getAuthorsBooksListResponse = new GetAuthorsBooksListResponse(authorsBooksList);
		socketController.write(getAuthorsBooksListResponse.json());
	}
}
