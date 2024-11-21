package com.magadhUniversity.service;

import com.magadhUniversity.model.Student;
import com.magadhUniversity.model.StudentMarks;
import com.magadhUniversity.repository.StudentMarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentMarksServiceImpl implements StudentMarksService {

    @Autowired
    private StudentMarksRepository studentMarksRepository;

    @Autowired
    private StudentService studentService;

    @Override
    public StudentMarks createStudentMarks(StudentMarks studentMarks) {
        Student student = studentService.getStudentById(studentMarks.getStudentId());
        studentMarks.setStudent(student);

        // Calculate the best internal marks, total marks, percentage, and division
        studentMarks.calculateBestInternalMarks();
        studentMarks.calculateTotalMarksAndPercentage();

        return studentMarksRepository.save(studentMarks);
    }

    @Override
    public List<StudentMarks> getAllStudentMarks() {
        return studentMarksRepository.findAll();
    }

    @Override
    public StudentMarks getStudentMarksById(Long markId) {
        return studentMarksRepository.findById(markId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid mark Id: " + markId));
    }

    @Override
    public StudentMarks updateStudentMarks(Long markId, StudentMarks studentMarks) {
        StudentMarks existingMarks = getStudentMarksById(markId);
        Student student = studentService.getStudentById(studentMarks.getStudentId());
        existingMarks.setStudent(student);
        existingMarks.setStudentId(studentMarks.getStudentId());
        existingMarks.setSubjectId(studentMarks.getSubjectId());
        existingMarks.setSemester(studentMarks.getSemester());
        existingMarks.setInternal1(studentMarks.getInternal1());
        existingMarks.setInternal2(studentMarks.getInternal2());
        existingMarks.setInternal3(studentMarks.getInternal3());

        // Calculate the best internal marks, total marks, percentage, and division
        existingMarks.calculateBestInternalMarks();
        existingMarks.calculateTotalMarksAndPercentage();

        existingMarks.setBestInternalMarks(studentMarks.getBestInternalMarks());
        existingMarks.setFinalExamMarks(studentMarks.getFinalExamMarks());
        existingMarks.setTotalMarks(studentMarks.getTotalMarks());
        existingMarks.setPercentage(studentMarks.getPercentage());
        existingMarks.setDivision(studentMarks.getDivision());
        return studentMarksRepository.save(existingMarks);
    }

    @Override
    public void deleteStudentMarks(Long markId) {
        studentMarksRepository.deleteById(markId);
    }
}
