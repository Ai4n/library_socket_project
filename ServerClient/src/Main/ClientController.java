package Main;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientController {
	public Scanner scan = new Scanner(System.in);
    private SocketController socketController;
    
	public ClientController(Socket socket) throws IOException {
		socketController = new SocketController(socket);
	}

	public void startMenu() throws ClassNotFoundException, IOException {

		loop: while (true) {
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
				break loop;
			}
		}
	}
	
	public void signUp() {
		String newLogin;
		String password1;
		String password2;
		String cryptPassword;
		String loginStatus;
		do {
			System.out.println("Enter new login: \n");
			newLogin = scan.next();
			socketController.write(ServerMessage.LOGIN_CHECK, newLogin);
			loginStatus = socketController.readUtf();
		} while (loginStatus.equals(ServerMessage.USER_EXIST.getMessage()));

		do {
			System.out.println("Enter password (at least four digits): ");
			password1 = scan.next();
			System.out.println("Repeat password (at least four digits): ");
			password2 = scan.next();
		} while (!(password1.equals(password2)));

		cryptPassword = PasswordUtils.encodePassword(password1);
		socketController.write(ServerMessage.ADD_USER, newLogin, cryptPassword);
	}
	
	public User login() {
		String result;
		do {
			System.out.println("Enter your login:");
			String login = scan.next();
			System.out.println("Enter your password:");
			String password1 = scan.next();
			String password = PasswordUtils.encodePassword(password1);
			socketController.write(ServerMessage.USER_CHECK, login, password);
			result = socketController.readUtf();
		} while (result.equals(ServerMessage.USER_NOT_EXIST.getMessage()));
		
		User user = socketController.read();
		return user;
	}
}
