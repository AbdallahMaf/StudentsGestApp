package com.groupeisi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	public Student selectStudent (int id) {
		Student student = null;
		try(Connection cnx = getConnection();
				
				PreparedStatement pstm = cnx.prepareStatement(SELECT_STUDENT_BY_ID);) {
			pstm.setInt(1, id);
			System.out.println(pstm);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String date = rs.getString("date");
				String classe = rs.getString("classe");
				student = new Student(id, nom, prenom, email, date, classe);
			}
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		return student;	
	}
	
	
	//SELECT ALL STUDENTS
	public List<Student> selectAllStudents(){
		List<Student> students = new ArrayList<>();
		
		try(Connection cnx = getConnection();
				
				PreparedStatement pstm = cnx.prepareStatement(SELECT_ALL_STUDENTS);){
				System.out.println(pstm);
			
				ResultSet rs = pstm.executeQuery();
				
				while(rs.next()) {
					int id = rs.getInt("id");
					String nom = rs.getString("nom");
					String prenom = rs.getString("prenom");
					String email = rs.getString("email");
					String date = rs.getString("date");
					String classe = rs.getString("classe");
					students.add(new Student( id, nom, prenom, email, date, classe));	
				}
			
		}catch (Exception e) {
			printSQLException(null);
		}
		return students;	
	}
	
	
	//UPDATE STUDENT
	public boolean updateStudent(Student student) throws SQLException {
		boolean rowUpdated;
		try (Connection cnx = getConnection();
				
				PreparedStatement statement = cnx.prepareStatement(UPDATE_STUDENT_SQL);){
				System.out.println("Updated Student:" + statement);
				statement.setString(1, student.getNom());
				statement.setString(2, student.getPrenom());
				statement.setString(3, student.getEmail());
				statement.setString(4, student.getDate());
				statement.setString(5, student.getClasse());
				statement.setInt(6, student.getId());
			
				rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	
	
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
