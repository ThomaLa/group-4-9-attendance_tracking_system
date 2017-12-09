package com.ase.restlet.resource;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

import com.ase.entity.Student;

public interface StudentResource {
    @Get
    public Student retrieve();

    @Put
    public void store(Student student);

  //  @Delete
  //  public void remove(); not supproted as of now !
}
