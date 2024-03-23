package com.auca.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.auca.controller.StudentRegistrationDao;
import com.auca.domain.Eregistration;
import com.auca.domain.StudentRegistration;

public class StudentRegistrationUpdateServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private StudentRegistrationDao studentRegistrationDao = new StudentRegistrationDao();
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		doPost(req, res);		
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		saveStudentRegistration(req, res);
	}
	
	public void saveStudentRegistration(HttpServletRequest req, HttpServletResponse res) {
	    try {
	        String studentIdStr = req.getParameter("code");
	        int studentId = Integer.parseInt(studentIdStr);
	        String acastatus = req.getParameter("status");
	        Eregistration status = Eregistration.valueOf(acastatus);

	        // Update the status for the given studentId
	        studentRegistrationDao.updateStudentRegistrationStatus(studentId, status);

	        req.getRequestDispatcher("adminDashboard.jsp").forward(req, res);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
