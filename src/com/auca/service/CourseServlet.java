package com.auca.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auca.controller.AcademicUnitDao;
import com.auca.controller.CourseDao;
import com.auca.controller.CourseDefinitionDao;
import com.auca.controller.SemesterDao;
import com.auca.controller.TeacherDao;
import com.auca.domain.AcademicUnit;
import com.auca.domain.Course;
import com.auca.domain.CourseDefinition;
import com.auca.domain.Semester;
import com.auca.domain.Teacher;

public class CourseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CourseDao courseDao = new CourseDao();
	private CourseDefinitionDao courseDefinitionDao = new CourseDefinitionDao();
	private TeacherDao teacherDao = new TeacherDao();
	private SemesterDao semesterDao = new SemesterDao();
	private AcademicUnitDao academicUnitDao = new AcademicUnitDao();
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		saveCourse(req, res);
	}
	
	public void saveCourse(HttpServletRequest req, HttpServletResponse res) {

	    try {
	        String crcoursedefinition = req.getParameter("coursecode");
	        String crteacher = req.getParameter("teachercode");
	        String crsemester = req.getParameter("semestercode");
	        long crsemesterCode = Long.parseLong(crsemester);
	        String cracademicunit = req.getParameter("academicunitcode");
	        	        
	        // Retrieve the actual entities based on the codes
	        CourseDefinition courseDefinition = courseDefinitionDao.getCourseDefinitionByCode(crcoursedefinition);
	        Teacher teacher = teacherDao.getTeacherByCode(crteacher);
	        
	        Semester semester = semesterDao.getSemesterByCode(crsemesterCode);
	        
	        AcademicUnit academicUnit = academicUnitDao.getAcademicUnitByCode(cracademicunit);

	        Course course = new Course();

	        course.setCousedefinition(courseDefinition);
	        course.setTeacher(teacher);
	        course.setSemester(semester);
	        course.setAcademicunit(academicUnit);

	        courseDao.saveCourse(course);

	        req.getRequestDispatcher("adminDashboard.jsp").forward(req, res);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	}


}
