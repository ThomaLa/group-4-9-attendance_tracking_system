package com.ase.restlet.resource;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

import com.ase.entity.AttendanceItem;

public interface AttendanceItemResource {
    @Get
    public AttendanceItem retrieve();

    @Put("json")
    public Representation update(Representation entity);

}
