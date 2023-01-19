package com.groupeisi.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;

/**
 * Servlet implementation class ServletStudent
 */
@WebServlet("/")
public class ServletStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
	switch(action) 
	{
	case "/new":
		showNewForm(request, response);
		break; 
		
	case "/insert":
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
	

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
		dispatcher.forward(request, response);
		
	}

}
