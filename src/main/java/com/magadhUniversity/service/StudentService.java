package com.magadhUniversity.service;

import com.magadhUniversity.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface StudentService {
    Student createStudent(Student student);
    List<Student> getAllStudents();
    void addStudent(Student student); // Add this method
    // Other CRUD methods
    Student getStudentById(Long studentId);
    void updateStudent(Long studentId, String name, String department, String year, String email);
    void deleteStudent(Long studentId);
    Page<Student> getStudentsByDepartment(String department, Pageable pageable);  // Use Pageable for pagination


}
