package com.ase.restlet.resource.impl;

import java.io.IOException;

import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

import com.ase.entity.AttendanceItem;
import com.ase.restlet.resource.AttendanceItemResource;

/**
 * The server side implementation of the Restlet resource.
 */
public class AttendanceItemServerResource extends ServerResource implements AttendanceItemResource {


	@Override
	public AttendanceItem retrieve() {
		String id = getAttribute("id");
		return new AttendanceItem(Long.parseLong(id),null,true,null);
	}

	@Override
	public Representation update(Representation entity) {
		try {
			entity.getText();
			return entity;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}