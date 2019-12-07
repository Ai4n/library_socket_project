package Main;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import com.google.gson.Gson;

import SocketExchange.*;

public class ClientController {
	public Scanner scan = new Scanner(System.in);
	private SocketController socketController;
	private String jsonMessage;
	private Gson gson = new Gson();

	public ClientController(Socket socket) throws IOException {
		socketController = new SocketController(socket);
	}

	public void startMenu() throws ClassNotFoundException, IOException {

		while (true) {
			int number;
			System.out.println("Choose options:\n" + "1. Singup\n" + "2. Login\n");
			number = scan.nextInt();
			switch (number) {
			case 1:
				signUp();
				break;
			case 2:
				User user = login();
				UserRole role = user.getRole();
				if (role.equals(UserRole.USER)) {
					UserController userController = new UserController(user, socketController);
					userController.userMenu();
				} else if (role.equals(UserRole.ADMIN)) {
					AdminController adminController = new AdminController(socketController);
					adminController.adminMenu();
				}
				break;
			default:
				break;
			}
		}
	}

	public void signUp() {
		String newLogin;
		String password1;
		String password2;
		String cryptPassword;
		IsLoginExistResponse isLoginExistResponse;
		do {
			System.out.println("Enter new login:\n");
			newLogin = scan.next();
			IsLoginExistRequest isLoginExistRequest = new IsLoginExistRequest(newLogin);
			socketController.write(isLoginExistRequest.json());
			jsonMessage = socketController.readUtf();
			isLoginExistResponse = gson.fromJson(jsonMessage, IsLoginExistResponse.class);
		} while (isLoginExistResponse.message == (ServerMessage.USER_EXIST));

		do {
			System.out.println("Enter password (at least four simbols): ");
			password1 = scan.next();
			System.out.println("Repeat password (at least four simbols): ");
			password2 = scan.next();
		} while (!(password1.equals(password2)));

		cryptPassword = PasswordUtils.encodePassword(password1);
		AddUserRequest addUserRequest = new AddUserRequest(newLogin, cryptPassword);
		socketController.write(addUserRequest.json());
	}

	public User login() {
		UserCheckResponse userCheckResponse;
		do {
			System.out.println("Enter your login:");
			String login = scan.next();
			System.out.println("Enter your password:");
			String password1 = scan.next();
			String password = PasswordUtils.encodePassword(password1);
			UserCheckRequest request = new UserCheckRequest(login, password);
			socketController.write(request.json());
			jsonMessage = socketController.readUtf();
			userCheckResponse = gson.fromJson(jsonMessage, UserCheckResponse.class);
		} while (userCheckResponse.message == ServerMessage.USER_NOT_EXIST);

		return userCheckResponse.getUser();
	}
}
