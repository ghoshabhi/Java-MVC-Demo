package com.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/students")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDbUtil studentDbUtil; 
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		// create student db util object and pass in the connection pool
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		}
		catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String theCommand = request.getParameter("command");
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			switch (theCommand) {
			case "LIST":
				listStudents(request, response);
				break;
			case "ADD":
				addStudent(request, response);
				break;
			case "LOAD":
				loadStudent(request, response);
				break;
			case "UPDATE":
				updateStudent(request, response);
				break;
			case "DELETE":
				deleteStudent(request, response);
			default:
				listStudents(request, response);
				break;
			}
		}
		catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
		String id = request.getParameter("studentId");
		studentDbUtil.deleteStudent(id);
		listStudents(request, response);
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
		int id = Integer.parseInt(request.getParameter("studentId"));
		String fName = request.getParameter("firstName");
		String lName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		Student studentToUpdate = new Student(id, fName, lName, email);
		studentDbUtil.updateStudent(studentToUpdate);
		
		listStudents(request, response);
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		String studentId = request.getParameter("studentId");
		
		Student student = studentDbUtil.getStudent(studentId);
		
		request.setAttribute("student", student);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update_student.jsp");
		
		dispatcher.forward(request, response);
		 
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		Student newStudent = new Student(firstName, lastName, email);
		
		studentDbUtil.addStudent(newStudent);
		
		listStudents(request, response);
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<Student> students = studentDbUtil.getStudents();
		request.setAttribute("students", students);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list_students.jsp");
		dispatcher.forward(request, response);
	}

}
