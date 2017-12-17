package com.ase.entity;

import com.google.appengine.repackaged.org.joda.time.DateTime;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class AttendanceItem {
    @Id private long attendanceId;
    private Ref<Student> student;
    private boolean hasAttended;
    private DateTime timeOfAttendance;

    public AttendanceItem() {

    }


    public AttendanceItem(long attendanceId, Ref<Student> student, boolean hasAttended, DateTime timeOfAttendance) {
        this.attendanceId = attendanceId;
        this.student = student;
        this.hasAttended = hasAttended;
        this.timeOfAttendance = timeOfAttendance;
    }

    public long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Ref<Student> getStudent() {
        return student;
    }

    public void setStudent(Ref<Student> student) {
        this.student = student;
    }

    public boolean isHasAttended() {
        return hasAttended;
    }

    public void setHasAttended(boolean hasAttended) {
        this.hasAttended = hasAttended;
    }


    public DateTime getTimeOfAttendance() {
        return timeOfAttendance;
    }

    public void setTimeOfAttendance(DateTime timeOfAttendance) {
        this.timeOfAttendance = timeOfAttendance;
    }
}
