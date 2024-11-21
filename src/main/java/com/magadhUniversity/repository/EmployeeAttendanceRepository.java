package com.magadhUniversity.repository;

import com.magadhUniversity.model.EmployeeAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Long> {
    List<EmployeeAttendance> findByEmployee_EmployeeId(Long employeeId);
}
