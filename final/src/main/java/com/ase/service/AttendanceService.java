package com.ase.service;

import com.ase.entity.AttendanceItem;

public interface AttendanceService {
    void saveAttendance(AttendanceItem attendanceItem);
    AttendanceItem getAttendanceItem(long attendanceId);
}
