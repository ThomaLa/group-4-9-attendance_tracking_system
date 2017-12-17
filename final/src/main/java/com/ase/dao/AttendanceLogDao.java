package com.ase.dao;

import com.ase.entity.AttendanceLog;

public interface AttendanceLogDao {
    void saveAttendanceLogToDB(AttendanceLog attendanceLog);
    void deleteAttendanceLogFromDB(long attendanceLogId);
    AttendanceLog getAttendanceLogFromDB(long attendanceLogId);
}
