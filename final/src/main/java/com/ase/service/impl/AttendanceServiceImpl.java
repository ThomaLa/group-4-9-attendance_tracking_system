package com.ase.service.impl;

import com.ase.dao.AttendanceItemDao;
import com.ase.dao.AttendanceLogDao;
import com.ase.dao.impl.AttendanceItemDaoImpl;
import com.ase.dao.impl.AttendanceLogDaoImpl;
import com.ase.entity.AttendanceItem;
import com.ase.service.AttendanceService;

public class AttendanceServiceImpl implements AttendanceService {
    private AttendanceItemDao attendanceItemDao = new AttendanceItemDaoImpl();
    private AttendanceLogDao attendanceLogDao = new AttendanceLogDaoImpl();

    @Override
    public void saveAttendance(AttendanceItem attendanceItem) {
        attendanceItemDao.saveAttendanceItemToDB(attendanceItem);
    }

    @Override
    public AttendanceItem getAttendanceItem(long attendanceId) {
        return attendanceItemDao.getAttendanceItemFromDB(attendanceId);
    }
}
