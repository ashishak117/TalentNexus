package com.magadhUniversity.service;

import com.magadhUniversity.model.StudentMarks;
import java.util.List;

public interface StudentMarksService {
    StudentMarks createStudentMarks(StudentMarks studentMarks);
    List<StudentMarks> getAllStudentMarks();
    StudentMarks getStudentMarksById(Long markId);  // Add this method
    StudentMarks updateStudentMarks(Long markId, StudentMarks studentMarks);
    void deleteStudentMarks(Long markId);
}
