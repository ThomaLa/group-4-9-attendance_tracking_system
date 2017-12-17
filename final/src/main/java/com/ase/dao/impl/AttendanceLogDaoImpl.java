package com.ase.dao.impl;

import com.ase.dao.AttendanceLogDao;
import com.ase.entity.AttendanceLog;
import com.googlecode.objectify.ObjectifyService;

public class AttendanceLogDaoImpl implements AttendanceLogDao {

    @Override
    public void saveAttendanceLogToDB(AttendanceLog attendanceLog) {
        ObjectifyService.ofy().save().entity(attendanceLog).now();
    }

    @Override
    public void deleteAttendanceLogFromDB(long attendanceLogId) {
        ObjectifyService.ofy().cache(false).delete().type(AttendanceLog.class).id(attendanceLogId).now();
    }

    @Override
    public AttendanceLog getAttendanceLogFromDB(long attendanceLogId) {
        return ObjectifyService.ofy().cache(false).load().type(AttendanceLog.class).id(attendanceLogId).now();
    }
}
