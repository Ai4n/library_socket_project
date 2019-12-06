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
			case GET_ALL_USERS:
				getAllUsersList();
				break;
			case SEARCH_BOOK:
				GetBooksRequest getBookRequest = gson.fromJson(jsonMessage, GetBooksRequest.class);
				searchBookInLibrary(getBookRequest.getTextForSearch());
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
				AddUserRequest addUserRequest = gson.fromJson(jsonMessage, AddUserRequest.class);
				addUser(addUserRequest.getNewLogin(), addUserRequest.getCryptPassword());
				break;
			case ADD_USER_BOOK:
				AddBookToUsersListRequest addBookToUsersListRequest = gson.fromJson(jsonMessage,
						AddBookToUsersListRequest.class);
				addBookToUsersList(addBookToUsersListRequest.getBookId(), addBookToUsersListRequest.getUserId());
				break;
			case SHOW_BOOKS:
				GetAllUserBooksRequest getAllUserBooksRequest = gson.fromJson(jsonMessage,
						GetAllUserBooksRequest.class);
				getAllUsersBooks(getAllUserBooksRequest.getUserId());
				break;
			case SHOW_AUTHORS_BOOKS:
				GetAuthorBooksListRequest getAuthorBooksListRequest = gson.fromJson(jsonMessage,
						GetAuthorBooksListRequest.class);
				getAllAuthorsBooks(getAuthorBooksListRequest.getAuthorId());
				break;
			case SEARCH_BOOKS:
				GetUserBooksRequest getUserBooksRequest = gson.fromJson(jsonMessage, GetUserBooksRequest.class);
				searchInUsersBookList(getUserBooksRequest.getUserId(), getUserBooksRequest.getText());
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
			case DELETE_USER:
				DeleteUserRequest deleteUserRequest = gson.fromJson(jsonMessage, DeleteUserRequest.class);
				deleteUser(deleteUserRequest.getUserId());
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
		GetAllBooksResponse getAllBooksResponse = new GetAllBooksResponse(bookRepo.getAllBooksList());
		socketController.write(getAllBooksResponse.json());
		;
	}

	private void getAllAuthorsList() {
		GetAllAuthorsResponse getAllAuthorsResponse = new GetAllAuthorsResponse(bookRepo.getAllAuthorsList());
		socketController.write(getAllAuthorsResponse.json());
	}

	private void getAllUsersList() {
		GetAllUsersListResponse getAllUsersListResponse = new GetAllUsersListResponse(userRepo.getAllUsersList());
		socketController.write(getAllUsersListResponse.json());
	}

	private void searchBookInLibrary(String string) {
		ArrayList<Book> foundedBooksList = bookRepo.searchBook(string);
		GetBooksResponse getBooksResponse = new GetBooksResponse(foundedBooksList);
		socketController.write(getBooksResponse.json());
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

	private void getAllUsersBooks(int userId) {
		ArrayList<Book> foundBooksList = bookRepo.getAllUsersBooks(userId);
		GetAllUserBooksResponse getAllUserBooksResponse = new GetAllUserBooksResponse(foundBooksList);
		socketController.write(getAllUserBooksResponse.json());
	}

	private void addBookToUsersList(int bookId, int userId) {
		bookRepo.addBookToUsersList(bookId, userId);
	}

	private void searchInUsersBookList(int userId, String text) {
		ArrayList<Book> booksList = bookRepo.searchBookInUsersList(userId, text);
		GetUserBooksResponse getUserBooksResponse = new GetUserBooksResponse(booksList);
		socketController.write(getUserBooksResponse.json());
	}

	private void checkUser(String login, String password) {
		User user = userRepo.checkUser(login, password);
		boolean isCredentialsCorrect = (user != null) ? true : false;
		UserCheckResponse userCheckResponse = new UserCheckResponse(isCredentialsCorrect, user);
		socketController.write(userCheckResponse.json());
	}

	private void checkLogin(String newLogin) {
		Boolean isLoginExist = userRepo.isLoginExist(newLogin);
		IsLoginExistResponse isLoginExistResponse = new IsLoginExistResponse(isLoginExist);
		socketController.write(isLoginExistResponse.json());
	}

	private void addUser(String login, String passWord) {
		User user = new User(login, passWord, UserRole.USER);
		userRepo.addUser(user);
	}

	private void deleteUser(int userId) {
		userRepo.deleteUser(userId);
	}

	private void deleteAuthor(int authorId) {
		bookRepo.deleteAuthor(authorId);
	}

	private void getAllAuthorsBooks(int authorId) {
		ArrayList<Book> authorsBooksList;
		authorsBooksList = bookRepo.getAllAuthorBooks(authorId);
		GetAuthorBooksListResponse getAuthorBooksListResponse = new GetAuthorBooksListResponse(authorsBooksList);
		socketController.write(getAuthorBooksListResponse.json());
	}
}
