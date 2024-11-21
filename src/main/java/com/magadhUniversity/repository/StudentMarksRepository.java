package com.magadhUniversity.repository;

import com.magadhUniversity.model.StudentMarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMarksRepository extends JpaRepository<StudentMarks, Long> {
    List<StudentMarks> findByStudentId(Long studentId);
}
