package socket.model.serverMessage;

public enum ServerMessage {
	
	ADD_AUTHOR("add_author"),
	ADD_BOOK("add_book"),
	ADD_USER("add_user"),
	ADD_USER_BOOK("add_user_book"),
	DELETE_AUTHOR("delete_author"),
	DELETE_BOOK("delete_book"),
	DELETE_USER_BOOK("delete_user_book"),
	DELETE_USER("delete_user"),
	GET_ALL_BOOKS("show_allbooks"),
	GET_ALL_AUTHORS("get_all_authors"),
	GET_ALL_USERS("get_all_users"),
	LOGIN_CHECK("login_check"),
	SEARCH_BOOK("search"), 
	SEARCH_BOOKS("search_books"),
	SHOW_AUTHORS_BOOKS("show_authors_books"),
	SHOW_BOOKS("show_books"),
	UPDATE_AUTHOR("update_author"),
	UPDATE_BOOK("update_book"),
	USER_CHECK("user_check"),
	USER_EXIST("user_exist"),
	USER_NOT_EXIST("user_not_exist");
	
	private String message;

	ServerMessage(String message){
		this.message = message;
	}
	
	public static ServerMessage create(String message) {
        for (ServerMessage serverMessage : ServerMessage.values()) {
            if (serverMessage.message.equals(message)) {
                return serverMessage;
            }
        }
        return null;
    }

	public String getMessage() {
		return message;
	}
}
