package com.ase.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ase.dao.AttendanceItemDao;
import com.ase.dao.AttendanceLogDao;
import com.ase.dao.GroupDao;
import com.ase.dao.StudentDao;
import com.ase.dao.TutorDao;
import com.ase.dao.impl.AttendanceItemDaoImpl;
import com.ase.dao.impl.AttendanceLogDaoImpl;
import com.ase.dao.impl.GroupDaoImpl;
import com.ase.dao.impl.StudentDaoImpl;
import com.ase.dao.impl.TutorDaoImpl;
import com.ase.entity.AttendanceItem;
import com.ase.entity.AttendanceLog;
import com.ase.entity.Group;
import com.ase.entity.Student;
import com.ase.entity.Tutor;
import com.ase.service.GroupService;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Ref;

public class GroupServiceImpl implements GroupService {

	StudentDao studentDAO = new StudentDaoImpl();
	TutorDao tutorDAO = new TutorDaoImpl();
	GroupDao groupDAO = new GroupDaoImpl();
	AttendanceLogDao attendanceLogDAO = new AttendanceLogDaoImpl();
	AttendanceItemDao attendanceItemDAO = new AttendanceItemDaoImpl();

	@Override
	public void joinStudentToGroup(User user, String groupName) {
		Student student = studentDAO
				.getStudentFromDB(UserServiceFactory.getUserService().getCurrentUser().getEmail());
		Group group = groupDAO.getGroupFromDB(groupName);
		for(Group currentGroup : groupDAO.getAllGroups()){
			//Not allow student to join the same group multiple times.
				currentGroup.removeStudent(student); //Ideally only one entry of the student should be present.
				groupDAO.saveGroupFromDB(currentGroup);
		}
		
		student.setGroup(group);
		group.addStudent(student);

		groupDAO.saveGroupFromDB(group);
		studentDAO.saveStudentToDB(student);
		
		Random r = new Random(); 
		
		ArrayList<Ref<AttendanceLog>> attendanceLogs = group.getSessions();
		for(Ref<AttendanceLog> attendancePtr:attendanceLogs){
			AttendanceLog log = attendancePtr.get();
			
			AttendanceItem attendanceItem = new AttendanceItem();
			attendanceItem.setAttendanceId(r.nextLong());
			attendanceItem.setHasAttended(false);
			attendanceItem.setStudent(Ref.create(student));
			
			attendanceItemDAO.saveAttendanceItemToDB(attendanceItem);
			
			log.addAttendanceItemRef(Ref.create(attendanceItem));
			attendanceLogDAO.saveAttendanceLogToDB(log);
		}
		
		
	}
	
	
	@Override
	public List<Group> getAllGroups(){
		return groupDAO.getAllGroups();
	}

	@Override
	public void createGroup(String groupName, User user) {
		// to create group
		Random r = new Random(); 


		Tutor tutor = tutorDAO.getTutorFromDB(user.getEmail());
		Group group = new Group(groupName);
		group.setTutor(tutor);
		tutor.addGroup(group);
	
		tutorDAO.saveTutorToDB(tutor);

		
		
		//Lets rougly create 10 attendance log items 
		for(short i=1;i<=10;i++){
			AttendanceLog attendanceLog = new AttendanceLog();
			attendanceLog.setAttendanceLogId(r.nextLong());
			attendanceLog.setWeekId(i);
			attendanceLogDAO.saveAttendanceLogToDB(attendanceLog);
			group.addAttendanceLog(attendanceLog);
		}
		
		groupDAO.saveGroupFromDB(group);
	}

	@Override
	public void deleteGroup(String groupName) {
		Group groupToDelete = groupDAO.getGroupFromDB(groupName);
		if (groupToDelete != null)
			groupDAO.deleteGroupFromDB(groupName);
		
	}
	
	@Override
	public void editGroup(String groupName,
			String groupDay, String groupHour, String groupPlace) {
		Group groupToEdit = groupDAO.getGroupFromDB(groupName);
		//TODO check that students remain inside
		groupToEdit.setDay(groupDay);
		groupToEdit.setHour(groupHour);
		groupToEdit.setPlace(groupPlace);
		groupDAO.saveGroupFromDB(groupToEdit);
	}


	@Override
	public Group getGroupByID(String groupName) {
		// TODO Auto-generated method stub
		return groupDAO.getGroupFromDB(groupName);
	}

}

