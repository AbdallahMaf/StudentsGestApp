package com.groupeisi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StudentDao {

	public String url = "jdbc:mysql://localhost:3306/studentgest?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public String username = "root";
	public String password = "";
	public String jdbcDriver = "com.mysql.jdbc.Driver";
	
	private static final String INSERT_STUDENT_SQL = "INSERT INTO students" + 
			" (nom, prenom, email, date, classe) VALUES " + " (?, ?, ?, ?, ?);";
	
	private static final String SELECT_STUDENT_BY_ID = "SELECT id, nom, prenom, email, date, classe FROM students WHERE id =?;";
	
	private static final String SELECT_ALL_STUDENTS = "SELECT * FROM students;";
	
	private static final String DELETE_STUDENT_SQL = "DELETE FROM students WHERE id =?;";
	
	private static final String UPDATE_STUDENT_SQL = "UPDATE students SET nom = ?, prenom = ?, email = ?, date = ?, classe = ?;";

	public StudentDao() {
		
	}
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	
}
