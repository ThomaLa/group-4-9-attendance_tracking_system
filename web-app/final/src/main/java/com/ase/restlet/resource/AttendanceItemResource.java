package com.ase.restlet.resource;

import org.restlet.representation.Variant;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

import com.ase.entity.AttendanceItem;
import com.ase.restlet.requestresponse.AttendanceItemUpdateResponse;

public interface AttendanceItemResource {
    @Get
    public AttendanceItem retrieve();

    @Put("txt|xml:xml")
    public AttendanceItemUpdateResponse update(Variant variant);


}
