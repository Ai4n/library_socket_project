package socketExchange;

import com.google.gson.Gson;

import main.ServerMessage;
import main.UserRole;

public class AddUserRequest extends SocketExchange{

	private int iDuser;
	private String name;
	private String surname;
	private String login;
	private String cryptPassword;
	private UserRole role;
	
	public AddUserRequest(int iDuser, String name, String surname, String login, String cryptPassword, UserRole role) {
		super(ServerMessage.ADD_USER);
		this.iDuser = iDuser;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.cryptPassword = cryptPassword;
		this.role = role;
	}

	public int getIdUser() {
		return iDuser;
	}
	
	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return cryptPassword;
	}

	public UserRole getRole() {
		return role;
	}
	
	public String json() {
		return new Gson().toJson(this);
	}

	
}
