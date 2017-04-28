package com.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {
	
	private DataSource dataSource;
	
	public StudentDbUtil(DataSource ds) {
		dataSource = ds;
	}
	
	public List<Student> getStudents() throws Exception {
		List<Student> students = new ArrayList<>();
		
		Connection conn = null;
		Statement stmnt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "select * from student order by last_name";
			stmnt = conn.createStatement();
			rs = stmnt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				
				Student student = new Student(id, firstName, lastName, email);
				students.add(student);
			}
			return students;
		}
		finally {
			// Close JDBC objects
			close(conn, stmnt, rs);
		}
	}

	private void close(Connection conn, Statement stmnt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmnt != null) {
				stmnt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void addStudent(Student newStudent) throws Exception {
		Connection conn = null;
		PreparedStatement stmnt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "insert into student (first_name, last_name, email) values(?,?,?)";
			stmnt = conn.prepareStatement(sql);
			stmnt.setString(1, newStudent.getFirstName());
			stmnt.setString(2, newStudent.getLastName());
			stmnt.setString(3, newStudent.getEmail());
			
			stmnt.execute();
		}
		finally {
			close(conn, stmnt, null);
		}
	}

	public Student getStudent(String studentId) throws Exception {
		Student student = null;
		Connection conn = null;
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		int id;
		
		try {
			id = Integer.parseInt(studentId);
			conn = dataSource.getConnection();
			String sql = "select * from student where id=?";
			stmnt = conn.prepareStatement(sql);
			stmnt.setInt(1, id);
			rs = stmnt.executeQuery();
			if (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				student = new Student(id, firstName, lastName, email);
			}
			else {
				throw new Exception("Could not find student with id:" + id);
			}
			return student;
		}
		finally {
			close(conn, stmnt, rs);
		}
	}

	public void updateStudent(Student studentToUpdate) throws Exception {
		Connection conn = null;
		PreparedStatement stmnt = null;
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "update student set first_name=?, last_name=?, email=? where id=?";
			
			stmnt = conn.prepareStatement(sql);
			
			stmnt.setString(1, studentToUpdate.getFirstName());
			stmnt.setString(2, studentToUpdate.getLastName());
			stmnt.setString(3, studentToUpdate.getEmail());
			stmnt.setInt(4, studentToUpdate.getId());
			
			stmnt.execute();
		}
		finally {
			close(conn, stmnt, null);
		}
	}

	public void deleteStudent(String id) throws Exception {
		Connection conn = null;
		PreparedStatement stmnt = null;
		
		try {
			int studentId = Integer.parseInt(id);
			conn = dataSource.getConnection();
			String sql = "delete from student where id=?";
			stmnt = conn.prepareStatement(sql);
			stmnt.setInt(1, studentId);
			stmnt.execute();
		}
		finally {
			close(conn, stmnt, null);
		}
		
	}
}
