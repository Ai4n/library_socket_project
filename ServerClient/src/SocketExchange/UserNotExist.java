package SocketExchange;

import com.google.gson.Gson;
import Main.ServerMessage;
import Main.User;

public class UserNotExist extends SocketExchange {
		private User user = null;
		
		public UserNotExist (User user) {
			super(ServerMessage.USER_NOT_EXIST);
			this.user = user;	
		}
		
		public static ServerMessage getMessage() {
			return ServerMessage.USER_NOT_EXIST;
		}
		
		public User getUser() {
			return null;
		}
		
		public String json() {
			return new Gson().toJson(this);
		}
}
