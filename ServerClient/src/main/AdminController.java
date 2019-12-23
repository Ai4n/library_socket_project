package main;

import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

import socketExchange.*;

public class AdminController {
	private Scanner scan = new Scanner(System.in);
	private SocketController socketController;
	private String jsonMessage;
	private Gson gson = new Gson();

	public AdminController(SocketController socketController) {
		this.socketController = socketController;
	}

	public void adminMenu() {
		while (true) {
			System.out.println("\nEnter options:\n" + "1.Add book to Library\n" + "2.Show books in Library\n"
					+ "3.Search book\n" + "4.Delete book\n" + "5.Update book\n" + "6.Add new Author\n"
					+ "7.Delete some Author\n" + "8.Show list of Authors\n" + "9.Show list of Author's books\n"
					+ "10.Delete User\n" + "11.Quit\n");
			int number = scan.nextInt();
			switch (number) {
			case 1:
				addBookToLibrary();
				break;
			case 2:
				printList(getAllBooksList());
				break;
			case 3:
				printList(searchBooksInLibrary());
				break;
			case 4:
				deleteBook();
				break;
			case 5:
				updateBook();
				break;
			case 6:
				addAuthor();
				break;
			case 7:
				deleteAuthor();
				break;
			case 8:
				printList(getAllAuthorList());
				break;
			case 9:
				printList(getAuthorBooksList());
				break;
			case 10:
				deleteUser();
				break;
			case 11:
				return;
			default:
				System.out.println("enter key from 1 to 11");
				break;
			}
		}
	}

	private void deleteUser() {
		ArrayList<User> usersList = getAllUserList();
		if(usersList == null) {
			return;
		}
		printList(usersList);
		System.out.println("Select number of user:");
		int number = scan.nextInt();
		if(number == 0 || number > usersList.size()) {
			return;
		}
		int userId = usersList.get(number - 1).getIdUser();
		System.out.println(userId);
		DeleteUserRequest deleteUserRequest = new DeleteUserRequest(userId);
		socketController.write(deleteUserRequest.json());
	}

	private ArrayList<User> getAllUserList() {
		GetAllUsersListRequest getAllUsersListRequest = new GetAllUsersListRequest();
		socketController.write(getAllUsersListRequest.json());
		jsonMessage = socketController.readUtf();
		GetAllUsersListResponse getAllUsersListResponse = gson.fromJson(jsonMessage, GetAllUsersListResponse.class);
		return getAllUsersListResponse.getAllUsersList();

	}

	private void addBookToLibrary() {
		ArrayList<Author> authorsList = getAllAuthorList();
		if(authorsList == null) {
			return;
		}
		printList(authorsList);
		System.out.println("Please select Author:");
		int number = scan.nextInt();
		if(number == 0 || number > authorsList.size()) {
			return;
		}
		Author author = authorsList.get(number - 1);
		System.out.println("Please enter Tite");
		scan.nextLine();
		String title = scan.nextLine();
		System.out.println("Please enter Year:");
		int year = scan.nextInt();
		System.out.println("Please enter Genre:");
		String genre = scan.next();
		Book book = new Book(author, title, year, genre);
		AddBookRequest addBookRequest = new AddBookRequest(book);
		
		socketController.write(addBookRequest.json());
	}

	private ArrayList<Author> getAllAuthorList() {
		GetAllAuthorsRequest getAllAuthorsRequest = new GetAllAuthorsRequest();
		socketController.write(getAllAuthorsRequest.json());
		jsonMessage = socketController.readUtf();
		GetAllAuthorsResponse getAuthorsResponse = gson.fromJson(jsonMessage, GetAllAuthorsResponse.class);
		return getAuthorsResponse.getAuthorsList();
	}

	public ArrayList<Book> getAllBooksList() {
		GetAllBooksRequest getAllBooksRequest = new GetAllBooksRequest();
		socketController.write(getAllBooksRequest.json());
		jsonMessage = socketController.readUtf();
		GetAllBooksResponse getAllBooksResponse = gson.fromJson(jsonMessage, GetAllBooksResponse.class);
		return getAllBooksResponse.getAllBooksList();
	}

	public ArrayList<Book> searchBooksInLibrary() {
		System.out.println("Please enter search text: ");
		String textForSearch = scan.next();
		SearchBookRequest searchBookRequest = new SearchBookRequest(textForSearch);
		socketController.write(searchBookRequest.json());
		jsonMessage = socketController.readUtf();
		SearchBookResponse searchBookResponse = gson.fromJson(jsonMessage, SearchBookResponse.class);
		return searchBookResponse.getFoundedBooksList();
	}
	
