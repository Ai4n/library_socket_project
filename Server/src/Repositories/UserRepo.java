package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public boolean checkLogin(String login) {
		PreparedStatement statement;
		String sql = "SELECT * FROM users WHERE login = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				return false; 
			}	
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return true;
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
}