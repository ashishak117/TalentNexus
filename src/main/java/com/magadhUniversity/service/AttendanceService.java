package com.magadhUniversity.service;

import com.magadhUniversity.model.Attendance;
import java.util.List;

public interface AttendanceService {
    void recordAttendance(Attendance attendance);
    List<Attendance> getAttendanceByStudentId(Long studentId);
    List<Attendance> getAttendanceByLecturerId(Long lecturerId);
}
