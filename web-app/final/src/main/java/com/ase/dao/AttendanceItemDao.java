package com.ase.dao;

import com.ase.entity.AttendanceItem;

public interface AttendanceItemDao {
    void saveAttendanceItemToDB(AttendanceItem attendanceItem);
    void deleteAttendanceItemFromDB(long attendanceId);
    AttendanceItem getAttendanceItemFromDB(long attendanceId);
}
