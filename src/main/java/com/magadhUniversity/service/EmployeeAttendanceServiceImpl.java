package com.magadhUniversity.service;

import com.magadhUniversity.model.EmployeeAttendance;
import com.magadhUniversity.repository.EmployeeAttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeAttendanceServiceImpl implements EmployeeAttendanceService {

    @Autowired
    private EmployeeAttendanceRepository employeeAttendanceRepository;

    @Override
    public EmployeeAttendance markAttendance(EmployeeAttendance attendance) {
        return employeeAttendanceRepository.save(attendance);
    }

    @Override
    public List<EmployeeAttendance> getAttendanceByEmployeeId(Long employeeId) {
        return employeeAttendanceRepository.findByEmployee_EmployeeId(employeeId);
    }

    @Override
    public List<EmployeeAttendance> getAllAttendanceRecords() {
        return employeeAttendanceRepository.findAll();
    }

    @Override
    public void deleteAttendance(Long attendanceId) {
        employeeAttendanceRepository.deleteById(attendanceId);
    }
}
