package com.ase.restlet.resource.impl;

import org.restlet.representation.Variant;
import org.restlet.resource.ServerResource;

import com.ase.entity.AttendanceItem;
import com.ase.restlet.requestresponse.AttendanceItemUpdateResponse;
import com.ase.restlet.resource.AttendanceItemResource;
import com.ase.service.AttendanceService;
import com.ase.service.impl.AttendanceServiceImpl;

/**
 * The server side implementation of the Restlet resource.
 */
public class AttendanceItemServerResource extends ServerResource implements AttendanceItemResource {


	AttendanceService attendanceService = new AttendanceServiceImpl();
	
	
	@Override
	public AttendanceItem retrieve() {
		String id = getAttribute("id");
		return attendanceService.getAttendanceItem(Long.parseLong(id));
	}

	@Override
	public AttendanceItemUpdateResponse update(Variant variant) {
		try {
//			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//			Document doc = dBuilder.parse(entity);
			String studentId = "asas";
			
			System.out.println(variant.toString());
			//entity.getElementsByTagName("studentId").item(0).getTextContent();
			Exception ex =  new Exception();
			throw new NullPointerException("Crazy Error");
			//return studentId;
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			return new AttendanceItemUpdateResponse(false, null,e.getMessage());
		}

	}
	
}