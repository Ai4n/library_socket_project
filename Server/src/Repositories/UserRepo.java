package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Main.Author;
import Main.Book;
import Main.Language;
import Main.User;
import Main.UserRole;

public class UserRepo {
	Connection connection;

	public UserRepo() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root",
					"rootroot");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public User checkUser(String login, String password) {
		PreparedStatement statement;
		String sql = "SELECT * FROM users WHERE login = ? AND password = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				int idUser = rs.getInt(1);
				String role = rs.getString(4);
				UserRole userRole = UserRole.create(role);
				User user = new User(idUser, login, password, userRole);
				return user;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public boolean isLoginExist(String login) {
		PreparedStatement statement;
		String sql = "SELECT * FROM users WHERE login = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public void addUser(User user) {
		String query = "INSERT into users values(null, ?, ?, ?)";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, String.valueOf(user.getRole()));
			ps.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	public void deleteUser(int userId) {
		String query = "DELETE FROM users WHERE idusers = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, userId);
			ps.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	public ArrayList<User> getAllUsersList() {
		ArrayList<User> allUsersList = new ArrayList<>();
		String query = "SELECT * FROM users";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				int iduser = rs.getInt(1);
				String login = rs.getString(2);
				String password = rs.getString(3);
				String userRole = rs.getString(4);
				UserRole role = UserRole.create(userRole);
				User user = new User(iduser, login, password, role);
				allUsersList.add(user);
			}

		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return allUsersList;
	}
}