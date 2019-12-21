package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.Author;
import main.Book;
import main.Language;
import main.User;

public class BookRepo {
	Connection connection;

	public BookRepo() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root",
					"rootroot");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Author getAuthorById(int id) {
		String query = "SELECT * FROM authors WHERE authorId = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString(2);
				String surname = rs.getString(3);
				Language language = Language.create(rs.getString(4));
				Author author = new Author(id, name, surname, language);
				return author;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Author> getAllAuthorsList() {
		ArrayList<Author> allAuthorsList = new ArrayList<>();
		String query = "SELECT * FROM authors";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				int authorId = rs.getInt(1);
				String name = rs.getString(2);
				String surname = rs.getString(3);
				String languageString = rs.getString(4);
				Language language = Language.valueOf(languageString);
				Author author = new Author(authorId, name, surname, language);
				allAuthorsList.add(author);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allAuthorsList;
	}

	public ArrayList<Book> getAllAuthorBooks(int authorId) {
		ArrayList<Book> allAuthorBooks = new ArrayList<>();
		String sql = "SELECT  a.name, a.surname, a.language, b.title, b.year, b.genre FROM authors a JOIN books b\n"
				+ "ON b.authorid = a.authorid where b.authorid = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, authorId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String authorName = rs.getString(1);
				String authorSurname = rs.getString(2);
				String languageString = rs.getString(3);
				Language authorLanguage = Language.create(languageString);
				String title = rs.getString(4);
				int year = rs.getInt(5);
				String genre = rs.getString(6);
				Author author = new Author(authorId, authorName, authorSurname, authorLanguage);
				Book book = new Book(author, title, year, genre);
				allAuthorBooks.add(book);

			}
			return allAuthorBooks;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Book> getAllBooksList() {
		ArrayList<Book> allBooks = new ArrayList<>();
		String query = "SELECT b.id,  a.name, a.surname, a.language, b.title, b.year, b.genre, b.authorid FROM books b\n"
				+ "JOIN authors a ON b.authorid = a.authorid";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				int bookId = rs.getInt(1);
				String authorName = rs.getString(2);
				String authorSurname = rs.getString(3);
				String language = rs.getString(4);
				Language authorLanguage = Language.create(language);
				String title = rs.getString(5);
				int year = rs.getInt(6);
				String genre = rs.getString(7);
				int authorId = rs.getInt(8);
				Author author = new Author(authorId, authorName, authorSurname, authorLanguage);
				Book book = new Book(bookId, author, title, year, genre);
				allBooks.add(book);
			}

		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return allBooks;
	}

	public boolean userHasBook(int idUser, int idBook) {
		PreparedStatement statement;
		String query = "SELECT * FROM users_books where iduser = ? and idbook = ?";
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, idUser);
			statement.setInt(2, idBook);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public void addBookInUserBookList(int bookId, int userId) {
		Boolean userHasBook = userHasBook(userId, bookId);
		if (userHasBook) {
			String sql = "INSERT into users_books values(null, ?, ?)";
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, userId);
				ps.setInt(2, bookId);
				ps.executeUpdate();
			} catch (SQLException ex) {
				System.out.println(ex);
			}
		}
	}

	public void updateBook(Book book) {
		String query = "UPDATE  books SET authorid = ?, title = ?, year = ?, genre = ? WHERE id = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, book.getAuthor().getAuthorId());
			ps.setString(2, book.getTitle());
			ps.setInt(3, book.getYear());
			ps.setString(4, book.getGenre());
			ps.setInt(5, book.getBookId());
			ps.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex);
		}

	}

	public void addBookToLibrary(Book book) {
		String query = "INSERT into books values(null, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, book.getAuthor().getAuthorId());
			ps.setString(2, book.getTitle());
			ps.setInt(3, book.getYear());
			ps.setString(4, book.getGenre());
			ps.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex);
		}

	}

	public ArrayList<Book> searchBookInLibrary(String text) {
		ArrayList<Book> foundBooks = new ArrayList<>();
		PreparedStatement statement;
		String sql = "SELECT * from books where title LIKE ? or genre LIKE ?";

		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, "%" + text + "%");
			statement.setString(2, "%" + text + "%");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int authorId = rs.getInt(2);
				Author author = getAuthorById(authorId);
				String title = rs.getString(3);
				int year = rs.getInt(4);
				String genre = rs.getString(5);
				Book book = new Book(author, title, year, genre);
				foundBooks.add(book);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return foundBooks;
	}

	public ArrayList<Book> getAllUserBooks(int userId) {
		ArrayList<Book> booksList = new ArrayList<>();
		PreparedStatement statement;
		String query = "SELECT b.id, b.authorId, b.title, b.year, b.genre from users_books ub " + "JOIN books b "
				+ "ON b.id = ub.idbook where ub.iduser = ?";
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int bookId = rs.getInt(1);
				int authorId = rs.getInt(2);
				Author author = getAuthorById(authorId);
				String title = rs.getString(3);
				int year = rs.getInt(4);
				String genre = rs.getString(5);
				Book book = new Book(bookId, author, title, year, genre);
				booksList.add(book);
			}

		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return booksList;
	}

	public ArrayList<Book> searchBookInUserBooksList(int userId, String text) {
		ArrayList<Book> foundBooks = new ArrayList<>();
		PreparedStatement statement;
		String query = "SELECT b.id, b.authorId, b.title, b.year, b.genre from users_books ub JOIN books b\n"
				+ "ON b.id = ub.idbook where ub.iduser = ? and (title LIKE ? or genre LIKE ?)";

		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			statement.setString(2, "%" + text + "%");
			statement.setString(3, "%" + text + "%");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int bookId = rs.getInt(1);
				int authorId = rs.getInt(2);
				Author author = getAuthorById(authorId);
				String title = rs.getString(3);
				int year = rs.getInt(4);
				String genre = rs.getString(5);
				Book book = new Book(bookId, author, title, year, genre);
				foundBooks.add(book);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return foundBooks;
	}

	public void deleteBookInLibrary(int bookId) {
		String query = "DELETE FROM books where id = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, bookId);
			ps.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	public void deleteBookData(int bookId) {
		String sql = "DELETE FROM users_books WHERE idbook = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, bookId);
			ps.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	public void addAuthor(Author author) {
		String query = "INSERT into authors values(null, ?, ?, ?)";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, author.getName());
			ps.setString(2, author.getSurname());
			ps.setString(3, String.valueOf(author.getLanguage()));
			ps.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex);
		}

	}

	public void deleteAuthor(int authorId) {
		String query = "DELETE FROM authors where authorid = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, authorId);
			ps.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}
}
