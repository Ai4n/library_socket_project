package main.controller;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.*;
import com.ai4n.socketExchange.model.SocketExchange;
import com.ai4n.socketExchange.model.socketExchange.*;
import com.ai4n.entities.book.Author;
import com.ai4n.entities.book.Book;
import com.ai4n.socketExchange.controller.SocketController;
import com.ai4n.entities.user.User;
import repositories.BookRepo;
import repositories.UserRepo;

public class ServerController extends Thread {

    private SocketController socketController;

    BookRepo bookRepo = new BookRepo();
    UserRepo userRepo = new UserRepo();
    Gson gson = new Gson();

    public ServerController(Socket socket) throws IOException {
        socketController = new SocketController(socket);
    }
    @Override
    public void run() {
        handleMessages();
    }

    public void handleMessages() {

        while (true) {
            /***
             * Test threads turned on
             */
            //System.out.println(Thread.currentThread().getName() + " #" + Thread.currentThread().getId());
            String jsonMessage = socketController.readUtf();
            SocketExchange request = gson.fromJson(jsonMessage, SocketExchange.class);
            System.out.println("message: " + request.message);

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
                    sendAllBooksList();
                    break;
                case GET_ALL_AUTHORS:
                    sendAllAuthorsList();
                    break;
                case GET_ALL_USERS:
                    sendAllUsersList();
                    break;
                case SEARCH_BOOK:
                    SearchBookRequest searchBookRequest = gson.fromJson(jsonMessage, SearchBookRequest.class);
                    searchBookInLibrary(searchBookRequest.getTextForSearch());
                    break;
                case USER_CHECK:
                    UserCheckRequest userCheckRequest = gson.fromJson(jsonMessage, UserCheckRequest.class);
                    checkUser(userCheckRequest.getLogin(), userCheckRequest.getPassword());
                    break;
                case LOGIN_CHECK:
                    IsLoginExistRequest isLoginExistRequest = gson.fromJson(jsonMessage, IsLoginExistRequest.class);
                    checkLogin(isLoginExistRequest.getNewLogin());
                    break;
                case ADD_USER:
                    AddUserRequest addUserRequest = gson.fromJson(jsonMessage, AddUserRequest.class);
                    addUser(addUserRequest.getUser());
                    break;
                case ADD_USER_BOOK:
                    AddBookToUsersBookListRequest addBookToUsersBookListRequest = gson.fromJson(jsonMessage,
                            AddBookToUsersBookListRequest.class);
                    addBookToUsersBookListRequest(addBookToUsersBookListRequest);
                    break;
                case SHOW_BOOKS:
                    GetAllUserBooksRequest getAllUserBooksRequest = gson.fromJson(jsonMessage,
                            GetAllUserBooksRequest.class);
                    sendAllUserBooks(getAllUserBooksRequest.getUserId());
                    break;
                case SHOW_AUTHORS_BOOKS:
                    GetAuthorBooksListRequest getAuthorBooksListRequest = gson.fromJson(jsonMessage,
                            GetAuthorBooksListRequest.class);
                    getAllAuthorsBooks(getAuthorBooksListRequest.getAuthorId());
                    break;
                case SEARCH_BOOKS:
                    SearchInUserBooksRequest searchInUserBooksRequest = gson.fromJson(jsonMessage,
                            SearchInUserBooksRequest.class);
                    searchBookInUserBooksList(searchInUserBooksRequest.getUserId(), searchInUserBooksRequest.getText());
                    break;
                case DELETE_USER_BOOK:
                    DeleteBookFromUsersBookList deleteBookFromUsersBookList = gson.fromJson(jsonMessage,
                            DeleteBookFromUsersBookList.class);
                    deleteUsersBookInList(deleteBookFromUsersBookList.getBookId(), deleteBookFromUsersBookList.getUserId());
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
                case CLOSE_SESSION:
                    socketController.closeSession();
                    return;
                default:
                    return;
            }

        }
    }

    private void addAuthor(Author author) {
        bookRepo.addAuthor(author);
    }

    private void addBook(Book book) {
        bookRepo.addBookToLibrary(book);
    }

    private void sendAllBooksList() {
        GetAllBooksResponse getAllBooksResponse = new GetAllBooksResponse(bookRepo.getAllBooksList());
        socketController.write(getAllBooksResponse);
    }

    private void sendAllAuthorsList() {
        GetAllAuthorsResponse getAllAuthorsResponse = new GetAllAuthorsResponse(bookRepo.getAllAuthorsList());
        socketController.write(getAllAuthorsResponse);
    }

    private void sendAllUsersList() {
        GetAllUsersListResponse getAllUsersListResponse = new GetAllUsersListResponse(userRepo.getAllUsersList());
        socketController.write(getAllUsersListResponse);
    }

    private void searchBookInLibrary(String string) {
        ArrayList<Book> foundedBooksList = bookRepo.searchBookInLibrary(string);
        SearchBookResponse searchBookResponse = new SearchBookResponse(foundedBooksList);
        socketController.write(searchBookResponse);
    }

    private void updateBook(Book book) {
        bookRepo.updateBook(book);
    }

    private void deleteBook(int bookId) {
        bookRepo.deleteBookInLibrary(bookId);
        bookRepo.deleteBookData(bookId);

    }

    private void deleteUsersBookInList(int bookId, int userId) {
        bookRepo.deleteUserBook(userId, bookId);
    }

    private void sendAllUserBooks(int userId) {
        ArrayList<Book> foundBooksList = bookRepo.getAllUserBooks(userId);
        GetAllUserBooksResponse getAllUserBooksResponse = new GetAllUserBooksResponse(foundBooksList);
        socketController.write(getAllUserBooksResponse);
    }

    private void addBookToUsersBookListRequest(AddBookToUsersBookListRequest request) {
        bookRepo.addBookInUserBookList(request.getBookId(), request.getUserId());
    }

    private void searchBookInUserBooksList(int userId, String text) {
        ArrayList<Book> booksList = bookRepo.searchBookInUserBooksList(userId, text);
        SearchInUserBooksResponse searchInUserBooksResponse = new SearchInUserBooksResponse(booksList);
        socketController.write(searchInUserBooksResponse);
    }

    private void checkUser(String login, String password) {
        User user = userRepo.checkUser(login, password);
        boolean isCredentialsCorrect = (user != null) ? true : false;
        UserCheckResponse userCheckResponse = new UserCheckResponse(isCredentialsCorrect, user);
        socketController.write(userCheckResponse);
    }

    private void checkLogin(String newLogin) {
        Boolean isLoginExist = userRepo.isLoginExist(newLogin);
        IsLoginExistResponse isLoginExistResponse = new IsLoginExistResponse(isLoginExist);
        socketController.write(isLoginExistResponse);
    }

    private void addUser(User user) {
        userRepo.addUser(user);
    }

    private void deleteUser(int userId) {
        userRepo.deleteUser(userId);
        userRepo.deleteDataInUserBooks(userId);
    }

    private void deleteAuthor(int authorId) {
        bookRepo.deleteAuthor(authorId);
    }

    private void getAllAuthorsBooks(int authorId) {
        ArrayList<Book> authorsBooksList;
        authorsBooksList = bookRepo.getAllAuthorBooks(authorId);
        GetAuthorBooksListResponse getAuthorBooksListResponse = new GetAuthorBooksListResponse(authorsBooksList);
        socketController.write(getAuthorBooksListResponse);
    }
}
