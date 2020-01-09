package controllers;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import com.google.gson.Gson;
import controllers.utils.PasswordUtils;
import entities.user.User;
import entities.user.UserRole;
import model.serverMessage.ServerMessage;
import socket.controller.SocketController;
import model.socketExchange.*;

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
		String name;
		String surname;
		String newLogin;
		String passwordTmp1;
		String passwordTmp2;
		String cryptPassword;
		
		IsLoginExistResponse isLoginExistResponse;
		do {
			System.out.println("Enter youre name:\n");
			name = scan.next();
			System.out.println("Enter youre surname:\n");
			surname = scan.next();
			System.out.println("Enter new login:\n");
			newLogin = scan.next();
			IsLoginExistRequest isLoginExistRequest = new IsLoginExistRequest(newLogin);
			socketController.write(isLoginExistRequest);
			jsonMessage = socketController.readUtf();
			isLoginExistResponse = gson.fromJson(jsonMessage, IsLoginExistResponse.class);
		} while (isLoginExistResponse.message == (ServerMessage.USER_EXIST));

		do {
			System.out.println("Enter password (at least four simbols): ");
			passwordTmp1 = scan.next();
			System.out.println("Repeat password (at least four simbols): ");
			passwordTmp2 = scan.next();
		} while (!(passwordTmp1.equals(passwordTmp2)));
		cryptPassword = PasswordUtils.encodePassword(passwordTmp1);
		User user = new User(name, surname, newLogin, cryptPassword, UserRole.USER);
		AddUserRequest addUserRequest = new AddUserRequest(user);
		socketController.write(addUserRequest);
	}

	public User login() {
		UserCheckResponse userCheckResponse;
		do {
			System.out.println("Enter your login:");
			String login = scan.next();
			System.out.println("Enter your password:");
			String password = scan.next();
			String hashedPassword = PasswordUtils.encodePassword(password);
			UserCheckRequest request = new UserCheckRequest(login, hashedPassword);
			socketController.write(request);
			jsonMessage = socketController.readUtf();
			userCheckResponse = gson.fromJson(jsonMessage, UserCheckResponse.class);
		} while (userCheckResponse.message == ServerMessage.USER_NOT_EXIST);

		return userCheckResponse.getUser();
	}
}
