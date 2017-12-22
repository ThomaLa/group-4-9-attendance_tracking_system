package com.ase.service;

import com.ase.entity.AttendanceItem;
import com.ase.entity.AttendanceLog;

public interface AttendanceService {
    void saveAttendance(AttendanceItem attendanceItem);
    AttendanceItem getAttendanceItem(long attendanceId);
    AttendanceLog getAttendanceLog(long attandanceLongId);
}
