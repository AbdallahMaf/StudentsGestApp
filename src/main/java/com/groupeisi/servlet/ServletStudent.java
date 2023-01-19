package com.groupeisi.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.groupeisi.dao.StudentDao;
import com.groupeisi.entities.Student;

import jakarta.servlet.RequestDispatcher;

/**
 * Servlet implementation class ServletStudent
 */
@WebServlet("/")
public class ServletStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		studentDao = new StudentDao();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		switch(action) {
		case "/new":
			showNewForm(request, response);
			break; 
			
		case "/insert":
			insertStudent(request, response);
			break; 
			
		case "/delete":
			break; 
			
		case "/edit":
			break; 
			
		case "/update":
			break; 
			
			default:
				break;
				
		}
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
		dispatcher.forward(request, response);	
	}
	
	// Insert
	private void insertStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String date = request.getParameter("date");
		String classe = request.getParameter("classe");
		Student newStudent = new Student(0, nom, prenom, email, date, classe);
			studentDao.insertStudent(newStudent);
	}
	
	

}
