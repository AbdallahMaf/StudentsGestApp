package com.groupeisi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

import com.groupeisi.entities.Student;

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

	//INSERT STUDENT
	public void insertStudent (Student student) {
		System.out.println(INSERT_STUDENT_SQL);
	
        try(Connection cnx = getConnection();
        	PreparedStatement pstm = cnx.prepareStatement(INSERT_STUDENT_SQL)) {
            	pstm.setString(1, student.getNom());
            	pstm.setString(2, student.getPrenom());
            	pstm.setString(3, student.getEmail());
            	pstm.setDate(4, java.sql.Date.valueOf(student.getDate()));
            	pstm.setString(5, student.getClasse());
                pstm.executeUpdate();
            } catch (SQLException e) {
				printSQLException(e);
			}
	}

	
	//SELECT STUDENT BY ID
	//SELECT ALL STUDENTS
	//UPDATE STUDENT
	//DELETE STUDENT
	
	
	private void printSQLException(SQLException ex) {
		for(Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("SQLError code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while(t != null ) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}

			}
		}
	}
}
