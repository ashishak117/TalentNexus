package com.magadhUniversity.controller;

import com.magadhUniversity.model.Student;
import com.magadhUniversity.model.StudentMarks;
import com.magadhUniversity.service.StudentMarksService;
import com.magadhUniversity.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
@RequestMapping("/student-marks")
public class StudentMarksController {

    private static final Logger logger = LoggerFactory.getLogger(StudentMarksController.class);

    @Autowired
    private StudentMarksService studentMarksService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        logger.info("Displaying the create student marks form");
        model.addAttribute("studentMarks", new StudentMarks());
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "create_student_marks";
    }

    @PostMapping("/create")
    public String createStudentMarks(@ModelAttribute("studentMarks") StudentMarks studentMarks) {
        Long studentId = studentMarks.getStudentId();
        logger.info("Creating student marks for student ID: {}", studentId);
        if (studentId == null) {
            logger.error("The given student ID must not be null");
            throw new IllegalArgumentException("The given student ID must not be null");
        }
        Student student = studentService.getStudentById(studentId);
        studentMarks.setStudent(student);

        // Calculate the best internal marks, total marks, percentage, and division
        studentMarks.calculateBestInternalMarks();
        studentMarks.calculateTotalMarksAndPercentage();

        studentMarksService.createStudentMarks(studentMarks);
        logger.info("Student marks created successfully for student ID: {}", studentId);
        return "redirect:/student-marks";
    }

    @GetMapping
    public String listStudentMarks(Model model) {
        logger.info("Listing all student marks");
        List<StudentMarks> studentMarksList = studentMarksService.getAllStudentMarks();
        model.addAttribute("studentMarksList", studentMarksList);
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "list_student_marks";
    }

    @GetMapping("/update/{markId}")
    public String showUpdateForm(@PathVariable("markId") Long markId, Model model) {
        logger.info("Displaying the update form for mark ID: {}", markId);
        StudentMarks studentMarks = studentMarksService.getStudentMarksById(markId);
        model.addAttribute("studentMarks", studentMarks);
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "update_student_marks";
    }

    @PostMapping("/update/{markId}")
    public String updateStudentMarks(@PathVariable("markId") Long markId, @ModelAttribute("studentMarks") StudentMarks studentMarks) {
        logger.info("Updating student marks for mark ID: {}", markId);
        Long studentId = studentMarks.getStudentId();
        logger.info("Student ID from studentMarks: {}", studentId);
        if (studentId == null) {
            logger.error("Student ID is null during update");
            throw new IllegalArgumentException("The given student ID must not be null");
        }

        Student student = studentService.getStudentById(studentId);
        studentMarks.setStudent(student);

        // Calculate the best internal marks, total marks, percentage, and division
        studentMarks.calculateBestInternalMarks();
        studentMarks.calculateTotalMarksAndPercentage();

        studentMarksService.updateStudentMarks(markId, studentMarks);
        logger.info("Student marks updated successfully for mark ID: {}", markId);
        return "redirect:/student-marks";
    }

    @PostMapping("/delete/{markId}")
    public String deleteStudentMarks(@PathVariable("markId") Long markId) {
        logger.info("Deleting student marks with ID: {}", markId);
        studentMarksService.deleteStudentMarks(markId);
        logger.info("Student marks deleted successfully for ID: {}", markId);
        return "redirect:/student-marks";
    }
}
