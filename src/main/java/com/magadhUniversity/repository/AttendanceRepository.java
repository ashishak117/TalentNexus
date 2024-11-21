package com.magadhUniversity.repository;

import com.magadhUniversity.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudentStudentId(Long studentId);  // Corrected method name
    List<Attendance> findByLecturerEmployeeId(Long lecturerId);
}
