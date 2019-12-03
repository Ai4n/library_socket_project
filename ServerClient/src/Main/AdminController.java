package Main;

import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

import SocketExchange.*;

public class AdminController {
	private Scanner scan = new Scanner(System.in);
	SocketController socketController;
	Gson gson = new Gson();

	public AdminController(SocketController socketController) {
		this.socketController = socketController;
	}

	public void adminMenu() {
		loop: while (true) {
			System.out.println(
					"\nEnter options:\n" + "1.Add book to Library\n" + "2.Show books in Library\n" + "3.Search book\n"
							+ "4.Delete book\n" + "5.Update book\n" + "6.Add new Author\n" + "7.Delete some Author\n"
							+ "8.Show list of Authors\n" + "9.Show list of Author's books\n" + "10.Quit\n");
			int number = scan.nextInt();
			switch (number) {
			case 1:
				addBookToLibrary();
				printList(getAllBooksList());
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
				printList(getAllAuthorsList());
				break;
			case 9:
				getAuthorBooksList();
				break;
			case 10:
				return;
			default:
				System.out.println("enter key from 1 to 10");
				break loop;
			}
		}
	}

	private void addBookToLibrary() {
		ArrayList<Author> authorsList = getAllAuthorsList();
		printList(authorsList);
		System.out.println("Please select Author:");
		scan.nextLine();
		int authorInt = (scan.nextInt() - 1); // -1 Create array index from number
		scan.nextLine();
		Author author = authorsList.get(authorInt);
		System.out.println("Please enter Title:");
		String title = scan.nextLine();
		System.out.println("Please enter Year:");
		int year = scan.nextInt();
		System.out.println("Please enter Genre:");
		String genre = scan.next();
		Book book = new Book(author, title, year, genre);
		if (book.equals(null)) {
			return;
		}
		AddBookRequest addBookRequest = new AddBookRequest(book);
		socketController.write(addBookRequest.json());
	}

	private ArrayList<Author> getAllAuthorsList() {
		GetAllAuthorsRequest getAllAuthorsRequest = new GetAllAuthorsRequest();
		socketController.write(getAllAuthorsRequest.json());
		String jsonMessage = socketController.readUtf();
		GetAllAuthorsResponse getAuthorsResponse = gson.fromJson(jsonMessage, GetAllAuthorsResponse.class);
		return getAuthorsResponse.getAuthorsList();
	}

	public ArrayList<Book> getAllBooksList() {
		GetAllBooksRequest getAllBooksRequest = new GetAllBooksRequest();
		socketController.write(getAllBooksRequest.json());
		String jsonMessage = socketController.readUtf();
		GetAllBooksResponse getAllBooksResponse = gson.fromJson(jsonMessage, GetAllBooksResponse.class);
		return getAllBooksResponse.getAllBooksList();
	}

	public ArrayList<Book> searchBooksInLibrary() {
		System.out.println("Please enter search text: ");
		String textForSearch = scan.next();
		SearchBooksRequest searchBooksRequest = new SearchBooksRequest(textForSearch);
		socketController.write(searchBooksRequest.json());
		String jsonMessage = socketController.readUtf();
		SearchBooksResponse searchBooksResponse = gson.fromJson(jsonMessage, SearchBooksResponse.class);
		return searchBooksResponse.getFoundedBooksList();
	}

	private void deleteBook() {
		ArrayList<Book> listBooks = getAllBooksList();
		if (listBooks.equals(null)) {
			return;
		}
		printList(listBooks);
		System.out.println("Choose number of book: \n");
		int number = scan.nextInt();
		Integer bookId = listBooks.get(number - 1).getBookId();
		DeleteBookRequest deleteBookRequest = new DeleteBookRequest(bookId);
		socketController.write(deleteBookRequest.json());
	}

	private void updateBook() {
		Book updatedBook = null;
		ArrayList<Book> listBooks = getAllBooksList();
		if (listBooks.equals(null)) {
			return;
		}
		printList(listBooks);
		System.out.println("Please enter number of book: ");
		int number = scan.nextInt();
		Book book = listBooks.get(number - 1);
		System.out.println("Choose field to update: \n" + "1. Author\n" + "2. Title\n" + "3. Year\n" + "4. Genre\n");
		int digit = scan.nextInt();
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
		ArrayList<Author> allAuthorsList = getAllAuthorsList();
		printList(allAuthorsList);
		System.out.println("Select Author number: ");
		int number = scan.nextInt();
		int authorId = allAuthorsList.get(number - 1).getAuthorId();
		DeleteAuthorRequest deleteAuthorRequest = new DeleteAuthorRequest(authorId);
		socketController.write(deleteAuthorRequest.json());
	}

	private void getAuthorBooksList() {
		printList(getAllAuthorsList());
		System.out.println("Select Author number: ");
		int authorId = scan.nextInt();
		GetAuthorsBooksListRequest getAuthorsBooksListRequest = new GetAuthorsBooksListRequest(authorId);
		socketController.write(getAuthorsBooksListRequest.json());
		String jsonMessage = socketController.readUtf();
		GetAuthorsBooksListResponse getAuthorsBooksListResponse = gson.fromJson(jsonMessage, GetAuthorsBooksListResponse.class);
		printList(getAuthorsBooksListResponse.getAuthorsBooksList());
	}

	private Book enterAuthor(Book book) {
		System.out.println("Enter Author's name: ");
		scan.nextLine();
		String name = scan.next();
		System.out.println("Enter Author's surname: ");
		String surname = scan.next();
		System.out.println("Enter Author's language (ENG, RUS, DEF etc.): ");
		Language lang = Language.create(scan.next());
		Author author = new Author(name, surname, lang);
	    book.setAuthor(author);
	    Book updatedBook = book;
		return updatedBook;
	}

	private Book enterTitle(Book book) {
		if (book.equals(null)) {
			return null;
		}
		System.out.println("Enter title of the book: ");
		String title = scan.next();
		book.setTitle(title);
		Book updatedBook = book;
		return updatedBook;
	}

	private Book enterYear(Book book) {
		if (book.equals(null)) {
			return null;
		}
		System.out.println("Enter 4 digits of year: ");
		int year = scan.nextInt();
		book.setYear(year);
		Book updatedBook = book;
		return updatedBook;
	}

	private Book enterGenre(Book book) {
		if (book.equals(null)) {
			return null;
		}
		System.out.println("Enter genre: ");
		String genre = scan.next();
		book.setGenre(genre);
		Book updatedBook = book;
		return updatedBook;
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
