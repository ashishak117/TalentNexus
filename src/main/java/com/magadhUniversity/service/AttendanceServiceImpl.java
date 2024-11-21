package com.magadhUniversity.service;

import com.magadhUniversity.model.Attendance;
import com.magadhUniversity.repository.AttendanceRepository;
import com.magadhUniversity.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public void recordAttendance(Attendance attendance) {
        attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> getAttendanceByStudentId(Long studentId) {
        return attendanceRepository.findByStudentStudentId(studentId);
    }

    @Override
    public List<Attendance> getAttendanceByLecturerId(Long lecturerId) {
        return attendanceRepository.findByLecturerEmployeeId(lecturerId);
    }
}