	private void deleteBook() {
		ArrayList<Book> listBooks = getAllBooksList();
		if (listBooks == null) {
			return;
		}
		printList(listBooks);
		System.out.println("Choose number of book: \n");
		int number = scan.nextInt();
		if(number == 0 || number > listBooks.size()) {
			return;
		}
		int bookId = listBooks.get(number - 1).getBookId();
		DeleteBookRequest deleteBookRequest = new DeleteBookRequest(bookId);
		socketController.write(deleteBookRequest.json());
	}

	private void updateBook() {
		ArrayList<Book> listBooks = getAllBooksList();
		if (listBooks == null) {
			return;
		}
		printList(listBooks);
		System.out.println("Please enter number of book: ");
		int number = scan.nextInt();
		if(number == 0 || number > listBooks.size()) {
			return;
		}
		Book book = listBooks.get(number - 1);
		Book updatedBook = null;
		System.out.println("Choose field to update: \n" + "1. Author\n" + "2. Title\n" + "3. Year\n" + "4. Genre\n");
		int digit = scan.nextInt();
		if(digit == 0 || digit > 4) {
			return;
		}
		switch (digit) {
		case 1:
			updatedBook = enterAuthor(book);
			break;
		case 2:
			updatedBook = enterTitle(book);
			break;
		case 3:
			updatedBook = enterYear(book);
			break;
		case 4:
			updatedBook = enterGenre(book);
			break;
		default:
			System.out.println("enter 1-4");
			break;
		}
		if (updatedBook == null) {
			return;
		}
		UpdateBookRequest updateBookRequest = new UpdateBookRequest(updatedBook);
		socketController.write(updateBookRequest.json());
	}

	private void addAuthor() {
		System.out.println("Enter Author's name: ");
		scan.nextLine();
		String name = scan.next();
		System.out.println("Enter Author's surname: ");
		String surname = scan.next();
		System.out.println("Enter Author's language (ENG, RUS, DEF etc.): ");
		Language lang = Language.create(scan.next());
		Author author = new Author(name, surname, lang);
		AddAuthorRequest addAuthorRequest = new AddAuthorRequest(author);
		socketController.write(addAuthorRequest.json());
	}

	private void deleteAuthor() {
		ArrayList<Author> allAuthorsList = getAllAuthorList();
		printList(allAuthorsList);
		System.out.println("Select Author number: ");
		int number = scan.nextInt();
		if(number == 0 || number > allAuthorsList.size()) {
			return;
		}
		int authorId = allAuthorsList.get(number - 1).getAuthorId();
		DeleteAuthorRequest deleteAuthorRequest = new DeleteAuthorRequest(authorId);
		socketController.write(deleteAuthorRequest.json());
	}

	private ArrayList<Book> getAuthorBooksList() {
		ArrayList<Author> allAuthorsList = getAllAuthorList();
		printList(allAuthorsList);
		System.out.println("Select Author number: ");
		int authorId = scan.nextInt();
		if(authorId == 0 || authorId > allAuthorsList.size()) {
			return null;
		}
		GetAuthorBooksListRequest getAuthorBooksListRequest = new GetAuthorBooksListRequest(authorId);
		socketController.write(getAuthorBooksListRequest.json());
		jsonMessage = socketController.readUtf();
		GetAuthorBooksListResponse getAuthorBooksListResponse = gson.fromJson(jsonMessage,
				GetAuthorBooksListResponse.class);
		return getAuthorBooksListResponse.getAuthorsBooksList();
	}

	private Book enterAuthor(Book mutableBook) {
		System.out.println("Enter Author's name: ");
		scan.nextLine();
		String name = scan.next();
		System.out.println("Enter Author's surname: ");
		String surname = scan.next();
		System.out.println("Enter Author's language (ENG, RUS, DEF etc.): ");
		Language lang = Language.create(scan.next());
		Author author = new Author(name, surname, lang);
		mutableBook.setAuthor(author);
		return mutableBook;
	}

	private Book enterTitle(Book mutableBook) {
		if (mutableBook == null) {
			return null;
		}
		System.out.println("Enter title of the book: ");
		scan.nextLine();
		String title = scan.nextLine();
		mutableBook.setTitle(title);
		return mutableBook;
	}

	private Book enterYear(Book mutableBook) {
		if (mutableBook == null) {
			return null;
		}
		System.out.println("Enter 4 digits of year: ");
		int year = scan.nextInt();
		if( year < 1000 || year > 3000) {
			return null;
		}
		mutableBook.setYear(year);
		return mutableBook;
	}

	private Book enterGenre(Book mutableBook) {
		if (mutableBook == null) {
			return null;
		}
		System.out.println("Enter genre: ");
		String genre = scan.next();
		mutableBook.setGenre(genre);
		return mutableBook;
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
