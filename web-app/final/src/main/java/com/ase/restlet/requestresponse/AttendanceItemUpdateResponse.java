package com.ase.restlet.requestresponse;

import com.ase.entity.AttendanceItem;
import com.google.appengine.repackaged.org.joda.time.DateTime;

public class AttendanceItemUpdateResponse {
	
	public AttendanceItemUpdateResponse(boolean result, AttendanceItem attendanceItem, String message) {
		super();
		this.result = result;
		this.attendanceItem = attendanceItem;
		Message = message;
	}
	
	public AttendanceItemUpdateResponse(){
		
	}
	
	public boolean result = false;
	public AttendanceItem attendanceItem = null;
	public String Message;
	
	
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public AttendanceItem getAttendanceItem() {
		return attendanceItem;
	}
	public void setAttendanceItem(AttendanceItem attendanceItem) {
		this.attendanceItem = attendanceItem;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	
}
