package com.ase.restlet.requestresponse;

import com.google.appengine.repackaged.org.joda.time.DateTime;

public class AttendanceItemUpdateRequest {
	public AttendanceItemUpdateRequest(){
		
	}
	
	public AttendanceItemUpdateRequest(long attendanceId, String studentId, boolean hasAttended, DateTime time) {
		super();
		this.attendanceId = attendanceId;
		this.studentId = studentId;
		this.hasAttended = hasAttended;
		this.time = time;
	}
	public long getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(long attendanceId) {
		this.attendanceId = attendanceId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public boolean isHasAttended() {
		return hasAttended;
	}
	public void setHasAttended(boolean hasAttended) {
		this.hasAttended = hasAttended;
	}
	public DateTime getTime() {
		return time;
	}
	public void setTime(DateTime time) {
		this.time = time;
	}
	private long attendanceId;
	private String studentId;
	private boolean hasAttended;
	private DateTime time;
}
