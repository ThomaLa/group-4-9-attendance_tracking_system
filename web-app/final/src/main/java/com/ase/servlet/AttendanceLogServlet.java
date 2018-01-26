package com.ase.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ase.entity.AttendanceItem;
import com.ase.entity.AttendanceLog;
import com.ase.entity.Student;
import com.ase.entity.Tutor;
import com.ase.service.AttendanceService;
import com.ase.service.StudentTutorManagementService;
import com.ase.service.impl.AttendanceServiceImpl;
import com.ase.service.impl.StudentTutorManagementServiceImpl;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Ref;

public class AttendanceLogServlet extends HttpServlet {

	AttendanceService AttendanceService = new AttendanceServiceImpl();
	StudentTutorManagementService studentTutorService = new StudentTutorManagementServiceImpl();
	
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
		Tutor tutor = studentTutorService.getTutor(userService.getCurrentUser());
		String email = "Null";
		if (tutor != null) {
			email = tutor.getEmail();
		}
		req.setAttribute("logouturl", userService.createLogoutURL(req.getRequestURI()));
		req.setAttribute("email", email);
		
		String groupName = req.getParameter("groupName");
		String attendanceLogId = req.getParameter("id");
		
		AttendanceLog attendanceLog = AttendanceService.getAttendanceLog(Long.parseLong(attendanceLogId));
		
		List<AttendanceItem> attendanceItems = new ArrayList<AttendanceItem>();
		
		for(Ref<AttendanceItem> attandancePointers: attendanceLog.getAttendanceItems()){
			attandancePointers.get().getStudent();
			attendanceItems.add(attandancePointers.get());
		}

		req.setAttribute("attendances", attendanceItems);
		req.setAttribute("groupName", groupName);
		
        req.getRequestDispatcher("/tutor/attendanceLog.jsp").forward(req, resp);
    }
}
