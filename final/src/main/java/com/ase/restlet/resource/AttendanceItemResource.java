package com.ase.restlet.resource;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

import com.ase.entity.AttendanceItem;
import com.ase.restlet.requestresponse.AttendanceItemUpdateResponse;

public interface AttendanceItemResource {
    @Get
    public AttendanceItem retrieve();

    @Put("xml:xml")
    public AttendanceItemUpdateResponse update(Representation entity);

}
