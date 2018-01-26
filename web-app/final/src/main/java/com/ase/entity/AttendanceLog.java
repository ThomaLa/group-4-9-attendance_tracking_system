package com.ase.entity;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.ArrayList;

@Entity
public class AttendanceLog {
    @Id private long attendanceLogId;
    private ArrayList<Ref<AttendanceItem>> attendanceItems = new ArrayList<Ref<AttendanceItem>> () ;
    private short weekId;

    public AttendanceLog() {

    }

    public AttendanceLog(long attendanceLogId, ArrayList<Ref<AttendanceItem>> attendanceItems, short weekId) {
        this.attendanceLogId = attendanceLogId;
        this.attendanceItems = attendanceItems;
        this.weekId = weekId;
    }

    public long getAttendanceLogId() {
        return attendanceLogId;
    }

    public void setAttendanceLogId(long attendanceLogId) {
        this.attendanceLogId = attendanceLogId;
    }

    public ArrayList<Ref<AttendanceItem>> getAttendanceItems() {
        return attendanceItems;
    }

    public void setAttendanceItems(ArrayList<Ref<AttendanceItem>> attendanceItems) {
        this.attendanceItems = attendanceItems;
    }
    
    public void addAttendanceItemRef(Ref<AttendanceItem> attendanceItem) {
        this.attendanceItems.add(attendanceItem);
    }

    public short getWeekId() {
        return weekId;
    }

    public void setWeekId(short weekId) {
        this.weekId = weekId;
    }
}
