package com.ase.dao.impl;

import com.ase.dao.AttendanceItemDao;
import com.ase.entity.AttendanceItem;
import com.googlecode.objectify.ObjectifyService;

public class AttendanceItemDaoImpl implements AttendanceItemDao {

    @Override
    public void saveAttendanceItemToDB(AttendanceItem attendanceItem) {
        ObjectifyService.ofy().save().entity(attendanceItem).now();
    }

    @Override
    public void deleteAttendanceItemFromDB(long attendanceId) {
        ObjectifyService.ofy().cache(false).delete().type(AttendanceItem.class).id(attendanceId).now();
    }

    @Override
    public AttendanceItem getAttendanceItemFromDB(long attendanceId) {
        return ObjectifyService.ofy().load().type(AttendanceItem.class).id(attendanceId).now();
    }
}
